package parser;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class VacancyDate extends GregorianCalendar {

    private final Map<String, Integer> monthNumber = new HashMap<>();

    private int month;
    private int day;
    private int year;
    private int hour;
    private int minutes;

    public VacancyDate(String date) {
        fillMonth();
        parseDate(date);
    }

    private void fillMonth() {
        monthNumber.put("янв", 0);
        monthNumber.put("фев", 1);
        monthNumber.put("мар", 2);
        monthNumber.put("апр", 3);
        monthNumber.put("май", 4);
        monthNumber.put("июн", 5);
        monthNumber.put("июл", 6);
        monthNumber.put("авг", 7);
        monthNumber.put("сен", 8);
        monthNumber.put("окт", 9);
        monthNumber.put("ноя", 10);
        monthNumber.put("дек", 11);
    }

    private void parseDate(String date) {
        int commaIndex = date.indexOf(',');
        String dayMonthYear = date.substring(0, commaIndex);
        String time = date.substring(commaIndex + 2);
        String[] data = dayMonthYear.split("\\s+");
        LocalDateTime today = LocalDateTime.now();
        if ("сегодня".equals(data[0])) {
            day = today.getDayOfMonth();
            month = today.getMonthValue() - 1;
            year = today.getYear();
        } else if ("вчера".equals(data[0])) {
            LocalDateTime yesterday = today.minusDays(1);
            day = yesterday.getDayOfMonth();
            month = yesterday.getMonthValue() - 1;
            year = yesterday.getYear();
        } else {
            day = Integer.parseInt(data[0]);
            month = monthNumber.get(data[1]);
            year = Integer.parseInt(data[2]) + 2000;
        }
        String[] timeData = time.split(":");
        hour = Integer.parseInt(timeData[0]);
        minutes = Integer.parseInt(timeData[1]);
        set(year, month, day, hour, minutes, 0);
    }


    public long getTimeDate() {
        return getTimeInMillis() / 1000;
    }

    @Override
    public boolean after(Object when) {
        long time1 = getTimeDate();
        long time2 = (long) when;
        return time1 > time2;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s %s:%s", day, month, year, hour, minutes);
    }

}
