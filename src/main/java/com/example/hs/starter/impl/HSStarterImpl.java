package com.example.hs.starter.impl;

import com.example.hs.bot.HSBot;
import com.example.hs.starter.HSStarter;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("hSStarter")
@AllArgsConstructor
public class HSStarterImpl implements HSStarter {

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  private final HSBot hsBot;

  @Override
  @EventListener(ApplicationReadyEvent.class)
  public void start() {
    Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook1") {
      @Override
      public void run() {
        executorService.shutdown();
      }
    });
    executorService.submit(hsBot::sendMessage);
  }
}
