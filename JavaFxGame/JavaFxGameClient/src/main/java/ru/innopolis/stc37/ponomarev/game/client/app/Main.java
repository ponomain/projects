package ru.innopolis.stc37.ponomarev.game.client.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.innopolis.stc37.ponomarev.game.client.controllers.MainController;


/**
 * 06.05.2021
 * JavaFxGameClient
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFileName = "/fxml/Main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFileName));

        primaryStage.setTitle("Game client");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        MainController controller = loader.getController();
        scene.setOnKeyPressed(controller.getKeyEventEventHandler());

        primaryStage.show();
    }
}
