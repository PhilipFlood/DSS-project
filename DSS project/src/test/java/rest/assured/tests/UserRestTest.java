//package rest.assured.tests;
//
//import static com.jayway.restassured.RestAssured.given;
//import static com.jayway.restassured.RestAssured.when;
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.*;
//
//import org.jboss.arquillian.junit.InSequence;
//import org.junit.Test;
//
//public class UserRestTest {
//
//	@Test
//	@InSequence(1)
//	public void retrieveUser() {
//		when().get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/allUsers").then().statusCode(200);
//
//	}
//
//	@Test
//	@InSequence(2)
//	public void searchUser() {
//		String myJson = "\"admin\"";
//		given().contentType("application/json").body(myJson).when()
//				.post("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/search").then().statusCode(200)
//				.assertThat().body("username", equalTo("admin"));
//
//	}
//
//	@Test
//	@InSequence(3)
//	public void verifyUser() {
//		String myJson = "\"admin root123\"";
//		given().contentType("application/json").body(myJson).when()
//				.post("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/authenticate").then()
//				.statusCode(200).assertThat().body("username", equalTo("admin"));
//
//	}
//
//	@Test
//	@InSequence(4)
//	public void addUser() {
//		String myJson = "\"abcde root123 2\"";
//		given().contentType("application/json").body(myJson).when()
//				.post("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/adduser").then().statusCode(200)
//				.assertThat().body("username", equalTo("abcde"));
//
//	}
//
//	@Test
//	@InSequence(4)
//	public void editUser() {
//		String myJson = "\"abcde fgh root123 2\"";
//		given().contentType("application/json").body(myJson).when()
//				.post("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/edituser").then().statusCode(200)
//				.assertThat().body("username", equalTo("fgh"));
//
//	}
//
//	@Test
//	@InSequence(6)
//	public void deleteUser() {
//		String myJson = "\"fgh\"";
//		given().contentType("application/json").body(myJson).when()
//				.post("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/users/deleteuser").then().statusCode(200)
//				.assertThat().body("username", equalTo("fgh"));
//
//	}
//}
