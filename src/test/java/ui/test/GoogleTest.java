package ui.test;

import ui.main.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import ui.page.MainSearchGooglePage;

/**
 * Тесты Google
 */
public class GoogleTest extends BaseTest {

    private static final String BASE_URL = "https://www.google.com/";

    /**
     * Зайти на страницу google, ввести wikipedia,
     * Убедиться, что для страницы википедии отображается заданный заголовок
     */
    @Test
    public void checkGoogleSearch() {
        Assert.assertEquals("Текст заголовка неверный",
                "Википедия — свободная энциклопедия", new MainSearchGooglePage(BASE_URL)
                        .inputTextAndSearchInGoogle("wikipedia")
                        .returnHeaderOfWikipedia());
    }
}
