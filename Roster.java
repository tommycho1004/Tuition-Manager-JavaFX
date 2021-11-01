package com.example.smproject3;

/**
 * The Roster class used as the container for students.
 * This class contains methods to perform several functions to the roster of students.
 *
 * @author Tommy Cho, Neha Gudur
 */

public class Roster {
    private Student[] roster = new Student[4];
    private int size = 0; //keep track of the number of students in the roster

    /**
     * A getter for the number of students in the roster
     *
     * @return int of students in the roster
     */
    public int getSize() {
        return size;
    }

    /**
     * A method to find a student and return which index they're in in the roster
     *
     * @param student Student who is being found
     * @return int index of student if found, -1 otherwise
     */
    public int find(Student student) {
        for (var i = 0; i < size; i++) {
            if (roster[i].getProfile().equals(student.getProfile())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * A method to grow the list of students.
     * The roster grows by 4 everytime a cap is reached.
     */
    private void grow() {
        Student[] newList = new Student[size + 4];
        for (int i = 0; i < size; i++) {
            newList[i] = roster[i];
        }
        roster = newList;
    }

    /**
     * A method to add the student to the roster.
     *
     * @param student the student being added
     * @return true if the student is added, false otherwise
     */
    public boolean add(Student student) {
        if (find(student) == -1) {
            size++;
            if (size % 4 == 0) {
                grow();
            }
            roster[size - 1] = student;
            return true;
        }
        return false;
    }

    /**
     * A method to remove a student from the roster.
     *
     * @param student the student being removed
     * @return true if the student is removed, false otherwise
     */
    public boolean remove(Student student) {
        if (size == 0) {
            return false;
        }
        if (find(student) != -1) {
            for (var j = find(student); j < size; j++) {
                roster[j] = roster[j + 1];
                if (roster[j] == null) {
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A method to print the roster of students as is.
     */
    public String print() {
        //System.out.println("* list of students in the roster **");
        if (size == 0) {
            return "Student roster is empty!\n";
        }
        String result = "* list of students in the roster **";
        for (int i = 0; i < size; i++) {
            //System.out.println(roster[i].toString());
            result = result + "\n" + roster[i].toString();
        }
        //System.out.println("* end of roster **");
        result = result + "\n * end of roster **\n";
        return result;
    }

    /**
     * A method to print the roster of students by order of alphabetical first name
     */
    public String printByName() {
        if (size == 0) {
            return "Student roster is empty!\n";
        }
        Student[] newArray = roster;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (newArray[j].getProfile().compareTo(newArray[j + 1].getProfile()) > 0) {//j happened before i
                    Student temp = newArray[j];
                    newArray[j] = newArray[j + 1];
                    newArray[j + 1] = temp;
                }
            }
        }
        //System.out.println("* list of students ordered by name **");
        String result = "* list of students ordered by name **";
        for (int i = 0; i < size; i++) {
            //System.out.println(newArray[i].toString());
            result = result + "\n" + newArray[i].toString();
        }
        //System.out.println("* end of roster **");
        result = result + "\n * end of roster **\n";
        return result;
    }

    /**
     * A method to print the roster of students by the date they paid
     * Only prints students who have paid some amount already
     */
    public String printByPaymentDate() {
        if (size == 0) {
            return "Student roster is empty!\n";
        }
        int paymentSize = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getMadePayment()) {
                paymentSize++;
            }
        }
        if (paymentSize == 0) {
            //System.out.println("There are no students who have paid yet.");
            return "There are no students who have paid yet.\n";
        } else {
            Student[] newArray = new Student[paymentSize];
            int k = 0;
            for (int i = 0; i < size; i++) {
                if (roster[i].getMadePayment()) {
                    newArray[k] = roster[i];
                    k++;
                }
            }
            for (int i = 0; i < paymentSize - 1; i++) {
                for (int j = 0; j < paymentSize - i - 1; j++) {
                    if (newArray[j].getLastPaid().compareTo(newArray[j + 1].getLastPaid()) > 0) {//j happened before i
                        Student temp = newArray[j];
                        newArray[j] = newArray[j + 1];
                        newArray[j + 1] = temp;
                    }
                }
            }
            //System.out.println("* list of students made payments ordered by payment date **");
            String result = "* list of students ordered by name **";
            for (int i = 0; i < paymentSize; i++) {
                //System.out.println(newArray[i].toString());
                result = result + "\n" + newArray[i].toString();
            }
            //System.out.println("* end of roster **");
            result = result + "\n * end of roster **\n";
            return result;
        }
    }

    /**
     * A method to calculate the initial tuition of a student who hasn't been set yet
     */
    public void calculate() {
        for (int i = 0; i < size; i++) {
            if (roster[i] instanceof International) {
                roster[i].tuitionDue();
            } else if (roster[i] instanceof TriState) {
                roster[i].tuitionDue();
            } else if (roster[i] instanceof NonResident) {
                roster[i].tuitionDue();
            } else if (roster[i] instanceof Resident) {
                roster[i].tuitionDue();
            }
        }
    }

    /**
     * A method that updates a student's payment towards their tuition
     *
     * @param student       Student that is paying their tuition
     * @param paymentAmount amount the student is paying in double form
     * @param date          Date the student is paying on
     * @return true if payment was successful, false otherwise
     */
    public boolean payTuition(Student student, double paymentAmount, Date date) { // false if amount greater than amount due
        if (find(student) != -1) {
            if (paymentAmount <= roster[find(student)].getTuitionDue()) {
                roster[find(student)].payTuition(paymentAmount, date);
                return true;
            } else return false; //the amount is greater than the amount due
        }
        return false; //didn't find the student
    }

    /**
     * A method that sets the study abroad status of an international student
     *
     * @param student Student who is studying abroad
     * @return true if the setting was successful, false otherwise
     */
    public boolean setStudyAbroad(Student student) {
        if (find(student) != -1) {
            if (roster[find(student)] instanceof International) {
                ((International) roster[find(student)]).setStudyAbroad();
                if (roster[find(student)].getCreditHours() > 12) {
                    roster[find(student)].setCreditHours(12);
                }
                roster[find(student)].setTotalPayment(0);
                roster[find(student)].setTuitionDue(0);
                Date temp = new Date("0/0/0");
                roster[find(student)].setLastPaid(temp);
                roster[find(student)].setMadePayment(false);
                roster[find(student)].tuitionDue();
                return true;
            } else return false; //student is not international
        } else return false; //student is not found
    }

    public boolean setNotStudyAbroad(Student student) {
        if (find(student) != -1) {
            if (student instanceof International) {
                ((International) student).setNotStudyAbroad();
                if (student.getCreditHours() > 12) {
                    student.setCreditHours(12);
                }
                student.setTotalPayment(0);
                student.setTuitionDue(0);
                Date temp = new Date("0/0/0");
                student.setLastPaid(temp);
                student.tuitionDue();
                return true;
            } else return false;
        } else return false;
    }

    /**
     * A method for the command to set financial aid of a resident student.
     *
     * @param student      Student that is being granted financial aid.
     * @param financialAid amount they are awarded
     * @return 4 if successful, 3 if the student is part time (unsuccessful),
     * 2 if the student already received it (unsuccessful), 1 if the student isn't a resident (unsuccessful)
     * and 0 if the student is not found (unsuccessful)
     */
    public int setFinancialAid(Student student, double financialAid) {

        if (find(student) != -1) {
            if (roster[find(student)] instanceof Resident) {
                if (!((Resident) roster[find(student)]).getFinancialAid()) {
                    //((Resident) student).getFinancialAid() == false)
                    if (roster[find(student)].getIsFullTime()) {
                        ((Resident) roster[find(student)]).receiveFinAid(financialAid);
                        return 4;
                    } else return 3;
                } else return 2;
            } else return 1;
        } else return 0;
    }

    public Student place(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return roster[i];
            }
        }
        return null;
    }
}


