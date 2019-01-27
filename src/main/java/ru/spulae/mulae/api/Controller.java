package ru.spulae.mulae.api;

/**
 * Created by ${user} on 27.01.19.
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spulae.mulae.Fundraising;
import ru.spulae.mulae.Payment;
import ru.spulae.mulae.Person;

@RestController
public class Controller {

  private ArrayList<Person> people = null;
  private Map<String, Person> peopleMap = null;
  private ArrayList<Fundraising> fundraisers = new ArrayList<>();;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @PostMapping(value = "/add_payment", produces = "application/json;charset=utf-8")
  public String addPayment(
      @RequestParam("fundraising_id") String fundraisingId,
      @RequestParam("name") String name,
      @RequestParam("amount") int amount,
      @RequestParam("description") String description) {
    boolean ok = false;
    for (Fundraising fundraising : fundraisers) {
      if (fundraising.getName().equals(fundraisingId)) {
        ok = fundraising.addPayment(peopleMap.get(name), new Payment(amount, description));
      }
    }
    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(ok));
    return gson.toJson(obj);
  }

  @PostMapping(value = "/set_block", produces = "application/json;charset=utf-8")
  public String setBlock(
      @RequestParam("fundraising_id") String fundraisingId,
      @RequestParam("name") String name) {
    for (Fundraising fundraising : fundraisers) {
      if (fundraising.getName().equals(fundraisingId)) {
        fundraising.setBlock(peopleMap.get(name));
      }
    }

    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    return gson.toJson(obj);
  }

  @PostMapping(value = "/set_agreement", produces = "application/json;charset=utf-8")
  public String setAgreement(
      @RequestParam("fundraising_id") String fundraisingId,
      @RequestParam("name") String name) {
    for (Fundraising fundraising : fundraisers) {
      if (fundraising.getName().equals(fundraisingId)) {
        fundraising.setAgreement(peopleMap.get(name));
      }
    }

    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    return gson.toJson(obj);
  }

  @PostMapping(value = "/get_fundraisers", produces = "application/json;charset=utf-8")
  public String getFundraisers (
      @RequestParam("name") String name) {
    System.out.println("eala");
    ArrayList<String> answer = new ArrayList<>();
    for (Fundraising fundraising : fundraisers) {
      System.out.println(fundraising.getParticipants().toString());
      for (Person person : fundraising.getParticipants()) {
        if (person.getName().equals(name)) {
          answer.add(fundraising.getName());
        }
      }
    }
    Gson gson = new Gson();
    String res = gson.toJson(answer);
    System.out.println("keks" + res);
    return res;
  }

  static class CreateFundraisingParams {
    public String fundraiserName;
    public List<String> participants;
  }

  @PostMapping(value = "/create_fundraising", produces = "application/json;charset=utf-8")
  public String createFundraising(
      @RequestBody String paramsJson) {
    CreateFundraisingParams params = new Gson().fromJson(paramsJson, CreateFundraisingParams.class);

    people = new ArrayList<>();
    peopleMap = new HashMap<>();
    int i = 0;
    for (String username : params.participants) {
      Person person = new Person(i++, username, 100000);
      people.add(person);
      peopleMap.put(username, person);
    }
    Fundraising fundraising = new Fundraising(params.fundraiserName, people);
    fundraisers.add(fundraising);

    System.out.println("kekek: " + paramsJson);

    Gson gson = new Gson();
    JsonObject obj = new JsonObject();
    obj.add("ok", new JsonPrimitive(true));
    obj.add("numFundraisings", new JsonPrimitive(fundraisers.size()));
    return gson.toJson(obj);
  }

}
