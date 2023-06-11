package api.tests;

import com.github.javafaker.Faker;
import io.restassured.response.*;
import api.endpoints.UserEndpoint;
import api.payloads.User;

import org.testng.Assert;
import org.testng.annotations.*;

public class UserTests {

	Faker faker;
	User payload;

	@BeforeClass
	private void setupData() {
		faker = new Faker();
		payload = new User();
		payload.setId(faker.idNumber().hashCode());
		payload.setEmail(faker.internet().emailAddress());
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastname(faker.name().lastName());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	private void testPostuser() {
		Response res = UserEndpoint.createUser(payload);
		res.then().log().all();

	}

	@Test(priority = 2)
	private void testReaduser() {
		Response res = UserEndpoint.readUser(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 201);

	}

	@Test(priority = 3)
	private void testUpdateuser() {
		payload.setFirstname(faker.name().firstName());
		payload.setLastname(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());

		Response res = UserEndpoint.updateUser(this.payload.getUsername(), payload);
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	}

	@Test(priority = 4)
	private void testReadupdateduser() {
		Response res = UserEndpoint.readUser(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(this.payload.getUsername());

	}

	@Test(priority = 5)
	private void deleteUser() {
		Response res = UserEndpoint.deleteUser(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println(this.payload.getUsername());

	}

}
