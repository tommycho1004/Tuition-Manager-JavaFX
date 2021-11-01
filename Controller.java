package com.example.smproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

public class HelloController {


    private Roster roster = new Roster();

    int minCredits = 3;
    int maxCredits = 24;
    int zero = 0;

    @FXML
    private Button addButton;

    @FXML
    private Button addInitialButton;

    @FXML
    private RadioButton baButton;

    @FXML
    private RadioButton baButton2;

    @FXML
    private Button calculateButton;

    @FXML
    private Button calculateInitialButton;

    @FXML
    private TextField creditHrs;

    @FXML
    private RadioButton csButton;

    @FXML
    private RadioButton csButton2;

    @FXML
    private RadioButton ctButton;

    @FXML
    private RadioButton eeButton;

    @FXML
    private RadioButton eeButton2;

    @FXML
    private TextField finAidAmountEnter;

    @FXML
    private ToggleGroup ifResident;

    @FXML
    private RadioButton internationalButton;

    @FXML
    private RadioButton itButton;

    @FXML
    private RadioButton itButton2;

    @FXML
    private ToggleGroup majors;

    @FXML
    private ToggleGroup majorsMan;

    @FXML
    private Button manageButton;

    @FXML
    private RadioButton meButton;

    @FXML
    private RadioButton meButton2;

    @FXML
    private TextField nameEnter;

    @FXML
    private TextField nameEnter2;

    @FXML
    private ToggleGroup nonResType;

    @FXML
    private RadioButton nonResidentButton;

    @FXML
    private RadioButton nyButton;

    @FXML
    private TextArea output;

    @FXML
    private Button payButton;

    @FXML
    private DatePicker paymentDateEnter;

    @FXML
    private TextField paymentEnter;

    @FXML
    private Label paymentSign;

    @FXML
    private Button printButton;

    @FXML
    private Button printByNameButton;

    @FXML
    private Button printByPaymentButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button removeInitialButton;

    @FXML
    private RadioButton residentButton;

    @FXML
    private Button setFinAidButton;

    @FXML
    private Button setNotStudyAbroadButton;

    @FXML
    private Button setStudyAbroadButton;

    @FXML
    private CheckBox studyAbroadButton;

    @FXML
    private RadioButton triStateButton;

    @FXML
    private ToggleGroup triStateType;

    @FXML
    private Button tuitionDueButton;

    @FXML
    private Label tuitionTitle;

    @FXML
    boolean creditChecker() {
        String credit = creditHrs.getText();
        if (credit.isEmpty()) {
            output.appendText("Credit hours missing.\n");
            return false;
        }
        try {
            int numCreditHrs = Integer.parseInt(creditHrs.getText());
        } catch (NumberFormatException e) {
            output.appendText("Invalid credit hours.\n");
            return false;
        }
        // add if credits checker is empty
        int numCreditHrs = Integer.parseInt(creditHrs.getText());
        if (numCreditHrs < zero) {
            output.appendText("Credit hours cannot be negative.\n");
            return false;
        } else if (numCreditHrs < minCredits) {
            output.appendText("Minimum credit hours is 3.\n");
            return false;
        } else if (numCreditHrs > maxCredits) {
            output.appendText("Credit hours exceed the maximum 24.\n");
            return false;
        } else return true;
    }

    @FXML
    public boolean dataChecker() {
        String stuName = nameEnter.getText();
        if (stuName.isEmpty()) {
            output.appendText("Name data missing.\n");
            return false;
        }
        RadioButton major = (RadioButton) majors.getSelectedToggle();
        if (major == null) {
            output.appendText("Major data missing.\n");
            return false;
        }
        RadioButton status = (RadioButton) ifResident.getSelectedToggle();
        if (status == null) {
            output.appendText("Student status data missing.\n");
            return false;
        }
        RadioButton tri = (RadioButton) triStateType.getSelectedToggle();
        if (nonResidentButton.isSelected() && triStateButton.isSelected()) {
            if (tri == null) {
                output.appendText("Tri-state data missing.\n");
                return false;
            }
        }
        return true;
    }

