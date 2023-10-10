package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = getPreparedStatement(connection, page, size);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), null, null);
                    Chatroom room = new Chatroom(resultSet.getInt("room_id"), resultSet.getString("room_name"), user, null);
                    if (resultSet.getString("status").equals("owner")) {
                        user.addCreatedRoom(room);
                    } else {
                        user.addRoom(room);
                    }
                    users.add(user);
                }
            }
        }
        return users;
    }

    private PreparedStatement getPreparedStatement(Connection connection, int page, int size) throws SQLException {
        String query = "WITH r AS (\n" +
                "    SELECT chat.rooms.id   AS room_id,\n" +
                "           chat.rooms.name AS room_name,\n" +
                "           owner,\n" +
                "           author\n" +
                "    FROM chat.rooms\n" +
                "             JOIN chat.messages ON rooms.id = messages.room\n" +
                ")\n" +
                "SELECT u.id,\n" +
                "       u.login,\n" +
                "       u.password,\n" +
                "       CASE\n" +
                "           WHEN r.owner = u.id THEN 'owner'\n" +
                "           WHEN r.author = u.id THEN 'author'\n" +
                "           ELSE 'other'\n" +
                "           END AS status,\n" +
                "    r.room_id, r.room_name\n" +
                "FROM chat.users u\n" +
                "         JOIN r ON u.id = r.owner OR u.id = r.author LIMIT ? OFFSET  ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, size);
        statement.setInt(2, page * size);
        return statement;
    }

}
