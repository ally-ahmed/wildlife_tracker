import java.util.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class EndangeredAnimal extends Animal{
	private String health;
	private String age; 
	public static final String DATABASE_TYPE = "endangered";
	
	public EndangeredAnimal(String name, String health, String age){
		this.name = name;
		this.health= health;
		this.age = age;
		type = DATABASE_TYPE;
	}
	
	public String getHealth(){
		return health;
	}
	
	public String getAge(){
	 return age;
	}
	
	public static List<EndangeredAnimal> allEndangered() {
		String sql = "SELECT * FROM animals WHERE type = 'endangered';";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(EndangeredAnimal.class);
		}
	}
	
	@Override
	public void save() 
	{
		try(Connection con = DB.sql2o.open())
		{
		  String sql = "INSERT INTO animals(name, health, age, type) VALUES (:name, :health, :age, :type)";
			this.id =(int)con.createQuery(sql,true)
					.addParameter("name", this.name)
					.addParameter("health", this.health)
					.addParameter("age", this.age)
					.addParameter("type",this.type)
					.executeUpdate()
					.getKey();
		}
	}
	
	public static EndangeredAnimal find(int id)
	{
		try(Connection con = DB.sql2o.open())
		{
			String sql = "SELECT * FROM animals WHERE id = :id;";
			EndangeredAnimal animal = con.createQuery(sql)
				.addParameter("id", id)
				.throwOnMappingFailure(false)
				.executeAndFetchFirst(EndangeredAnimal.class);
			return animal;
		}
	}
	
	
	public void update(String name, String health, String age) 
	{
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE animals SET name = :name, health=:health, age=:age WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
			.addParameter("health",health)
			.addParameter("age",age)
      .executeUpdate();
    }
  }


}