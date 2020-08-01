package entities;


import interfaces.Dao;

public abstract class Entity<T> implements Dao<T> {

    public boolean contains(String s) {return false;}

}
