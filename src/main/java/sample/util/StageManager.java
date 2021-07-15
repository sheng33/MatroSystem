package sample.util;


import com.microsoft.sqlserver.jdbc.StringUtils;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 场景管理器
 */
public class StageManager {

    /**
     * 场景集合
     */
    private static Map<String, Stage> stageMap = new ConcurrentHashMap<>();

    /**
     * 根据key存放Scene
     * @param key
     * @param stage
     */
    public static void put(String key, Stage stage) {
        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不为空!");
        }
        if(Objects.isNull(stage)) {
            throw new RuntimeException("scene不为空!");
        }
        stageMap.put(key, stage);
    }

    /**
     * 根据key获取Scene
     * @param key
     * @return
     */
    public static Stage getStage(String key) {
        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不为空!");
        }
        return stageMap.get(key);
    }
    public static void getStage(String url,String stageName) {
        Platform.runLater(()->{
            Stage saveDiary = StageManager.getStage(stageName);
            // 每次创建场景前，判断该场景是否被创建过，创建过直接显示场景即可，无需多次创建，但是需要清除上次输入的数据
            if(Objects.isNull(saveDiary)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(StageManager.class.getResource(url));
                    Parent pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle(stageName);
                    stage.setResizable(false);
                    stage.show();
                    scene.setUserData(fxmlLoader);
                    // 存放Scene
                    StageManager.put(stageName, stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                saveDiary.show();
            }
        });
    }
}
