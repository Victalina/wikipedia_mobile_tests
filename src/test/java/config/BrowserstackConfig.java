package config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:browserstack.properties"
})

public interface BrowserstackConfig extends Config {

  @Key("os")
  String getOs();

  @Key("device")
  String getDevice();

  @Key("os_version")
  String getOsVersion();

  @Key("app")
  String getApp();

  @Key("remoteUrl")
  URL getRemoteUrl();

  @Key("project")
  String getProject();

  @Key("build")
  String getBuild();

  @Key("name")
  String getName();
}
