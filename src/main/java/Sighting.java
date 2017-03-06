import java.util.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sighting
{
	private int id;
	private String location;
	private int animalId;
	private String rangerName;
	private Timestamp time;
	
	public Sighting(String location,int animalId, String rangerName)
	{
		this.location = location;
		this.animalId = animalId;
		this.rangerName = rangerName;
		
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public int getAnimalId()
	{
		return animalId;
	}
	
	public String getRangerName()
	{
		return rangerName;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Timestamp getTime(){
		return time;
	} 
	
	
	public static List<Sighting> all()
	{
		String sql = "SELECT * FROM sightings;";
		try(Connection con = DB.sql2o.open())
		{
			return con.createQuery(sql)
				.throwOnMappingFailure(false)
				.executeAndFetch(Sighting.class);
			
		}
	}
	
	@Override
  public boolean equals(Object otherSighting){
    if (!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getLocation().equals(newSighting.getLocation()) &&
             this.getAnimalId() == newSighting.getAnimalId();
    }
  }
	
	public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (location, animalId, rangerName, time) VALUES (:location, :animalId, :rangerName, now())";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("location", this.location)
        .addParameter("animalId", this.animalId)
        .addParameter("rangerName", this.rangerName)
        .executeUpdate()
        .getKey();
    }
  }
	
	
	public static Sighting find(int id)
	{
			try(Connection con = DB.sql2o.open())
			{
				String sql = "SELECT * FROM sightings WHERE id = :id;";
				Sighting sighting = con.createQuery(sql)
					.addParameter("id", id)
					.throwOnMappingFailure(false)
					.executeAndFetchFirst(Sighting.class);
				return sighting;
			}
	}
	
	
	
	
}