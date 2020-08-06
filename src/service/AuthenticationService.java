package service;

import entities.User;

public interface AuthenticationService {

    /**
     * Check if the credentials entered in the UI have a match in DB
     *
     * @param username
     * @param password
     * @return the user entity. Null if there is no match in DB
     */
    User login(String username, String password);

    /**
     * Register a user
     *
     * @param user - user object that needs to be stored in DB
     * @return - true if succesfull registration
     */
    boolean register(User user);
}
