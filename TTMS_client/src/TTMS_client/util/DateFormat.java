package util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormat {

    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Timestamp stringToTimestamp(String str){
        return Timestamp.valueOf(str);
    }

    public static String timestampToString(Timestamp timestamp){
        return df.format(timestamp);
    }

    public static Date strToDate(String str) throws ParseException {
        return df.parse(str);
    }

    public static String dateToStr(Date date){
        return df.format(date);
    }

}
