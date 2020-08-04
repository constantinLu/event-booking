package controller;

import entities.User;

public class AdminController {

    private User loggedUser;

    public AdminController(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
