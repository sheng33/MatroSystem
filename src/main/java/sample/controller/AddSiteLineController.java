package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Dao.LayerDao;
import sample.Dao.TempSiteInfo;
import sample.util.AlertUtil;
import sample.util.StageManager;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSiteLineController implements Initializable {
    @FXML
    private TextField siteName;

    @FXML
    private MenuButton siteType;

    @FXML
    private MenuButton layerMenu;

    @FXML
    private TextField lineCount;
    @FXML
    void checkLayer(MouseEvent event) throws SQLException {
        layerMenu.getItems().clear();
        System.out.println("检索");
        int siteId = jdbcUtil.getSiteId(siteName.getText());
        System.out.println(siteId);
        ResultSet resultSet = jdbcUtil.getLayerAllBySiteId(siteId);
        while(resultSet.next()){
            MenuItem menuItem = new MenuItem();
            menuItem.setId(String.valueOf(resultSet.getInt(1)));
            String layerName="1";
            int level = resultSet.getInt(3);
            System.out.println("层:"+level);
            switch (level){
                case 1:layerName="第一层"; break;
                case 2:layerName="第二层"; break;
                case 3:layerName="第三层"; break;
                case 4:layerName="第四层"; break;
                case 5:layerName="第五层"; break;
                default:
                    layerName = "数据异常";
            }
            menuItem.setText(layerName);
            menuItem.setOnAction(actionEvent -> {
                layerMenu.setText(menuItem.getText());
                layerMenu.setId(menuItem.getId());
            });
            System.out.println(menuItem.getText());
            layerMenu.getItems().add(menuItem);
        }
    }
    @FXML
    void onClicka(MouseEvent event) throws SQLException {
        int pd = 0;
        if (jdbcUtil.getSiteId(siteName.getText())==-1){
            AlertUtil.openAlert("内容错误","站点名称在数据库里不存在", Alert.AlertType.ERROR);
            pd = 1;
        }
        if (siteType.getText()==null){
            AlertUtil.openAlert("内容错误","类型不能为空", Alert.AlertType.ERROR);
            pd = 1;
        }
        if (lineCount.getText()==null){
            AlertUtil.openAlert("内容错误","线路位置不能为空", Alert.AlertType.ERROR);
            pd =1;
        }
        if(pd!=1){
            Stage stage = StageManager.getStage("添加线路信息");
            FXMLLoader loader = (FXMLLoader) stage.getScene().getUserData();
            AddLineController controller = loader.getController();
            TempSiteInfo tempSiteInfo = new TempSiteInfo();
            tempSiteInfo.setSiteId(jdbcUtil.getSiteId(siteName.getText()));
            tempSiteInfo.setSiteName(siteName.getText());
            tempSiteInfo.setSiteType(siteType.getText());
            tempSiteInfo.setIndex(lineCount.getText());
            tempSiteInfo.setLayerId(layerMenu.getId());
            tempSiteInfo.setLayerName(layerMenu.getText());
            controller.addSiteInfo(tempSiteInfo);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setText("起始站");
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setText("中途站");
        MenuItem menuItem3 = new MenuItem();
        menuItem3.setText("终点站");

        menuItem1.setOnAction(actionEvent -> {
            siteType.setText(menuItem1.getText());
            siteType.setId(menuItem1.getId());
        });
        menuItem2.setOnAction(actionEvent -> {
            siteType.setText(menuItem2.getText());
            siteType.setId(menuItem2.getId());
        });
        menuItem3.setOnAction(actionEvent -> {
            siteType.setText(menuItem3.getText());
            siteType.setId(menuItem3.getId());
        });
        siteType.getItems().add(menuItem1);
        siteType.getItems().add(menuItem2);
        siteType.getItems().add(menuItem3);
    }
}
