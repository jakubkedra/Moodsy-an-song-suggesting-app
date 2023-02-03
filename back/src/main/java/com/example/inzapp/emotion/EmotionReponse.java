package com.example.inzapp.emotion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmotionReponse {


    private String faceId;
    private EmotionList FaceAttributes;

}
