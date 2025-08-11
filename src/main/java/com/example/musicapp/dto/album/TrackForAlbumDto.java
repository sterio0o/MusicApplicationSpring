package com.example.musicapp.dto.album;

import com.example.musicapp.model.Track;

public record TrackForAlbumDto(
        Integer id,
        String title
) {
    public static TrackForAlbumDto fromEntity(Track track) {
        return new TrackForAlbumDto(
                track.getTrackId(),
                track.getTitle()
        );
    }
}
