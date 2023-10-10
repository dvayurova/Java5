package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> rooms;

    public User(Integer id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void addCreatedRoom(Chatroom room) {
        if (createdRooms == null) {
            createdRooms = new ArrayList<>();
        }
        createdRooms.add(room);
    }

    public void addRoom(Chatroom room) {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        rooms.add(room);
    }

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
        StringBuilder stringCreatedRooms = new StringBuilder();
        if (createdRooms == null) {
            stringCreatedRooms.append("createdRooms = null");
        } else {
            stringCreatedRooms.append("createdRooms: ");
            for (Chatroom rooms : createdRooms) {
                stringCreatedRooms.append(rooms.toString() + "\n");
            }
        }
        StringBuilder stringRooms = new StringBuilder();
        if (rooms == null) {
            stringRooms.append("rooms = null");
        } else {
            stringRooms.append("rooms: ");
            for (Chatroom rooms : rooms) {
                stringRooms.append(rooms.toString() + "\n");
            }
        }

        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + stringCreatedRooms.toString() +
                ", rooms=" + stringRooms.toString() +
                '}';
    }
}
