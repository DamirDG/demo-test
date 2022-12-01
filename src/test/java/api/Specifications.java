package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Спецификации для API запросов и ответов сервисов reqres
 */
public class Specifications {

    /**
     * Заполняем спецификацию запроса
     *
     * @param url - путь API запроса
     */
    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Заполняем спецификацию ответа
     *
     * @param expCode - код ответа, который мы ожидаем
     */
    public static ResponseSpecification responseSpec(int expCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expCode)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Инициализация параметров спецификаций для RestAssured
     *
     * @param request  - спецификация запроса
     * @param response - спецификация ответа
     */
    public static void installSpecification(RequestSpecification request
            , ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
