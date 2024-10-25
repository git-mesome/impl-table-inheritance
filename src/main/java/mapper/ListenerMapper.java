package mapper;

import domain.Listener;
import domain.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListenerMapper extends AbstractPersonMapper{

  public ListenerMapper(Connection connection) {
    super(connection);
  }

  @Override
  protected String getTableName() {
    return "listener"; // 테이블 이름 반환
  }

  @Override
  protected Listener createDomainObject() {
    return new Listener();
  }

  //데이터베이스 지정한 ID 행 찾기
  public Listener find(long id) throws SQLException {
    return (Listener) abstractFind(id);
  }

  @Override
  public void load(Person obj, ResultSet resultSet) throws SQLException {
    super.load(obj, resultSet);
    Listener listener = (Listener) obj;
    listener.setFavoriteGenre(resultSet.getString("favorite_genre"));
  }

}
