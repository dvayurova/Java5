package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        DataSource dataSource = new HikariDataSource(config);

        MessagesRepositoryJdbcImpl repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        User creator = new User(3, "user3", "password3", null, null);
        User author = creator;
        Chatroom room = new Chatroom(3, "third", creator, null);
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        try {
            repositoryJdbc.save(message);
            System.out.println(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
