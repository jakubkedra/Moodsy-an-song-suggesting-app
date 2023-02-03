package com.example.inzapp.emotion;


import com.example.inzapp.reader.PropertiesReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class EmotionService {

    private static final String FACE_URL = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceAttributes=emotion,gender&detectionModel=detection_01";
   // @Value("${apiKey}")
    private final String apiKey = PropertiesReader.getProperty("apiKey");;
    private final RestTemplate restTemplate = new RestTemplate();


    public StrongestEmotion getEmotionsOfPhoto(byte[] s) throws IOException{



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Ocp-Apim-Subscription-Key", apiKey);
        HttpEntity< byte [] > req = new HttpEntity<>(s, headers);
        EmotionReponse[] faceattributes = restTemplate.postForObject(FACE_URL,req, EmotionReponse[].class);

        Map<String, Double> emotionMap = new HashMap<>();

        emotionMap.put(Emotion.Fields.happiness,faceattributes[0].getFaceAttributes().getEmotion().getHappiness());
        emotionMap.put(Emotion.Fields.anger,faceattributes[0].getFaceAttributes().getEmotion().getAnger());
        emotionMap.put(Emotion.Fields.sadness,faceattributes[0].getFaceAttributes().getEmotion().getSadness());
        emotionMap.put(Emotion.Fields.surprise,faceattributes[0].getFaceAttributes().getEmotion().getSurprise());
        emotionMap.put(Emotion.Fields.neutral,faceattributes[0].getFaceAttributes().getEmotion().getNeutral());
        emotionMap.put(Emotion.Fields.fear,faceattributes[0].getFaceAttributes().getEmotion().getFear());
        emotionMap.put(Emotion.Fields.contempt,faceattributes[0].getFaceAttributes().getEmotion().getContempt());
        emotionMap.put(Emotion.Fields.disgust,faceattributes[0].getFaceAttributes().getEmotion().getDisgust());


        StrongestEmotion strongestEmotion =  maxOfMap(emotionMap); // emocja z najwiekszym wspolczynnkiem
        EmotionalMusicModel em = EmotionalMusicModel.valueOf(strongestEmotion.getName()); // pobranie wartosci valence i arousal dla danej emocji

        strongestEmotion.setValues(em.getArousalValueMin(),em.getArousalValueMax(),em.getValenceValueMin(),em.getValenceValueMax());


       return  strongestEmotion;



    }


    // zwroc emocje z najwiekszym wspolczynnkiem
    public <String, Double extends Comparable<Double>> StrongestEmotion maxOfMap(Map<String, Double> map) {
        Map.Entry<String, Double> maxEntry = Collections.max(map.entrySet(), Map.Entry.comparingByValue());
        return new StrongestEmotion((java.lang.String) maxEntry.getKey(), (java.lang.Double) maxEntry.getValue());
    }






}
