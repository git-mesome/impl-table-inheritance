package mapper;

import database.DB;
import domain.Album;
import domain.Group;
import domain.Track;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumMapperTest {

  private AlbumMapper mapper;
  private ArtistMapper artistMapper;
  private Connection connection;

  @BeforeEach
  public void setUp() throws SQLException {
    // 데이터베이스 연결
    connection = DB.connect();
    // ArtistMapper 초기화
    artistMapper = new ArtistMapper(connection);
    // AlbumMapper 초기화
    mapper = new AlbumMapper(connection, artistMapper);
  }

  @AfterEach
  public void tearDown() throws SQLException {
    // 연결 닫기
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }

  @Test
  public void testFind() throws SQLException {
    Album album = mapper.find(1); // 1번 아티스트 찾기

    assertNotNull(album);
    assertEquals(1, album.getId());
    assertEquals("홍길동전", album.getTitle());
    assertEquals("홍길동", album.getArtist().getName());


    List<Track> tracks = album.getTracks();
    assertNotNull(tracks);
    assertEquals("홍트랙", tracks.getFirst().getTitle());
  }

}