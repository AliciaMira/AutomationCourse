package testApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestDeApis {

    @Test
    public void loginTest(){
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                /*.extract()//La respuesta que trae el metodo lo extrae como un string
                .asString();*/
                .body("token", notNullValue());
    }
    @Test
    public void getSingleUserTest(){

        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2));//5

    }

    @Test
    public void create(){
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201);
    }



    @Test
    public void  patchRegistroTest(){
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("updatedAt", notNullValue());
    }

    @Test
    public void deleteRegistroTest(){
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

}
