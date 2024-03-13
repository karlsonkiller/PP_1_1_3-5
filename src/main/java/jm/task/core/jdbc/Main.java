package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();

        dao.saveUser("Паша1", "Павлов1", (byte) 1);
        dao.saveUser("Паша2", "Павлов2", (byte) 6);
        dao.saveUser("Паша3", "Павлов3", (byte) 65);
        dao.saveUser("Паша4", "Павлов4", (byte) 12);

        dao.getAllUsers();

        dao.cleanUsersTable();

        dao.dropUsersTable();


    }
}
