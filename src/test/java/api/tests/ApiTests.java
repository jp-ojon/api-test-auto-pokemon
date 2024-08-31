package api.tests;

import org.testng.*;
import org.testng.annotations.*;
import api.endpoints.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


public class ApiTests {

	// Declare variables with common usage
	Response response;
	int statusCode;
	String contentType;
	String country;
	String state;
	long responseTime;
	boolean placeFound;

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

		// Get the list of ability names
		List<String> abilityNames = jsonPath.getList("abilities.ability.name");
		System.out.println("Abilities: "+ abilityNames);

		// Assert that 'lightning-rod' is one of the abilities
		assertThat(abilityNames, hasItem("lightning-rod"));
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
        Response response = RestAssured.get("http://localhost:8080/pokemon/charmander");
        
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
