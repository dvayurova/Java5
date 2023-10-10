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
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        DataSource dataSource = new HikariDataSource(config);

        MessagesRepositoryJdbcImpl repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = null;
        try {
            messageOptional = repositoryJdbc.findById(1L);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!messageOptional.isPresent()) {
            return;
        }
        Message message = messageOptional.get();
        message.setText("Bye!");
        message.setTime(null);
        try {
            repositoryJdbc.update(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(repositoryJdbc.findById(1L).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
