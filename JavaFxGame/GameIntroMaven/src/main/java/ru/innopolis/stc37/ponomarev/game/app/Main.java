package ru.innopolis.stc37.ponomarev.game.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.innopolis.stc37.ponomarev.game.dto.StatisticDto;
import ru.innopolis.stc37.ponomarev.game.repositories.*;
import ru.innopolis.stc37.ponomarev.game.services.GameService;
import ru.innopolis.stc37.ponomarev.game.services.GameServiceImpl;

import javax.sql.DataSource;
import java.util.Random;
import java.util.Scanner;

/**
 * 01.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {

        HikariConfig configuration = new HikariConfig();
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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter players");

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        Long gameId = gameService.startGame("127.0.0.1", "127.0.0.2", first, second);
        String shooter = first;
        String target = second;
        int i = 0;
        Random random = new Random();

        while (i < 4) {
            System.out.println(shooter + " take shot at " + target);
            scanner.nextLine();

            int success = random.nextInt(2);

            if (success == 0) {
                System.out.println("Get hit");
                gameService.shot(gameId, shooter, target);
            } else {
                System.out.println("Miss");
            }

            String temp = shooter;
            shooter = target;
            target = temp;
            i++;
        }

//        StatisticDto statistic = gameService.finishGame(gameId, first, second);
//        System.out.println(statistic);
    }
}



