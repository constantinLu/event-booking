package service;

import entities.Entity;
import entities.Event;
import entities.Roles;
import entities.User;
import networking.DBTables;
import networking.JDBC;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if(!JDBC.isConnected()){
            JDBC.createConnection();
        }
        String query = String.format("select * from %s", DBTables.USER_TABLE);
        ArrayList<Entity> userList =  JDBC.getAll(query, User.class.getName());
        List<User> users = userList.stream().map(x->{
            return (User)x;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public boolean updateUserRole(int userId, Roles newRole) {
        String query =  String.format("UPDATE %s SET role = '%s' WHERE id = %s",DBTables.USER_TABLE,
                newRole.name() ,userId);
        return JDBC.update(query);
    }
}
