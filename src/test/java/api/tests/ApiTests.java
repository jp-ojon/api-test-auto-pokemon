package api.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import api.endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTests {

    // Declare variables with common usage
    Response response;
    int statusCode;
    String contentType;

    @Test(priority = 1, description = "Pikachu Test")
    public void testPikachu() {

        // Perform get request and store the response
        response = EndPoints.getPokemon("pikachu");

        // Verify the response status code. Status code is 200
        statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code should be 200");

        // Verify the response content type. Content type is JSON
        contentType = response.getContentType();
        System.out.println("Content type: " + contentType);
        Assert.assertTrue(contentType.contains("application/json"), "Content type should be JSON");

        // Extract JsonPath from the response
        JsonPath jsonPath = response.jsonPath();

        // Get the list of ability names from the jsonpath response
        List<String> abilityNames = jsonPath.getList("abilities.ability.name");
        System.out.println("Abilities: " + abilityNames);

		// Assert that 'lightning-rod' is one of the abilities
        Assert.assertTrue(abilityNames.contains("lightning-rod"), "'lightning-rod' should be one of the abilities");
    }

    @Test(priority = 2, description = "Charmander 404 Test")
    public void testCharmander404() {

        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        wireMockServer.start();

        // Configure WireMock to return a 404 for the /pokemon/charmander endpoint
        WireMock.configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/pokemon/charmander"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("Resource Not Found")));

        // Make a request to the mocked endpoint using RestAssured
        response = RestAssured.get("http://localhost:8080/pokemon/charmander");

        // Print response details
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Headers: " + response.getHeaders());

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404");

        // Stop WireMock server
        wireMockServer.stop();

    }

}
