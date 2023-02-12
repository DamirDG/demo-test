package ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.main.BaseTest;
import ui.main.TestListener;
import ui.page.MainSearchGooglePage;
import utils.ConfProperties;

/**
 * Тесты Google
 */

@DisplayName("UI tests for google.com")
@ExtendWith(TestListener.class)
public class GoogleTest extends BaseTest {

    private static final String BASE_URL = ConfProperties.getProperty("google_base_url");

    /**
     * Зайти на страницу google, ввести wikipedia,
     * Убедиться, что для страницы википедии отображается заданный заголовок
     */
    @Test()
    @DisplayName("Проверка заголовка wikipedia в google.com")
    @Owner("Kasimov Damir")
    @Description("Открываем google.com, ищем wikipedia, проверяем заголовок")
    public void checkGoogleSearchTest() {
        Assertions.assertEquals(
                "Википедия — свободная энциклопедия", new MainSearchGooglePage(BASE_URL)
                        .inputTextAndSearchInGoogle("википедия")
                        .returnHeaderOfWikipedia());
    }
}
