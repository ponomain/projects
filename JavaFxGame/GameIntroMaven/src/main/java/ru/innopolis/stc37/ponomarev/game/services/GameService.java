package ru.innopolis.stc37.ponomarev.game.services;

import ru.innopolis.stc37.ponomarev.game.dto.StatisticDto;

/**
 * 26.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface GameService {
    /**
     * Метод вызывается для обеспечения начала игры. Если игрок с таким никнеймом уже есть, то мы работаем с ним
     * Если такого нет - то создаем нового
     * @param firstIp - АйПи адрес, с которого зашел первый игрок
     * @param secondIp - АйПи адрес, с которого зашел второй игрок
     * @param firstPlayerNickname имя первого игрока
     * @param secondPlayerNickname имя второга игрока
     * @return идентификатор игры
     */
    Long startGame(String firstIp, String secondIp,String firstPlayerNickname, String secondPlayerNickname);


    /**
     * Фиксирует выстрел игроков (попавшие)
     * @param gameId идентификатор игры
     * @param shooterNickname имя первого игрока
     * @param targetNickname имя второго игрока
     */
    void shot(Long gameId, String shooterNickname, String targetNickname);

    StatisticDto finishGame(Long gameId, String first, String second, long seconds);
}


