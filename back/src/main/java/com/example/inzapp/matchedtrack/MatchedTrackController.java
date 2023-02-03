package com.example.inzapp.matchedtrack;


import com.example.inzapp.track.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchedTrackController {

    private final MatchedTrackService service;

    private final TrackService trackService;

    public MatchedTrackController(MatchedTrackService service, TrackService trackService) {
        this.service = service;
        this.trackService = trackService;
    }



    @PostMapping("/saveTrack")
    ResponseEntity<MatchedTrackDto> saveMatchingTrack(Authentication authentication, @RequestParam("image") MultipartFile file, @RequestParam String genres) throws IOException {

        return ResponseEntity.ok(service.saveMatchingTrack(authentication, genres,file.getBytes()));
    }

    @DeleteMapping("/deleteTrack/{id}")
    public ResponseEntity<?> deleteTrackById(Principal principal, @PathVariable Long id){

        String email = principal.getName();
        service.deleteMatchingTrack(email,id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }


    @GetMapping("/getMatchedTracks")
    public List<MatchedTrackDto> getMatchedTracks(Principal principal){
        return service.getMatchedTracks(principal);
    }



}
