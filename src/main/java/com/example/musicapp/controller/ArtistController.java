package com.example.musicapp.controller;

import com.example.musicapp.dto.artist.ArtistRequestDto;
import com.example.musicapp.dto.artist.ArtistResponseDto;
import com.example.musicapp.model.Artist;
import com.example.musicapp.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Operation(
            summary = "Получить артиста по имени name",
            description = "Находит артиста и возвращает данные о нем"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Артист найден"),
            @ApiResponse(responseCode = "404", description = "Артист не найден")
    })
    @GetMapping("/{name}")
    public ResponseEntity<ArtistResponseDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok(artistService.convertToDto(artistService.findByName(name)));
    }

    @Operation(
            summary = "Получить артиста по стране country",
            description = "Находит всех артистов из указанной страны и возвращает данные о них"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Артист найден"),
            @ApiResponse(responseCode = "404", description = "Артист не найден")
    })
    @GetMapping("/country/{country}")
    public ResponseEntity<List<ArtistResponseDto>> getByCountry(@PathVariable String country) {
        return ResponseEntity.ok(artistService.findArtistByCountry(country));
    }

    @Operation(
            summary = "Создать ариста",
            description = "Создает артиста с переданными данными"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Артист создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос или формат данных"),
    })
    @PostMapping("/create")
    public ResponseEntity<ArtistRequestDto> createArtist(@RequestBody ArtistRequestDto dto) {
        Artist newArtist = artistService.createArtist(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{name}")
                .buildAndExpand(newArtist.getName())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Создать несколько артистов",
            description = "Создает несколько артистов с переданными данными"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Артист создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос или формат данных"),
    })
    @PostMapping("/butch")
    public ResponseEntity<List<Artist>> createArtists(
            @RequestBody List<ArtistRequestDto> dto) {
        List<Artist> artists = dto.stream()
                .map(artistService::createArtist)
                .toList();
        return ResponseEntity.ok(artists);
    }

    @Operation(
            summary = "Изменить артиста по идентификатору id",
            description = "Изменяет артиста на переданные в него данные"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Артист изменен"),
            @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
            @ApiResponse(responseCode = "404", description = "Артист не найден")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ArtistResponseDto> updateArtist(
            @PathVariable Integer id,
            @RequestBody ArtistRequestDto dto
            ) {
        ArtistResponseDto updateArtist = artistService.updateArtist(id, dto);
        return ResponseEntity.ok(updateArtist);
    }

    @Operation(
            summary = "Удалить артиста по идентификатору id",
            description = "Удаляет артиста по идентификатору id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Альбом удален"),
            @ApiResponse(responseCode = "404", description = "Альбом не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
