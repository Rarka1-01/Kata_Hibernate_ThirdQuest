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

        List<User> userList = new ArrayList<>();

        userList.add(new User("Egor", "Evgenivich", (byte) 8));
        userList.add(new User("Artem", "Egorovich", (byte) 9));
        userList.add(new User("Maxim", "Atemovich", (byte) 10));
        userList.add(new User("Evgeniy", "Maximovich", (byte) 11));

        UserServiceImpl uS = new UserServiceImpl();

        uS.createUsersTable();

        for (User user : userList) {
            uS.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User " + user.getName() + " successfully added to database");
        }

        System.out.println("\n\n");

        List<User> users = uS.getAllUsers();

        for (User user : users) {
            System.out.println(user + "\n");
        }

        uS.cleanUsersTable();
        uS.dropUsersTable();

    }
}
