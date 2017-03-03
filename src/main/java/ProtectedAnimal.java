import java.util.*;
import org.sql2o.*;

public class ProtectedAnimal extends Animal
{
	public ProtectedAnimal(String name)
	{
	 this.name = name;
	}
	
	public static List<ProtectedAnimal> all() {
		String sql = "SELECT * FROM animals;";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(ProtectedAnimal.class);
		}
	}

	public static Animal find(int id)
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

