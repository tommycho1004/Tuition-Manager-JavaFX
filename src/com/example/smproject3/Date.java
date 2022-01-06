package com.example.smproject3;

import java.util.Calendar;
import java.util.StringTokenizer;


/**
 * The date class that's used for setting the payment date of each album.
 * Use this class to check if a date is valid or not.
 *
 * @author Tommy Cho
 */
public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    /**
     * A method that converts a date object to a string.
     * If there is no current date, return "--/--/--"
     *
     * @return the date in string form
     */
    public String dateString() {
        if (year == 0) {
            return "--/--/--";
        } else {
            return month + "/" + day + "/" + year;
        }
    }

    /**
     * Parameterized constructor that takes the input string "mm/dd/yyyy" and converts it into the date type
     *
     * @param date a string date in the form of mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }


    /**
     * Returns today's date
     */
    Calendar today = Calendar.getInstance();

    public Date() {
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH);
        this.day = today.get(Calendar.DATE);
    }

    /**
     * static integers
     */
    public static final int QUAD = 4;
    public static final int CENT = 100;
    public static final int QUADCENT = 400;
    public static final int NOLEAPFEB = 28;
    public static final int LEAPFEB = 29;
    public static final int LONG_MONTH = 31;
    public static final int SHORT_MONTH = 30;
    public static final int LIMIT_YEAR = 2021;

    public static final int JAN = 0;
    public static final int FEB = 1;
    public static final int MAR = 2;
    public static final int MAY = 4;
    public static final int JUL = 6;
    public static final int AUG = 7;
    public static final int OCT = 9;
    public static final int DEC = 11;

    /**
     * A helper method that determines how many days february has.
     *
     * @param year the year of the date
     * @return integer of days in february
     */
    private int febDays(int year) {
        if (year % QUAD == 0) {
            if (year % CENT == 0) {
                if (year % QUADCENT == 0) {
                    return LEAPFEB;
                } else return NOLEAPFEB;
            } else return LEAPFEB;
        } else return NOLEAPFEB;
    }

    /**
     * A helper method that outputs how many days in a specific month there is.
     *
     * @param month the month in date
     * @param year  the year in date
     * @return integer days in the month we're looking for
     */
    private int daysInMonth(int month, int year) { //month-1
        month = month - 1;
        if (month == FEB) {
            return febDays(year);
        } else if (month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC) {
            return LONG_MONTH;
        } else {
            return SHORT_MONTH;
        }
    }

    /**
     * A method that determines if a date is valid or not
     *
     * @return true if the date is valid, false if otherwise
     */
    public boolean isValid() {
        if (this.year < LIMIT_YEAR || this.year > today.get(Calendar.YEAR)) {
            return false;
        }
        if (this.month - 1 > 11 || this.month - 1 < 0) {
            return false;
        }
        if (this.day > daysInMonth(this.month, this.year) || this.day <= 0) {
            return false;
        }
        if (this.year == today.get(Calendar.YEAR)) {
            if (this.month - 1 > today.get(Calendar.MONTH)) {
                return false;
            }
            if (this.month == today.get(Calendar.MONTH) + 1) {
                return this.day <= today.get(Calendar.DATE);
            }
        }
        return true;
    }

    /**
     * A method that compares a date with another one and returns a positive or negative number
     * depending on which date was earlier
     *
     * @param date that is being compared
     * @return positive int if the date of this is later than the param date that is being compared
     */
    @Override
    public int compareTo(Date date) {
        int diff = this.year - date.year;
        if (diff != 0) {
            return diff;
        }
        diff = this.month - date.month;
        if (diff != 0) {
            return diff;
        }
        return this.day - date.day;
    }
}