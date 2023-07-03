package main.java.com.mit.du.reader.CustomerPages;


import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.mit.du.Main;
import main.java.com.mit.du.backend.CommonTask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHome implements Initializable {
    public StackPane rootPane;
    public AnchorPane rootAnchorPane;
    public static int welcomed = 0;
    public void BackToLoginPage(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/main/java/com/mit/du/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);
        welcomed = 0;
    }

    public void UserInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/main/java/com/mit/du/reader/CustomerPages/CustomerInfo/UserInfo.fxml", null,this.getClass(),"User Home", 550, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(welcomed == 0) {
            CommonTask.showJFXAlert(rootPane, rootAnchorPane, "information", "Login Success!", "Successfully Logged In!", JFXDialog.DialogTransition.CENTER);
            welcomed = 1;
        }
    }
}