    @FXML
    void clearer() {
        nameEnter.clear();
        majors.selectToggle(null);
        ifResident.selectToggle(null);
        nonResType.selectToggle(null);
        triStateType.selectToggle(null);
        studyAbroadButton.setSelected(false);
        creditHrs.clear();
        tuitionDueButton.setVisible(false);
        removeButton.setVisible(false);
        addButton.setVisible(false);
        tuitionDueButton.setDisable(true);
        removeButton.setDisable(true);
        addButton.setDisable(true);
        nameEnter.setDisable(true);
        eeButton.setDisable(true);
        csButton.setDisable(true);
        meButton.setDisable(true);
        itButton.setDisable(true);
        baButton.setDisable(true);
        residentButton.setDisable(true);
        nonResidentButton.setDisable(true);
        triStateButton.setDisable(true);
        internationalButton.setDisable(true);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        studyAbroadButton.setDisable(true);
        creditHrs.setDisable(true);
    }


    @FXML
    void addButtonClick(ActionEvent event) {
        if (creditChecker() && dataChecker()) {
            int numCreditHrs = Integer.parseInt(creditHrs.getText());
            RadioButton major = (RadioButton) majors.getSelectedToggle();
            String stuMajor = major.getText();
            String stuName = nameEnter.getText();
            RadioButton status = (RadioButton) ifResident.getSelectedToggle();
            if (status.getText().equals("Resident")) {
                Resident resident = new Resident(stuName, stuMajor, numCreditHrs);
                if (!roster.add(resident)) {
                    output.appendText("Student is already in the roster.\n");
                } else {
                    output.appendText("Student added.\n");
                }
            }
            if (status.getText().equals("Non-Resident")) {
                if (nonResType.getSelectedToggle() == null) {
                    NonResident nonResident = new NonResident(stuName, stuMajor, numCreditHrs);
                    if (!roster.add(nonResident)) {
                        output.appendText("Student is already in the roster.\n");
                    } else {
                        output.appendText("Student added.\n");
                    }
                } else {
                    RadioButton nonResidentType = (RadioButton) nonResType.getSelectedToggle();
                    if (nonResidentType.getText().equals("TriState")) {
                        RadioButton state = (RadioButton) triStateType.getSelectedToggle();
                        String state1 = null;
                        if (state.getText().equals("New York")) {
                            state1 = "ny";
                        }
                        if (state.getText().equals("Connecticut")) {
                            state1 = "ct";
                        }
                        TriState triState = new TriState(stuName, stuMajor, numCreditHrs, state1);
                        if (!roster.add(triState)) {
                            output.appendText("Student is already in the roster.\n");
                        } else {
                            output.appendText("Student added.\n");
                        }
                    } else if (nonResidentType.getText().equals("International")) {
                        International international = new International(stuName, stuMajor, numCreditHrs, studyAbroadButton.isSelected());
                        if (numCreditHrs < 12) {
                            output.appendText("International students must enroll at least 12 credits.\n");
                        } else if (!roster.add(international)) {
                            output.appendText("Student is already in the roster.\n");
                        } else {
                            output.appendText("Student added.\n");
                        }
                    }
                }
            }
        }
        clearer();
    }

    @FXML
    void addInitialButtonClick(ActionEvent event) {
        clearer();
        nameEnter.setDisable(false);
        eeButton.setDisable(false);
        csButton.setDisable(false);
        meButton.setDisable(false);
        itButton.setDisable(false);
        baButton.setDisable(false);
        residentButton.setDisable(false);
        nonResidentButton.setDisable(false);
        creditHrs.setDisable(false);
        addButton.setVisible(true);
        addButton.setDisable(false);
    }

    @FXML
    void calculateInitialButtonClicked(ActionEvent event) {
        clearer();
        nameEnter.setDisable(false);
        eeButton.setDisable(false);
        csButton.setDisable(false);
        meButton.setDisable(false);
        itButton.setDisable(false);
        baButton.setDisable(false);
        residentButton.setDisable(false);
        nonResidentButton.setDisable(false);
        creditHrs.setDisable(false);
        tuitionDueButton.setVisible(true);
        tuitionDueButton.setDisable(false);
    }

    @FXML
    void removeInitialButtonClicked(ActionEvent event) {
        clearer();
        nameEnter.setDisable(false);
        eeButton.setDisable(false);
        csButton.setDisable(false);
        meButton.setDisable(false);
        itButton.setDisable(false);
        baButton.setDisable(false);
        removeButton.setVisible(true);
        removeButton.setDisable(false);
    }

    @FXML
    void intButtonClicked(ActionEvent event) {
        studyAbroadButton.setDisable(false);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        triStateType.selectToggle(null);
    }

    @FXML
    void nonResidentButtonClick(ActionEvent event) {
        triStateButton.setDisable(false);
        internationalButton.setDisable(false);
    }

