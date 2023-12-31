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
import com.mit.du.common.tableview.AdminCustomerTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerInfoEdit implements Initializable {

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
        String customerName = UserNameEdit.getText();
        String customerNID = UserNidEdit.getText();
        String customerPassword = UserPassEdit.getText();
        String customerEmail = UserEmailEdit.getText();
        String customerPhone = UserPhoneEdit.getText();
        String customerAddress = UserAddressEdit.getText();
        if (customerName.isEmpty() || customerNID.isEmpty() || customerPassword.isEmpty() || customerEmail.isEmpty() || customerAddress.isEmpty()) {
            CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE CUSTOMERINFO SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, customerName);
            preparedStatementUpdate.setString(2, customerPassword);
            preparedStatementUpdate.setString(3, customerEmail);
            preparedStatementUpdate.setString(4, customerPhone);
            preparedStatementUpdate.setString(5, customerAddress);
            preparedStatementUpdate.setString(6, customerNID);
            try{
                preparedStatementUpdate.execute();
//                CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");
//                CommonUtil.pageNavigation("UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
                Stage stage = (Stage) UserConfirm.getScene().getWindow();
                stage.close();
            } catch (SQLException e){
                CommonUtil.showAlert(Alert.AlertType.ERROR, "Error", "Maybe Sql error!");
            } finally {
                DBUtil.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCustomerInfo(AdminCustomerTable adminCustomerTable){
                    UserNameEdit.setText(adminCustomerTable.getName());
                    UserNidEdit.setText(adminCustomerTable.getNID());
                    UserNidEdit.setDisable(true);
                    UserEmailEdit.setText(adminCustomerTable.getEmail());
                    UserPhoneEdit.setText(adminCustomerTable.getPhone());
                    UserPassEdit.setText(adminCustomerTable.getPass());
                    UserAddressEdit.setText(adminCustomerTable.getAddress());
    }

    public void BackBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
         }
}
