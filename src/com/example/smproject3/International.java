package com.example.smproject3;

import java.text.DecimalFormat;

/**
 * The International subclass that extends from the NonResident superclass.
 * Use this class to do get calculation information for international students.
 *
 * @author Tommy Cho
 */
public class International extends NonResident {
    private boolean studyAbroad; //true if they are studying abroad, false otherwise

    //static double additional fee for international students
    private static double additionalFee = 2650.0;
    private static int maxCreds = 12;

    /**
     * Getter method for study abroad status
     * @return true if student is study abroad, false otherwise
     */
    public boolean getStudyAbroad(){
        return studyAbroad;
    }

    /**
     * Setter method for study abroad status
     * This only sets it to true because the project description doesn't say a student can go from study-abroad to
     * non study-abroad.
     */
    public void setStudyAbroad() {
        if (this.getCreditHours() > maxCreds) {
            this.setCreditHours(maxCreds);
        }
        this.studyAbroad = true;
    }

    public void setNotStudyAbroad() {
        this.studyAbroad = false;
    }


    /**
     * Parameterized constructor for an international student that calls the super constructor.
     *
     * @param name  Name of the student in string form
     * @param major Major of the student in string form
     */
    public International(String name, String major) {
        super(name, major);
    }

    /**
     * /**
     * Parameterized constructor for an international student that calls the super constructor.
     *
     * @param name        Name of the student in string form
     * @param major       Major of the student in string form
     * @param creditHours Credit hours the student is taking in int form
     * @param studyAbroad True if they are study abroad, false otherwise
     */
    public International(String name, String major, int creditHours, boolean studyAbroad) {
        super(name, major, creditHours);
        this.studyAbroad = studyAbroad;
    }

    /**
     * A helper method that returns whether a student is study abroad status or not in string form
     *
     * @return "study abroad" if they are study abroad, nothing otherwise
     */
    private String studyAbroadString() {
        if (studyAbroad) {
            return ":study abroad";
        } else {
            return "";
        }
    }

    /**
     * The calculation method for the tuition of a non-resident student.
     */
    @Override
    public void tuitionDue() {
        double total;
        if (this.getTuitionDue() == 0 && this.getTotalPayment() == 0) {
            if (!studyAbroad) {
                if (this.getCreditHours() <= extraCredits) {
                    //less than 16 hours calculation (internationals cannot be part time)
                    total = baseTuition + universityFee + additionalFee;
                } else {
                    //more than 16 hours calculation
                    total = baseTuition + universityFee + additionalFee + (this.getCreditHours() - extraCredits) *
                            creditHourTuition;
                }
            } else {
                //if study abroad calculation
                total = universityFee + additionalFee;
            }
            this.setTuitionDue(total);
        }
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
        return this.getProfile().toString() + ":" + this.getCreditHours() + " credit hours:tuition due:" +
                dec.format(this.getTuitionDue()) + ":total payment:" + dec.format(this.getTotalPayment()) +
                ":last payment date: " + this.getLastPaid().dateString() + ":non-resident:international" +
                this.studyAbroadString();
    }
}