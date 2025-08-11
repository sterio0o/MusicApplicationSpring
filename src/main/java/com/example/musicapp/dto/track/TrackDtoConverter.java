package com.example.musicapp.dto.track;

import com.example.musicapp.model.Track;

public class TrackDtoConverter {
    public static TrackResponseDto toTrackResponseDto(Track track) {
        return new TrackResponseDto(
                track.getTrackId(),
                track.getTitle(),
                AlbumForTrackDto.fromEntity(track.getAlbum()),
                ArtistForTrackDto.fromEntity(track.getArtist())
        );
    }
}
