package restassured;

import io.restassured.RestAssured;
import org.testng.annotations.*;
import utils.ConfigReader;

public class BaseTest {

	@BeforeSuite
	public void setup() {
		RestAssured.baseURI = ConfigReader.getProperty("baseURI");
	}

}