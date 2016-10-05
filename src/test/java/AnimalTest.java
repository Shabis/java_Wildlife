import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {
  private Animal testAnimal;
  private Animal nextAnimal;

  @Rule
   public DBRule database = new DBRule();

   @Before
   public void initialize() {
     testAnimal = new Animal ("Grey Wolf", false);
     testAnimal.save();
     nextAnimal = new Animal ("Spotted Owl", false);
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
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void Animal_instantiatesWithName_String() {
    assertEquals("Grey Wolf", testAnimal.getName());
  }

  @Test
  public void Animal_instantiatesWithEndangeredCorrectly_false() {
    assertEquals(false, testAnimal.getEndangered());
  }
