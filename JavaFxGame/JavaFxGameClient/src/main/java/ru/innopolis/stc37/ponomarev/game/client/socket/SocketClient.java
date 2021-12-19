package ru.innopolis.stc37.ponomarev.game.client.socket;

import javafx.application.Platform;
import ru.innopolis.stc37.ponomarev.game.client.controllers.MainController;
import ru.innopolis.stc37.ponomarev.game.client.utils.GameUtils;

import java.io.*;
import java.net.Socket;

/**
 * 04.05.2021
 * lesson28 - client
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class SocketClient extends Thread {
    private final Socket socket;
    private final PrintWriter  toServer;
    private final BufferedReader fromServer;
    private final MainController mainController;
    private final GameUtils gameUtils;

    public SocketClient(MainController mainController, String host, int port) {
        try {
            this.socket = new Socket(host, port);
            //поток символок, который мы можем направлять серверу
            toServer = new PrintWriter(socket.getOutputStream(), true);
            // поток символов, который приходит с сервера
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.mainController = mainController;
            this.gameUtils = mainController.getGameUtils();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            String messageFromServer;
            try {
                messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    switch (messageFromServer) {
                        case "left":
                            gameUtils.goLeft(mainController.getEnemy());
                            break;
                        case "right":
                            gameUtils.goRight(mainController.getEnemy());
                            break;
                        case "shot":
                            Platform.runLater(() -> gameUtils.createBulletFor(mainController.getEnemy(), true));
                            break;
                        default:
                            System.out.println(messageFromServer);
                            break;
                    }
                    if (messageFromServer.contains("statistic")) {
                        mainController.getStatistic().setVisible(true);
                        String line;
                        while ((line = fromServer.readLine()) != null) {
                            mainController.getStatistic().appendText(line);
                            mainController.getStatistic().appendText("\n");
                        }
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Процедура передачи сообщения от игрока - серверу
     * @param message - передаваемое сообщение
     */
    public void sendMessage(String message) {
        toServer.println(message);
    }
}

