package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;

public class AddTrainController {

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

    @FXML
    void onClicka(MouseEvent event) {
        System.out.println("鼠标点击提交");
    }

}
