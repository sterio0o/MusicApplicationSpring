package com.example.musicapp.repository;

import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findAlbumByTitle(String title);

    Album findByAlbumId(Integer id);

    List<Album> findByGenre(String genre);

    void deleteById(Integer id);
}
