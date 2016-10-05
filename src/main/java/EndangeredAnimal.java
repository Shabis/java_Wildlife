import java.util.List;
import java.util.Timer;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class EndangeredAnimal extends Animal {
  private int age;
  public String health;

  public static final String HEALTH_LEVEL_3 = "healthy";
  public static final String HEALTH_LEVEL_2 = "injured";
  public static final String HEALTH_LEVEL_1 = "unresponsive";

  public EndangeredAnimal(String name, boolean endangered, String health, int age) {
    super(name, endangered);
    this.age = age;
    this.health = health;
  }

  public int getAge() {
    return age;
  }

  public String getHealth() {
    return health;
  }
  
  public static List<EndangeredAnimal> all(boolean endangered) {
      String sql = "SELECT * FROM animals WHERE endangered = true;";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(EndangeredAnimal.class);
      }
    }

  @Override
  public void save() {
    if (name.length() <= 0) {
      throw new UnsupportedOperationException("Please enter the name of your animal");
    } else if (health.length() <= 0) {
      throw new UnsupportedOperationException("Please enter the health of your animal");
    } else if (age <= 0) {
      throw new UnsupportedOperationException("Please enter a valid age for your animal");
      }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("endangered", this.endangered)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }
}
