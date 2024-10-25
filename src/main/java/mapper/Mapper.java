package mapper;

import domain.Album;
import domain.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Mapper<T> {

  protected Connection connection;

  public Mapper(Connection connection) {
    this.connection = connection;
  }

  protected void load(T obj, ResultSet resultSet) throws SQLException {
    loadBasic(obj, resultSet); // 기본 데이터 로드
  }

  protected void loadBasic(T obj, ResultSet resultSet) throws SQLException {
    if (obj instanceof Person) {
      ((Person) obj).setId(resultSet.getLong("id")); // Person의 ID 로드
    } else if (obj instanceof Album) {
      ((Album) obj).setId(resultSet.getLong("id")); // Album의 ID 로드
    }
  }

  public T abstractFind(long id) throws SQLException {
    try (ResultSet resultSet = findRow(id)) {
      if (resultSet == null || !resultSet.next()) {
        return null; // 행이 없으면 null 반환
      } else {
        T result = createDomainObject(); // 도메인 객체 생성
        load(result, resultSet); // 데이터 로드
        return result; // 객체 반환
      }
    }
  }


  protected ResultSet findRow(long id) throws SQLException {
    String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setLong(1, id);
    return statement.executeQuery(); // ResultSet 반환
  }

  protected Connection getConnection() {
    return connection;
  }

  protected abstract String getTableName();

  protected abstract T createDomainObject();
}
