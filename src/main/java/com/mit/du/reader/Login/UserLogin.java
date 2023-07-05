package com.mit.du.reader.Login;

import com.jfoenix.controls.JFXDialog;
import com.mit.du.backend.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import com.mit.du.Main;
import com.mit.du.backend.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLogin implements Initializable {

    public TextField customerNIDField;
    public PasswordField customerPassField;
    public static String currentCustomerNID;
    public ImageView closeWindow;

    public StackPane rootPane;
    public AnchorPane rootAnchorPane;

    @FXML
    public void UserLoginn(ActionEvent actionEvent) throws IOException, SQLException {
        Connection connection = DBUtil.getConnections();
        String customerNID = customerNIDField.getText();
        currentCustomerNID = customerNID;
        String customerPass = customerPassField.getText();
        try {

            if (customerNID.isEmpty() == true || customerPass.isEmpty() == true) {
//                CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
                CommonUtil.showJFXAlert(rootPane, rootAnchorPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
            } else {
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerNID);
                preparedStatement.setString(2, customerPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    //CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonUtil.pageNavigation("/com/mit/du/reader/CustomerPages/UserMain.fxml", Main.stage, this.getClass(), "User Dashboard", 800, 400);
                } else {
                    CommonUtil.showJFXAlert(rootPane, rootAnchorPane, "warning", "Login Failed!", "Wrong UserNid/Password !", JFXDialog.DialogTransition.CENTER);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.closeConnections();
        }
    }
    @FXML
    public void UserSignup(ActionEvent mouseEvent) throws IOException {
        CommonUtil.pageNavigation("UserSignup.fxml", Main.stage ,this.getClass(),"User Signup", 600, 400);
    }
@FXML
    public void BackToMain(ActionEvent mouseEvent) throws IOException {
        CommonUtil.pageNavigation("/resources/fxml/sample.fxml", Main.stage,this.getClass(),"Blog Management System", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });


    }
}
