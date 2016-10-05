import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
  private EndangeredAnimal testAnimal;
  private EndangeredAnimal nextAnimal;

  @Rule
   public DBRule database = new DBRule();

   @Before
   public void initialize() {
     testAnimal = new EndangeredAnimal ("Grey Wolf", true, "injured", 1);
     testAnimal.save();
     nextAnimal = new EndangeredAnimal ("Spotted Owl", true, "unresponsive", 2);
     nextAnimal.save();
   }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAnimalsQuery = "DELETE FROM animals *;";
      con.createQuery(deleteAnimalsQuery).executeUpdate();
    }
  }

  @Test
  public void Animal_instantiatesCorrectly_true() {
    assertEquals(true, testAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void Animal_instantiatesWithName_String() {
    assertEquals("Grey Wolf", testAnimal.getName());
  }

  @Test
  public void Animal_instantiatesWithEndangeredCorrectly_true() {
    assertEquals(true, testAnimal.getEndangered());
  }

  @Test
  public void Animal_instantiatesWithCorrectHealth_1() {
    assertEquals("injured", testAnimal.getHealth());
  }
}
