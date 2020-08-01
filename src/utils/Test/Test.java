package Utils.Test;


import entities.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Event;
import entities.User;
import networking.DBTables;
import networking.JDBC;
import service.EventService;
import service.EventServiceImpl;

public class Test {
    public static void main(String[] args) {
        JDBC.createConnection();
        getAllEvents();
//        createEvent();
//        printTable(DBTables.EVENT_TABLE, Event.class.getName());
//        printTable(DBTables.USER_TABLE, Event.class.getName());
    }

    public static void getAllEvents(){
        EventService eventService = new EventServiceImpl();
        List<Event> events=eventService.getAllEvents();
        printResultList(events);
    }
    public static void createEvent(){
        EventService eventService = new EventServiceImpl();
        Event.Builder builder = new Event.Builder();
        builder.title("Test event").description("Description test").isOnline(false).location("London").startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).constraints("COVID constraints").isBookingAllowed(true);


        eventService.addEvent(builder.createEvent());
    }
    public static void printTable (String table, String className) {

//        JDBC.createConnection();
        System.out.println("-----------------------------------");
        System.out.println(String.format("ALL %s", table));
        String query = String.format("SELECT * FROM %s", table);
        ArrayList<Entity> obj = JDBC.getAll(query, className);
        printResultList(obj);
        System.out.println("------------------------------------");
    }

    public static void printResultList(List list){
        list.stream().forEach(x->{ System.out.println(x);
            System.out.println("***");
        });
    }
}
