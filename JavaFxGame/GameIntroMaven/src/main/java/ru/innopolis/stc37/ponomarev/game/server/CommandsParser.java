package ru.innopolis.stc37.ponomarev.game.server;

/**
 * 07.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class CommandsParser {

    private CommandsParser() {

    }

    public static boolean isMessageForShot(String messageFromClient) {
        return messageFromClient.equals("shot");
    }

    public static boolean isMessageForDamage(String messageFromClient) {
        return messageFromClient.equals("damage");
    }

    public static boolean isMessageForMove(String messageFromClient) {
        return messageFromClient.equals("left") || messageFromClient.equals("right");
    }

    public static boolean isMessageForFinishGame(String messageFromClient) {
        return messageFromClient.equals("0 HP");
    }

    public static boolean isMessageForNickname(String messageFromClient) {
        return messageFromClient.startsWith("name: ");
    }
}

