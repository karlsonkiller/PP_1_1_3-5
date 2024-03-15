package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) {
        UserDaoHibernateImpl hib = new UserDaoHibernateImpl();

        hib.createUsersTable();

        hib.saveUser("Петя", "Петров", (byte) 15);

        hib.getAllUsers();

        hib.removeUserById(1);

        hib.saveUser("Петя", "Петров", (byte) 15);
        hib.cleanUsersTable();


    }

}
