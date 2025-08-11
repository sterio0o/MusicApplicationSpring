package com.example.musicapp.repository;

import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;
import com.example.musicapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findByAlbumTitle(String album);

    Track findByTitle(String title);

    void deleteByTrackId(Integer id);
}
