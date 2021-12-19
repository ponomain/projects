package ru.innopolis.stc37.ponomarev.game.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.innopolis.stc37.ponomarev.game.repositories.*;
import ru.innopolis.stc37.ponomarev.game.server.GameServer;
import ru.innopolis.stc37.ponomarev.game.services.GameService;
import ru.innopolis.stc37.ponomarev.game.services.GameServiceImpl;

import javax.sql.DataSource;

/**
 * 04.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class MainServer {
    public static void main(String[] args) {

        var configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/game_db");
        configuration.setDriverClassName("org.postgresql.Driver");
        configuration.setUsername("postgres");
        configuration.setPassword("sgue8qej");
        configuration.setMaximumPoolSize(20);
        DataSource dataSource = new HikariDataSource(configuration);

        GamesRepository gamesRepository = new GamesRepositoryJdbcImpl(dataSource);
        PlayersRepository playersRepository = new PlayersRepositoryJdbcImpl(dataSource);
        ShotsRepository shotsRepository = new ShotsRepositoryJdbcImpl(dataSource);
        GameService gameService = new GameServiceImpl(gamesRepository, playersRepository, shotsRepository);

        var gameServer = new GameServer(gameService);
        gameServer.start(7777);
    }
}

