package pro.sky.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.NotificationTaskService;

@SpringBootApplication
@EnableScheduling
public class TelegramBotApplication {
	@Autowired
	NotificationTaskService notificationTaskService;
	@Autowired
	private TelegramBot telegramBot;
	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

	}

	@Scheduled(cron = "0 0/1 * * * *")
	public void notification() {
		Long chatId = 5106040053L;
		String string = notificationTaskService.getNotification().toString();
		if (string != "[]") {
			SendMessage message = new SendMessage(chatId, notificationTaskService.getNotification().toString());
			telegramBot.execute(message);
		}
	}

}
