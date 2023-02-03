package com.example.inzapp.matchedtrack;


import com.example.inzapp.emotion.EmotionService;
import com.example.inzapp.emotion.StrongestEmotion;
import com.example.inzapp.exceptions.MatchedTrackNotFoundException;
import com.example.inzapp.track.TrackDto;
import com.example.inzapp.track.TrackService;
import com.example.inzapp.user.User;
import com.example.inzapp.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchedTrackService {

    private final MatchedTrackRepository repository;
    private final EmotionService emotionService;
    private final TrackService trackService;
    private final UserService userService;


    public MatchedTrackService(MatchedTrackRepository repository, EmotionService emotionService, TrackService trackService, UserService userService) {
        this.repository = repository;
        this.emotionService = emotionService;
        this.trackService = trackService;
        this.userService = userService;
    }

    public MatchedTrackDto saveMatchingTrack(Authentication authentication, String genres, byte[] photo) throws IOException {

        StrongestEmotion strongestEmotion = emotionService.getEmotionsOfPhoto(photo);

        TrackDto t1 = trackService.getTracks(genres, strongestEmotion.getArousal(), strongestEmotion.getValence());


        User user = userService.getUser(authentication.getName());

        MatchedTrackDto matchedTrack = MatchedTrackDto.builder()
                .userID(user.getId())
                .name(t1.getName())
                .artistName(t1.getArtistName())
                .photo(photo)
                .preview_url(t1.getPreview_url())
                .strongestEmotion(strongestEmotion.getName())
                .uri(t1.getUri())
                .url(t1.getUrl())
                .build();

        repository.save(MatchedTrackMapper.toBO(user,matchedTrack));

        return matchedTrack;
    }

    public void deleteMatchingTrack(String email,Long id) {

        Optional<MatchedTrack> trackToDelete = repository.findById(id);

        User user = userService.getUser(email);


        if(trackToDelete.get().getUserID() != user ){ // jezeli zapisany utwor nie nalezy do tego uzytkownika to wyrzuc wyjatek

            throw new MatchedTrackNotFoundException();
        }

        repository.deleteById(id);

    }

    public List<MatchedTrackDto> getMatchedTracks(Principal principal){

        User user = userService.getUser(principal.getName());


        List<MatchedTrack> list =  repository.getMatchedTracks(user);
        List<MatchedTrackDto> listDtos = new ArrayList<>();
        for(MatchedTrack m : list){
            listDtos.add(MatchedTrackMapper.toDTO(m));
        }

        return listDtos;
    }

}
