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
}
