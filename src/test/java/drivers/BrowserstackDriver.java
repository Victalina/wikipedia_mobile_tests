package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;

public class BrowserstackDriver implements WebDriverProvider {

  private static final BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    MutableCapabilities caps = new MutableCapabilities();

    caps.setCapability("browserstack.user", System.getProperty("login"));
    caps.setCapability("browserstack.key", System.getProperty("key"));
    caps.setCapability("app", config.getApp());
    caps.setCapability("os", config.getOs());
    caps.setCapability("device", config.getDevice());
    caps.setCapability("os_version", config.getOsVersion());
    caps.setCapability("project", config.getProject());
    caps.setCapability("build", config.getBuild());
    caps.setCapability("name", config.getName());

    return new RemoteWebDriver(
            config.getRemoteUrl(), caps);
  }
}
