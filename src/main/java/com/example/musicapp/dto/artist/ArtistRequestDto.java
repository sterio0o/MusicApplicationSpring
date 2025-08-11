package com.example.musicapp.dto.artist;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

//Получает данные от клиента
//Используется для валидации и десериализации входящих данных
//Содержит только те поля, которые может передать клиент
public record ArtistRequestDto(
        String name,
        String country,
        LocalDate birthDate
) {

}
