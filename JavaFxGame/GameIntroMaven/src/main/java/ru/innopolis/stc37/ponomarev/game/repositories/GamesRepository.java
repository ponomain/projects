package ru.innopolis.stc37.ponomarev.game.repositories;


import ru.innopolis.stc37.ponomarev.game.models.Game;

/**
 * 26.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface GamesRepository {
    void save(Game game);

    Game findById(Long gameId);

    void update(Game game);

}


