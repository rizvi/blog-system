package com.mit.du.blogger.BloggerPages;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMain implements Initializable {
    public BorderPane borderpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windowLoad("/blogger/BloggerPages/BloggerHome.fxml");
    }

    public void windowLoad(String URL)
    {
        try
        {
            Pane window = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(window);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }

    public void ManageRooms(ActionEvent actionEvent) {
        windowLoad("/blogger/BloggerPages/ManagerManageRooms.fxml");
    }

    public void ManagerHome(ActionEvent actionEvent) {
        windowLoad("/blogger/BloggerPages/BloggerHome.fxml");
    }

    public void ManagerCheckIn(ActionEvent actionEvent) {
        windowLoad("/blogger/BloggerPages/ManagerCheckIn.fxml");
    }

    public void ManagerCheckOut(ActionEvent actionEvent) {
        windowLoad("/blogger/BloggerPages/ManagerCheckOut.fxml");
    }

    public void CheckOutDetails(ActionEvent actionEvent) {
        windowLoad("/blogger/BloggerPages/ManagerCheckOutDetails.fxml");
    }

    public void closeApplication(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
