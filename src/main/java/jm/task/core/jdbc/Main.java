package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Harry", "Potter", (byte) 16);
        userService.saveUser("Ron", "Ron Weasley", (byte) 16);
        userService.saveUser("Hermione", "Granger", (byte) 16);
        userService.saveUser("Draco", "Malfoy", (byte) 16);

        userService.removeUserById(4);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
