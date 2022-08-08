
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readers.ConfigurationReader;

import static io.restassured.RestAssured.given;

public class RestApiHelper {
    private static final Logger log = LogManager.getLogger(RestApiHelper.class);

    public RestApiHelper() {
        RestAssured.baseURI = ConfigurationReader.read().getString("conf.api_host");
    }

    public Response get(String endpoint) {
        log.info("Sending request to: " + RestAssured.baseURI + endpoint);
        return given()
                .get(endpoint)
                .then()
                .extract().response();
    }
}
