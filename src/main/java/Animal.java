import java.util.*;
import org.sql2o.*;

public abstract class Animal
{
	
	public String name;
	public int id;
	
	
	public String getName()
	{
	  return name;
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
	
	
  public void save() 
	{
		try(Connection con = DB.sql2o.open())
		{
		  String sql = "INSERT INTO animals(name) VALUES (:name)";
			this.id =(int)con.createQuery(sql,true)
					.addParameter("name", this.name)
					.executeUpdate()
					.getKey();
		}
	}
	
	
	
	
	

	
	
	
}