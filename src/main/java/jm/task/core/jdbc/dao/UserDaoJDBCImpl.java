package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name LONGTEXT, lastName LONGTEXT, age SMALLINT)";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS users";
    private static final String INSERT_USER = "INSERT users(name, lastName, age) VALUES(?, ?, ?)";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String REMOVE_ALL_USERS = "DELETE FROM users";

    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    @Override
    public void createUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(CREATE_USER_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(DROP_USER_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(REMOVE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(GET_ALL_USERS)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("lastName"), result.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(REMOVE_ALL_USERS)) {
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
