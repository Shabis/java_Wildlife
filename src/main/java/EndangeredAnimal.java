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
}  
