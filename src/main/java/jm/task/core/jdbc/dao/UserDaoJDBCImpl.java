package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    private static final Connection connection = Util.getConnection();


    private static final String SAVE = "INSERT INTO users (name, lastname, age) VALUES (?,?,?)";
    private static final String REMOVE = "DELETE FROM users WHERE id = ?";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("CREATE TABLE users " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(50), lastname VARCHAR(50), age INT(3))");

            System.out.println("Table created successfully.");
        } catch (Exception ignore) {

        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()){
            stmt.executeUpdate("DROP TABLE users");
            System.out.println("Base Drop.");
        } catch (Exception ignore) {

        }
    }

    public void saveUser(String person_name, String lastName, byte age) {
        try (PreparedStatement stmt = connection.prepareStatement(SAVE)){
            stmt.setString(1, person_name);
            stmt.setString(2, lastName);
            stmt.setInt( 3,age);
            stmt.executeUpdate();
            System.out.println("User с именем — "+ person_name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(REMOVE)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
            System.out.println("User c id " + id + " был удален");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                int age = rs.getInt("age");

                User user = new User(id, name, lastname, (byte) age);
                userList.add(user);
            }

            for (User user : userList) {
                System.out.println(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Table clean.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
