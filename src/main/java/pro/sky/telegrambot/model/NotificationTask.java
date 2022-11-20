package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "task")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long chatid;
    private String messagetext;
    private LocalDateTime datatime;

    @Override
    public String toString() {
        return messagetext + "  " + datatime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getChatid() {
        return chatid;
    }

    public void setChatid(Long chatId) {
        this.chatid = chatId;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messageText) {
        this.messagetext = messageText;
    }

    public LocalDateTime getDatatime() {
        return datatime;
    }

    public void setDatatime(LocalDateTime dataTime) {
        this.datatime = dataTime;
    }
}
