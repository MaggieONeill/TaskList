import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args){
    staticFileLocation("/public");
      String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());


    post("/task", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String description = request.queryParams("description");
      Task newTask = new Task(description);
      request.session().attribute("task", newTask);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
