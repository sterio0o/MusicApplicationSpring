package com.example.musicapp.dto.album;

public record AlbumRequestDto(
        String title,
        Integer releaseYear,
        String genre,
        Integer artist
) {

}
