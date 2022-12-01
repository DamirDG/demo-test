package api.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

/**
 * Запросы Rest Assured
 */
public class Request {

    /**
     * GET запрос
     *
     * @param basePath ресурс к которому идет запрос
     * @return возвращаем ResponseBody запроса
     */
    public ResponseBody getRequest(String basePath) {
        Response response = RestAssured
                .given()
                //.log().all()
                .get(basePath)
                .then()
                //.log().all()
                .extract()
                .response();
        return response.getBody();
    }

    /**
     * POST запрос
     *
     * @param basePath ресурс к которому идет запрос
     * @param body     тело запроса
     * @return возвращаем ResponseBody запроса
     */
    public ResponseBody postRequest(String basePath, Object body) {
        Response response = RestAssured
                .given()
                //.log().all()
                .body(body)
                .post(basePath)
                .then()
                //.log().all()
                .extract()
                .response();
        return response.getBody();
    }
}
