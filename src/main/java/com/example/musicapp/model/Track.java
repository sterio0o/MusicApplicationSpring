package com.example.musicapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id", foreignKey = @ForeignKey(
            foreignKeyDefinition = "FOREIGN KEY (album_id) REFERENCES album(album_id)" +
                    " ON DELETE CASCADE ON UPDATE CASCADE"
    ))
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(
            foreignKeyDefinition = "FOREIGN KEY (artist_id) REFERENCES artist(artist_id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE"
    ))
    private Artist artist;

    public Track() {}

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
