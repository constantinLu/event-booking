package service;

import entities.Booking;
import entities.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    boolean addEvent(Event event);

    boolean updateEvent(Event event);

    Event getEventById(int id);

    List<Event> getEventsOrganisedByUser(int userId);

    List<Event> getBookedEvents(List<Booking> bookings);

    boolean isEventBooked(int eventId, int userId);

    boolean bookEvent(Event event, int userId);

}
