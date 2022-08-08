import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DexcomApiInfoTest {
    private static final Logger log = LogManager.getLogger(DexcomApiInfoTest.class);
    private static Response response;

    /***
     * Verification of the /info endpoint
     * Status Code should be 200
     * Content media type should be JSON
     * Content media type should be XML
     * Dexcom API Device Identifier should be 00386270000668
     * Dexcom API Version should be 3.1.0.0
     * Dexcom API Part Number (PN) should be 350-0019
     * Dexcom API Sub-Components should contain contain api-gateway object
     * Dexcom API Sub-Components should contain contain insulin-service object
     */

    @BeforeAll
    static void get_info_response() {
        RestApiHelper api = new RestApiHelper();
        response = api.get("info");
    }

    @Test
    public void test_info_api_status_code_equals_200() {
        log.info("Verify that /info response code is 200");
        Assertions.assertEquals(200, response.statusCode());
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_content_type_equals_json() {
        log.info("Verify that /info content type is JSON media type");
        Assertions.assertEquals("application/json", response.contentType());
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_content_type_equals_xml() {
        log.info("Verify that /info content type is XML media type");
        Assertions.assertEquals("application/xml", response.contentType());
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_response_body_device_identifier() {
        log.info("Verify that /info UDI / Device Identifier equals to 00386270000668");
        Assertions.assertEquals("00386270000668", response.body().jsonPath().getString("find {it.'Product Name' == 'Dexcom API'}.'UDI / Device Identifier'"));
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_response_body_version() {
        log.info("Verify that /info Version equals to 3.1.0.0");
        Assertions.assertEquals("3.1.0.0", response.body().jsonPath().getString("find {it.'Product Name' == 'Dexcom API'}.'UDI / Production Identifier'.Version"));
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_response_body_part_number() {
        log.info("Verify that /info Part Number (PN) equals to 350-0019");
        Assertions.assertEquals("350-0019", response.body().jsonPath().getString("find {it.'Product Name' == 'Dexcom API'}.'UDI / Production Identifier'.'Part Number (PN)'"));
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_response_body_api_gateway() {
        log.info("Verify that /info Sub-Components contain api-gateway object");
        Assertions.assertNotNull(response.body().jsonPath().getString("find {it.'Product Name' == 'Dexcom API'}.'UDI / Production Identifier'.'Sub-Components'.find {it.Name == 'api-gateway'}"));
        log.info("Verification Passed");
    }

    @Test
    public void test_info_api_response_body_insulin_service() {
        log.info("Verify that /info Sub-Components contain insulin-service object");
        Assertions.assertNotNull(response.body().jsonPath().getString("find {it.'Product Name' == 'Dexcom API'}.'UDI / Production Identifier'.'Sub-Components'.find {it.Name == 'insulin-service'}"));
        log.info("Verification Passed");
    }


}
