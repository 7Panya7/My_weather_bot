package ru.telegram.myweather.config.bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Getter
@Setter
public class TelegramBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;
    private TelegramFacade telegramFacade;


    public TelegramBot(DefaultBotOptions options, TelegramFacade telegramFacade) {
        super(options);
        this.telegramFacade = telegramFacade;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Optional<BotApiMethod<?>> updateOptional = telegramFacade.handleUpdate(update);
        return updateOptional.orElse(new SendMessage());
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
