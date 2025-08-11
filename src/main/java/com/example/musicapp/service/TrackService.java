package com.example.musicapp.service;

import com.example.musicapp.dto.track.ArtistForTrackDto;
import com.example.musicapp.dto.track.TrackRequestDto;
import com.example.musicapp.dto.track.TrackResponseDto;
import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;
import com.example.musicapp.model.Track;
import com.example.musicapp.repository.AlbumRepository;
import com.example.musicapp.repository.ArtistRepository;
import com.example.musicapp.repository.TrackRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public TrackService(TrackRepository trackRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    public TrackResponseDto convertToDto(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        else {
            return TrackResponseDto.fromEntity(track);
        }
    }

    public TrackResponseDto findByTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }

        return convertToDto(trackRepository.findByTitle(title));
    }

    public List<Track> findByAlbumTitle(String album) {
        if (album == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }

        return trackRepository.findByAlbumTitle(album);
    }

    @Transactional
    public TrackResponseDto createTrack(TrackRequestDto dto) {
        Track newTrack = new Track();
        Artist artist = artistRepository.findArtistByArtistId(dto.artist());
        Album album = albumRepository.findByAlbumId(dto.album());

        newTrack.setTitle(dto.title());
        newTrack.setAlbum(album);
        newTrack.setArtist(artist);
        trackRepository.save(newTrack);
        return convertToDto(newTrack);
    }

    @Transactional
    public TrackResponseDto updateTrack(String title, TrackRequestDto updates) {
        if (!title.isEmpty()) {
            Track track = trackRepository.findByTitle(title);
            Artist artist = artistRepository.findArtistByArtistId(updates.artist());
            Album album = albumRepository.findByAlbumId(updates.album());

            if (updates.title() != null) {
                track.setTitle(updates.title());
            }
            if (updates.album() != null) {
                track.setAlbum(album);
            }
            if (updates.artist() != null) {
                track.setArtist(artist);
            }
            Track updateTrack = trackRepository.save(track);
            return convertToDto(updateTrack);
        }
        else {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
    }

    @Transactional
    public void deleteTrack(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Некоректно введеные данные");
        }
        else trackRepository.deleteByTrackId(id);
    }
}
