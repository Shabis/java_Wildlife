import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class SightingTest {
  private Sighting testSighting;
  private Sighting nextSighting;

  @Rule
   public DBRule database = new DBRule();

   @Before
   public void initialize() {
     testSighting = new Sighting (1, "East Hills", "Ranger Brian");
     nextSighting = new Sighting (2, "Creater Valley", "Ranger Roger");
   }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteSightingsQuery = "DELETE FROM sightings *;";
      con.createQuery(deleteSightingsQuery).executeUpdate();
    }
  }

  @Test
  public void Sighting_instantiatesCorrectly_true() {
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void Sighting_instantiatesWithAnimalId_String() {
    assertEquals(2, nextSighting.getAnimalId());
  }

  @Test
  public void Sighting_instantiatesWithCorrectLocation_false() {
    assertEquals("East Hills", testSighting.getLocation());
  }

  @Test
  public void Sighting_instantiatesWithCorrectRanger_false() {
    assertEquals("Ranger Brian", testSighting.getRanger());
  }
}
