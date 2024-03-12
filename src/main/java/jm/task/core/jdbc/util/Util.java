package jm.task.core.jdbc.util;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceRegistryAwareService;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/katauser";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rar123456";

    private Driver driver;
    private SessionFactory sessionFactory;

    public Util() {

        try {
            Configuration con = new Configuration();
            con.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            con.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/katauser");
            con.setProperty("hibernate.connection.username", "root");
            con.setProperty("hibernate.connection.password", "rar123456");

            con.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            con.setProperty("hibernate.hbm2ddl.auto", "update");

            con.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sessionFactory = con.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
