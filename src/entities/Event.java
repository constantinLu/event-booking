package entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import networking.DBTables;
import utils.BooleanMapper;
import utils.LocalDateTimeMapper;

public class Event extends Entity<Event> {

    private int eventId;
    private String title;
    private String description;
    private boolean isOnline; //
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String constraints;
    private boolean isBookingAllowed; // if booking btn is allowed
    private int organiserId; // userul care a creat

    public Event() {
    }

    Event(Builder builder) {
        this.eventId = builder.eventId;
        this.title = builder.title;
        this.location = builder.location;
        this.description = builder.description;
        this.constraints = builder.constraints;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.isBookingAllowed = builder.isBookingAllowed;
        this.isOnline = builder.isOnline;
        this.organiserId = builder.organiserId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isOnline=" + isOnline +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", constraints='" + constraints + '\'' +
                ", isBookingAllowed=" + isBookingAllowed +
                '}';
    }

    public static class Builder {
        private int eventId;
        private String title;
        private boolean isOnline;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String constraints;
        private boolean isBookingAllowed;
        private String description;
        private int organiserId;

        public Builder eventId(int eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isOnline(boolean isOnline) {
            this.isOnline = isOnline;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder constraints(String constraints) {
            this.constraints = constraints;
            return this;
        }

        public Builder isBookingAllowed(boolean isBookingAllowed) {
            this.isBookingAllowed = isBookingAllowed;
            return this;
        }

        public Builder organiserId(int organiserId) {
            this.organiserId = organiserId;
            return this;
        }

        public Event createEvent() {
            return new Event(this);
        }

    }

    @Override
    public String getInsertQuery() {
        return String.format("INSERT INTO %s VALUES (null, '%s', '%s', '%s', '%s', '%s','%s','%s','%s','%s')", DBTables.EVENT_TABLE,
                getTitle(), getDescription(), BooleanMapper.map(isOnline()), getLocation(), getStartDate(), getEndDate(), getConstraints(), BooleanMapper.map(isBookingAllowed()), getOrganiserId());
    }


    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET title = '%s', description = '%s', is_online = '%s', location = '%s', start_date = '%s', end_date='%s', constraints = '%s', is_booking_allowed = '%s' WHERE id = '%s'", DBTables.EVENT_TABLE,
                getTitle(), getDescription(), BooleanMapper.map(isOnline()), getLocation(), getStartDate(), getEndDate(), getConstraints(), BooleanMapper.map(isBookingAllowed()), getEventId());
    }

    @Override
    public void setObject(HashMap<String, String> object) {
        setEventId(Integer.parseInt(object.get("id")));
        setTitle(object.get("title"));
        setDescription(object.get("description"));
        setOnline(BooleanMapper.map(object.get("is_online")));
        setLocation(object.get("location"));
        setStartDate(LocalDateTimeMapper.map(object.get("start_date")));
        setEndDate(LocalDateTimeMapper.map(object.get("end_date")));
        setConstraints(object.get("limitations"));
        setBookingAllowed(BooleanMapper.map(object.get("is_booking_allowed")));
        setOrganiserId(Integer.valueOf(object.get("organiser_id")));
    }

    @Override
    public Event getObject() {
        return this;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getOrganiserId() {
        return organiserId;
    }

    public void setOrganiserId(int organiserId) {
        this.organiserId = organiserId;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public void setBookingAllowed(boolean bookingAllowed) {
        isBookingAllowed = bookingAllowed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getConstraints() {
        return constraints;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public boolean isBookingAllowed() {
        return isBookingAllowed;
    }

}

