import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "vagabond", "472113");
  }

   @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAnimalsQuery = "DELETE FROM animals *;";
//      String deleteStylistsQuery = "DELETE FROM  *;";
      con.createQuery(deleteAnimalsQuery).executeUpdate();
//      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

}