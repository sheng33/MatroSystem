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
import sample.Dao.LineSiteDao;
import sample.Dao.RuningTimeDao;
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
        if(!Objects.isNull(stage)) {
            stage.close();
            System.out.println("关闭场景成功！");
        }
    }
    ObservableList<RuningTimeVo> data =FXCollections.observableArrayList();
    ArrayList<RuningTimeVo> dataListVo = new ArrayList<>();
    ArrayList<RuningTimeDao> dataListDao = new ArrayList<>();
    @FXML
    void scrollToColumn(ActionEvent event) {
        System.out.println("页面滚动B");
    }
    public void init() throws SQLException {
        ResultSet resultSet = jdbcUtil.getRuningTimeAll();
        while(resultSet.next()){
            RuningTimeVo vo = new RuningTimeVo();
            RuningTimeDao dao = new RuningTimeDao();
            dao.setId(resultSet.getInt(1));
            dao.setNowSite(resultSet.getInt(2));
            dao.setTrain(resultSet.getInt(3));
            dao.setLine(resultSet.getInt(4));
            dao.setNextSite(resultSet.getInt(5));
            dao.setState(resultSet.getInt(6));
            dao.setRunTime(resultSet.getString(7));
            dataListVo.add(dao.getRuningTimeVo());
            dataListDao.add(dao);
            data.add(vo);
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
        Runnable runnable = () -> {
            try {
                while(true){
                    simulation();
                    Thread.sleep(1000);
                    if (dataListVo.isEmpty()){
                        init();
                    }
                    update();
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
    void update(){
        this.data.clear();
        this.data.addAll(dataListVo);
    }
    //模拟列车运行
    private void simulation() throws InterruptedException, SQLException {
        Thread.sleep(4000);
        int count = 0;
        for (RuningTimeVo runingTimeVo : dataListVo) {
            int state = RuningTimeEnum.getValue(runingTimeVo.getState())+1;
            if (state==6) continue;
            if (state==5) state = 1;
            if (state==1){
                runingTimeVo.setNowSite(runingTimeVo.getNextSite());
            }
            if (state==3||state==2){
                RuningTimeDao dao = dataListDao.get(count);
                LineSiteDao siteDao =  jdbcUtil.getLineSiteByLineIdAndByStieId(dao.getLine(),dao.getNextSite());
                dao.setNowSite(siteDao.getNowSiteId());
                dao.setNextSite(jdbcUtil.getNextSiteId(dao.getLine(),siteDao.getOperationDirection(),siteDao.getLinePosition()+1));
                String nextSiteName = jdbcUtil.getSiteName(dao.getNowSite());
                if (!nextSiteName.equals(""))  runingTimeVo.setNextSite(jdbcUtil.getSiteName(dao.getNowSite()));
                else runingTimeVo.setState(RuningTimeEnum.getName(5));
            }
            runingTimeVo.setState(RuningTimeEnum.getName(state));
            count++;
        }
    }
}
