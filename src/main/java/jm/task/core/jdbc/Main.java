package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Петя", "Петров", (byte) 15);

        userService.getAllUsers();

        userService.removeUserById(1);

        userService.saveUser("Петя", "Петров", (byte) 15);
        userService.cleanUsersTable();


    }

}
