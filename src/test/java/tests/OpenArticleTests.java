package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.xpath;

@Tags({
        @Tag("all_android_tests"),
        @Tag("open_article_tests")
})
@DisplayName("Проверка открытия статьи")
@Owner("Victalina")
@Epic("Мобильное приложение Wikipedia")
@Story("Открытие статьи")
@Feature("Открытие статьи")
public class OpenArticleTests extends TestBase {

  @Test
  @DisplayName("Проверка отрытие статьи через поиск")
  @Severity(SeverityLevel.CRITICAL)
  void openArticleTest() {

    step("Ввести ключевое слово в поиск", () -> {
      back();
      $(accessibilityId("Search Wikipedia")).click();
      $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Github");
    });
    step("Открыть первую статью в результатах поиска", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .first().click());
    step("Закрыть поп-ап предложения игр", () ->
            $(id("org.wikipedia.alpha:id/closeButton")).click());
    step("Проверить открытую статью", () -> {
      $(xpath("//android.view.View[@resource-id='pcs']")).shouldBe(visible);
      $(xpath("//android.view.View[@text='GitHub']")).shouldBe(visible);
    });
  }
}
