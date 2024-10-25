package mapper;

import domain.Artist;
import domain.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistMapper extends AbstractPersonMapper {
  public ArtistMapper(Connection connection) {
    super(connection);
  }

  @Override
  protected String getTableName() {
    return "artist"; // 테이블 이름 반환
  }

  @Override
  protected Artist createDomainObject() {
    return new Artist();
  }

  //데이터베이스 지정한 ID 행 찾기
  public Artist find(long id) throws SQLException {
    return (Artist) abstractFind(id);
  }

  @Override
  public void load(Person obj, ResultSet resultSet) throws SQLException {
    super.load(obj, resultSet);
    Artist artist = (Artist) obj;
    artist.setGenre(resultSet.getString("genre"));
  }

}
