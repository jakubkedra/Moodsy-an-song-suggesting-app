package com.example.inzapp.emotion;

public enum EmotionalMusicModel {

    happiness(350,600,700,1000),
    anger(750,1000,1,150),
    surprise(900,1000,500,600),
    disgust(350,500,100,300),
    fear(900,1000,50,200),
    sadness(200,350,200,400),
    neutral(1,100,400,500),
    contempt(450,500,1,100);


    private final int arousalValueMin;
    private final int arousalValueMax;
    private final int valenceValueMin;
    private final int valenceValueMax;

    EmotionalMusicModel(int arousalValueMin, int arousalValueMax, int valenceValueMin, int valenceValueMax) {
        this.arousalValueMin = arousalValueMin;
        this.arousalValueMax = arousalValueMax;
        this.valenceValueMin = valenceValueMin;
        this.valenceValueMax = valenceValueMax;
    }

    public int getArousalValueMin() {
        return arousalValueMin;
    }

    public int getArousalValueMax() {
        return arousalValueMax;
    }

    public int getValenceValueMin() {
        return valenceValueMin;
    }

    public int getValenceValueMax() {
        return valenceValueMax;
    }



}
