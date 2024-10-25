package mapper;

import domain.Album;
import domain.Artist;
import domain.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper extends Mapper<Album> {

  private final ArtistMapper artistMapper;

  public AlbumMapper(Connection connection, ArtistMapper artistMapper) {
    super(connection);
    this.artistMapper = artistMapper;
  }

  @Override
  protected String getTableName() {
    return "album";
  }

  @Override
  protected Album createDomainObject() {
    return new Album();
  }

  //데이터베이스 지정한 ID 행 찾기
  public Album find(long id) throws SQLException {
    String sql = findStatement(id);
    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
      statement.setLong(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          Album album = createDomainObject();
          load(album, resultSet); // 앨범 및 트랙 로드
          return album;
        }
      }
    }
    return null; // 앨범을 찾을 수 없을 경우
  }

  protected String findStatement(long id) throws SQLException {
    return "SELECT a.id, a.title, a.artist_id, t.title as trackTitle " +
        "FROM album a, track t " +
        "WHERE a.id = ? AND t.album_id = a.id";
  }


  @Override
  public void load(Album obj, ResultSet resultSet) throws SQLException {
    // ResultSet에서 앨범 정보 로드
    super.load(obj, resultSet);
    Album album = (Album) obj;
    album.setId(resultSet.getLong("id")); // id 설정
    album.setTitle(resultSet.getString("title")); // title 설정
    long artistID = resultSet.getLong("artist_id");

    // ArtistMapper를 통해 Artist 객체 로드
    Artist artist = artistMapper.find(artistID);

    album.setArtist(artist);
    loadTracks(album, resultSet);
  }

  public void loadTracks(Album album, ResultSet resultSet) throws SQLException {
    do {
      // 각 트랙의 데이터를 가져와서 앨범에 추가
      String trackTitle = resultSet.getString("trackTitle");
      Track track = new Track(trackTitle);
      album.addTrack(track);
    } while (resultSet.next()); // 다음 결과로 이동
  }


}
