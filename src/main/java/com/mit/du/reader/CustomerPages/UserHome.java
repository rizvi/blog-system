package com.mit.du.reader.CustomerPages;


import com.jfoenix.controls.JFXDialog;
import com.mit.du.backend.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import com.mit.du.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHome implements Initializable {
    public StackPane rootPane;
    public AnchorPane rootAnchorPane;
    public static int welcomed = 0;
    public void BackToLoginPage(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/resources/fxml/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);
        welcomed = 0;
    }

    public void UserInfo(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/com/mit/du/reader/CustomerPages/CustomerInfo/UserInfo.fxml", null,this.getClass(),"User Home", 550, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(welcomed == 0) {
            CommonUtil.showJFXAlert(rootPane, rootAnchorPane, "information", "Login Success!", "Successfully Logged In!", JFXDialog.DialogTransition.CENTER);
            welcomed = 1;
        }
    }
}
