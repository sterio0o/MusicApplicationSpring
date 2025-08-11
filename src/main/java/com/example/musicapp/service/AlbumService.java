package com.example.musicapp.service;

import com.example.musicapp.dto.album.AlbumRequestDto;
import com.example.musicapp.dto.album.AlbumResponseDto;
import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;
import com.example.musicapp.repository.AlbumRepository;
import com.example.musicapp.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public AlbumResponseDto convertToDto(Album album) {
        if (album == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        return AlbumResponseDto.fromEntity(album);
    }

    public AlbumResponseDto findAlbumByTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        else return convertToDto(albumRepository.findAlbumByTitle(title));
    }

    public AlbumResponseDto findAlbumById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        return convertToDto(albumRepository.findByAlbumId(id));
    }

    @Transactional
    public AlbumResponseDto createAlbum(AlbumRequestDto dto) {
        Album newAlbum = new Album();
        Artist artist = artistRepository.findArtistByArtistId(dto.artist());
        if (dto.title() != null) {
            newAlbum.setTitle(dto.title());
        }
        if (dto.artist() != null) {
            newAlbum.setArtist(artist);
        }
        if (dto.releaseYear() != null) {
            newAlbum.setReleaseYear(dto.releaseYear());
        }
        if (dto.genre() != null) {
            newAlbum.setGenre(dto.genre());
        }
        albumRepository.save(newAlbum);
        return convertToDto(newAlbum);
    }

    @Transactional
    public AlbumResponseDto updateAlbum(Integer id, AlbumRequestDto updates) {
        Album album = albumRepository.findByAlbumId(id);
        Artist artist = artistRepository.findArtistByArtistId(updates.artist());

        if (updates.title() != null) {
            album.setTitle(updates.title());
        }
        if (updates.genre() != null) {
            album.setGenre(updates.genre());
        }
        if (updates.releaseYear() != null) {
            album.setReleaseYear(updates.releaseYear());
        }
        if (updates.artist() != null) {
            album.setArtist(artist);
        }
        Album updateAlbum = albumRepository.save(album);
        return convertToDto(updateAlbum);
    }

    @Transactional
    public void deleteAlbum(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        albumRepository.deleteById(id);
    }

    //Сделать так чтобы в БД были только уникальные испольнители
}