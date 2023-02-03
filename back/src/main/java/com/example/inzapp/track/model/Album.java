package com.example.inzapp.track.model;


import com.example.inzapp.track.model.Artist;
import com.example.inzapp.track.model.Picture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {



   private List<Picture> images;
   private List<Artist> artists;



}
