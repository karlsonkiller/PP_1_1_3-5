package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(50), lastname VARCHAR(50), age INT(3))").executeUpdate();
            session.getTransaction();
        } catch (HibernateException ignore) {

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction();
        } catch (HibernateException ignore) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = this.sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            try {
                sessionFactory.openSession().getTransaction().rollback();
            } catch (HibernateException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            try {
                sessionFactory.openSession().getTransaction().rollback();
            } catch (HibernateException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        } catch (HibernateException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction();
        } catch (Exception ignore) {}
    }
}
