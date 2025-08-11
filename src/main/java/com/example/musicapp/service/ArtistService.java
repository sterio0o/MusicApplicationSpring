package com.example.musicapp.service;

import com.example.musicapp.dto.artist.ArtistRequestDto;
import com.example.musicapp.dto.artist.ArtistResponseDto;
import com.example.musicapp.model.Artist;
import com.example.musicapp.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistResponseDto convertToDto(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        return ArtistResponseDto.fromEntity(artist);
    }

    public Artist findByName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }

        return artistRepository.findByName(name);
    }

    public List<ArtistResponseDto> findArtistByCountry(String country) {
        if (country.isEmpty()) {
            throw new IllegalArgumentException("Название страны не может быть пустым");
        }

        List<ArtistResponseDto> res = new ArrayList<>();
        for (Artist artist : artistRepository.findArtistByCountry(country)) {
            res.add(convertToDto(artist));
        }
        return res;
    }

    @Transactional
    public Artist createArtist(ArtistRequestDto dto) {
        Artist artist = new Artist();
        artist.setName(dto.name());
        artist.setCountry(dto.country());
        artist.setBirthDate(dto.birthDate());
        return artistRepository.save(artist);
    }

    @Transactional
    public ArtistResponseDto updateArtist(Integer id, ArtistRequestDto updates) {
        if (id == null) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        else {
            Artist artist = artistRepository.findArtistByArtistId(id);
            if (updates.name() != null) {
                artist.setName(updates.name());
            }
            if (updates.country() != null) {
                artist.setCountry(updates.country());
            }
            if (updates.birthDate() != null) {
                artist.setBirthDate(updates.birthDate());
            }
            Artist updateArtist = artistRepository.save(artist);
            return convertToDto(updateArtist);
        }
    }

    @Transactional
    public void deleteArtist(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        artistRepository.deleteById(id);
    }
}
