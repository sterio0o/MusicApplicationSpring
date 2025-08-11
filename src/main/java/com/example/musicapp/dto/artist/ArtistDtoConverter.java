package com.example.musicapp.dto.artist;

import com.example.musicapp.model.Artist;

public class ArtistDtoConverter {
    public static ArtistResponseDto toArtistResponseDto(Artist artist) {
        return new ArtistResponseDto(
                artist.getArtistId(),
                artist.getName(),
                artist.getCountry(),
                artist.getBirthDate()
        );
    }
}
