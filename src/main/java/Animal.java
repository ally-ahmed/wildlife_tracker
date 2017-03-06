import java.util.*;
import org.sql2o.*;

public abstract class Animal
{
	
	public String name;
	public int id;
	public String type;
	
	
	public String getName()
	{
	  return name;
	}
	
	public String getType()
	{
	  return type;
	}
	public int getId() {
    return id;
  }
	
	@Override
  public boolean equals(Object otherAnimal)
	{
   if(!(otherAnimal instanceof Animal))
	 {
	   return false;
	 } else
	 {
	   Animal newAnimal = (Animal) otherAnimal;
		 return this.getName().equals(newAnimal.getName()) &&
		 				this.getId() == newAnimal.getId();
	 }
  }
	
		
//	public static List<Animal> all() {
//    String sql = "SELECT * FROM animals";
//    try(Connection con = DB.sql2o.open()) {
//     return con.createQuery(sql).executeAndFetch(Animal.class);
//    }
//  }
	
  public void save() 
	{
		try(Connection con = DB.sql2o.open())
		{
		  String sql = "INSERT INTO animals(name, type) VALUES (:name, :type);";
			this.id =(int)con.createQuery(sql,true)
					.addParameter("name", this.name)
					.addParameter("type", this.type)
					.executeUpdate()
					.getKey();
		}
	}
	
	public void delete() 
	{
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM animals WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
	
	public void update(String name) 
	{
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE animals SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
	
	public List<Sighting> getSightings(){
		
		try(Connection con = DB.sql2o.open())
		{
			String sql = "SELECT * FROM sightings WHERE animalId = :id;";
			return con.createQuery(sql)
				.addParameter("id", this.id)
				.throwOnMappingFailure(false)
				.executeAndFetch(Sighting.class);
			
		}
			
			
			
	}
	
	
	
	

	
	
	
}