package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

public class ApiBase {
    // todo - создаю переменную как в Postmann,что б потом переедать их в автоматических настройках разово
    final String BASE_URI = "https://demoqa.com";
    final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlRvbGlrIiwicGFzc3dvcmQiOiJTaWRvcmVua28xMjMkIiwiaWF0IjoxNjg0MzEyNDk3fQ.amyQ3qjxtBgVRoANYRsv5hwcKGtNMCyHbPWUxlpyVXM";

    RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", API_KEY)  // обращать внимание, что написано в сваггере в авторизации "Authorization" -
                                                            // там может быть другое имя например  "Access-Token"
            .build();

    public Response getRequest(String endPoint, Integer responseCode) {
        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response getRequestWithParam(String endPoint, Integer responseCode, String paramName, String id) {
        Response response = RestAssured.given()
                .spec(specification)
                .pathParam(paramName, id)
                .when()
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response postRequest(String endPoint, Integer responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response putRequest(String endPoint, Integer responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    public Response deleteRequest(String endPoint, Integer responseCode, String id) {
        Response response = RestAssured.given()
                .spec(specification)
                .pathParam("id", id)
                .when()
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }


}
