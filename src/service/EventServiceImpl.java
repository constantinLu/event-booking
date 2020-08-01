package service;

import entities.Entity;
import entities.Event;
import entities.User;
import networking.DBTables;
import networking.JDBC;

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
}
