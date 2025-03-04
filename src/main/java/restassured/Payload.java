package restassured;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

	@JsonProperty("name")
	private String name;

	@JsonProperty("job")
	private String job;

	public Payload(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return this.name;
	}

	public String getJob() {
		return this.job;
	}
}