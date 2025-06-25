package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:localEmulator.properties"
})

public interface LocalConfig extends Config {

  @Key("platformVersion")
  String getPlatformVersion();

  @Key("deviceName")
  String getDeviceName();
}
