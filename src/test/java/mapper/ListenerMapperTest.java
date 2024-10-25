package mapper;

import database.DB;
import domain.Artist;
import domain.Listener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ListenerMapperTest {
  private ListenerMapper mapper;
  private Connection connection;

  @BeforeEach
  public void setUp() throws SQLException {
    // 데이터베이스 연결
    connection = DB.connect();
    mapper = new ListenerMapper(connection);
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
    Listener listener = mapper.find(1); // 1번 아티스트 찾기

    assertNotNull(listener);
    assertEquals(1, listener.getId());
    assertEquals("rock", listener.getFavoriteGenre());
  }
}