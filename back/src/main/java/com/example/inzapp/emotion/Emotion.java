package com.example.inzapp.emotion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;


@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emotion {


    private double anger;
    private double contempt;
    private double disgust;
    private double fear;
    private double happiness;
    private double neutral;
    private double sadness;
    private double surprise;


}
