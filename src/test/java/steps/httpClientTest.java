package steps;

import static org.hamcrest.CoreMatchers.is;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//--------------------------------------------------
//to musialam dodac zeby zadzialao w Assert.asserThat zadzialalo conteinsString
import static org.hamcrest.CoreMatchers.*;
//------------------------------------------------
import org.junit.Assert;
import requests.PostBody;
import requests.ResponseToString;

import static org.mockito.Mockito.verify;


public class httpClientTest {

  //to jest glupi przyklad. Serwer nie dziala. Chodzilo tylko o to, jak dziala HttpClient


  PostBody postBody = new PostBody();
  CloseableHttpClient httpClient = HttpClients.createDefault();
  HttpResponse response;

  //chcialam sprobowac z klasa ResponseToString - po to te 2 linijki
  //ale skoro serwer nie dziala to dupa
 // HttpResponse httpResponse;
 // ResponseToString responseToString = new ResponseToString();



  @When("^users upload data on a project$")
  public void usersUploadDataOnAProject() throws Throwable {
    HttpPost request = new HttpPost();
    StringEntity entity = new StringEntity(postBody.getJsonString());
    request.addHeader("content-type", "application/json");
    request.setEntity(entity);
    this.response = httpClient.execute(request);

    // to z innego przykladu ALE DOBRE
//    String jsonMimeType = "application/json";
//    String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
//    Assert.assertEquals( jsonMimeType, mimeType );

  }

  @Then("^the server should handle it and return a success status$")
  public void theServerShouldHandleItAndReturnASuccessStatus() throws Throwable {
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());
//    verify(postRequestedFor(urlEqualTo("/create")) //NIE WIEM DLACZEGO VERITY() NIE DZIALA
//        .withHeader("content-type", equalTo("application/json")));


    //  System.out.println(responseToString.convertResponseToString(response)); // tu chciala zobaczyc
    //jak i czy w ogole dziala ResponseToString - chyba to jest zle


  }

  @When("^users want to get information on the \"([^\"]*)\" project$")
  public void usersWantToGetInformationOnTheProject(String projectName) throws Throwable {
    HttpGet request = new HttpGet("http://localhost:8080/projects/" + projectName.toLowerCase());
    request.addHeader("accept", "application/json");
    HttpResponse httpResponse = httpClient.execute(request);
   // this.httpResponse = httpClient.execute(request); // robie tak, zeby moc wykorzystac w asercji nizej
    }



  @Then("^the requested data is returned$")
  public void theRequestedDataIsReturned() throws Throwable {
    Assert.assertThat("tu powinien byc response tring z metody w klasie ResponseToString", containsString("\"testing-framework\": \"cucumber\""));
    Assert.assertThat("tu powinien byc response tring z metody w klasie ResponseToString", containsString("\"website\": \"cucumber.io\""));
//    verify(getRequestedFor(urlEqualTo("/projects/cucumber"))
//        .withHeader("accept", equalTo("application/json")));

 //   Assert.assertThat(responseToString.convertResponseToString(httpResponse), containsString("\"testing-framework\": \"cucumber\""));
  //  Assert.assertThat(responseToString.convertResponseToString(httpResponse), containsString("\"website\": \"cucumber.io\""));


  }


}
