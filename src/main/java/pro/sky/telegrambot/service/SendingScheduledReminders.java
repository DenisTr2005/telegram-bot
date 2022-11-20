package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;

import java.util.List;

@Service
@EnableScheduling
public class SendingScheduledReminders {
    private final NotificationTaskService notificationTaskService;
    private final TelegramBot telegramBot;

    public SendingScheduledReminders(NotificationTaskService notificationTaskService, TelegramBot telegramBot) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void notification() {
        List<NotificationTask> listOfTask = notificationTaskService.getNotification();
        if (!listOfTask.isEmpty()) {
            listOfTask.stream().forEach(n-> {
                SendMessage message = new SendMessage(n.getChatid(), n.getMessagetext());
                telegramBot.execute(message);});
        }
    }
}
