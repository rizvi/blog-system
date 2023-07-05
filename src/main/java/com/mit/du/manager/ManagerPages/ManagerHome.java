package com.mit.du.manager.ManagerPages;

import javafx.event.ActionEvent;
import com.mit.du.Main;
import com.mit.du.backend.CommonUtil;

import java.io.IOException;

public class ManagerHome {
    public void ManagerInfo(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("ManagerInfo/ManagerInfo.fxml", null, this.getClass(),"User Home", 550, 400);
//        CommonUtil.pageNavigation("/sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml", null, this.getClass(),"Edit Info", 550, 400);

    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/resources/fxml/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);

    }
}
