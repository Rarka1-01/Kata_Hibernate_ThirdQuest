package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao uD;

    public UserServiceImpl() {
        uD = new UserDaoHibernateImpl();
    }

    @Override
    public void createUsersTable() {
        uD.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        uD.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        uD.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        uD.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return uD.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        uD.cleanUsersTable();
    }
}
