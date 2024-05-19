package com.example.hs.bot;

import com.example.hs.botconfig.HSBotConfig;
import com.example.hs.service.HSDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@AllArgsConstructor
public class HSBot extends TelegramLongPollingBot {

  private final HSBotConfig botConfig;

  private final HSDataService hsDataService;

  private final BlockingQueue<Message> receiveQueue = new LinkedBlockingQueue<>();

  @Override
  public String getBotUsername() {
    return null;
  }

  @Override
  public String getBotToken() {
    return botConfig.getToken();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      receiveQueue.add(update.getMessage());
    }
  }

  public void sendMessage() {
    SendMessage sendMessage = new SendMessage();
    try {
      Message message = receiveQueue.take();
      var textToSend = hsDataService.readData();
      sendMessage.setChatId(String.valueOf(message.getChatId()));
      sendMessage.setText(textToSend);
      execute(sendMessage);
    } catch (TelegramApiException | InterruptedException | IOException ignored) {
    }
  }

  @Override
  public void onUpdatesReceived(List<Update> updates) {
    super.onUpdatesReceived(updates);
  }
}
