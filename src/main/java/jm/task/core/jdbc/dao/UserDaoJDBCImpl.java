package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT," +
                    " name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL," +
                    " age TINYINT, PRIMARY KEY (id))");
            System.out.println("Создание таблицы прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Удаление таблицы прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  users(name, lastName,age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных", name);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Удаление пользователя прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")){
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("LastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
                System.out.println("Получены данные из БД");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement  = connection.prepareStatement("TRUNCATE  users")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}