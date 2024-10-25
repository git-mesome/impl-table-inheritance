package mapper;

import domain.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractPersonMapper extends Mapper<Person>{

  public AbstractPersonMapper(Connection connection) {
    super(connection);
  }

  @Override
  public void load(Person obj, ResultSet resultSet) throws SQLException {
    super.load(obj, resultSet);
    obj.setName(resultSet.getString("name"));
  }

  @Override
  protected String getTableName() {
    return "";
  }

  @Override
  protected Person createDomainObject() {
    return null;
  }
}
