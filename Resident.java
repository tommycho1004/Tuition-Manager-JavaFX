package Project3;
import java.text.DecimalFormat;


/**
 * The Resident subclass that extends from the Student superclass.
 * Use this class to calculate the tuition due for a resident.
 *
 * @author Tommy Cho, Neha Gudur
 */
public class Resident extends Student {
    private boolean financialAid = false; //true if they received it, false if they didn't
    private double financialAidAmount;

    //static doubles for tuitions and fees
    private static double baseTuition = 12536.0;
    private static double creditHourTuition = 404.0;

    /**
     * Parameterized constructor for a resident that calls the super constructor.
     *
     * @param name        Name of the student in string form
     * @param major       Major of the student in string form
     * @param creditHours Credit hours the student is taking in int form
     */
    public Resident(String name, String major, int creditHours) {
        super(name, major, creditHours);
    }

    /**
     * A getter for the financial aid status of a student
     *
     * @return true if received, false otherwise
     */
    public boolean getFinancialAid() {
        return financialAid;
    }

    /**
     * A method that gives a student a specified amount of financial aid and updates their tuition due
     *
     * @param amount amount of financial aid a student receives
     */
    public void receiveFinAid(double amount) {
        double newTuition = this.getTuitionDue() - amount;
        this.setTuitionDue(newTuition);
        this.financialAid = true;
        this.financialAidAmount = amount;
    }

    /**
     * A method to convert financial aid received to a usable string form
     *
     * @return financial aid received in string form
     */
    private String finAidString() {
        if (financialAid) {
            DecimalFormat dec1 = new DecimalFormat("#.00");
            return ":financial aid $" + dec1.format(this.financialAidAmount);
        } else {
            return "";
        }
    }

    /**
     * The calculation method for the tuition of a resident student.
     */
    @Override
    public void tuitionDue() {
        double total;
        if (this.getTuitionDue() == 0 && this.getTotalPayment() == 0) {
            if (this.getIsFullTime()) {
                if (this.getCreditHours() <= extraCredits) {
                    //full time less than 16 credits calculation:
                    total = baseTuition + universityFee;
                } else {
                    //full time more than 16 credits calculation:
                    total = baseTuition + universityFee + (this.getCreditHours() - extraCredits) * creditHourTuition;
                }
            } else {
                //part time calculation
                total = this.getCreditHours() * creditHourTuition + partTimeUniversityFee;
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
                ":last payment date: " + this.getLastPaid().dateString() + ":resident" + this.finAidString();
    }
}
