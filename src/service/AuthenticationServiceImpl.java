package service;

import connection.JdbcConnection;
import connection.Tables;
import entities.User;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User login(String username, String password) {
        if (!JdbcConnection.isConnected()) {
            JdbcConnection.createConnection();
        }
        String query = String.format("select * from %s where username='%s' and password ='%s' ", Tables.USER_TABLE, username, password);
        User user = (User) JdbcConnection.get(query, User.class.getName());
        return user;
    }

    @Override
    public boolean register(User user) {
        if (!JdbcConnection.isConnected()) {
            JdbcConnection.createConnection();
        }
        String query = user.getInsertQuery();
        return JdbcConnection.insert(query);
    }
}
