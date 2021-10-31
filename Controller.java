package com.example.smproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    private Roster roster;

    int minCredits = 3;
    int maxCredits = 24;
    int zero = 0;

    @FXML
    private Button addButton;

    @FXML
    private TextField creditHrs;

    @FXML
    private ToggleGroup ifResident;

    @FXML
    private RadioButton internationalButton;

    @FXML
    private ToggleGroup majors;

    @FXML
    private RadioButton meButton;

    @FXML
    private TextField nameEnter;

    @FXML
    private ToggleGroup nonResType;

    @FXML
    private RadioButton nonResidentButton;

    @FXML
    private RadioButton nyButton;

    @FXML
    private RadioButton ctButton;

    @FXML
    private TextArea output;

    @FXML
    private Button removeButton;

    @FXML
    private RadioButton residentButton;

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

}

