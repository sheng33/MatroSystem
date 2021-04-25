package sample.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Dao.LayerDao;
import sample.util.AlertUtil;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLayerController implements Initializable {

    @FXML
    private TextField siteName;

    @FXML
    private ChoiceBox<String> level;


    @FXML
    private CheckBox isEnable;

    @FXML
    private Button submit;

    @FXML
    void onClicka(MouseEvent event) throws SQLException {
        LayerDao layerDao = new LayerDao();
        int siteId = jdbcUtil.getSiteNameId(siteName.getText());
        if (siteId==-1){
            AlertUtil.openAlert("查询站点id","找不着站点名为"+siteName.getText()+"的id", Alert.AlertType.ERROR);
        }else{
            layerDao.setNowSiteId(siteId);
            layerDao.setLayerLevel(getLevel());
            layerDao.setState(!isEnable.isSelected()?0:1);
            System.out.println(layerDao.toString());
            jdbcUtil.addLayer(layerDao);
        }
    }

    private int getLevel(){
        switch (level.getValue()) {
            case "第一层":
                return  1;
            case "第二层":
                return 2;
            case "第三层":
                return 3;
            case "第四层":
                return 4;
            case "第五层":
                return 5;
        }
        return -1;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        level.getItems().add("第一层");
        level.getItems().add("第二层");
        level.getItems().add("第三层");
        level.getItems().add("第四层");
        level.getItems().add("第五层");
    }
}
