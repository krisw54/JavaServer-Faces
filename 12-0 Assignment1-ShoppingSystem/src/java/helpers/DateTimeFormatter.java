package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kris
 */
public class DateTimeFormatter {

    public DateTimeFormatter() {
        
    }

    public String formatDateNow() {
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = df.format(now);
        return date;
    }

    public String formatTimeNow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public String formatDate(Date theDate) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = df.format(theDate);
        return date;
    }

    public String formatTime(Calendar theTime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(theTime.getTime());
    }
}
