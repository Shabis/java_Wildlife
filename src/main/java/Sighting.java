import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import org.sql2o.*;

public class Sighting {
  public int id;
  public int animalId;
  public Timestamp time;
  public String location;
  public String ranger;

  public Sighting(int animalId, String location, String ranger) {
    this.animalId = animalId;
    this.location = location;
    this.ranger = ranger;
    this.save();
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animalId;
  }

  public Timestamp getTime() {
    return time;
  }

  public String getLocation() {
    return location;
  }

  public String getRanger() {
    return ranger;
  }

  public static List<Sighting> all() {
  String sql = "SELECT * FROM sightings";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql)
    .throwOnMappingFailure(false)
    .executeAndFetch(Sighting.class);
    }
  }

  @Override
  public boolean equals(Object otherSighting) {
    if (!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getRanger().equals(newSighting.getRanger()) &&
      this.getAnimalId() == (newSighting.getAnimalId()) &&
      this.getLocation().equals(newSighting.getLocation());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalId, time, location, ranger) VALUES (:animalId, now(), :location, :ranger);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("location", this.location)
        .addParameter("ranger", this.ranger)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings where id=:id";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
        return sighting;
    }
  }
}
