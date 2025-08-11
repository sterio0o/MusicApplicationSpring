package com.example.musicapp.dto.track;

import com.example.musicapp.model.Artist;

public record ArtistForTrackDto(
        Integer id,
        String name
) {
    public static ArtistForTrackDto fromEntity(Artist artist) {
        return new ArtistForTrackDto(
                artist.getArtistId(),
                artist.getName()
        );
    }
}
