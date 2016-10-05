import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Animal {
  public int id;
  public String name;
  public boolean endangered;

  public Animal(String name, boolean endangered) {
    this.name = name;
    this.endangered = endangered;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean getEndangered() {
    return endangered;
  }

  public static List<Animal> all() {
    String sql = "SELECT * FROM animals";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Animal.class);
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName())
      && this.getEndangered() == newAnimal.getEndangered();
    }
  }

  public void save() {
    if (name.length() <= 0) {
      throw new UnsupportedOperationException("Please enter the name of your animal");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("endangered", this.endangered)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
        return animal;
      }
    }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE animals SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
