package service;

import entities.Booking;
import entities.Entity;
import entities.Event;
import entities.User;
import networking.DBTables;
import networking.JDBC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    @Override
    public List<Event> getAllEvents() {
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query= String.format("select * from %s ", DBTables.EVENT_TABLE);
        ArrayList<Entity> eventList =  JDBC.getAll(query,Event.class.getName());
        List<Event> events = eventList.stream().map(x->{
            return (Event)x;
        }).collect(Collectors.toList());

        return events;
    }

    @Override
    public boolean addEvent(Event event) {
        String query = event.getInsertQuery();
        return JDBC.insert(query);
    }

    @Override
    public boolean updateEvent(Event event) {
        String query = event.getUpdateQuery();
        return JDBC.update(query);
    }

    @Override
    public Event getEventById(int id) {
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query = String.format("select * from %s where id = %s", DBTables.EVENT_TABLE,id);
        return (Event) JDBC.get(query,Event.class.getName());
    }

    public boolean isEventAlreadyBooked(int eventId, int userId){
        BookingService bookingService = new BookingServiceImpl();
        Booking booking = bookingService.getValidBookingByEventIdAndUserId(eventId, userId);
        if (booking == null) {
            return false;
        } else {
            return true;
        }
    }
    public List<Event> getEventsOrganisedByUser(int userId){
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query= String.format("select * from %s where organiser_id = %s", DBTables.EVENT_TABLE, userId);
        ArrayList<Entity> eventList =  JDBC.getAll(query,Event.class.getName());
        List<Event> events = eventList.stream().map(x->{
            return (Event)x;
        }).collect(Collectors.toList());

        return events;
    }

    public boolean bookEvent(Event event, int userId){
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setEventId(event.getEventId());
        booking.setBookingDate(LocalDateTime.now());
        booking.setCancelDate(null);

        BookingService bookingService = new BookingServiceImpl();
        Booking bookingEntity = bookingService.getBookingByEventIdAndUserId(event.getEventId(),userId);
        if(bookingEntity == null){
            return bookingService.addBooking(booking);
        }else{
            return bookingService.updateBooking(booking);
        }
    }
}