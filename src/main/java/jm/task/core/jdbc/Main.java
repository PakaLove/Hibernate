package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Dmitriy","Sidoroff",(byte)32);
        userDaoHibernate.saveUser("Alexey","Grey",(byte)20);
        userDaoHibernate.saveUser("Vasiliy","Poopkin",(byte)22);
        userDaoHibernate.saveUser("Ivan","Paskal",(byte)40);

        for (User user : userDaoHibernate.getAllUsers()){
            System.out.println(user);
        }
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