    @FXML
    void removeButtonClick(ActionEvent event) {
        String stuName = nameEnter.getText();
        if (stuName.isEmpty()) {
            output.appendText("Name data missing.\n");
            return;
        }
        RadioButton major = (RadioButton) majors.getSelectedToggle();
        if (major == null) {
            output.appendText("Major data missing.\n");
            return;
        }
        Student temp = new Student(stuName, major.getText());
        if (!roster.remove(temp)) {
            output.appendText("Student is not in the roster.\n");
        } else {
            output.appendText("Student removed from the roster.\n");
        }
        clearer();
    }

    @FXML
    void residentButtonClick(ActionEvent event) {
        triStateButton.setDisable(true);
        internationalButton.setDisable(true);
        studyAbroadButton.setDisable(true);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        nonResType.selectToggle(null);
        studyAbroadButton.setSelected(false);
        triStateType.selectToggle(null);
    }

    @FXML
    void triStateButtonClicked(ActionEvent event) {
        nyButton.setDisable(false);
        ctButton.setDisable(false);
        studyAbroadButton.setDisable(true);
        studyAbroadButton.setSelected(false);
    }

    @FXML
    void tuitionDueButtonClick(ActionEvent event) {
        if (creditChecker() && dataChecker()) {
            int numCreditHrs = Integer.parseInt(creditHrs.getText());
            RadioButton major = (RadioButton) majors.getSelectedToggle();
            String stuMajor = major.getText();
            String stuName = nameEnter.getText();
            RadioButton status = (RadioButton) ifResident.getSelectedToggle();
            Student student = new Student(stuName, stuMajor);
            if (status.getText().equals("Resident")) {
                Resident resident = new Resident(stuName, stuMajor, numCreditHrs);
                resident.tuitionDue();
                String tuition = String.valueOf(resident.getTuitionDue());
                output.appendText("Tuition Due: " + tuition + "\n");
            } else if (status.getText().equals("Non-Resident")) {
                if (nonResType.getSelectedToggle() == (null)) {
                    NonResident nonResident = new NonResident(stuName, stuMajor, numCreditHrs);
                    nonResident.tuitionDue();
                    String tuition = String.valueOf(nonResident.getTuitionDue());
                    output.appendText("Tuition Due: " + tuition + "\n");
                } else {
                    RadioButton nonRes = (RadioButton) nonResType.getSelectedToggle();
                    if (nonRes.getText().equals("TriState")) {
                        RadioButton state = (RadioButton) triStateType.getSelectedToggle();
                        TriState triState = new TriState(stuName, stuMajor, numCreditHrs, state.getText());
                        triState.tuitionDue();
                        String tuition = String.valueOf(triState.getTuitionDue());
                        output.appendText("Tuition Due: " + tuition + "\n");
                    }
                    if (nonRes.getText().equals("International")) {
                        International international = new International(stuName, stuMajor, numCreditHrs, studyAbroadButton.isSelected());
                        international.tuitionDue();
                        String tuition = String.valueOf(international.getTuitionDue());
                        output.appendText("Tuition Due: " + tuition + "\n");
                    }
                }
            }
        }
        clearer();
    }

    //2nd Tab Methods Here

    @FXML
    public boolean dataChecker2() {
        String stuName = nameEnter2.getText();
        if (stuName.isEmpty()) {
            output.appendText("Name data missing.\n");
            return false;
        }
        RadioButton major = (RadioButton) majorsMan.getSelectedToggle();
        if (major == null) {
            output.appendText("Major data missing.\n");
            return false;
        }
        return true;
    }

    public boolean dateChecker() {
        if (paymentDateEnter.getValue() == null) {
            output.appendText("Date missing.\n");
            return false;
        } else {
            return true;
        }
    }

    public boolean financialAidChecker(double financialAid) {
        if (financialAid < 0 || financialAid > 10000) {
            output.appendText("Invalid amount.");
            return false;
        } else {
            return true;
        }
    }

    @FXML
    void setFinAid(ActionEvent event) {
        String name = nameEnter2.getText();
        RadioButton major2 = (RadioButton) majorsMan.getSelectedToggle();
        String major = major2.getText();
        try {
            double finAidAmount = Double.parseDouble(finAidAmountEnter.getText());
        } catch (NumberFormatException e) {
            output.appendText("Payment amount invalid.\n");
            clearer2();
            return;
        }
        double finAidAmount = Double.parseDouble(finAidAmountEnter.getText());
        if (financialAidChecker(finAidAmount)) {
            Student temp = new Student(name, major);
            int aidScenario = roster.setFinancialAid(temp, finAidAmount);
            if (aidScenario == 4) {
                output.appendText("Tuition updated.");
            } else if (aidScenario == 0) {
                output.appendText("Student not in the roster.");
            } else if (aidScenario == 1) {
                output.appendText("Not a resident student.");
            } else if (aidScenario == 2) {
                output.appendText("Awarded once already.");
            } else if (aidScenario == 3) {
                output.appendText("Parttime student doesn't qualify for the award.");
            }
        }
        clearer2();
    }

