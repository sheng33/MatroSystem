package sample.controller;

import com.dooapp.fxform.FXForm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import sample.Dao.LineDao;
import sample.Dao.RuningTimeDao;
import sample.Dao.RuningTimeEnum;
import sample.Dao.TrainDao;
import sample.util.StageManager;
import sample.util.jdbcUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RuningTimeController implements Initializable {
    @FXML
    private Color x5;

    @FXML
    private Font x6;

    @FXML
    private TableView<RuningTimeDao> runingTime;

    @FXML
    private TableColumn<RuningTimeDao,Integer> train;

    @FXML
    private TableColumn<RuningTimeDao,Integer> line;

    @FXML
    private TableColumn<RuningTimeDao,Integer> nowSite;

    @FXML
    private TableColumn<RuningTimeDao,Integer> nextSite;

    @FXML
    private TableColumn<RuningTimeDao,String> runTime;

    @FXML
    private TableColumn<RuningTimeDao,Integer> state;
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
    void addSite(ActionEvent event) {
        System.out.println("站点信息录入");
        System.out.println(event);
    }
    @FXML
    void addLayer(ActionEvent event) {
        System.out.println("停靠层信息录入");
        System.out.println(event);
    }
    @FXML
    void addLine(ActionEvent event) {
        System.out.println("线路信息录入");
        System.out.println(event);
    }
    @FXML
    void addTrain(ActionEvent event) {

        Platform.runLater(()->{
            Stage saveDiary = StageManager.getStage("saveDiary");
            // 每次创建场景前，判断该场景是否被创建过，创建过直接显示场景即可，无需多次创建，但是需要清除上次输入的数据
            if(Objects.isNull(saveDiary)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../addtrain.fxml"));
                    Parent pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("添加数据");
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                    // 存放Scene
                    StageManager.put("saveDiary", stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                saveDiary.show();
            }
        });
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

    ObservableList<RuningTimeDao> data =FXCollections.observableArrayList();
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

    @FXML
    void scrollToColumn(ActionEvent event) {
        System.out.println("页面滚动B");
    }
    public void init() throws SQLException {
        ResultSet resultSet = jdbcUtil.getRuningTimeAll();
        while(resultSet.next()){
            RuningTimeDao dao = new RuningTimeDao();
            dao.setId(resultSet.getInt(1));
            dao.setNowSite(jdbcUtil.getSiteName(resultSet.getInt(2)));
            dao.setTrain(jdbcUtil.getTrainName(resultSet.getInt(3)));
            dao.setLine(jdbcUtil.getLineName(resultSet.getInt(4)));
            dao.setNextSite(jdbcUtil.getSiteName(resultSet.getInt(5)));
            dao.setState(RuningTimeEnum.getName(resultSet.getInt(6)));
            dao.setRunTime(resultSet.getString(7));
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
//            init();
        addTrain(null);
    }
}
