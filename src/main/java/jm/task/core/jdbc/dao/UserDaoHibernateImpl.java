package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Statement;
import java.util.List;
/*public List<User> index() {
        List<User> users = new ArrayList<>();
        try {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM user";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastname"));
        user.setAge(resultSet.getByte("age"));

        users.add(user);
        }

        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        return users;
        }

public void save(User user) {

        try {
        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO user VALUES(" + 1 + ",'" + user.getId() + "'" + "'" + user.getName() + "'" + user.getLastName() + "'" + user.getAge() + "')";

        statement.executeUpdate(SQL);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }*/

public class UserDaoHibernateImpl implements UserDao {

    

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
