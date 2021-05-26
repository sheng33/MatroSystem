package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Dao.RuningTimeDao;
import sample.Dao.RuningTimeVo;
import sample.controller.AddLineController;
import sample.controller.CoreController;
import sample.controller.RuningTimeController;
import sample.util.StageManager;

import java.util.Objects;


public class Main extends Application {
    CoreController coreController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader  =  new FXMLLoader(getClass().getResource("../main.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("地铁调度系统");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        coreController = new CoreController();
//        coreController.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
