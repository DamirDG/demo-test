package ui.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница с результатами поиска Google
 */
public class SearchResultGooglePage {

    private final SelenideElement headerOfResultWikipedia = $x("//a[contains(@href, 'https://ru.wikipedia.org')]//h3");

    /**
     * Возвращаем текст заголовка Википедии
     *
     * @return текст элемента headerOfResultWikipedia
     */
    @Step("Возвращаем текст заголовка Википедии")
    public String returnHeaderOfWikipedia() {
        return headerOfResultWikipedia.getText();
    }
}
