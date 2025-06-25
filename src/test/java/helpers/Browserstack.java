package helpers;

import static io.restassured.RestAssured.given;

public class Browserstack {

  public static String videoUrl(String sessionId) {
    String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
    return given()
            .auth().basic("victorialinova_cMLaDe", "JSBMB9p6qjZTdxMrM9DU")
            .get(url)
            .then()
            .log().body()
            .log().status()
            .statusCode(200)
            .extract().path("automation_session.video_url");
  }
}
