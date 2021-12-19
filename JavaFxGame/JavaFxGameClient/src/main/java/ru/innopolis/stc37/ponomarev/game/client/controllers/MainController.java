package ru.innopolis.stc37.ponomarev.game.client.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.innopolis.stc37.ponomarev.game.client.socket.SocketClient;
import ru.innopolis.stc37.ponomarev.game.client.utils.GameUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 06.05.2021
 * JavaFxGameClient
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class MainController implements Initializable {

    private GameUtils gameUtils;

    private SocketClient socketClient;

    public ImageView getPlayer() {
        return player;
    }

    @FXML
    private ImageView player;

    @FXML
    private ImageView enemy;

    @FXML
    private Button buttonGo;

    @FXML
    private Button buttonConnection;

    @FXML
    private TextField textPlayerName;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label hpPlayer;

    @FXML
    private Label hpEnemy;

    @FXML
    private TextArea statistic;

    @FXML
    private TextArea battle;

    @FXML
    private ProgressBar enemyHp;

    @FXML
    private ProgressBar playerHp;

    @FXML
    private TextField controls;

    @FXML
    private TextField moveLeft;

    @FXML
    private TextField moveRight;

    @FXML
    private TextField space;

    @FXML
    private ImageView defeat;

    @FXML
    private ImageView victory;

    @FXML
    private ImageView check;

    /**
     * Метод определения действия игроком, в зависимости от нажатой клавиши
     */
    private final EventHandler<KeyEvent> keyEventEventHandler = keyEvent -> {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            moveToRight();
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            moveToLeft();
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            gameUtils.createBulletFor(player, false);
            socketClient.sendMessage("shot");
        }
    };

    /**
     * Процедура передвижения игрока влево
     */
    private void moveToLeft() {
        if (player.getX() > -300) {
            gameUtils.goLeft(player);
            socketClient.sendMessage("left");
        } else {
            socketClient.sendMessage("stop");
        }
    }

    /**
     * Процедура передвежения игрока вправо
     */
    private void moveToRight() {
        if (player.getX() < 300) {
            gameUtils.goRight(player);
            socketClient.sendMessage("right");
        } else {
            socketClient.sendMessage("stop");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameUtils = new GameUtils();
        Thread thread = socketClient;
        buttonConnection.setOnAction(actionEvent -> {
            socketClient = new SocketClient(this, "localhost", 7777);
            socketClient.start();
            if (socketClient != null) {
                check.setVisible(true);
            }
            buttonConnection.setDisable(true);
            buttonGo.setDisable(false);
            textPlayerName.setDisable(false);
            gameUtils.setPane(pane);
            gameUtils.setSocketClient(socketClient);
        });
        buttonGo.setOnAction(actionEvent -> {
            socketClient.sendMessage("name: " + textPlayerName.getText());
            buttonGo.setDisable(true);
            textPlayerName.setDisable(true);
            buttonGo.getScene().getRoot().requestFocus();
            battle.setVisible(false);
            buttonConnection.setVisible(false);
            buttonGo.setVisible(false);
            textPlayerName.setVisible(false);
            controls.setVisible(false);
            moveLeft.setVisible(false);
            moveRight.setVisible(false);
            space.setVisible(false);
            playerHp.setDisable(false);
            enemyHp.setDisable(false);
            check.setVisible(false);
        });

        gameUtils.setController(this);
    }

    public ImageView getEnemy() {
        return enemy;
    }

    public EventHandler<KeyEvent> getKeyEventEventHandler() {
        return keyEventEventHandler;
    }

    public GameUtils getGameUtils() {
        return gameUtils;
    }

    public Label getHpPlayer() {
        return hpPlayer;
    }

    public Label getHpEnemy() {
        return hpEnemy;
    }

    public TextArea getStatistic() {
        return statistic;
    }

    public ProgressBar getEnemyHp() {
        return enemyHp;
    }

    public ProgressBar getPlayerHp() {
        return playerHp;
    }

    public ImageView getDefeat() {
        return defeat;
    }

    public ImageView getVictory() {
        return victory;
    }
}

