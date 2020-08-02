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

    //
//    private final String timestampFormat = "yyyy-MM-dd";
//    private int eventID;
//    private String eventName;
//    private Timestamp eventDate;
//    private String eventLocation;
//    private String eventDescription;
//    private int eventOrganiser;
//
//
//    public Event(int eventID, String eName, String eDate, String eLocation, String eDescription, int eOrganiser) {
//        this.eventID = eventID;
//        eventName = eName;
//        eventDate = setDate(eDate);
//        eventLocation = eLocation;
//        eventDescription = eDescription;
//        eventOrganiser = eOrganiser;
//    }
//
//    public Event(String eName, String eDate, String eLocation, String eDescription, int eOrganiser) {
//        eventName = eName;
//        eventDate = setDate(eDate);
//        eventLocation = eLocation;
//        eventDescription = eDescription;
//        eventOrganiser = eOrganiser;
//    }
//
//    public int getEventID() {
//        return eventID;
//    }
//    public String getEventName() {return eventName;}
//    public String getEventDate() {
//        return getDate(this.eventDate);
//    }
//    public String getEventLocation(){return eventLocation;}
//    public String getEventDescription() {return eventDescription;}
//    public int getEventOrganiser() {return eventOrganiser;}
//
//    public void setEventID(int eventID) {
//        this.eventID = eventID;
//    }
//
//    public void setEventName(String eventName) {
//        this.eventName = eventName;
//    }
//
//    public void setEventDate(String eventDate) {
//       this.eventDate = setDate(eventDate);
//    }
//
//    public void setEventLocation(String eventLocation) {
//        this.eventLocation = eventLocation;
//    }
//
//    public void setEventDescription(String eventDescription) {
//        this.eventDescription = eventDescription;
//    }
//
//    public void setEventOrganiser(int eventOrganiser) {
//        this.eventOrganiser = eventOrganiser;
//    }
//
//    private String getDate (Timestamp timestamp) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(timestampFormat);
//        Date date = new Date();
//        date.setTime(timestamp.getTime());
//        return dateFormat.format(date);
//    }
//
//    private Timestamp setDate (String date) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(timestampFormat);
//        try {
//            calendar.setTime(dateFormat.parse(date));
//            return new Timestamp(calendar.getTimeInMillis());
//        }
//        catch (ParseException e) {
//            return null;
//        }
//    }
//
//    public Timestamp getTimeStamp () {
//        return this.eventDate;
//    }
//
//    public String getInsertQuery() {
//        return String.format("INSERT INTO EVENTS VALUES (null, '%s', '%s', '%s', '%s', '%d')",
//                getEventName(), getEventDate(), getEventLocation(), getEventDescription(), getEventOrganiser());
//    }
//
//    public String getUpdateQuery() {
//        return String.format("UPDATE EVENTS SET Event_Name = '%s', Event_Date = '%s', Event_Location = '%s', Event_Description = '%s' WHERE EVENT_ID = '%s'",
//                getEventName(), getEventDate(), getEventLocation(), getEventDescription(), getEventID());
//    }
//
//    public void setObject(HashMap<String, String> object) {
//        setEventID(Integer.parseInt(object.get("Event_ID")));
//        setEventName(object.get("Event_Name"));
//        setEventDate(object.get("Event_Date"));
//        setEventLocation(object.get("Event_Location"));
//        setEventDescription(object.get("Event_Description"));
//        setEventOrganiser(Integer.parseInt(object.get("Event_Organiser")));
//    }
//
//    public Event getObject() {
//        return this;
//    }
//
//    @Override
//    public boolean contains (String s) {
//        String searchValue = s.toLowerCase();
//        return ((getEventName() != null && getEventName().toLowerCase().contains(searchValue)) ||
//                (getEventDescription() != null && getEventDescription().toLowerCase().contains(searchValue)) ||
//                (getEventLocation() != null && getEventLocation().toLowerCase().contains(searchValue)));
//    }
//
//
//    @Override
//    public String toString () {
//        return String.format("ID: %d\nEvent title: %s\nEvent date: %s\nEvent location: %s\nEvent description: %s\nEvent organiser: %d",
//                getEventID(), getEventName(), getEventDate(), getEventLocation(), getEventDescription(), getEventOrganiser());
//    }
}

