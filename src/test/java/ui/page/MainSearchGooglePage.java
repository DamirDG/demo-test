package ui.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница поиска Google
 */
public class MainSearchGooglePage {

    public MainSearchGooglePage(String url) {
        Selenide.open(url);
    }

    private final SelenideElement inputSearchField = $x("//input");//Локатор поля поиска на странице Google

    /**
     * Вводим в поле поиск заданный текст и нажимаем Enter
     *
     * @param text - текст, который вводим в поле поиска
     * @return - возвращаем SearchResultGooglePage. Страница результатов поиска.
     */
    @Step("Вводим в поле поиск заданный текст и нажимаем Enter")
    public SearchResultGooglePage inputTextAndSearchInGoogle(String text) {
        inputSearchField.sendKeys(text);
        inputSearchField.pressEnter();
        return new SearchResultGooglePage();
    }
}
