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
import sample.Dao.RuningTimeDao;
import sample.Dao.RuningTimeEnum;

import java.net.URL;
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


    public void showList(){
         ObservableList<RuningTimeDao> data =
                FXCollections.observableArrayList();
         data.add( new RuningTimeDao("1",1,1,1,1,1,"2020-12-11 22:22:22"));
         data.add( new RuningTimeDao("1",1,1,1,1,1,"2020-12-11 22:22:22"));
        train.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        runTime.setCellValueFactory(new PropertyValueFactory<>("runTime"));
        nextSite.setCellValueFactory(new PropertyValueFactory<>("nextSiteId"));
        nowSite.setCellValueFactory(new PropertyValueFactory<>("nowSiteId"));
        line.setCellValueFactory(new PropertyValueFactory<>("lineId"));

        train.setMinWidth(100);

        runingTime.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Test");
        System.out.println(RuningTimeEnum.getName(2));
        System.out.println(RuningTimeEnum.getValue("等待中"));
        showList();
    }
}
