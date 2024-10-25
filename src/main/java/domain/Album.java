package domain;

import java.util.ArrayList;
import java.util.List;

public class Album {
  private Long id;
  private String title;
  private Artist artist;
  private List<Track> tracks = new ArrayList<>();

  public Album() {
    // 기본 생성자
  }


  public Album(Long id, String title, Artist artist) {
    this.id = id;
    this.title = title;
    this.artist = artist;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public void addTrack(Track track) {
    tracks.add(track);
  }

  public List<Track> getTracks() {
    return tracks;
  }
  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }
}
