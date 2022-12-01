package api;

import api.helper.Request;
import api.pojo.reqres.getusers.response.DataItem;
import api.pojo.reqres.getusers.response.ResponseGetUsers;
import api.pojo.reqres.registration.request.RequestRegistration;
import api.pojo.reqres.registration.response.ResponseRegistration;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ConfProperties;
import utils.dbase.JdbcConnector;
import utils.dbase.SqlQueries;

import java.util.List;
import java.util.Map;

/**
 * API тесты для сервиса reqres.in
 */
public class ReqresTest {

    public static final String BASE_URL = ConfProperties.getProperty("reqres_base_url");
    Request request = new Request();

    /**
     * Выполнить get запрос https://reqres.in/api/users?page=2
     * Проверить, что avatar в массиве data содержит id
     */
    @Test
    @Owner("Kasimov Damir")
    @Description("Делаем GET запрос и проверяем поле avatar в каждом DataItem")
    public void checkAvatarIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
        List<DataItem> listOfData =
                request.getRequest("users?page=2")
                        .as(ResponseGetUsers.class, ObjectMapperType.GSON)
                        .getData();
        listOfData.forEach(x -> Assertions
                .assertTrue(x.getAvatar().contains(x.getId() + "-image.jpg")));
    }

    /**
     * Отправить POST запрос https://reqres.in/api/register и проверить поля ответа.
     */
    @Test
    @Owner("Kasimov Damir")
    @Description("Делаем POST запрос успешной регистрации")
    public void successfulRegistrationTest() {
        RequestRegistration registrationBody = new RequestRegistration();
        registrationBody.setEmail("eve.holt@reqres.in");//заполняем поле Email для body
        registrationBody.setPassword("pistol");//заполняем поле Password для body
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
        ResponseRegistration registration =
                request.postRequest("register", registrationBody).as(ResponseRegistration.class, ObjectMapperType.GSON);
        Assertions.assertEquals(registration.getId(), 5);
        Assertions.assertEquals(registration.getToken(), "QpwL5tke4Pnpja7X4");
        //Assert.assertEquals(registration.getError(), "Missing password");
    }

    /**
     * Якобы мы сделали API запрос и нужно проверить изменения в БД
     */
    @Test
    @Owner("Kasimov Damir")
    @Description("Проверяем запись в таблице с name=Alice")
    public void checkNewUserInDataBaseTest() {
        List<Map<String, Object>> firstNames = JdbcConnector.getJdbcTemplate()
                .queryForList(SqlQueries.SELECT_NAME_WHERE_AGE_IS_30_IN_DB);
        Assertions.assertTrue(firstNames.size() == 1);
        Assertions.assertEquals("Alice", firstNames.get(0).get("name"));
    }

}