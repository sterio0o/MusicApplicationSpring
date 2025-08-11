package com.example.musicapp.dto.track;

import com.example.musicapp.model.Album;
import com.example.musicapp.model.Track;

public record TrackResponseDto(
        Integer id,
        String title,
        AlbumForTrackDto album,
        ArtistForTrackDto artist
) {
    public static TrackResponseDto fromEntity(Track track) {
        return  TrackDtoConverter.toTrackResponseDto(track);
    }
}
