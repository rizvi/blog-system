package com.mit.du.zadmin.AdminPages;

import javafx.event.ActionEvent;
import com.mit.du.Main;
import com.mit.du.common.CommonUtil;

import java.io.IOException;

public class AdminHome {
    public void BackToMain(ActionEvent actionEvent) throws IOException {
        CommonUtil.pageNavigation("/main/java/com/mit/du/sample.fxml", Main.stage,this.getClass(),"Admin Dashboard", 600, 400);
    }
}
