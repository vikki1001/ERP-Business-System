package com.ksv.inventory.controller.employee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import com.ksv.inventory.entity.Employee;
import com.ksv.inventory.interfaces.EmployeeInterface;
import com.ksv.inventory.model.EmployeeModel;

public class EditController implements Initializable, EmployeeInterface {

    @FXML
    private TextField firstField, lastField, usernameField, phoneField, emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private long selectedEmployeeId;
    private EmployeeModel employeeModel;
    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeModel = new EmployeeModel();
        resetValues();
    }

    public void setEmployee(Employee employee, long selectedEmployeeId) {
        this.employee = employee;
        this.selectedEmployeeId = selectedEmployeeId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Employee editedEmployee = new Employee(
                    employee.getId(),
                    firstField.getText(),
                    lastField.getText(),
                    emailField.getText(),
                    usernameField.getText(),
                    DigestUtils.sha1Hex(passwordField.getText()),
                    phoneField.getText(),
                    addressArea.getText()
            );

            employeeModel.updateEmployee(editedEmployee);
            EMPLOYEELIST.set((int) selectedEmployeeId, editedEmployee);

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Employee Updated!");
            alert.setContentText("Employee is updated successfully");
            alert.showAndWait();
        }
    }

    private void setData() {
        firstField.setText(employee.getFirstName());
        lastField.setText(employee.getLastName());
        emailField.setText(employee.getEmail());
        usernameField.setText(employee.getUserName());
        passwordField.setText(employee.getPassword());
        phoneField.setText(employee.getPhone());
        addressArea.setText(employee.getAddress());
    }

    private void resetValues() {
        firstField.setText("");
        lastField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (firstField.getText() == null || firstField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }

        if (lastField.getText() == null || lastField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }

        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }

        if (addressArea.getText() == null || addressArea.getText().length() == 0) {
            errorMessage += "No email address!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
