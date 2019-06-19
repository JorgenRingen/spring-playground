package org.springplayground.samples.contenttypenegotiation.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDefaultContentType() throws Exception {

        ResponseEntity<String> companyResponse = testRestTemplate.getForEntity("/companies", String.class);
        assertThat(companyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode root = objectMapper.readTree(companyResponse.getBody());
        assertThat(root.get(0).get("name").textValue()).isNotNull();
        assertThat(root.get(0).get("founded").textValue()).isNotNull();
    }

    @Test
    void testMinimalContentType() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/vnd.companies.api.v1.company-minimal+json");

        ResponseEntity<String> companyMinimalResponse = testRestTemplate.exchange("/companies", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        assertThat(companyMinimalResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode root = objectMapper.readTree(companyMinimalResponse.getBody());
        assertThat(root.get(0).get("name").textValue()).isNotNull();
        assertThat(root.get(0).get("founded")).isNull(); // minimal doesn't include the "founded" property
    }
}