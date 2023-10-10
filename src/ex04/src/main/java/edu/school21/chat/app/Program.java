package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Program {
    public static void main(String[] args)  {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        DataSource dataSource = new HikariDataSource(config);

        UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> userList = null;
        try {
            userList = usersRepository.findAll(2, 3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Size of result list = " + userList.size());
        int i = 1;
        for(User user : userList){
            System.out.println(i + ". user: ");
            System.out.println(user);
            i++;
        }
    }

}
