package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import sample.Dao.SiteDao;
import sample.util.AlertUtil;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddSiteController implements Initializable {
    @FXML
    private TextField siteName;

    @FXML
    private Spinner<String> startTimeHH;

    @FXML
    private Spinner<String> startTimeMM;

    @FXML
    private Spinner<String> endTimeHH;

    @FXML
    private Spinner<String> endTimeMM;

    @FXML
    private Button submit;
    private void initTIme(){
        startTimeHH.setEditable(true);
        startTimeMM.setEditable(true);
        endTimeHH.setEditable(true);
        endTimeMM.setEditable(true);
        startTimeHH.setPromptText("00");
        startTimeMM.setPromptText("00");
        endTimeHH.setPromptText("00");
        endTimeMM.setPromptText("00");
    }


    @FXML
    void onClicka(MouseEvent event) {
        if (siteName.getText().equals("")){
        }else{
            SiteDao siteDao = new SiteDao();
            siteDao.setSiteName(siteName.getText());
            siteDao.setEndTime(String.valueOf(endTimeHH.getValue())+":"+String.valueOf(endTimeMM.getValue()));
            siteDao.setStartTime(String.valueOf(startTimeHH.getValue())+":"+String.valueOf(startTimeMM.getValue()));
            try {
                jdbcUtil.addSite(siteDao);
            } catch (SQLException throwables) {
                System.out.println("添加失败");
                AlertUtil.openAlert("添加失败", throwables.getMessage(), Alert.AlertType.INFORMATION);
                throwables.printStackTrace();
            }
            AlertUtil.openAlert("创建成功","创建成功", Alert.AlertType.INFORMATION);
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTIme();
    }
}