import java.util.*;
import org.sql2o.*;

public class ProtectedAnimal extends Animal
{

	public static final String DATABASE_TYPE = "protected";
	
	public ProtectedAnimal(String name)
	{
	 this.name = name;
	 type = DATABASE_TYPE;
	}
	
	public static List<ProtectedAnimal> all() {
		String sql = "SELECT * FROM animals;";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(ProtectedAnimal.class);
		}
	}
	
	public static List<ProtectedAnimal> allProtected() {
		String sql = "SELECT * FROM animals WHERE type = 'protected';";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(ProtectedAnimal.class);
		}
	}

	public static ProtectedAnimal find(int id)
	{
			try(Connection con = DB.sql2o.open())
			{
				String sql = "SELECT * FROM animals WHERE id = :id;";
				ProtectedAnimal animal = con.createQuery(sql)
					.addParameter("id", id)
					.throwOnMappingFailure(false)
					.executeAndFetchFirst(ProtectedAnimal.class);
				return animal;
			}
	}
	
	
	

}

