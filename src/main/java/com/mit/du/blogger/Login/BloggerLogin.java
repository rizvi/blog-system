package com.mit.du.blogger.Login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import com.mit.du.Main;
import com.mit.du.common.CommonUtil;
import com.mit.du.common.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BloggerLogin implements Initializable {
	public ImageView closeWindow;
	public JFXTextField employeeNIDField;
	public JFXPasswordField employeePassField;
	public static String currentEmployeeNID;

	public void ManagerLogin(ActionEvent actionEvent) throws IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String employeeNID = employeeNIDField.getText();
		currentEmployeeNID = employeeNID;
		String employeePass = employeePassField.getText();
		try {
			connection = DBUtil.getConnections();
			if (employeeNID.isEmpty() || employeePass.isEmpty()) {
				CommonUtil.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
			} else {
				String sql = "SELECT * FROM EMPLOYEEINFO WHERE NID = ? AND PASSWORD = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, employeeNID);
				preparedStatement.setString(2, employeePass);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					CommonUtil.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
					CommonUtil.pageNavigation("/blogger/BloggerPages/BloggerMain.fxml", Main.stage, this.getClass(), "Manager Dashboard", 800, 600);
				} else {
					CommonUtil.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnections();
		}
//        CommonUtil.pageNavigation("/sample/blogger/BloggerPages/BloggerMain.fxml", Main.stage,this.getClass(),"Manager Dashboard", 800, 600);
	}


	public void BackToMain(ActionEvent actionEvent) throws IOException {
		CommonUtil.pageNavigation("/fxml/sample.fxml", Main.stage, this.getClass(), "Blog Management System", 600, 400);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		closeWindow.setOnMouseClicked(event -> {
			System.exit(0);
		});

	}
}
