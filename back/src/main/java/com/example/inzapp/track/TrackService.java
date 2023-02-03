package com.example.inzapp.track;


import com.example.inzapp.reader.PropertiesReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class TrackService {

    private final RestTemplate restTemplate = new RestTemplate();


   // @Value("${client-id}")
    private final String client_id = PropertiesReader.getProperty("client-id");;
   // @Value("${client-secret}")
    private final String client_secret = PropertiesReader.getProperty("client-secret");;



    public String getAccessToken(){ //dynamiczna genereacja access tokenu

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id",client_id);
        map.add("client_secret",client_secret);
        map.add("grant_type","client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Token> response =
                restTemplate.exchange("https://accounts.spotify.com/api/token",
                        HttpMethod.POST,
                        entity,
                        Token.class);

        return response.getBody().getAccess_token();
    }



    public TrackDto getTracks(String genres,double arousal, double valence) {

        String accessToken = getAccessToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer "+accessToken);

        HttpEntity<Tracks> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Tracks> exchange = restTemplate.exchange("https://api.spotify.com/v1/recommendations?limit=100&seed_genres={genres}&target_energy={arousal}&target_valence={valence}",
                HttpMethod.GET,
                httpEntity,
                Tracks.class,genres,
        arousal,valence);

        TrackDto trackDto = new TrackDto();
        List<TrackDto> tracks = new ArrayList<>();

        for(Track t : exchange.getBody().getTracks()){
            if(t.getPreview_url() != null) { // jezeli utwor nie ma preview url to go nie dodawaj
                tracks.add(TrackDto.builder().name(t.getName())
                        .uri(t.getUri())
                        .preview_url(t.getPreview_url())
                        .url(t.getAlbum().getImages().get(0).getUrl())
                        .artistName(t.getAlbum().getArtists().get(0).getName())
                        .build());
            }
        }
        Random rand = new Random();
        TrackDto t1 =  tracks.get(rand.nextInt(tracks.size())); // wylosuj utwor z listy utworow

    return t1;

    }








}
