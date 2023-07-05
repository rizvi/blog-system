package com.mit.du.zadmin.Login;

import com.mit.du.backend.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.mit.du.backend.DBUtil;
import com.mit.du.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {

    public TextField adminNIDField;
    public PasswordField adminPassField;
    public static String currentAdminNID;
    public ImageView closeWindow;

    public void LoginAdminHome(ActionEvent actionEvent) throws IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String adminNID = adminNIDField.getText();
        currentAdminNID = adminNID;
        String adminPass = adminPassField.getText();
        try {
            connection = DBUtil.getConnections();
            if (adminNID.isEmpty() || adminPass.isEmpty()) {
                CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
            } else {
                String sql = "SELECT * FROM ADMININFO WHERE NID = ? AND PASSWORD = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, adminNID);
                preparedStatement.setString(2, adminPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonUtil.pageNavigation("/com/mit/du/zadmin/AdminPages/AdminMain.fxml", Main.stage,this.getClass(),"Admin Dashboard", 1000, 600);
                } else {
                    CommonUtil.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.closeConnections();
        }
        }

    public void BackToMain(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/resources/fxml/sample.fxml", Main.stage,this.getClass(),"Blog Management System", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
