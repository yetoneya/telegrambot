package com.example.hs.botconfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.yaml")
public class HSBotConfig {

  @Value("${bot.name}")
  String botName;

  @Value("${bot.token}")
  String token;
}
