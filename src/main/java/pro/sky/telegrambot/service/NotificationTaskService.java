package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {
    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public List<NotificationTask> getNotification() {
        return notificationTaskRepository.getByDatatime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

    public void create(Long chartId, String message) {
        Pattern pattern = Pattern.compile("([\\d\\.\\:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            String dataTime = matcher.group(1);
            String messageText = matcher.group(3);
            LocalDateTime localDateTime = LocalDateTime.parse(dataTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            NotificationTask notificationTask = new NotificationTask();
            notificationTask.setChatid(chartId);
            notificationTask.setMessagetext(messageText);
            notificationTask.setDatatime(localDateTime);

            notificationTaskRepository.save(notificationTask);
        }
    }

}
