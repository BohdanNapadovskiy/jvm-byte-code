package ru.napadovskiu.parseDate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Package of final task SQL_JDBC.
 * Class parse date.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.02.2018
 */
public class ParseDate {

    /**
     * Map for mounts.
     */
    private Map<String, String> mapMounts = new HashMap<>();

    /**
     * Constructor for class.
     */
    public ParseDate() {
        this.mapMounts.put("янв","01");
        this.mapMounts.put("фев","02");
        this.mapMounts.put("мар","03");
        this.mapMounts.put("апр","04");
        this.mapMounts.put("май","05");
        this.mapMounts.put("июн","06");
        this.mapMounts.put("июл","07");
        this.mapMounts.put("авг","08");
        this.mapMounts.put("сен","09");
        this.mapMounts.put("окт","10");
        this.mapMounts.put("ноя","11");
        this.mapMounts.put("дек","12");

    }

    /**
     * Metho convert string with date to Date.
     * @param stringDate string date
     * @return Date
     * @throws ParseException exception
     */
    public Timestamp getDateFromString(String stringDate) throws ParseException {
        long date = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(stringDate.contains("вчера")) {
            date = sdf.parse(sdf.format(new Date())).getTime();
        } else if(stringDate.contains("сегодня")) {
            Instant now = Instant.now(); //current date
            Instant before = now.minus(Duration.ofDays(1));

            Date dateBefore = Date.from(before);
            date = sdf.parse(sdf.format(dateBefore)).getTime();
        } else {

            String sDay = stringDate.substring(0,2);
            String strMounth = stringDate.substring(sDay.length(),sDay.length()+4);
            String sMounth = this.mapMounts.get(strMounth.replaceAll(" ",""));
            String sYear = stringDate.substring(sDay.length()+strMounth.length(),sDay.length()+strMounth.length()+3);
            sYear = "20"+sYear.trim();
            String strDate = new String(sYear+"-"+sMounth+"-"+sDay);
            date = sdf.parse(strDate).getTime();
        }
        Timestamp result = new Timestamp(date);
        return result;
    }


    /**
     * Method return date of beginning of year.
     * @return
     */
    public Timestamp getDateToBeginningYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), 0, 1, 0, 0, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }



}
