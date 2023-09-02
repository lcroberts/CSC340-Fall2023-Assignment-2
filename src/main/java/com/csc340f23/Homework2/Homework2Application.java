package com.csc340f23.Homework2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Homework2Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework2Application.class, args);
		apiCall();
		System.exit(0);
	}

	public static void apiCall() {
		try {
			// String apiRequest = "https://api.open-meteo.com/v1/forecast?latitude=36.0726&longitude=-79.792&hourly=temperature_2m&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&timezone=America%2FNew_York&forecast_days=1";
			String apiRequest = "https://api.open-meteo.com/v1/forecast?latitude=36.0726&longitude=-79.792&hourly=temperature_2m&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&forecast_days=1";
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();

			String result = restTemplate.getForObject(apiRequest, String.class);
			JsonNode root = mapper.readTree(result);

			String temp = root.findValue("temperature").asText();
			String windSpeed = root.findValue("windspeed").asText();

			System.out.printf("Right now the temperature in Greensboro is %s degrees fahrenheit with winds of %s miles per hour.\n",
					temp, windSpeed);

		} catch (JsonProcessingException ex) {
			System.out.println("Error in weather");
		}
	}
}
