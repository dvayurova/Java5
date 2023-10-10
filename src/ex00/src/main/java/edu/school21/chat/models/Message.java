package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Integer id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, time);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
