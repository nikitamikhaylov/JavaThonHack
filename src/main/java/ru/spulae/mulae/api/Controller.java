package ru.spulae.mulae.api;

/**
 * Created by ${user} on 27.01.19.
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @PostMapping(value = "/add_payment", produces = "application/json;charset=utf-8")
  public String addPayment(
      @RequestParam("fundraising_id") String fundraisingId,
      @RequestParam("user_id") String userId,
      @RequestParam("amount") int amount,
      @RequestParam("description") String description) {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    return gson.toJson(obj);
  }

  @PostMapping(value = "/set_block", produces = "application/json;charset=utf-8")
  public String setBlock(
      @RequestParam("fundraising_id") String fundraisingId) {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    return gson.toJson(obj);
  }

  @PostMapping(value = "/set_agreement", produces = "application/json;charset=utf-8")
  public String setAgreement(
      @RequestParam("fundraising_id") String fundraisingId) {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    return gson.toJson(obj);
  }

  @GetMapping(value = "/get_updates", produces = "application/json;charset=utf-8")
  public String getUpdates() {
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    obj.add("updates", new JsonArray());
    return gson.toJson(obj);
  }

  class CreateFundraisingParams {
    public List<String> usernames;
  }

  @PostMapping(value = "/create_fundraising", produces = "application/json;charset=utf-8")
  public String createFundraising(
      @RequestBody CreateFundraisingParams params) {
    ;
  }

}
