package com.example.inzapp;

import com.example.inzapp.emotion.EmotionalMusicModel;
import com.example.inzapp.emotion.StrongestEmotion;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmotionValuesTests {


    @Test
    //@RepeatedTest(10)
    void ArousalValueTest(){

        //given
        StrongestEmotion strongestEmotion =  new StrongestEmotion("anger", 1.0);

        //when
        EmotionalMusicModel em = EmotionalMusicModel.valueOf(strongestEmotion.getName());
        strongestEmotion.setValues(em.getArousalValueMin(),em.getArousalValueMax(),em.getValenceValueMin(),em.getValenceValueMax());

        //then
        assertEquals(strongestEmotion.getArousal(),0.875);

    }

    @Test
    //@RepeatedTest(10)
    void ValenceValueTest(){

        //given
        StrongestEmotion strongestEmotion =  new StrongestEmotion("sadness", 0.9);

        //when
        EmotionalMusicModel em = EmotionalMusicModel.valueOf(strongestEmotion.getName());
        strongestEmotion.setValues(em.getArousalValueMin(),em.getArousalValueMax(),em.getValenceValueMin(),em.getValenceValueMax());

        //then
        assertEquals(strongestEmotion.getValence(),0.270);

    }
    @Test
    void SadnessValuesTest(){

        //given
        StrongestEmotion strongestEmotion =  new StrongestEmotion("sadness", 0.9);

        //when
        EmotionalMusicModel em = EmotionalMusicModel.valueOf(strongestEmotion.getName());

        //then

        assertEquals(em.getArousalValueMax(),350);
        assertEquals(em.getArousalValueMin(),200);
        assertEquals(em.getValenceValueMax(),400);
        assertEquals(em.getValenceValueMin(),200);


    }




}
