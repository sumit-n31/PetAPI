package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.response.*;

import io.restassured.http.ContentType;

public class UserEndpoint {

	public static Response createUser(User payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(Routes.post_url);

		return res;
	}

	public static Response readUser(String userName) {

		Response res = given().pathParam("username", userName)

				.when().get(Routes.get_url);

		return res;
	}

	public static Response updateUser(String userName, User payload) {

		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName)
				.body(payload)

				.when().put(Routes.update_url);

		return res;
	}

	public static Response deleteUser(String userName) {

		Response res = given().pathParam("username", userName)

				.when().delete(Routes.delete_url);

		return res;
	}

}
