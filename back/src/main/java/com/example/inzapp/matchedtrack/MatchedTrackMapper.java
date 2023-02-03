package com.example.inzapp.matchedtrack;

import com.example.inzapp.user.User;


public class MatchedTrackMapper {


    private MatchedTrackMapper() {

    }

    public static MatchedTrackDto toDTO(MatchedTrack BO) { // exposed over web service
        return new MatchedTrackDto(BO.getUserID().getId(), BO.getName(),BO.getArtistName(), BO.getPreview_url(),
                BO.getUri(), BO.getUrl(), BO.getStrongestEmotion(), BO.getPhoto());
    }

    public static MatchedTrack toBO(User user, MatchedTrackDto dto) { //business object

        return new MatchedTrack(user,dto.getName(),dto.getArtistName(), dto.getPreview_url(),
                dto.getUri(), dto.getUrl(), dto.getStrongestEmotion(), dto.getPhoto());
    }

}
