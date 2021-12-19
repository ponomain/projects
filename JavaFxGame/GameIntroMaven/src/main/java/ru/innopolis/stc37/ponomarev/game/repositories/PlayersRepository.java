package ru.innopolis.stc37.ponomarev.game.repositories;

import ru.innopolis.stc37.ponomarev.game.models.Player;

/**
 * 26.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface PlayersRepository {
    Player findByNickname(String nickname);
    void save(Player player);
    void update(Player player) ;
}




