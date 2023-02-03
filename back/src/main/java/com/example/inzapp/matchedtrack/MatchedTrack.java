package com.example.inzapp.matchedtrack;

import com.example.inzapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MatchedTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User userID;

    private String name;
    private String artistName;
    private String preview_url;
    private String uri;
    private String url;
    private String strongestEmotion;

    @Lob
    @Column(name = "photo", columnDefinition="LONGBLOB")
    private byte[] photo;


    public MatchedTrack(User userID, String name, String artistName, String preview_url, String uri, String url, String strongestEmotion, byte[] photo) {

        this.userID = userID;
        this.name = name;
        this.artistName = artistName;
        this.preview_url = preview_url;
        this.uri = uri;
        this.url = url;
        this.strongestEmotion = strongestEmotion;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchedTrack that = (MatchedTrack) o;
        return Id.equals(that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
