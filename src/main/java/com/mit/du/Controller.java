package com.mit.du;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import com.mit.du.common.CommonUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public ImageView closeWihdow;
    public JFXButton customerLoginBtn;
    public JFXButton managerLoginBtn;
    public JFXButton adminLoginBtn;
    public JFXButton btn;

    @FXML
    void Customer_Login(ActionEvent event) throws IOException {
        CommonUtil.pageNavigation("/reader/Login/UserLogin.fxml",Main.stage,this.getClass(),"Reader Login", 600, 400);
    }

    @FXML
    void Manager_Login(ActionEvent event) throws IOException {
        CommonUtil.pageNavigation("/blogger/Login/BloggerLogin.fxml",Main.stage,this.getClass(),"Blogger Login", 600, 400);
    }

    @FXML
    void Admin_Login(ActionEvent event) throws IOException {
        CommonUtil.pageNavigation("/admin/Login/AdminLogin.fxml", Main.stage,this.getClass(),"Admin Login", 600, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeWihdow.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }
}
