package com.example.musicapp.controller;

import com.example.musicapp.dto.artist.ArtistResponseDto;
import com.example.musicapp.dto.track.TrackRequestDto;
import com.example.musicapp.dto.track.TrackResponseDto;
import com.example.musicapp.service.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @Operation(
            summary = "Получить трек по названию title",
            description = "Возвращает трек с названием, альбомом и артистом"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Трек найден"),
            @ApiResponse(responseCode = "404", description = "Трек не найден")
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<TrackResponseDto> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(trackService.findByTitle(title));
    }

    @Operation(
            summary = "Создать трек",
            description = "Создает трек с названием, альбомом и артистом"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Трек создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос или формат данных")
    })
    @PostMapping("/create")
    public ResponseEntity<TrackResponseDto> createTrack(@RequestBody TrackRequestDto dto) {
        TrackResponseDto newTrack = trackService.createTrack(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{title}")
                .buildAndExpand(newTrack.title())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Создать несколько треков",
            description = "Создает несколько треков с переданными данными"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Трек создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос или формат данных"),
    })
    @PostMapping("/butch")
    public ResponseEntity<List<TrackResponseDto>> createTracks(
            @RequestBody List<TrackRequestDto> dto) {
        List<TrackResponseDto> tracks = dto.stream()
                .map(trackService::createTrack)
                .toList();
        return ResponseEntity.ok(tracks);
    }

    @Operation(
            summary = "Изменить трек по названию title",
            description = "Изменяет трек на переданные в него данные"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Трек изменен"),
            @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
            @ApiResponse(responseCode = "404", description = "Трек не найден")
    })
    @PutMapping("/title/{title}")
    public ResponseEntity<TrackResponseDto> updateTrack(
            @PathVariable String title,
            @RequestBody TrackRequestDto dto) {
        TrackResponseDto updateTrack = trackService.updateTrack(title, dto);
        return ResponseEntity.ok(updateTrack);
    }

    @Operation(
            summary = "Удалить трек по идентификатору id",
            description = "Удаляет трек по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Трек удален"),
            @ApiResponse(responseCode = "404", description = "Трек не найден")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteTrack(@PathVariable Integer id) {
        trackService.deleteTrack(id);
    }
}
