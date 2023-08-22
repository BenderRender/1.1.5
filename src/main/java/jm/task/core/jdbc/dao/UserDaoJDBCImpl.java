package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate( "CREATE TABLE IF NOT EXISTS Users("
                    + "user_id INTEGER NOT NULL AUTO_INCREMENT" + ","
                    + "name VARCHAR(30) NOT NULL" + ","
                    + "lastname VARCHAR(30) NOT NULL" + ","
                    + "age INTEGER NOT NULL" + ","
                    + "PRIMARY KEY (user_id) " + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.getConnection().createStatement()) {
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement("INSERT INTO Users(name,lastname,age) VALUES (?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnection().createStatement()){
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement("DELETE FROM Users WHERE user_id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
