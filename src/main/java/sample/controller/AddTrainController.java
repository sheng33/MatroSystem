package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Dao.LineDao;
import sample.Dao.TrainDao;
import sample.Dao.TrainTypeEnum;
import sample.util.AlertUtil;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddTrainController implements Initializable {

    @FXML
    private TextField trainName;

    @FXML
    private MenuButton trainType;

    @FXML
    private MenuButton lineName;

    @FXML
    private DatePicker serviceTime;

    @FXML
    private Button submit;

    private void initTrainType(){
        HashMap<Integer,String> map = TrainTypeEnum.getAll(); //读取列车类型枚举类型
        for (int i = 0; i <map.size(); i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setId(String.valueOf(i+1));
            menuItem.setText(map.get(i+1));
            menuItem.setOnAction(actionEvent -> {
                trainType.setText(menuItem.getText());
                trainType.setId(menuItem.getId());
            });
            trainType.getItems().add(menuItem);
        }
    }
    private void initLine(){
        try {
            ResultSet resultSet = jdbcUtil.getAllLineInfo();
            ArrayList<LineDao> lineDaos = new ArrayList<>();
            while(resultSet.next()){
                LineDao lineDao = new LineDao();
                lineDao.setLineId(resultSet.getInt(1));
                lineDao.setLineName(resultSet.getString(2));
                lineDao.setFirstRunTime(resultSet.getString(3));
                lineDao.setEndRunTime(resultSet.getString(4));
                lineDaos.add(lineDao);
            }
            for (int i = 0; i < lineDaos.size(); i++) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(String.valueOf(lineDaos.get(i).getLineId()));
                menuItem.setText(lineDaos.get(i).getLineName());
                menuItem.setOnAction(actionEvent -> {
                    lineName.setText(menuItem.getText());
                    lineName.setId(menuItem.getId());
                });
                lineName.getItems().add(menuItem);
            }
        } catch (SQLException throwables) {
            System.out.println("读取线路信息失败");
            throwables.printStackTrace();
        }



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTrainType(); // 初始化列车类型菜单
        initLine(); // 初始化线路菜单
    }
    @FXML
    void onClicka(MouseEvent event) {
        int access = 0;
        if (trainName.getText().trim().equals("")){
            trainName.setPromptText("不能为空");
            trainName.setStyle("-fx-border-color: red");
            access=1;
        }else {
            trainName.setStyle("-fx-border-color: ActiveBorder");
        }
        if (trainType.getText().equals("请选择")){
            trainType.setStyle("-fx-border-color: red");
            access=1;
        }else{
            trainType.setStyle("-fx-border-color: ActiveBorder");
        }
        if (serviceTime.getValue()==null){
            serviceTime.setPromptText("不能为空");
            serviceTime.setStyle("-fx-border-color: red");
            access=1;
        }else{
            serviceTime.setStyle("-fx-border-color: ActiveBorder");
        }
        if (access==0){
            TrainDao trainDao = new TrainDao();
            System.out.println("点击的列车类型:"+trainType.getText()+"  id:"+trainType.getId());
            trainDao.setTrainName(trainName.getText());
            trainDao.setTrainType(trainType.getId());
            trainDao.setServiceTime(serviceTime.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            try {
                jdbcUtil.addTrain(trainDao);
            } catch (SQLException throwables) {
                System.out.println("添加失败");
                AlertUtil.openAlert("添加失败", throwables.getMessage(), Alert.AlertType.INFORMATION);
                throwables.printStackTrace();
            }
        }
        System.out.println("鼠标点击提交");
        AlertUtil.openAlert("创建成功","创建成功", Alert.AlertType.INFORMATION);
        Platform.exit();
    }

}
