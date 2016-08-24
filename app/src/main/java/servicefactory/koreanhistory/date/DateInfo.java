package servicefactory.koreanhistory.date;

import java.text.SimpleDateFormat;

/**
 * Created by jihwan on 2015-04-19.
 */
public class DateInfo {
    private static DateInfo dateInfoInstance = null;

    private DateInfo() {
    }

    public static DateInfo getDateInfoInstance() {
        if (dateInfoInstance == null)
            dateInfoInstance = new DateInfo();

        return dateInfoInstance;
    }

    public String dateTime() {
        // current date and time load to system.
        long dateTime = System.currentTimeMillis();

        // current time store in Data Object
        java.util.Date date = new java.util.Date(dateTime);

        // formate setting
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy년 MM월 dd일. aa hh시 mm분");
        String strDate = sdfDate.format(date);

        return strDate;
    }

    public String todayDate() {
        // current time load to system.
        long todayDate = System.currentTimeMillis();

        // current time store in Data Object
        java.util.Date date = new java.util.Date(todayDate);

        // formate setting
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdfDate.format(date);

        return strDate;
    }

    public String currentTime() {
        // current time load to system.
        long nowTime = System.currentTimeMillis();

        // current time store in Data Object
        java.util.Date time = new java.util.Date(nowTime);

        // formate setting
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String strNow = sdfTime.format(time);

        return strNow;
    }
}