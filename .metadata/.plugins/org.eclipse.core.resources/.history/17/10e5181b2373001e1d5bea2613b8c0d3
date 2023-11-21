package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@ActiveProfiles("Test")
@Sql(scripts = {"classpath:migrations/V1.0__Jeep_Schema.sql",
		"classpath:flyaway/migrations/V1.0__Jeep_Schema.sql"},
		config = @SqlConfig(encoding = "utf-8"))
public class FetchJeepTest extends FetchJeepTestSupport {

	
	
	
	
	@Test
	void testThatJeepsAreReturnedWhenValidModelAndTrimAreSupplied() {
		
		JeepModel model = JeepModel.WRANGLER;
		String trim = "sport"; 
		String uri = 
				String.format("%s?model=%strim=%s", getBaseUri(), model, trim);
		
		
		ResponseEntity<List<Jeep>> response =
			getRestTemplate().exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
		
		//And: the actual list returned is the same as the expected list
		List<Jeep> expected = buildExpected();
		assertThat(response.getBody()).isEqualTo(expected);
		
		
		
	}
}
