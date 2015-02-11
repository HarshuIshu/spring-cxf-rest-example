package com.idodevjobs.sample.service;

import com.idodevjobs.sample.model.ExampleModel;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
//@ContextConfiguration(locations = {"classpath:spring-cxf-rest-context.xml"})
public class ExampleServiceImplRestTest {

    private RestTemplate restTemplate = new RestTemplate();


    private static String URL = "http://localhost:8080/spring-cxf-rest-example/services/example/";

    @Test
    public void testCxfRestService() {
        ResponseEntity<ExampleModel> entity = restTemplate.getForEntity(URL + "2", ExampleModel.class);
        assertThat(entity.getStatusCode(), equalTo(OK));
    }

    @Test
    public void testWithRestassured() {
        given()
            .log().everything()
        .expect()
            .statusCode(HttpStatus.NOT_IMPLEMENTED.value())
            .log().all()
        .when()
            .get(URL + "1");
//            .log().all().when().get(URL+"1").then().assertThat().statusCode(HttpStatus.NOT_IMPLEMENTED.value());
    }
    @Test
    public void testPost() {
        given()
                .contentType(ContentType.JSON)
//          .header(acceptJson)
            .body("{\"string\":\"example\",\"anInt\":1001}")
            .log().everything()
//          .queryParam("id", 2)
        .expect()
            .statusCode(204)
//            .log().ifError()
            .log().all()
                .when()
        .post(URL);
    }
//    @Test
//    public void testCustomException2() {
//        ResponseEntity<Object> response = restTemplate.getForEntity(URL + "1", Object.class);
//        assertThat(response.getStatusCode(), is(HttpStatus.NOT_IMPLEMENTED));
//    }

    @Test
    public void testCustomException() {
        try {
            restTemplate.getForEntity(URL + "1", ExampleModel.class);
        } catch (HttpServerErrorException d) {
            assertThat(d.getStatusCode(), is(HttpStatus.NOT_IMPLEMENTED));
        }
    }
}