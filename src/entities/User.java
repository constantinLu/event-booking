package entities;


import connection.Tables;
import java.util.HashMap;
import utils.RoleMapper;

public class User extends Entity<User> {
    private int userId;

    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Roles role;

    public User() {

    }

    User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.role = builder.role;
    }


    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getInsertQuery() {
        //    @Override
//    public String getInsertQuery() {
        return String.format("INSERT INTO %s VALUES (null, '%s', '%s', '%s', '%s', '%s','%s')", Tables.USER_TABLE,
                getFirstName(), getLastName(), getEmail(), getUsername(), getPassword(), Roles.STUDENT.name());
//    }
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void setObject(HashMap<String, String> object) {
        setUserId(Integer.parseInt(object.get("id")));
        setUsername(object.get("username"));
        setEmail(object.get("email"));
        setFirstName(object.get("first_name"));
        setLastName(object.get("last_name"));
        setRole(RoleMapper.map(object.get("role")));

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Id: ").append(userId).append("\n");
        sb.append("Username:").append(username).append("\n");
        sb.append("FirstName:").append(firstName).append("\n");
        sb.append("Last Name").append(lastName).append("\n");
        sb.append("Email:").append(email).append("\n");
        sb.append("Role: ").append(role).append("\n");
        return sb.toString();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public static class Builder {
        private int userId;

        String username;
        String password;
        String firstName;
        String lastName;
        String email;
        Roles role;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withRole(Roles role) {
            this.role = role;
            return this;
        }

        public User createUser() {
            return new User(this);
        }
    }
}
