package ru.innopolis.stc37.ponomarev.game.client.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ru.innopolis.stc37.ponomarev.game.client.controllers.MainController;
import ru.innopolis.stc37.ponomarev.game.client.socket.SocketClient;


/**
 * 06.05.2021
 * JavaFxGameClient
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class GameUtils {
    private static final int DAMAGE_TO_PLAYER = 5;

    private static final int PLAYER_STEP = 10;

    private AnchorPane pane;

    private final Image fail = new Image("file:src/main/resources/images/fail.png");

    private MainController controller;

    private SocketClient socketClient;

    /**
     * Процедура визуального отображение передвижения игрока влево
     * @param player - исходный игрок
     */
    public void goLeft(ImageView player) {
        player.setX(player.getX() - PLAYER_STEP);
    }

    /**
     * Процедура визуального отображения передвижения игрока вправо
     * @param player - исходный игрок
     */
    public void goRight(ImageView player) {
        player.setX(player.getX() + PLAYER_STEP);
    }

    /**
     * Функция создания "пули" для обоих игроков
     * @param player - исходный игрок
     * @param isEnemy - проверка на то, является ли игрок - врагом
     * @return - созданную пулю
     */
    public ImageView createBulletFor(ImageView player, boolean isEnemy) {
        ImageView bulletFromPlayer = new ImageView("file:src/main/resources/images/playerBullet.png");
        ImageView bulletFromEnemy = new ImageView("file:src/main/resources/images/enemyBullet.png");
        ImageView finalBullet;
        int value;
        if (isEnemy) {
            finalBullet = bulletFromEnemy;
            value = 1;
        } else {
            finalBullet = bulletFromPlayer;
            value = -1;
        }
        pane.getChildren().add(finalBullet);
        finalBullet.setX((player.getX() + 33) + player.getLayoutX());
        finalBullet.setY(player.getY() + player.getLayoutY());

        final ImageView target;
        final Label targetHp;
        final ProgressBar targetHpProgress;
        if (!isEnemy) {
            target = controller.getEnemy();
            targetHp = controller.getHpEnemy();
            targetHpProgress = controller.getEnemyHp();
        } else {
            target = controller.getPlayer();
            targetHp = controller.getHpPlayer();
            targetHpProgress = controller.getPlayerHp();
            finalBullet.setY((player.getY() + 70) + player.getLayoutY());
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), animation -> {
            finalBullet.setY(finalBullet.getY() + value);

            isBulletOutOfBounds(finalBullet);
            if (finalBullet.isVisible() && isIntersects(finalBullet, target)) {
                setDamageToPlayer(targetHp, targetHpProgress);
                finalBullet.setVisible(false);
                isPlayerDefeated();
                if (!isEnemy) {
                    socketClient.sendMessage("damage");
                    isEnemyDefeated(target, targetHp);
                }
            }
        }));
        timeline.setCycleCount(500);
        timeline.play();
        return finalBullet;
    }

    /**
     * Процедура проверки на то, победил ли игрок
     * @param target - объект "цель"
     * @param targetHp - количество нанесенного урона цели
     */
    private void isEnemyDefeated(ImageView target, Label targetHp) {
        if (Integer.parseInt(targetHp.getText().replace("%", "")) == 100) {
            target.setImage(fail);
            controller.getVictory().setVisible(true);
            socketClient.sendMessage("0 HP");
        }
    }

    /**
     * Процедура проверки на то, проиграл ли игрок
     */
    private void isPlayerDefeated() {
        if (Integer.parseInt(controller.getHpPlayer().getText().replace("%", "")) == 100) {
            controller.getPlayer().setImage(fail);
            controller.getDefeat().setVisible(true);
        }
    }

    /**
     * Процедура проверки на то, вышла ли пуля за границы
     * @param finalBullet - созданная пуля
     */
    private void isBulletOutOfBounds(ImageView finalBullet) {
        if (finalBullet.getY() > 550 || finalBullet.getY() < 20) {
            finalBullet.setVisible(false);
        }
    }

    /**
     * Функция проверки на то, пересекла ли пуля цель
     * @param bullet - созданная пуля
     * @param player - цель
     * @return - возвращает true или false
     */
    private boolean isIntersects(ImageView bullet, ImageView player) {
        return bullet.getBoundsInParent().intersects(player.getBoundsInParent());
    }

    /**
     * Процедура увеличения полоски "damage" у игрока, если пуля в него попала
     * @param hpLabel - урон, который получает игрок в цифрах
     * @param targetProgress - урон, который отображается постепенно заполняющееся полоской
     */
    private void setDamageToPlayer(Label hpLabel, ProgressBar targetProgress) {
        int hpPlayer = Integer.parseInt(hpLabel.getText().replace("%", ""));
        if (hpPlayer < 100) {
            hpLabel.setText((hpPlayer + DAMAGE_TO_PLAYER) + "%");
            targetProgress.setProgress(targetProgress.getProgress() + 0.05);
        }
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }
}

