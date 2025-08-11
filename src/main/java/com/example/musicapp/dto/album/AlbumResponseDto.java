package com.example.musicapp.dto.album;

import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;

import java.util.List;

public record AlbumResponseDto(
    Integer id,
    String title,
    Integer releaseYear,
    String genre,
    ArtistForAlbumDto artist,
    List<TrackForAlbumDto> track
) {
    public static AlbumResponseDto fromEntity(Album album) {
        return AlbumDtoConverter.toAlbumResponseDto(album);
    }
}
