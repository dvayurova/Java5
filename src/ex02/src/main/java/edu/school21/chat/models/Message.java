package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime time;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime time) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.time = time;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "Message : {" + "\n" +
                "id=" + id + ", \n" +
                "author=" + author + ", \n" +
                "room=" + room +", \n" +
                "text=\"" + text + "\", \n" +
                "dateTime=" +  time.format(formatter) + "\n" +
                '}';
    }
}
