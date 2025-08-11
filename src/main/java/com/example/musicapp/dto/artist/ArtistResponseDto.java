package com.example.musicapp.dto.artist;

import com.example.musicapp.model.Artist;

import java.time.LocalDate;

//Возвращает данные клиенту
//Содержит то, что допускается видеть клиенту
public record ArtistResponseDto(
        Integer id,
        String name,
        String country,
        LocalDate birthDate
) {
    public static ArtistResponseDto fromEntity(Artist artist) {
        return ArtistDtoConverter.toArtistResponseDto(artist);
    }
}