    @FXML
    void payTuition(ActionEvent event) {
        if (dataChecker2()) {
            String name = nameEnter2.getText();
            RadioButton major2 = (RadioButton) majorsMan.getSelectedToggle();
            String major = major2.getText();
            try {
                double paymentAmount = Double.parseDouble(paymentEnter.getText());
            } catch (NumberFormatException e) {
                output.appendText("Payment amount invalid.\n");
                clearer2();
                return;
            }
            double paymentAmount = Double.parseDouble(paymentEnter.getText());
            if (dateChecker()) {
                Date date = new Date(paymentDateEnter.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                if (!date.isValid()) {
                    output.appendText("Payment date invalid.\n");
                } else {
                    if (paymentAmount > 0.0) {
                        Student student = new Student(name, major);
                        if (roster.find(student) == -1) {
                            output.appendText("Student not found.\n");
                        } else if (!roster.payTuition(student, paymentAmount, date)) {
                            output.appendText("Amount is greater than the amount due.\n");
                        } else {
                            output.appendText("Payment applied.\n");
                        }
                    } else {
                        output.appendText("Payment amount invalid.\n");
                    }
                }
            }
        }
        clearer2();
    }

    @FXML
    void setNotStudyAbroad(ActionEvent event) {
        String name = nameEnter2.getText();
        RadioButton majors = (RadioButton) majorsMan.getSelectedToggle();
        String major = majors.getText();
        International temp = new International(name, major);
        if (!roster.setNotStudyAbroad(temp)) {
            output.appendText("Couldn't find the international student.\n");
        } else {
            output.appendText("Tuition updated.\n");
        }
        clearer2();
    }

    @FXML
    void clearer2() {
        nameEnter2.clear();
        majorsMan.selectToggle(null);
        setStudyAbroadButton.setDisable(true);
        setNotStudyAbroadButton.setDisable(true);
        finAidAmountEnter.clear();
        finAidAmountEnter.setDisable(true);
        setFinAidButton.setDisable(true);
        tuitionTitle.setDisable(true);
        paymentSign.setDisable(true);
        paymentEnter.clear();
        paymentEnter.setDisable(true);
        paymentDateEnter.setDisable(true);
        payButton.setDisable(true);
        paymentDateEnter.setValue(null);
    }

    @FXML
    void setStudyAbroad(ActionEvent event) {
        String name = nameEnter2.getText();
        RadioButton majors = (RadioButton) majorsMan.getSelectedToggle();
        String major = majors.getText();
        International temp = new International(name, major);
        if (!roster.setStudyAbroad(temp)) {
            output.appendText("Couldn't find the international student.\n");
        } else {
            output.appendText("Tuition updated.\n");
        }
        clearer();
    }

    @FXML
    void manageButtonClick(ActionEvent event) {
        if (dataChecker2()) {
            String name = nameEnter2.getText();
            RadioButton majors = (RadioButton) majorsMan.getSelectedToggle();
            Student temp = new Student(name, majors.getText());
            Student temp2 = roster.place(temp);
            if (roster.find(temp) == -1) {
                output.appendText("Student not found.\n");
                return;
            } else {
                tuitionTitle.setDisable(false);
                paymentSign.setDisable(false);
                payButton.setDisable(false);
                paymentEnter.setDisable(false);
                paymentDateEnter.setDisable(false);
            }
            if (temp2 instanceof International) {
                setStudyAbroadButton.setDisable(false);
                setNotStudyAbroadButton.setDisable(false);
            }
            if (temp2 instanceof Resident) {
                setFinAidButton.setDisable(false);
                finAidAmountEnter.setDisable(false);
            }
        }
    }

    //for third tab

    @FXML
    void calculateTuition(ActionEvent event) {
        roster.calculate();
        output.appendText("Calculation completed.\n");
    }

    @FXML
    void printRoster(ActionEvent event) {
        output.appendText(roster.print());
    }

    @FXML
    void printRosterByName(ActionEvent event) {
        output.appendText(roster.printByName());
    }

    @FXML
    void printRosterByPayment(ActionEvent event) {
        output.appendText(roster.printByPaymentDate());
    }

}


