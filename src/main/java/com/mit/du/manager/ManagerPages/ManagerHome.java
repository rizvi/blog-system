package main.java.com.mit.du.manager.ManagerPages;

import javafx.event.ActionEvent;
import main.java.com.mit.du.Main;
import main.java.com.mit.du.backend.CommonTask;

import java.io.IOException;

public class ManagerHome {
    public void ManagerInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("ManagerInfo/ManagerInfo.fxml", null, this.getClass(),"User Home", 550, 400);
//        CommonTask.pageNavigation("/main.java.sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml", null, this.getClass(),"Edit Info", 550, 400);

    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/main/java/com/mit/du/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);

    }
}
