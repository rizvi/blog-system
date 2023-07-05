package com.mit.du.admin.AdminPages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMain implements Initializable {

    public BorderPane borderpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windowLoad("/admin/AdminPages/AdminHome.fxml");
    }

    public void windowLoad(String URL) {
        try {
            Pane window = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(window);
        } catch (Exception err) {
            System.out.println("Problem : " + err);
        }
    }

    public void AdminHome(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminHome.fxml");
    }

    public void AddEmployee(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminAddEmployee.fxml");
    }

    public void AdminEmployeeInfo(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminEmployeeInfo.fxml");
    }

    public void AdminCustomerInfo(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminCustomerInfo.fxml");
    }

    public void AdminEarnLog(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminEarningLog.fxml");
    }

    public void AdminTotalEarnings(ActionEvent actionEvent) {
        windowLoad("/admin/AdminPages/AdminTotalEarnings.fxml");
    }


    public void closeApplication(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
