package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.messages WHERE id = " + id);
        if (resultSet.next()) {
            User user = findUserById(resultSet.getLong(2), connection);
            Chatroom chatroom = findRoomById(resultSet.getLong(3), connection);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
            Optional<Message> message = Optional.of(new Message(resultSet.getLong("id"), user, chatroom, resultSet.getString("message"), LocalDateTime.parse(resultSet.getString("date"), formatter)));
            return message;
        }
        return Optional.empty();
    }

    private User findUserById(Long id, Connection connection) throws SQLException {
        Statement userStatement = connection.createStatement();
        ResultSet userSet = userStatement.executeQuery("SELECT * FROM chat.users WHERE id = " + id);
        userSet.next();
        return new User(userSet.getInt(1), userSet.getString(2), userSet.getString(3), null, null);
    }

    private Chatroom findRoomById(Long id, Connection connection) throws SQLException {
        Statement roomStatement = connection.createStatement();
        ResultSet roomSet = roomStatement.executeQuery("SELECT * FROM chat.rooms WHERE id = " + id);
        roomSet.next();
        return new Chatroom(roomSet.getInt(1), roomSet.getString(2), null, null);
    }

    @Override
    public void save(Message message) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into chat.messages (author, room, message) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getText());
        if (statement.executeUpdate() == 0) {
            throw new NotSavedSubEntityException("Creating message failed, incorrect identifiers");
        }
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            message.setId(resultSet.getLong(1));
        } else {
            throw new SQLException("Failed to retrieve generated keys for message");
        }
    }

}
