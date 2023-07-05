package com.mit.du.blogger.BloggerPages;

import javafx.event.ActionEvent;
import com.mit.du.Main;
import com.mit.du.common.CommonUtil;

import java.io.IOException;

public class BloggerHome {
    public void ManagerInfo(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/blogger/BloggerPages/BloggerInfo/BloggerInfo.fxml", null, this.getClass(),"User Home", 550, 400);
//        CommonUtil.pageNavigation("/sample/blogger/BloggerPages/RoomInfoEdit/roomInfoEdit.fxml", null, this.getClass(),"Edit Info", 550, 400);

    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/fxml/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);

    }
}
