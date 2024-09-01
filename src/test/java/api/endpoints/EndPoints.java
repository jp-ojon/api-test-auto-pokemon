package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class EndPoints {
	//contains only the CRUD methods implementation
	
	/**
	 * This method would send a get request with parameters by pokemon
	 * @param pokemon 
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