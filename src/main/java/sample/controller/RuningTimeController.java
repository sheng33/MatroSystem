package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import sample.Dao.RuningTimeVo;
import sample.Dao.RuningTimeEnum;
import sample.util.StageManager;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RuningTimeController implements Initializable {
    @FXML
    private Color x5;

    @FXML
    private Font x6;

    @FXML
    private TableView<RuningTimeVo> runingTime;

    @FXML
    private TableColumn<RuningTimeVo,Integer> train;

    @FXML
    private TableColumn<RuningTimeVo,Integer> line;

    @FXML
    private TableColumn<RuningTimeVo,Integer> nowSite;

    @FXML
    private TableColumn<RuningTimeVo,Integer> nextSite;

    @FXML
    private TableColumn<RuningTimeVo,String> runTime;

    @FXML
    private TableColumn<RuningTimeVo,Integer> state;
    @FXML
    private Color x2;

    @FXML
    private Font x1;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    @FXML
    private Pagination page;
    @FXML
    private MenuItem addTrain;
    @FXML
    private MenuItem addLayer;
    @FXML
    private MenuItem addLine;
    @FXML
    private MenuItem addSite;


    @FXML
    void addLayer(ActionEvent event) {
        StageManager.getStage("../../addLayer.fxml","添加停靠层信息");
    }
    @FXML
    void addLine(ActionEvent event) {
        StageManager.getStage("../../addLine.fxml","添加线路信息");
    }
    @FXML
    void addTrain(ActionEvent event) {
        StageManager.getStage("../../addtrain.fxml","添加列车信息");
    }
    @FXML
    void addSite(ActionEvent event) {
        StageManager.getStage("../../addSite.fxml","添加站点信息");
    }



    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = StageManager.getStage("saveDiary");
//        logger.info("stage: {}",stage);
        if(!Objects.isNull(stage)) {
            stage.close();
//            logger.info("关闭场景成功!");
            System.out.println("关闭场景成功！");
        }
    }

    @FXML
    void scroll(ScrollEvent event) {
        System.out.println(event.getTouchCount());
        System.out.println("页面滚动");
    }

    @FXML
    void scrollFinished(ScrollEvent event) {
        System.out.println("页面滚动结束");
    }

    @FXML
    void scrollStarted(ScrollEvent event) {
        System.out.println("页面滚动开始");

    }

    @FXML
    void scrollTo(ActionEvent event) {
        System.out.println("页面滚动A");

    }
    ObservableList<RuningTimeVo> data =FXCollections.observableArrayList();
    ArrayList<RuningTimeVo> dataList = new ArrayList<>();
    @FXML
    void scrollToColumn(ActionEvent event) {
        System.out.println("页面滚动B");
    }
    public void init() throws SQLException {
        ResultSet resultSet = jdbcUtil.getRuningTimeAll();
        while(resultSet.next()){
            RuningTimeVo dao = new RuningTimeVo();
            dao.setId(resultSet.getInt(1));
            dao.setNowSite(jdbcUtil.getSiteName(resultSet.getInt(2)));
            dao.setTrain(jdbcUtil.getTrainName(resultSet.getInt(3)));
            dao.setLine(jdbcUtil.getLineName(resultSet.getInt(4)));
            dao.setNextSite(jdbcUtil.getSiteName(resultSet.getInt(5)));
            dao.setState(RuningTimeEnum.getName(resultSet.getInt(6)));
            dao.setRunTime(resultSet.getString(7));
            dataList.add(dao);
            data.add(dao);
        }
        train.setCellValueFactory(new PropertyValueFactory<>("train"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        runTime.setCellValueFactory(new PropertyValueFactory<>("runTime"));
        nextSite.setCellValueFactory(new PropertyValueFactory<>("nextSite"));
        nowSite.setCellValueFactory(new PropertyValueFactory<>("nowSite"));
        line.setCellValueFactory(new PropertyValueFactory<>("line"));
        train.setMinWidth(100);
        state.setMinWidth(100);
        nextSite.setMinWidth(100);
        nowSite.setMinWidth(100);
        line.setMinWidth(100);
        runTime.setMinWidth(200);
        train.setResizable(true);
        runingTime.setItems(data);
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
//        addSite(null);
//        addTrain(null);
//        addLayer(null);
//        addLine(null);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        Thread.sleep(1000);
                        if (dataList.isEmpty()){
                            init();
                        }
                        update();
                    }
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
    void update(){
        this.data.clear();
        this.data.addAll(dataList);
    }

    //模拟列车运行
    private void simulation(){

    }

}
