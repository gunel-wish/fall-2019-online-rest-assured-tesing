package com.automation.tests.day4;

import com.automation.tests.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class ORDSTestsDay4 {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ORDS.URI");
    }
    /**
     * Warm-up!
     * Given accept type is JSON
     * When users sends a GET request to "/employees"
     * Then status code is 200
     * And Content type is application/json
     * And response time is less than 3 seconds
     */
    @Test
    @DisplayName("Verify status code, content type and response time")
    public void employeesTest1(){
        given().
                accept(ContentType.JSON).
                when().
                get("/employees").prettyPeek().
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                time(lessThan(3L), TimeUnit.SECONDS);
    }
    /**
     *
     Given accept type is JSON
     And parameters: q = {"country_id":"US"}
     When users sends a GET request to "/countries"
     Then status code is 200
     And Content type is application/json
     *
     */
    @Test
    @DisplayName("Verify country name, content type and status code for country with ID US")
    public  void verifyCountriesTest1(){
        given().accept(ContentType.JSON).
                queryParam("q" , "{\"country_id\":\"US\"}").
                when().
                     get("/countries").prettyPeek().
                then().assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                body("items[0].country_name",is("United States of America"));

        Response response = given().
                             accept(ContentType.JSON).
                            when().
                              get("/countries").prettyPeek();


    }

















}
