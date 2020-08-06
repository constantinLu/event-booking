package service;

import java.util.HashMap;

public interface Dao<T> {

    String getInsertQuery();

    String getUpdateQuery();

    void setObject(HashMap<String, String> object);
}
