package service;

import connection.JdbcConnection;
import entities.Booking;
import entities.Entity;
import entities.Event;
import connection.Tables;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    @Override
    public List<Event> getAllEvents() {
        if(!JdbcConnection.isConnected()){
            JdbcConnection.createConnection();
        }
        String query= String.format("select * from %s ", Tables.EVENT_TABLE);
        ArrayList<Entity> eventList =  JdbcConnection.getAll(query,Event.class.getName());
        List<Event> events = eventList.stream().map(x->{
            return (Event)x;
        }).collect(Collectors.toList());

        return events;
    }

    @Override
    public boolean addEvent(Event event) {
        String query = event.getInsertQuery();
        return JdbcConnection.insert(query);
    }

    @Override
    public boolean updateEvent(Event event) {
        String query = event.getUpdateQuery();
        return JdbcConnection.update(query);
    }

    @Override
    public Event getEventById(int id) {
        if(!JdbcConnection.isConnected()){
            JdbcConnection.createConnection();
        }
        String query = String.format("select * from %s where id = %s", Tables.EVENT_TABLE,id);
        return (Event) JdbcConnection.get(query,Event.class.getName());
    }

    public boolean isEventBooked(int eventId, int userId){
        BookingService bookingService = new BookingServiceImpl();
        Booking booking = bookingService.getValidBookingByEventIdAndUserId(eventId, userId);
        if (booking == null) {
            return false;
        } else {
            return true;
        }
    }
    public List<Event> getEventsOrganisedByUser(int userId){
        if(!JdbcConnection.isConnected()){
            JdbcConnection.createConnection();
        }
        String query= String.format("select * from %s where organiser_id = %s", Tables.EVENT_TABLE, userId);
        ArrayList<Entity> eventList =  JdbcConnection.getAll(query,Event.class.getName());
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
            return bookingService.activateCancelledBooking(booking);
        }
    }

    public List<Event> getBookedEvents(List<Booking> bookings){
        List eventIds= bookings.stream().map(x->x.getEventId()).collect(Collectors.toList());
        String ids = bookings.stream().map(x -> String.valueOf(x.getEventId())).reduce("", (s, r) -> s+r+",");
        ids=ids.substring(0,ids.length()-1);
        System.out.println("ids after stream: "+ ids);
        String query = String.format("select * from %s where id in (%s)", Tables.EVENT_TABLE,ids);
        ArrayList<Entity> eventList = JdbcConnection.getAll(query,Event.class.getName());
        List<Event> events = eventList.stream().map(x->{
            return (Event)x;
        }).collect(Collectors.toList());

        return events;
    }
}