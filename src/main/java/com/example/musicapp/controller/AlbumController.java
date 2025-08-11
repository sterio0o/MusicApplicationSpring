package com.example.musicapp.controller;


import com.example.musicapp.dto.album.AlbumRequestDto;
import com.example.musicapp.dto.album.AlbumResponseDto;
import com.example.musicapp.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Operation(
            summary = "Получить альбом по названию title",
            description = "Возвращает альбом с треками и артистом"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Альбом найден"),
            @ApiResponse(responseCode = "404", description = "Альбом не найден")
    })
    @GetMapping("/title/{title}") //Возможно стоит поменять все пути и написать документацию к API
    public ResponseEntity<AlbumResponseDto> getAlbumByTitle(@PathVariable String title) {
        return ResponseEntity.ok(albumService.findAlbumByTitle(title));
    }

    @Operation(
            summary = "Получить альбом по идентификатору id",
            description = "Возвращает альбом с треками и артистом"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Альбом найден"),
            @ApiResponse(responseCode = "404", description = "Альбом не найден")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<AlbumResponseDto> getAlbumById(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.findAlbumById(id));
    }

    //GET по жанру

    @Operation(
            summary = "Создание альбома",
            description = "Создает альбом с названием, годом выпуска и артистом"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Альбом создан"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос или формат данных")
    })
    @PostMapping("/create")
    public ResponseEntity<AlbumResponseDto> createAlbum(@RequestBody AlbumRequestDto dto) {
        AlbumResponseDto album = albumService.createAlbum(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("title")
                .buildAndExpand(album.title())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Изменить альбом по идентификатору id",
            description = "Изменяет альбом на переданные в него данные"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Альбом изменен"),
            @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
            @ApiResponse(responseCode = "404", description = "Альбом не найден")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<AlbumResponseDto> updateAlbum(
            @PathVariable Integer id,
            @RequestBody AlbumRequestDto dto
    ) {
        AlbumResponseDto updateAlbum = albumService.updateAlbum(id, dto);
        return ResponseEntity.ok(updateAlbum);
    }

    @Operation(
            summary = "Удалить альбом по идентификатору id",
            description = "Удаляет альбом по id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Альбом удален"),
            @ApiResponse(responseCode = "404", description = "Альбом не найден")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
}
