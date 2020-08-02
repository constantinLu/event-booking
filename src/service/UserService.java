package service;

import entities.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    String getOrganiserName(int id);
    List<User> getAllUsers();
    boolean updateUserRole(int id);
}
