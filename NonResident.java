package Project3;
import java.text.DecimalFormat;


/**
 * The Non Resident subclass that extends from the Student superclass.
 * Use this class to calculate the tuition due for a non-resident.
 *
 * @author Tommy Cho, Neha Gudur
 */
public class NonResident extends Student {
    protected static double baseTuition = 29737.0;
    protected static double creditHourTuition = 966.0;

    /**
     * Parameterized constructor for a non-resident that calls the super constructor.
     *
     * @param name  Name of the student in string form
     * @param major Major of the student in string form
     */
    public NonResident(String name, String major) {
        super(name, major);
    }

    /**
     * Parameterized constructor for a non-resident that calls the super constructor.
     *
     * @param name        Name of the student in string form
     * @param major       Major of the student in string form
     * @param creditHours Credit hours the student is taking in int form
     */
    public NonResident(String name, String major, int creditHours) {
        super(name, major, creditHours);
    }

    /**
     * The calculation method for the tuition of a non-resident student.
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
                    //full time more than 16 credits calculation
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
                ":last payment date: " + this.getLastPaid().dateString() + ":non-resident";
    }
}
