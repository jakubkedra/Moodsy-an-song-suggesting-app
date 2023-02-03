package com.example.inzapp.emotion;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StrongestEmotion {

    private String name;
    private double value;
    private double arousal;
    private double valence;

    public StrongestEmotion(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public void setValues(int aMin, int aMax, int vMin, int vMax){

        this.arousal =  (((aMax + aMin) / 2.0) * this.value) / 1000;
        this.valence =  (((vMax + vMin) / 2.0) * this.value) / 1000;
    }

}
