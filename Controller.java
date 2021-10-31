package com.example.smproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.time.format.DateTimeFormatter;

public class HelloController {

    private Roster roster = new Roster();

    int minCredits = 3;
    int maxCredits = 24;
    int zero = 0;

    @FXML
    private ToggleGroup Major;

    @FXML
    private Button addButton;

    @FXML
    private RadioButton baButton;

    @FXML
    private RadioButton baButton2;

    @FXML
    private Button calculateButton;

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
    private Button printButton;

    @FXML
    private Button printByNameButton;

    @FXML
    private Button printByPaymentButton;

    @FXML
    private Button removeButton;

    @FXML
    private RadioButton residentButton;

    @FXML
    private Button setFinAidButton;

    @FXML
    private CheckBox studyAbroadButton;

    @FXML
    private RadioButton triStateButton;

    @FXML
    private ToggleGroup triStateType;

    @FXML
    private TextField tuitionDueAmt;

    @FXML
    private Button tuitionDueButton;



    @FXML
    boolean creditChecker() {
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
        if (nonResidentButton.isSelected() && triStateButton.isSelected()) ;
        {
            if (tri == null) {
                output.appendText("Tri-state data missing.\n");
                return false;
            }
        }
        return true;
    }


    @FXML
    void addButtonClick(ActionEvent event) {
        if (creditChecker() && dataChecker()) {
            int numCreditHrs = Integer.parseInt(creditHrs.getText());
            RadioButton major = (RadioButton) majors.getSelectedToggle();
            String stuMajor = major.getText();
            String stuName = nameEnter.getText();
            RadioButton status = (RadioButton) ifResident.getSelectedToggle();
            String statusText = status.getText();
            output.appendText(statusText);
            /*if (statusText.equals("Resident")) {
                Resident resident = new Resident(stuName, stuMajor, numCreditHrs);
                if (!roster.add(resident)) {
                    output.appendText("Student is already in the roster.\n");
                } else {
                    //roster.add(resident); //(try)
                    output.appendText("Student added.\n");
                }
            }*/
        }
    }

    @FXML
    void intButtonClicked(ActionEvent event) {
        studyAbroadButton.setDisable(false);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        nyButton.setSelected(false);
        ctButton.setSelected(false);
    }

    @FXML
    void nonResidentButtonClick(ActionEvent event) {
        triStateButton.setDisable(false);
        internationalButton.setDisable(false);
    }

    @FXML
    void removeButtonClick(ActionEvent event) {

    }

    @FXML
    void residentButtonClick(ActionEvent event) {
        triStateButton.setDisable(true);
        internationalButton.setDisable(true);
        studyAbroadButton.setDisable(true);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        triStateButton.setSelected(false);
        internationalButton.setSelected(false);
        studyAbroadButton.setSelected(false);
        nyButton.setSelected(false);
        ctButton.setSelected(false);
    }

    @FXML
    void triStateButtonClicked(ActionEvent event) {
        nyButton.setDisable(false);
        ctButton.setDisable(false);
        studyAbroadButton.setDisable(true);
        internationalButton.setSelected(false);
        studyAbroadButton.setSelected(false);
    }

    @FXML
    void tuitionDueButtonClick(ActionEvent event) {

    }

    //2nd Tab Methods Here
    @FXML
    void financialAidEnter(KeyEvent event) {
        setFinAidButton.setDisable(false);
        payButton.setDisable(true);
        paymentEnter.clear();
        paymentDateEnter.setValue(null);
    }

    @FXML
    void paymentAmountEnter(KeyEvent event) {
        payButton.setDisable(false);
        setFinAidButton.setDisable(true);
        finAidAmountEnter.clear();
    }

    @FXML
    public boolean dataChecker2() {
        String stuName = nameEnter2.getText();
        if (stuName.isEmpty()) {
            output.appendText("Name data missing.\n");
            return false;
        }
        RadioButton major = (RadioButton) Major.getSelectedToggle();
        if (major == null) {
            output.appendText("Major data missing.\n");
            return false;
        }
        return true;
    }

    public boolean dateChecker(){
        if (paymentDateEnter.getValue() == null){
            output.appendText("Date missing.\n");
            return false;
        }
        else{
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
        RadioButton major2 = (RadioButton) Major.getSelectedToggle();
        String major = major2.getText();
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
    }

    @FXML
    void payTuition(ActionEvent event) {
        if (dataChecker2()) {
            String name = nameEnter2.getText();
            RadioButton major2 = (RadioButton) Major.getSelectedToggle();
            String major = major2.getText();
            double paymentAmount = Double.parseDouble(paymentEnter.getText());
            if (dateChecker()){
                Date date = new Date(paymentDateEnter.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                if (!date.isValid()) {
                    output.appendText("Payment date invalid.\n");
                }
                else {
                    if (paymentAmount > 0.0) {
                        Student student = new Student(name, major);
                        if (roster.find(student) == -1){
                            output.appendText("Student not found.\n");
                        }
                        else if (!roster.payTuition(student, paymentAmount, date)) {
                            output.appendText("Amount is greater than the amount due.\n");
                        } else {
                            output.appendText("Payment applied.\n");
                        }
                    }
                    else{
                        output.appendText("Payment amount invalid.\n");
                    }
                }
            }
        }
    }

    //for third tab

    @FXML
    void calculateTuition(ActionEvent event) {

    }

    @FXML
    void printRoster(ActionEvent event) {

    }

    @FXML
    void printRosterByName(ActionEvent event) {

    }

    @FXML
    void printRosterByPayment(ActionEvent event) {

    }
}
