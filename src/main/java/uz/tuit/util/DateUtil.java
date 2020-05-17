package uz.tuit.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String MONTH_DAY_YEAR = "MM/DD/YYYY";

    public static Date getDate(String date) {
        if (date == null || date.trim().isEmpty())
            return null;

        DateFormat format = new SimpleDateFormat(MONTH_DAY_YEAR);

        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
