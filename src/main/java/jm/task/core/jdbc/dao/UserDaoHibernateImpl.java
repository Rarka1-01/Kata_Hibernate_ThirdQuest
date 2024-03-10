package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name LONGTEXT, lastName LONGTEXT, age SMALLINT)";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS users";
    private final Util util;

    public UserDaoHibernateImpl() {
        this.util = new Util();
    }


    @Override
    public void createUsersTable() {
        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(CREATE_USER_TABLE)){
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try(PreparedStatement preparedStatement = util.getConnection().prepareStatement(DROP_USER_TABLE)){
            preparedStatement.execute();
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session s = util.getSessionFactory().openSession()) {
            s.beginTransaction();
            s.persist(new User(name, lastName, age));
            s.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session s = util.getSessionFactory().openSession()) {

            s.beginTransaction();
            User removeUser = s.get(User.class, id);

            if (removeUser == null)
                throw new Exception("Object null");

            s.delete(removeUser);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session s = util.getSessionFactory().openSession()) {

            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);

            users = s.createQuery(criteria).list();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session s = util.getSessionFactory().openSession()) {

            List<User> users = this.getAllUsers();

            if (users == null)
                throw new Exception("List is null");

            s.beginTransaction();

            for (User i : users) {
                s.delete(i);
            }

            s.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
