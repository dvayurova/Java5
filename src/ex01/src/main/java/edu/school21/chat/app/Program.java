package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        DataSource dataSource = new HikariDataSource(config);

        MessagesRepositoryJdbcImpl repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        System.out.println("Enter a message ID");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        try {
            Message message = repositoryJdbc.findById(id).get();
            System.out.println(message);
        } catch (SQLException e) {
            System.err.println("Error: "+ e.getLocalizedMessage());
        }

    }
}
