package api.test;

import api.helper.Request;
import api.pojo.reqres.getusers.response.DataItem;
import api.pojo.reqres.getusers.response.ResponseGetUsers;
import api.pojo.reqres.registration.request.RequestRegistration;
import api.pojo.reqres.registration.response.ResponseRegistration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import utils.dbase.DbConnector;
import utils.dbase.SqlQueries;

import java.sql.ResultSet;
import java.util.List;

/**
 * API тесты для сервиса reqres.in
 */
public class ReqresTest {

    private static final String BASE_URL = "https://reqres.in/api/";
    Request request = new Request();
    DbConnector jdbc = new DbConnector();
    RequestSpecification requestSpecification = RestAssured.given();

    /**
     * Выполнить get запрос https://reqres.in/api/users?page=2
     * Проверить, что avatar в массиве data содержит id
     */
    @Test
    public void checkAvatarId() {
        requestSpecification
                .baseUri(BASE_URL + "users")
                .queryParam("page", 2)
                .contentType(ContentType.JSON);
        List<DataItem> listOfData =
                request.getRequest(requestSpecification, 200)
                        .as(ResponseGetUsers.class, ObjectMapperType.GSON)
                        .getData();
        listOfData.forEach(x -> Assert.
                assertTrue("avatar не содержит  id-image.jpgg",
                        x.getAvatar().contains(x.getId() + "-image.jpg")));
    }

    /**
     * Отправить POST запрос https://reqres.in/api/register и проверить поля ответа.
     */
    @Test
    public void successfulRegistrationTest() {
        RequestRegistration registrationBody = new RequestRegistration();
        registrationBody.setEmail("eve.holt@reqres.in");
        registrationBody.setPassword("pistol");
        requestSpecification
                .baseUri(BASE_URL + "register")
                .contentType(ContentType.JSON)
                .body(registrationBody);
        ResponseRegistration registration =
                request.postRequest(requestSpecification, 200).as(ResponseRegistration.class, ObjectMapperType.GSON);
        Assert.assertEquals(registration.getId(), 4);
        Assert.assertEquals(registration.getToken(), "QpwL5tke4Pnpja7X4");
    }

    /**
     * Якобы мы сделали API запрос и нужно проверить изменения в БД
     */
    @Test
    public void checkNewUserInDataBase() throws Exception {
        ResultSet resultSet = jdbc.requestToDataBase(SqlQueries.SELECT_NAME_WHERE_AGE_IS_30_IN_DB);
        while (resultSet.next()) {
            Assert.assertEquals("Alice", resultSet.getString(1));
        }
    }

}
