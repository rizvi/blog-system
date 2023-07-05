package com.mit.du.admin.AdminPages;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.mit.du.common.CommonUtil;
import com.mit.du.common.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminAddEmployee {

    public TextField EmployeeNameField;
    public TextField EmployeeEmailField;
    public TextField EmployeePhoneField;
    public TextField EmployeeAddressField;
    public TextField EmployeeNIDField;
    public TextField EmployeePassField;
    Connection connection = DBUtil.getConnections();
    public void CreateEmployee(ActionEvent actionEvent) throws SQLException {
        String employeeName = EmployeeNameField.getText();
        String employeeNID = EmployeeNIDField.getText();
        String employeePassword = EmployeePassField.getText();
        String employeeEmail = EmployeeEmailField.getText();
        String employeePhone = EmployeePhoneField.getText();
        String employeeAddress = EmployeeAddressField.getText();

        if (employeeName.isEmpty() || employeeNID.isEmpty() || employeePassword.isEmpty() || employeeEmail.isEmpty() || employeeAddress.isEmpty() || employeePhone.isEmpty()) {
            CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "INSERT INTO EMPLOYEEINFO(NAME, NID, PASSWORD, EMAIL, PHONE, ADDRESS) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeName);
            preparedStatement.setString(2, employeeNID);
            preparedStatement.setString(3, employeePassword);
            preparedStatement.setString(4, employeeEmail);
            preparedStatement.setString(5, employeePhone);
            preparedStatement.setString(6, employeeAddress);
            try{
                preparedStatement.execute();
                CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Successful", "Blogger is created successfully!");
                //CommonUtil.pageNavigation("UserLogin.fxml", Main.stage,this.getClass(),"User Home", 600, 400);
            } catch (SQLException e){
                CommonUtil.showAlert(Alert.AlertType.ERROR, "Error", "Account already exists with this NID!");
            } finally {
                DBUtil.closeConnections();
            }
        }

    }
}
