package utils;

public class BooleanMapper {
    public static String map(boolean value) {
        return value ? "y" : "n";
    }

    public static boolean map(String value) {
        return value.equals("y");
    }

    public static String mapForUi(boolean value) {
        return value ? "Yes" : "No";
    }

}
