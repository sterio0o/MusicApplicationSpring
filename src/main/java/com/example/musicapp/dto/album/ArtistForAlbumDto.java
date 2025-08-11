package com.example.musicapp.dto.album;

import com.example.musicapp.model.Artist;

public record ArtistForAlbumDto(
        Integer id,
        String name
) {
    public static ArtistForAlbumDto fromEntity(Artist artist) {
        return new ArtistForAlbumDto(
                artist.getArtistId(),
                artist.getName()
        );
    }
}
