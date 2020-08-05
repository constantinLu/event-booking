package service;

import connection.JdbcConnection;
import entities.Entity;
import entities.Roles;
import entities.User;
import connection.Tables;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        if(!JdbcConnection.isConnected()){
            JdbcConnection.createConnection();
        }
        String query = String.format("select * from %s where id = %s", Tables.USER_TABLE,id);
        return (User) JdbcConnection.get(query,User.class.getName());
    }
    @Override
    public String getOrganiserName(int id){
        User user = getUserById(id);
        return user.getFirstName()+ " " + user.getLastName();
    }
    @Override
    public List<User> getAllUsers() {
        if(!JdbcConnection.isConnected()){
            JdbcConnection.createConnection();
        }
        String query = String.format("select * from %s", Tables.USER_TABLE);
        ArrayList<Entity> userList =  JdbcConnection.getAll(query, User.class.getName());
        List<User> users = userList.stream().map(x->{
            return (User)x;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public boolean updateUserRole(int userId, Roles newRole) {
        String query =  String.format("UPDATE %s SET role = '%s' WHERE id = %s", Tables.USER_TABLE,
                newRole.name() ,userId);
        return JdbcConnection.update(query);
    }
}
