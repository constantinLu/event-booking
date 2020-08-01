package controller;

import entities.User;

public class EventsController {

    private User loggedUser;

    public EventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
