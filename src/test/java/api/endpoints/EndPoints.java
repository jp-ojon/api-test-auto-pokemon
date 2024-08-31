package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class EndPoints {
	//contains only the CRUD methods implementation
	
	/**
	 * This method would send a get request with parameters by Country, City and State
	 * @param country 
	 * @param state
	 * @param city
	 * @return response
	 */
	public static Response getPokemon(String pokemon) {
		Response response = given().pathParam("pokemon", pokemon)
				.when().get(Routes.pokemon)
				.then()//.log().all()
				.extract().response();
		return response;
	}
	
}