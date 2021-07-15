package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Dao.LineDao;
import sample.Dao.LineSiteDao;
import sample.Dao.TempSiteInfo;
import sample.util.AlertUtil;
import sample.util.StageManager;
import sample.util.jdbcUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLineController implements Initializable {
    @FXML
    private TextField lineName;
    @FXML
    private Spinner<String> startTimeHH;
    @FXML
    private Spinner<String> startTimeMM;
    @FXML
    private Spinner<String> endTimeHH;
    @FXML
    private Spinner<String> endTimeMM;
    @FXML
    private RadioButton pos1;
    @FXML
    private RadioButton pos2;
    @FXML
    private TableView<TempSiteInfo> siteTable;
    @FXML
    private TableColumn<String, String> siteName;
    @FXML
    private TableColumn<String, String> lineIndex;
    @FXML
    private TableColumn<String, String> siteType;
    @FXML
    private TableColumn<String, String> layerCount;
    @FXML
    void submit(MouseEvent event) throws SQLException {
        int pd = 0;
        if (lineName.getText()==null){
            AlertUtil.openAlert("内容不能为空","线路名不能为空", Alert.AlertType.ERROR);
            pd = 1;
        }
        String startTime = String.valueOf(startTimeHH.getValue())+":"+String.valueOf(startTimeMM.getValue());
        String endTime = String.valueOf(endTimeHH.getValue())+":"+String.valueOf(endTimeMM.getValue());
        int pos = 0;
        if (pos1.isSelected()) pos = 1;
        if (pos2.isSelected()) pos = 2;
        if (pos == 0){
            AlertUtil.openAlert("内容不能为空","请选择列车运行方向", Alert.AlertType.ERROR);
            pd = 1;
        }
        if (pd==0){
            LineDao lineDao = new LineDao(lineName.getText(),startTime,endTime);
            if (jdbcUtil.addLine(lineDao)!=1){
                AlertUtil.openAlert("新增失败","线路数据新增失败", Alert.AlertType.ERROR);
            }
            int lineId = jdbcUtil.getLineId(lineDao.getLineName());
            for (int i = 0; i < data.size(); i++) {
                int nowSiteId = data.get(i).getSiteId();
                int isStartSite = data.get(i).getSiteType().equals("起始站")?1:0;
                int isEndSite = data.get(i).getSiteType().equals("终点站")?1:0;
                LineSiteDao lineSiteDao = new LineSiteDao();
                lineSiteDao.setLineId(lineId);
                lineSiteDao.setNowSiteId(nowSiteId);
                lineSiteDao.setLayerId(Integer.parseInt(data.get(i).getLayerId()));
                lineSiteDao.setOperationDirection(pos==1?"正向":"反向");
                lineSiteDao.setIsStartSite(isStartSite);
                lineSiteDao.setIsEndSite(isEndSite);
                lineSiteDao.setLinePosition(Integer.parseInt(data.get(i).getIndex()));
                jdbcUtil.addLineSiteTable(lineSiteDao);
            }
        }
    }
    @FXML
    void addTrain(MouseEvent event) {
        StageManager.getStage("../../addSiteLine.fxml","增加列车");
    }
    ObservableList<TempSiteInfo> data = FXCollections.observableArrayList();
    public void addSiteInfo(TempSiteInfo siteInfo){
        data.add(siteInfo);
        siteName.setCellValueFactory(new PropertyValueFactory<>("siteName"));
        lineIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        layerCount.setCellValueFactory(new PropertyValueFactory<>("layerName"));
        siteType.setCellValueFactory(new PropertyValueFactory<>("siteType"));
        siteTable.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTimeHH.setEditable(true);
        startTimeMM.setEditable(true);
        endTimeHH.setEditable(true);
        endTimeMM.setEditable(true);
        startTimeHH.setPromptText("00");
        startTimeMM.setPromptText("00");
        endTimeHH.setPromptText("00");
        endTimeMM.setPromptText("00");
        pos1.setOnMouseClicked(event -> {
            if (pos1.isSelected()){
                pos2.setSelected(false);
            }
            pos1.setSelected(true);
        });
        pos2.setOnMouseClicked(event -> {
            if (pos2.isSelected()){
                pos1.setSelected(false);
            }
            pos2.setSelected(true);
        });
    }
}
