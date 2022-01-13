package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Statement statement = util.getConnection().createStatement();
    PreparedStatement preparedStatement = null;

    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("CREATE TABLE user (ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(50),LASTNAME VARCHAR (50), AGE INT)");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("DROP TABLE user");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("INSERT INTO user(name,lastname,age) VALUES(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE * FROM user WHERE id=?");

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> person = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            String SQL = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                person.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void cleanUsersTable() {
        final String SQL = "DELETE FROM user";
        try {
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
