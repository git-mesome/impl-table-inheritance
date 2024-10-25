package mapper;

import domain.Group;
import domain.Group;
import domain.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper extends AbstractPersonMapper{
  
  public GroupMapper(Connection connection) {
    super(connection);
  }
  @Override
  protected String getTableName() {
    return "group_artist"; // 테이블 이름 반환
  }

  @Override
  protected Group createDomainObject() {
    return new Group();
  }

  //데이터베이스 지정한 ID 행 찾기
  public Group find(long id) throws SQLException {
    return (Group) abstractFind(id);
  }

  @Override
  public void load(Person obj, ResultSet resultSet) throws SQLException {
    super.load(obj, resultSet);
    Group group = (Group) obj;
    group.setGenre(resultSet.getString("genre"));
    group.setAgency(resultSet.getString("agency"));
  }
  
}
