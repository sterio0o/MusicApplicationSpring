package com.example.musicapp.dto.track;

import com.example.musicapp.model.Album;
import com.example.musicapp.model.Artist;

public record TrackRequestDto(
        String title,
        Integer album,
        Integer artist
) {

}
