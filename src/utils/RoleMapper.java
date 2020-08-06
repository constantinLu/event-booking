package utils;

import entities.Roles;


public class RoleMapper {
    public static Roles map(String role) {
        if (Roles.ADMINISTRATOR.name().equals(role)) {
            return Roles.ADMINISTRATOR;
        }
        if (Roles.EVENT_ORGANISER.name().equals(role)) {
            return Roles.EVENT_ORGANISER;
        }
        return Roles.STUDENT;
    }
}
