package com.example.inzapp.matchedtrack;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchedTrackDto {

    private Long userID;
    private String name;
    private String artistName;
    private String preview_url;
    private String uri;
    private String url;
    private String strongestEmotion;
    private byte[] photo;


}
