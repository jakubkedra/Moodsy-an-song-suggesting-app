package com.example.inzapp.track;


import com.example.inzapp.track.model.Album;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {


    private String name;
    private Album album;
    private String preview_url;
    private String uri;




}
