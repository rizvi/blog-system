package main.java.com.mit.du.zadmin.AdminPages;

import javafx.event.ActionEvent;
import main.java.com.mit.du.Main;
import main.java.com.mit.du.backend.CommonTask;

import java.io.IOException;

public class AdminHome {
    public void BackToMain(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("/main/java/com/mit/du/sample.fxml", Main.stage,this.getClass(),"Admin Dashboard", 600, 400);
    }
}
