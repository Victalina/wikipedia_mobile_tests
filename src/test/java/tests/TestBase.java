package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

  @BeforeAll
  static void setup() {
    if (System.getProperty("deviceHost").equals("browserstack")) {
      Configuration.browser = BrowserstackDriver.class.getName();
    } else if ((System.getProperty("deviceHost").equals("local"))) {
      Configuration.browser = LocalDriver.class.getName();
    } else {
      throw new RuntimeException("No such deviceHost");
    }
    Configuration.browserSize = null;
    Configuration.timeout = 30000;
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    open();
  }

  @AfterEach
  void addAttachments() {
    switch (System.getProperty("deviceHost")) {
      case "browserstack": {
        String sessionId = Selenide.sessionId().toString();
        Attach.pageSource();
        closeWebDriver();
        Attach.addVideo(sessionId);
        break;
      }
      case "local": {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();
        break;
      }
      default: {
        throw new RuntimeException("No such deviceHost");
      }
    }
  }
}
