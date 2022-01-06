package com.example.smproject3;

import java.text.DecimalFormat;

/**
 * The Student superclass that stores the attributes of each student and their financial aid information.
 * Use this class to populate attributes into a new Student object.
 *
 * @author Tommy Cho
 */
public class Student {
    private Profile profile;
    private int creditHours;
    private boolean isFullTime;
    private Date lastPaid;
    private double totalPayment;
    private double tuitionDue = 0.0;
    private boolean madePayment;

    public static double universityFee = 3268.0;
    public static double partTimeUniversityFee = 2614.4;
    public static int extraCredits = 16;
    public static int partTimeCreds = 12;

    /**
     * A setter method for credit hours
     *
     * @param creditHours credit hours of a student
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * A setter method for last paid date
     *
     * @param date Date of current payment
     */
    public void setLastPaid(Date date) {
        this.lastPaid = date;
    }

    /**
     * A setter method for the total payment a student has made
     *
     * @param payment total payment in double form
     */
    public void setTotalPayment(double payment) {
        this.totalPayment = payment;
    }

    /**
     * A setter method for the tuition due for a student
     *
     * @param tuition tuition due in double form
     */
    public void setTuitionDue(double tuition) {
        this.tuitionDue = tuition;
    }

    /**
     * A getter method for a student's profile
     *
     * @return the student's profile in Profile object form
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * A getter method for the number of credit hours a student has
     *
     * @return credit hours in int form
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * A getter method for the fulltime status of a student
     *
     * @return true if student is full time, false otherwise
     */
    public boolean getIsFullTime() {
        return isFullTime;
    }

    /**
     * A getter method for date last paid
     *
     * @return Date of last paid tuition
     */
    public Date getLastPaid() {
        if (lastPaid == null) {
            return new Date("0/0/0");
        } else {
            return lastPaid;
        }
    }

    /**
     * A getter method for the total payment a student has made
     *
     * @return the total payment in double form
     */
    public double getTotalPayment() {
        return totalPayment;
    }

    /**
     * A getter method for the tuition that a student is due
     *
     * @return tuition due in double form
     */
    public double getTuitionDue() {
        return tuitionDue;
    }

    /**
     * A getter method for whether a student made a payment already or not
     *
     * @return true if a payment has been made, false otherwise
     */
    public boolean getMadePayment() {
        return madePayment;
    }

    /**
     * A setter for whether a student made a payment already or not
     *
     * @param s true if the student made a payment, false if they didn't
     */
    public void setMadePayment(boolean s) {
        this.madePayment = s;
    }

    /**
     * A parameterized constructor for the student object
     *
     * @param name  Name of the student in string form
     * @param major Major of the student in string form
     */
    public Student(String name, String major) {
        this.profile = new Profile(name, major);
    }

    /**
     * A parameterized constructor for the student object
     *
     * @param name        Name of the student in string form
     * @param major       Major of the student in string form
     * @param creditHours Credit hours the student is taking in integer form
     */
    public Student(String name, String major, int creditHours) {
        this.profile = new Profile(name, major);
        this.creditHours = creditHours;
        this.isFullTime = creditHours >= partTimeCreds;
    }

    /**
     * The do-nothing method of calculating tuition.
     * The other sub-classes will have a method with the same name that will override this one.
     */
    public void tuitionDue() {
    }

    /**
     * A method lets a student pay their tuition
     *
     * @param amount   integer value of how much the student is paying right now
     * @param datePaid date that the student is paying their tuition
     */
    public void payTuition(double amount, Date datePaid) {
        this.tuitionDue = this.tuitionDue - amount;
        this.lastPaid = datePaid;
        this.totalPayment = this.totalPayment + amount;
        this.madePayment = true;
    }

    /**
     * Converts the information of the student to a string format
     *
     * @return the student's profile in the form of a string
     */
    @Override
    public String toString() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        return this.profile.toString() + ":" + this.creditHours + " credit hours:tuition due:" +
                dec.format(this.tuitionDue) + ":total payment:" + dec.format(this.totalPayment) +
                ":last payment date: " + this.lastPaid.dateString() + ":student";
    }
}