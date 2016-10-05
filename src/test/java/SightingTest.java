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

  @Test
  public void Sighting_returnsAllInstancesOfSighting_true() {
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(nextSighting));
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Sighting newSighting = testSighting;
    assertEquals(newSighting, testSighting);
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void save_assignsIdToObject() {
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(testSighting.getId(), savedSighting.getId());
  }

  @Test
  public void find_returnsSightingWithSameId_nextSighting() {
    assertEquals(Sighting.find(nextSighting.getId()), nextSighting);
  }
}
