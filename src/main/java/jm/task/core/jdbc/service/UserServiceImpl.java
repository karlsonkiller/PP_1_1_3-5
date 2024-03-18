package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoHibernateImpl userServ = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userServ.createUsersTable();
    }

    public void dropUsersTable() {
        userServ.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userServ.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userServ.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userServ.getAllUsers();
    }

    public void cleanUsersTable() {
        userServ.cleanUsersTable();
    }
}
