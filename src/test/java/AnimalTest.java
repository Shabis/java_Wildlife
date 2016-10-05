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

  @Test
  public void Animal_returnsAllInstancesOfAnimal_true() {
    assertEquals(true, Animal.all().get(0).equals(testAnimal));
    assertEquals(true, Animal.all().get(1).equals(nextAnimal));
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Animal newAnimal = testAnimal;
    assertEquals(newAnimal, testAnimal);
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }

  @Test
  public void save_assignsIdToObject() {
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test
  public void find_returnsAnimalWithSameId_nextAnimal() {
    assertEquals(Animal.find(nextAnimal.getId()), nextAnimal);
  }

  @Test
  public void update_updatesAnimalName_true() {
    testAnimal.update("Bald Eagle");
    assertEquals("Bald Eagle", Animal.find(testAnimal.getId()).getName());
  }
}
