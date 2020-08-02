package service;

import entities.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    boolean addEvent(Event event);

    boolean updateEvent(Event event);

    Event getEventById(int id);

    boolean isEventAlreadyBooked(int eventId, int userId);

}
