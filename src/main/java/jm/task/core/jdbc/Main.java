package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDaoHibernateImpl uD = new UserDaoHibernateImpl();

/*        uD.saveUser("Egor", "Egorovich", (byte)10);
        uD.saveUser("Egor", "Egorovich", (byte)11);
        uD.saveUser("Egor", "Egorovich", (byte)12);
        uD.saveUser("Egor", "Egorovich", (byte)13);

        uD.cleanUsersTable();

        List<User> users = uD.getAllUsers();

        for(User user: users){
            System.out.println(user + "\n");
        }*/
    }
}
