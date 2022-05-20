package ru.telegram.myweather.config.bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Getter
@Setter
@Configuration
@PropertySource("classpath:telegram.properties")
@ConfigurationProperties(prefix = "telegrambot")
public class TelegramBotConfig {
    @Value("${telegrambot.webHookPath}")
    private String webHookPath;
    @Value("${telegrambot.botUserName}")
    private String botUserName;
    @Value("${telegrambot.botToken}")
    private String botToken;

    @Bean
    public TelegramBot myWeatherTelegramBot(TelegramFacade telegramFacade) {
        DefaultBotOptions options = new DefaultBotOptions();
        TelegramBot mySuperTelegramBot = new TelegramBot(options,telegramFacade);
        mySuperTelegramBot.setWebHookPath(webHookPath);
        mySuperTelegramBot.setBotUserName(botUserName);
        mySuperTelegramBot.setBotToken(botToken);
        return mySuperTelegramBot;
    }
}
