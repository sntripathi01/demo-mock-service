package com.demo.mocko.service.contract;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMockServiceApplicationTests {

	private MockServerClient externalServices;

	public static final String CONTRACT_TEST_DIR = "contractTest";

	@Before
	public void setup() {
		RestAssured.port = 8080;
		RestAssured.baseURI = "http://localhost";

		externalServices = startClientAndServer(9000);
	}

	@Test
	public void testVersionServicePositiveCase() throws Exception {

		externalServices.when(request().withMethod("GET").withPath("/version"))
				.respond(response().withStatusCode(200)
						.withHeader((new org.mockserver.model.Header("Content-Type", "application/json")))
						.withBody(returnStringFromFile(CONTRACT_TEST_DIR, "version_service_response.json")));

		Response getAccountResponse = given().when().contentType("application/json").accept("application/json")
				.queryParam("version", "1").get("/version").then().statusCode(200).extract().response();
		assertEquals("4", getAccountResponse.getBody().jsonPath().get("currentVersion"));
		assertEquals("3", getAccountResponse.getBody().jsonPath().get("stableVersion"));
		assertEquals(true, getAccountResponse.getBody().jsonPath().get("needToUpdate"));
	}

	@After
	public void tearDown() {
		externalServices.stop();
	}

	public static String returnStringFromFile(String testDir, String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get("src/" + testDir + "/resources/" + fileName)));
	}
}
