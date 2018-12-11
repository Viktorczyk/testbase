package MyApp.RestController;


import MyApp.Model.Position;
import com.google.inject.internal.Nullable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.head;

public class TestRestController extends FunctionalTest{

    @Test
    public void basicPingTest(){

        given().when().get("headers").then().statusCode(200);
    }


   @Test
    public void invalidHeaders() {
        given().when().get("headers/4")
                .then().statusCode(200);
    }

    @Test
    public void invalidPosition() {
        given().pathParam("posId", 1)
                .when().get("position/{posId}")
                .then().statusCode(200);
       }

    @Test
    public void testDelete() {
        given().contentType("application/json").pathParam("posId", 5)
                .when().delete("pos/{posId}")
                .then().statusCode(200);

    }

    @Test
    public void testDeleteHeader() {
        given().contentType("application/json").pathParam("headId", 1)
                .when().delete("delHeaders/{headId}")
                .then().statusCode(200);

    }

    @Test
    public void testHeader(){
        Map<String, String> header = new HashMap<>();
        header.put("number", "22");
        header.put("description", "opisik");
        header.put("typHeader", "RW");

        given()
                .contentType("application/json")
                .body(header)
                .when().post("/rest/addheader")
                .then()
                .statusCode(200);

    }



}
