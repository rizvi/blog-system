package com.mit.du.admin.AdminPages.EditCustomerEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mit.du.common.CommonUtil;
import com.mit.du.common.DBUtil;
import com.mit.du.common.tableview.AdminEmployeeTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeInfoEdit implements Initializable {

    public Button UserConfirm;
    @FXML
    private TextField UserNameEdit;

    @FXML
    private TextField UserNidEdit;

    @FXML
    private TextField UserEmailEdit;

    @FXML
    private TextField UserPhoneEdit;

    @FXML
    private TextField UserPassEdit;

    @FXML
    private TextArea UserAddressEdit;

    @FXML
    void UserConfirmEdit(ActionEvent event) throws IOException, SQLException {
        Connection connection = DBUtil.getConnections();
        String employeeName = UserNameEdit.getText();
        String employeeNID = UserNidEdit.getText();
        String employeePassword = UserPassEdit.getText();
        String employeeEmail = UserEmailEdit.getText();
        String employeePhone = UserPhoneEdit.getText();
        String employeeAddress = UserAddressEdit.getText();
        if (employeeName.isEmpty() || employeeNID.isEmpty() || employeePassword.isEmpty() || employeePhone.isEmpty() || employeeAddress.isEmpty()) {
            CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE EMPLOYEEINFO SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, employeeName);
            preparedStatementUpdate.setString(2, employeePassword);
            preparedStatementUpdate.setString(3, employeeEmail);
            preparedStatementUpdate.setString(4, employeePhone);
            preparedStatementUpdate.setString(5, employeeAddress);
            preparedStatementUpdate.setString(6, employeeNID);
            try{
                preparedStatementUpdate.execute();
//                CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");
//                CommonUtil.pageNavigation("UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
                Stage stage = (Stage) UserConfirm.getScene().getWindow();
                stage.close();
            } catch (SQLException e){
                CommonUtil.showAlert(Alert.AlertType.ERROR, "Error", "Maybe Sql Error!");
            } finally {
                DBUtil.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void BackBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    public void setEmployeeInfo(AdminEmployeeTable adminEmployeeTable) {
        UserNameEdit.setText(adminEmployeeTable.getName());
        UserNidEdit.setText(adminEmployeeTable.getNID());
        UserNidEdit.setDisable(true);
        UserEmailEdit.setText(adminEmployeeTable.getEmail());
        UserPhoneEdit.setText(adminEmployeeTable.getPhone());
        UserPassEdit.setText(adminEmployeeTable.getPass());
        UserAddressEdit.setText(adminEmployeeTable.getAddress());
    }
}
