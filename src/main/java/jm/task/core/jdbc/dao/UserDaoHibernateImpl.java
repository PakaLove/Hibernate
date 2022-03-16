package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory sessionFactory = util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


        @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println(user);
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            User user1 = (User) session.get(User.class, id);
            session.delete(user1);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            for (User u : users) {
                System.out.println(u);
            }
            session.getTransaction().commit();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
