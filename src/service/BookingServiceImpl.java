package service;

import entities.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Override
    public boolean addBooking(Booking booking) {
        return false;
    }

    @Override
    public boolean cancelBooking(Booking booking) {
        return false;
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return null;
    }

    @Override
    public Booking getValidBookingByEventIdAndUserId(int eventId, int userId) {
        return null;
    }
}
