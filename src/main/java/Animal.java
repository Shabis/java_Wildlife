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
