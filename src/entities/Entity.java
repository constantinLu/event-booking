package entities;


import service.Dao;

public abstract class Entity<T> implements Dao<T> {

    public boolean contains(String s) {
        return false;
    }
}
