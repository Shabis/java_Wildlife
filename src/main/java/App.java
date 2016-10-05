import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import spark.ModelAndView;
import java.sql.Timestamp;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.all());
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("endangered", EndangeredAnimal.all());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sighting", Sighting.all());
      model.put("animals", Animal.all());
      model.put("template", "templates/sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      boolean endangered = true;
      String health = request.queryParams("health");
      int age = Integer.parseInt(request.queryParams("age"));
      EndangeredAnimal animal = new EndangeredAnimal (name, endangered, health, age);
      animal.save();
      model.put("animal", animal);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      boolean endangered = false;
      Animal animal = new Animal (name, endangered);
      animal.save();
      model.put("animal", animal);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String ranger = request.queryParams("ranger");
      int animalId = Integer.parseInt(request.queryParams("animalId"));
      String location = request.queryParams("location");
      Sighting sighting = new Sighting (animalId, location, ranger);
      model.put("sighting", sighting);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
