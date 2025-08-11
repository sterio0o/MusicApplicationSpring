package com.example.musicapp.dto.album;

import com.example.musicapp.model.Album;

public class AlbumDtoConverter {
    public static AlbumResponseDto toAlbumResponseDto(Album album) {
        if (album == null) return null;

        return new AlbumResponseDto(
                album.getAlbumId(),
                album.getTitle(),
                album.getReleaseYear(),
                album.getGenre(),
                ArtistForAlbumDto.fromEntity(album.getArtist()),
                album.getTracks().stream()
                        .map(TrackForAlbumDto::fromEntity)
                        .toList()
        );
    }
}
