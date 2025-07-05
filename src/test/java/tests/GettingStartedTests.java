package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tags({
        @Tag("all_android_tests"),
        @Tag("get_started_screens_tests")
})
@DisplayName("Проверка стартовых экранов приложения")
@Owner("Victalina")
@Epic("Мобильное приложение Wikipedia")
@Story("Стартовые экраны приложения")
@Feature("Стартовые экраны приложения")
public class GettingStartedTests extends TestBase {

  @Test
  @DisplayName("Пропуск стартовых экранов приложения по кнопке 'Skip'")
  @Severity(SeverityLevel.CRITICAL)
  void skipGettingStartedScreensTest() {

    step("Нажать на кнопку 'Skip' на стартовом экране приложения", () -> {
      $(id("org.wikipedia.alpha:id/primaryTextView"))
              .shouldHave(text("The Free Encyclopedia"));
      $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
    });
    step("Проверить отображение главной страницы приложения", () ->
            $(id("org.wikipedia.alpha:id/feed_view")).shouldBe(visible));
  }

  @Test
  @DisplayName("Пропуск стартовых экранов приложения по системной кнопке 'Назад'")
  @Severity(SeverityLevel.NORMAL)
  void skipByBackGettingStartedScreensTest() {

    step("Нажать на системную кнопку 'Назад' на стартовом экране приложения", () ->
            back());
    step("Проверить отображение главной страницы приложения", () ->
            $(id("org.wikipedia.alpha:id/feed_view")).shouldBe(visible));
  }

  @Test
  @DisplayName("Проверка стартовых экранов приложения")
  @Severity(SeverityLevel.NORMAL)
  void verifyGettingStartedScreensTest() {

    step("Проверить первый стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia")));
    step("Открыть второй стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
    step("Проверить второй стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore")));
    step("Открыть третий стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
    step("Проверить третий стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync")));
    step("Открыть четвертый стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
    step("Проверить четвертый стартовый экран приложения", () ->
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Data & Privacy")));
    step("Нажать на кнопку 'Get started'", () ->
            $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click());
    step("Проверить отображение главной страницы приложения", () ->
            $(id("org.wikipedia.alpha:id/feed_view")).shouldBe(visible));
  }
}
