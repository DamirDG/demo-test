package api.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

/**
 * Запросы Rest Assured
 */
public class Request {

    /**
     * GET запрос
     *
     * @param specification спецификауия запроса с параметрами
     * @param statusCode    код ответа, который мы ожидаем
     * @return возвращаем ResponseBody запроса
     */
    public ResponseBody getRequest(RequestSpecification specification, int statusCode) {
        Response response = RestAssured
                .given()
                .spec(specification)
                .get()
                .then()
                //.log().all()
                .statusCode(statusCode)
                .extract()
                .response();
        return response.getBody();
    }

    /**
     * POST запрос
     *
     * @param specification спецификауия запроса с параметрами
     * @param statusCode    код ответа, который мы ожидаем
     * @return возвращаем ResponseBody запроса
     */
    public ResponseBody postRequest(RequestSpecification specification, int statusCode) {
        Response response = RestAssured
                .given()
                .spec(specification)
                //.log().all()
                .post()
                .then()
                //.log().all()
                .statusCode(statusCode)
                .extract()
                .response();
        return response.getBody();
    }
}
