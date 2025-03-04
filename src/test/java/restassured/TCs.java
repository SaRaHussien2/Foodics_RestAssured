package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TCs extends BaseTest {

    /**
     * Test Case 1: Create a new user with valid data
     * Verifies that the user is successfully created and returned with the expected details.
     */
    @Test(priority = 1)
    public void createUserType() throws JsonProcessingException {
        // Create user payload with test data
        Payload user = new Payload(TestData.userName, TestData.userJob);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        // Send POST request to create a new user
        Response response = given()
                .header(Constants.contentTypeHeader, Constants.applicationTypeValue)
                .body(requestBody)
                .post(Constants.UserAPIEndpoint);

        // Extract and store the new user ID for future tests
        String newUserID = response.then().extract().path("id");
        TestData.setUserId(newUserID);

        // Print request and response details for debugging
        System.out.println("Request: " + requestBody);
        System.out.println("Response: " + response.prettyPrint());

        // Validate response status and returned data
        Assert.assertEquals(response.getStatusCode(), 201, "User creation failed");
        Assert.assertNotNull(response.jsonPath().getString("id"), "User ID is null");
        Assert.assertEquals(response.jsonPath().getString("name"), user.getName(), "User name mismatch");
        Assert.assertEquals(response.jsonPath().getString("job"), user.getJob(), "User job mismatch");
    }

    /**
     * Test Case 2: Validate error handling for creating a user with empty data
     * Ensures that the API returns a 400 status code when required fields are missing.
     */
    @Test(priority = 2)
    public void errorHandlingUserWithEmptyData() throws JsonProcessingException {
        Payload user = new Payload("", ""); // Empty user details
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        // Send POST request with empty data
        Response response = given()
                .header(Constants.contentTypeHeader, Constants.applicationTypeValue)
                .body(requestBody)
                .post(Constants.UserAPIEndpoint);

        // Print request and response details
        System.out.println("Request: " + requestBody);
        System.out.println("Response: " + response.prettyPrint());

        // Validate error response
        Assert.assertEquals(response.getStatusCode(), 400, "Expected error for missing required parameters");
    }

    /**
     * Test Case 3: Retrieve user data
     * Validates that the API returns correct user details.
     */
    @Test(priority = 3)
    public void getUserData() {
        // Fetch user data using a pre-existing user ID due to system issue (new users not being saved)
        Response response = RestAssured.get(Constants.UserAPIEndpoint + "/" + TestData.userID);
        
        // Validate response status and user details
        Assert.assertEquals(response.getStatusCode(), 200, "User not found");
        Assert.assertEquals(response.jsonPath().get("data.id").toString(), "2", "User ID mismatch");
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet", "First name mismatch");
        
        // Print response body
        System.out.println(response.body().prettyPrint());
    }

    /**
     * Test Case 4: Validate error handling for a non-existing user
     * Ensures that querying a non-existent user returns a 404 error.
     */
    @Test(priority = 4)
    public void errorHandlingNonExistingUser() {
        Response response = RestAssured.get("/api/users/200"); // Invalid user ID
        
        // Validate response status
        Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 for non-existent user");
    }

    /**
     * Test Case 5: Update an existing user's details
     * Verifies that user information is successfully updated and reflects the new data.
     */
    @Test(priority = 5)
    public void updateUserTest() throws JsonProcessingException {
        // Create updated user payload
        Payload user = new Payload(TestData.updatedUserName, TestData.updatedUserJob);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        // Send PUT request to update the user
        Response response = given()
                .header(Constants.contentTypeHeader, Constants.applicationTypeValue)
                .body(requestBody)
                .put(Constants.UserAPIEndpoint + "/" + TestData.getUserId());

        // Extract new user ID and store it
        String newUserID = response.then().extract().path("id");
        TestData.setUserId(newUserID);

        // Print request and response details
        System.out.println("UserId: " + TestData.getUserId());
        System.out.println("Request: " + requestBody);
        System.out.println("Response: " + response.prettyPrint());

        // Validate response status and updated data
        Assert.assertEquals(response.getStatusCode(), 200, "User update failed");
        Assert.assertEquals(response.jsonPath().getString("name"), user.getName(), "User name not updated");
        Assert.assertEquals(response.jsonPath().getString("job"), user.getJob(), "User job not updated");
    }
}
