package ru.innopolis.stc37.ponomarev.game.server;

import ru.innopolis.stc37.ponomarev.game.services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static ru.innopolis.stc37.ponomarev.game.server.CommandsParser.*;

/**
 * 04.05.2021
 * lesson28
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class GameServer {

    private ClientThread firstPlayer;
    private ClientThread secondPlayer;
    private ServerSocket serverSocket;

    private boolean isGameStarted = false;
    private boolean isGameInProcess = true;
    private long startTimeMills;
    private long gameId;

    private final GameService gameService;

    private final Lock lock = new ReentrantLock();

    public GameServer(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Процедура запуска сервера
     *
     * @param port - порт
     */
    public void start(int port) {
        try {
            //запустили сервер на определенном порту
            serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            System.out.println("Waiting for first client...");
            firstPlayer = connect();

            System.out.println("Waiting for second client...");
            secondPlayer = connect();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Функция запуска отдельных потоков для каждого клиента
     *
     * @return - возвращается поток для клиента
     */
    private ClientThread connect() {
        Socket client;
        try {
            // уводит приложение в ожидание, пока не присоединится какой-либо клиент
            // как только клиент подключен к серверу
            // объект - соединение возвращается как результат выполнения метода
            client = serverSocket.accept();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // создали ему отдельный поток
        var clientThread = new ClientThread(client);
        // запустили бесконечный поток
        clientThread.start();
        System.out.println("Client connected...");
        clientThread.sendMessage("You connected to server...");
        clientThread.sendMessage("Enter nickname...");
        return clientThread;
    }

    //отдельный поток для клиента
    private class ClientThread extends Thread {

        private final PrintWriter toClient;

        private final BufferedReader fromClient;
        private String playerNickname;
        private final String ip;

        public ClientThread(Socket client) {
            try {
                this.toClient = new PrintWriter(client.getOutputStream(), true);
                this.fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                this.ip = client.getInetAddress().getHostAddress();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        /**
         * Процедура запуска игры на сервере
         */
        @Override
        public void run() {
            // бесконечный цикл, пока активен сервер
            while (isGameInProcess) {
                String messageFromClient;
                try {
                    // получили сообщение от клиента
                    messageFromClient = fromClient.readLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
                // считываение имени от игроков и присвоение
                if (messageFromClient != null) {
                    if (isMessageForNickname(messageFromClient)) {
                        resolveNicknames(messageFromClient);
                    } else if (isMessageForMove(messageFromClient)) {
                        resolveMove(messageFromClient);
                    } else if (isMessageForShot(messageFromClient)) {
                        resolveShot(messageFromClient);
                    } else if (isMessageForFinishGame(messageFromClient)) {
                        resolveFinishGame();
                    } else if (isMessageForDamage(messageFromClient)) {
                        resolveDamage();
                    }
                }

                lock.lock();
                try {
                    if (isReadyForStartGame()) {
                        gameId = gameService.startGame(firstPlayer.getIp(), secondPlayer.getIp(), firstPlayer.playerNickname, secondPlayer.playerNickname);
                        startTimeMills = System.currentTimeMillis();
                        isGameStarted = true;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        /**
         * Процедура завершения игры
         */
        private void resolveFinishGame() {
            var statisticDto = gameService.finishGame(gameId, firstPlayer.playerNickname, secondPlayer.playerNickname, (System.currentTimeMillis() - startTimeMills) / 1000);
            gameLog("statistic");
            gameLog(statisticDto.toString());
            isGameStarted = false;
            isGameInProcess = false;
            firstPlayer.playerNickname = null;
            secondPlayer.playerNickname = null;
        }

        /**
         * Процедура реализации попадания одного игрока в другого
         */
        private void resolveDamage() {
            if (meFirst()) {
                gameService.shot(gameId, firstPlayer.playerNickname, secondPlayer.playerNickname);
            } else {
                gameService.shot(gameId, secondPlayer.playerNickname, firstPlayer.playerNickname);
            }
        }


        /**
         * Функция проверки на готовности к старту игры
         *
         * @return возвращает true или false
         */
        private boolean isReadyForStartGame() {
            return firstPlayer.playerNickname != null && secondPlayer.playerNickname != null && !isGameStarted
                    && !firstPlayer.playerNickname.equals(secondPlayer.playerNickname);
        }


        /**
         * Процедура вывода присвоенного имени игроку
         *
         * @param message - имя, считываемое от игрока
         */
        private void resolveNicknames(String message) {
            if (meFirst()) {
                fixNickname(message, firstPlayer, "First player: ", secondPlayer);
            } else {
                fixNickname(message, secondPlayer, "Second player: ", firstPlayer);
            }
        }

        /**
         * Процедура проверки на то, какой игрок стрелял
         *
         * @param messageFromClient - сообщение о "выстреле" отправляемый игроком
         */
        private void resolveShot(String messageFromClient) {
            if (meFirst()) {
                secondPlayer.sendMessage(messageFromClient);
            } else {
                firstPlayer.sendMessage(messageFromClient);
            }
        }

        /**
         * Процедура проверки на то, какой игрок двигался
         *
         * @param messageFromClient - сообщение о "передвижении" отправляемый игроком
         */
        private void resolveMove(String messageFromClient) {
            if (meFirst()) {
                secondPlayer.sendMessage(messageFromClient);
            } else {
                firstPlayer.sendMessage(messageFromClient);
            }
        }

        /**
         * Процедура присвоение имени игроку
         *
         * @param nickname      - имя считываемое от игрока
         * @param currentPlayer - один из игроков
         * @param s             - вывод сообщения от сервера
         * @param anotherPlayer - один из игроков
         */
        private void fixNickname(String nickname, ClientThread currentPlayer, String s, ClientThread anotherPlayer) {
            currentPlayer.playerNickname = nickname.substring(6);
            System.out.println(s + nickname.substring(6));
            anotherPlayer.sendMessage(nickname.substring(6));
        }

        /**
         * Процедура передачи сообщения от сервера - клиенту
         *
         * @param message - сообщение сервера
         */
        public void sendMessage(String message) {
            toClient.println(message);
        }


        /**
         * Функция проверки, кто является первым игроком
         *
         * @return возвращается true или false
         */
        private boolean meFirst() {
            return this == firstPlayer;
        }

        /**
         * Процедура вывода специфичных сообщений игрокам, что уже в игре
         *
         * @param message - сообщение от сервера - игрокам
         */
        private void gameLog(String message) {
            firstPlayer.sendMessage(message);
            secondPlayer.sendMessage(message);
        }

        public String getIp() {
            return ip;
        }
    }
}

