package requests;

import org.apache.http.HttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class ResponseToString {

  HttpResponse response;

  public String convertResponseToString(HttpResponse response) throws IOException {
    InputStream responseStream = response.getEntity().getContent();
    Scanner scanner = new Scanner(responseStream, "UTF-8");
    String responseString = scanner.useDelimiter("\\Z").next();
    scanner.close();
    return responseString;
  }
}
