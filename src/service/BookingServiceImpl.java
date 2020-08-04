package service;

import entities.Booking;
import entities.Entity;
import networking.DBTables;
import networking.JDBC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    @Override
    public boolean addBooking(Booking booking) {
        String query = booking.getInsertQuery();
        return JDBC.insert(query);
    }

    @Override
    public boolean cancelBooking(int eventId, int userId) {
        String query = String.format("update %s set cancel_date = '%s' where event_id=%s and user_id=%s", DBTables.BOOKING_TABLE, LocalDateTime.now(), eventId,userId);
        return JDBC.update(query);
    }

    public boolean activateCancelledBooking(Booking booking){
        String query = String.format("UPDATE %s SET booking_date = '%s', cancel_date = null WHERE event_id = %s and user_id=%s", DBTables.BOOKING_TABLE, booking.getBookingDate(), booking.getEventId(),booking.getUserId());

        return JDBC.update(query);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        String query = String.format("select * from %s where user_id=%s",DBTables.BOOKING_TABLE,userId);
        ArrayList<Entity> bookingList =  JDBC.getAll(query, Booking.class.getName());
        List<Booking> bookings = bookingList.stream().map(x->{
            return (Booking)x;
        }).collect(Collectors.toList());
        return bookings;
    }

    @Override
    public Booking getValidBookingByEventIdAndUserId(int eventId, int userId) {
        String query = String.format("select * from %s where cancel_date is null and user_id=%s and event_id=%s",DBTables.BOOKING_TABLE,userId,eventId);
        Booking booking = (Booking) JDBC.get(query, Booking.class.getName());
        return booking;
    }
    @Override
    public Booking getBookingByEventIdAndUserId(int eventId, int userId) {
        String query = String.format("select * from %s where user_id=%s and event_id=%s",DBTables.BOOKING_TABLE,userId,eventId);
        Booking booking = (Booking) JDBC.get(query, Booking.class.getName());
        return booking;
    }
}
