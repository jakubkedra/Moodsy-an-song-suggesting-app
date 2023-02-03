package com.example.inzapp.matchedtrack;


import com.example.inzapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchedTrackRepository extends JpaRepository<MatchedTrack, Long> {


    @Query("SELECT m FROM MatchedTrack m WHERE m.userID = ?1")
    List<MatchedTrack> getMatchedTracks(User user);

}
