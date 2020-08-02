package service;

import entities.User;
import networking.DBTables;
import networking.JDBC;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query = String.format("select * from %s where id = %s", DBTables.USER_TABLE,id);
        return (User) JDBC.get(query,User.class.getName());
    }
    @Override
    public String getOrganiserName(int id){
        User user = getUserById(id);
        return user.getFirstName()+ " " + user.getLastName();
    }
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUserRole(int id) {
        return false;
    }
}
