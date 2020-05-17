package uz.tuit.util;

public class DataUtil {

    public static Double getDouble(String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long getLong(String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
