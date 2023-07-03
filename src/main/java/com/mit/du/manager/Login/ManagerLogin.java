package main.java.com.mit.du.manager.Login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import main.java.com.mit.du.Main;
import main.java.com.mit.du.backend.CommonTask;
import main.java.com.mit.du.backend.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerLogin implements Initializable {
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
			connection = DBConnection.getConnections();
			if (employeeNID.isEmpty() || employeePass.isEmpty()) {
				CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
			} else {
				String sql = "SELECT * FROM EMPLOYEEINFO WHERE NID = ? AND PASSWORD = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, employeeNID);
				preparedStatement.setString(2, employeePass);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
					CommonTask.pageNavigation("/main/java/com/mit/du/manager/ManagerPages/ManagerMain.fxml", Main.stage, this.getClass(), "Manager Dashboard", 800, 600);
				} else {
					CommonTask.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnections();
		}
//        CommonTask.pageNavigation("/main.java.sample/manager/ManagerPages/ManagerMain.fxml", Main.stage,this.getClass(),"Manager Dashboard", 800, 600);
	}


	public void BackToMain(ActionEvent actionEvent) throws IOException {
		CommonTask.pageNavigation("/main/java/com/mit/du/sample.fxml", Main.stage, this.getClass(), "Blog Management System", 600, 400);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		closeWindow.setOnMouseClicked(event -> {
			System.exit(0);
		});

	}
}
