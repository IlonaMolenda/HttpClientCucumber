package requests;

public class PostBody {

  String jsonString = "{\n"
      + "    \"testing-framework\": \"cucumber\",\n"
      + "    \"supported-language\": \n"
      + "    [\n"
      + "        \"Ruby\",\n"
      + "        \"Java\",\n"
      + "        \"Javascript\",\n"
      + "        \"PHP\",\n"
      + "        \"Python\",\n"
      + "        \"C++\"\n"
      + "    ],\n"
      + " \n"
      + "    \"website\": \"cucumber.io\"\n"
      + "}";

  public String getJsonString() {
    return jsonString;
  }
}
