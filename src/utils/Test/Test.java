package Utils.Test;


import entities.Entity;
import java.util.ArrayList;

import entities.User;
import networking.DBTables;
import networking.JDBC;

public class Test {
    public static void main(String[] args) {
        printTable(DBTables.USER_TABLE, User.class.getName());
    }

    public static void printTable (String table, String className) {

        JDBC.createConnection();
        System.out.println("-----------------------------------");
        System.out.println(String.format("ALL %s", table));
        String query = String.format("SELECT * FROM %s", table);
        ArrayList<Entity> obj = JDBC.getAll(query, className);
        for (Entity object : obj) {
            System.out.println(object.toString());
            System.out.println("***");
        }
        System.out.println("------------------------------------");
    }
}
