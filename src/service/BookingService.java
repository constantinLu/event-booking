package service;

import entities.Booking;

import java.util.List;

public interface BookingService {
    boolean addBooking(Booking booking);
    boolean cancelBooking(Booking booking);
    List<Booking> getBookingsByUserId(int userId);
    Booking getValidBookingByEventIdAndUserId(int eventId, int userId);
    Booking getBookingByEventIdAndUserId(int eventId, int userId);
    boolean updateBooking(Booking booking);

}
