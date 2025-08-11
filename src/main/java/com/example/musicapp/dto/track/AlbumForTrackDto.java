package com.example.musicapp.dto.track;

import com.example.musicapp.model.Album;

public record AlbumForTrackDto(
        Integer id,
        String title,
        String genre,
        Integer releaseYear
) {
    public static AlbumForTrackDto fromEntity(Album album) {
        return new AlbumForTrackDto(
                album.getAlbumId(),
                album.getTitle(),
                album.getGenre(),
                album.getReleaseYear()
        );
    }
}
