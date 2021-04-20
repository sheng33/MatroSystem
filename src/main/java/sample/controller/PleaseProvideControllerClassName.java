package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.SneakyThrows;
import sample.Dao.RuningTimeDao;
import sample.Dao.RuningTimeEnum;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PleaseProvideControllerClassName implements Initializable {



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


    public void showList() throws SQLException {
        ObservableList<RuningTimeDao> data =FXCollections.observableArrayList();
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

        runingTime.setItems(data);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showList();
    }
}
