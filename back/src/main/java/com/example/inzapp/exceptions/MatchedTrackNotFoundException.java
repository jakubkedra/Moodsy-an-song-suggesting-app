package com.example.inzapp.exceptions;

public class MatchedTrackNotFoundException extends RuntimeException {
    public static final String MatchedTrack_NOT_FOUND_EXC = "MatchedTrack of given ID does not exist in user's history";

    public MatchedTrackNotFoundException() {
        super(MatchedTrack_NOT_FOUND_EXC);
    }
}