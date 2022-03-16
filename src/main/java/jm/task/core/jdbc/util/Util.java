package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/Connection1?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Paka1337";
    private final static String DRIVER = "com.mysql.jdbc.Driver";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Подключение к БД прошло успешно");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД");

        } return connection;
    }

    public SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = null;
        try{
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Connection1?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "Paka1337");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sessionFactory;
    }
}

