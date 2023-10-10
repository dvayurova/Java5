package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(rooms, user.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, rooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", rooms=" + rooms +
                '}';
    }
}
