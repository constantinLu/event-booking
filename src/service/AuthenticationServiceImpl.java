package service;

import entities.User;
import networking.DBTables;
import networking.JDBC;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User login(String username, String password) {
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query= String.format("select * from %s where username='%s' and password ='%s' ", DBTables.USER_TABLE, username,password);
        User user = (User) JDBC.get(query,User.class.getName());
        return user;
    }

    @Override
    public User register(User user) {
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query = user.getInsertQuery();
        User createdUser = (User) JDBC.get(query,User.class.getName());
        return createdUser;
    }
}
