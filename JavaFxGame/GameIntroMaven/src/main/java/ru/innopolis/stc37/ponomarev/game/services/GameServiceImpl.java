package ru.innopolis.stc37.ponomarev.game.services;

import ru.innopolis.stc37.ponomarev.game.dto.StatisticDto;
import ru.innopolis.stc37.ponomarev.game.models.Game;
import ru.innopolis.stc37.ponomarev.game.models.Player;
import ru.innopolis.stc37.ponomarev.game.models.Shot;
import ru.innopolis.stc37.ponomarev.game.repositories.GamesRepository;
import ru.innopolis.stc37.ponomarev.game.repositories.PlayersRepository;
import ru.innopolis.stc37.ponomarev.game.repositories.ShotsRepository;

import java.time.LocalDateTime;

/**
 * 26.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class GameServiceImpl implements GameService {
    private final GamesRepository gamesRepository;
    private final PlayersRepository playersRepository;
    private final ShotsRepository shotsRepository;

    public GameServiceImpl(GamesRepository gamesRepository, PlayersRepository playersRepository, ShotsRepository shotsRepository) {
        this.gamesRepository = gamesRepository;
        this.playersRepository = playersRepository;
        this.shotsRepository = shotsRepository;
    }

    @Override
    public Long startGame(String firstIp, String secondIp, String firstPlayerNickname, String secondPlayerNickname) {
        System.out.println("Received : " + firstIp + " # " + secondIp + " # " + firstPlayerNickname + " # " + secondPlayerNickname);
        Player first = checkIfExists(firstIp, firstPlayerNickname);
        Player second = checkIfExists(secondIp, secondPlayerNickname);
        // создали игру
        var game = Game.builder()
                .dateTime(LocalDateTime.now())
                .playerFirst(first)
                .playerSecond(second)
                .playerFirstShotsCount(0)
                .playerSecondShotsCount(0)
                .secondsGameTimeAmount(0L)
                .build();

        // сохранили игру в репозитории
        gamesRepository.save(game);
        return game.getId();
    }

    private Player checkIfExists(String ip, String nickname) {
        var player = playersRepository.findByNickname(nickname);
        // если нет первого игрока под таким именем
        if (player == null) {
            // создаем игрока
            player = Player.builder()
                    .ip(ip)
                    .nickname(nickname)
                    .points(0)
                    .maxWinsCount(0)
                    .maxLosesCount(0)
                    .build();
            // сохраняем
            playersRepository.save(player);
        } else {
            // если такой игрок был, то обновляем АйПи адрес
            player.setIp(ip);
            playersRepository.update(player);
        }
        return player;
    }

    @Override
    public void shot(Long gameId, String shooterNickname, String targetNickname) {
        System.out.println(gameId + " # " + shooterNickname + " # " + targetNickname);
        // получаем того, кто стрелял из репозитория
        var shooter = playersRepository.findByNickname(shooterNickname);
        // получаем того, в кого  стреляли из репозитория
        var target = playersRepository.findByNickname(targetNickname);
        // получаем игру
        var game = gamesRepository.findById(gameId);
        // создаем выстрел
        var shot = Shot.builder()
                .dateTime(LocalDateTime.now())
                .game(game)
                .shooter(shooter)
                .target(target)
                .build();
        // увеличиваем очки у стрелявшего
        shooter.setPoints(shooter.getPoints() + 1);
        // если стрелявший - первый игрок
        if (game.getPlayerFirst().getId().equals(shooter.getId())) {
            // сохраняем информацию о выстреле
            game.setPlayerFirstShotsCount(game.getPlayerFirstShotsCount() + 1);
        }
        // если стрелявший - второй игрок
        if (game.getPlayerSecond().getId().equals(shooter.getId())) {
            // сохраняем информацию о выстреле
            game.setPlayerSecondShotsCount(game.getPlayerSecondShotsCount() + 1);
        }
        // обновляем данные по стреляющему
        playersRepository.update(shooter);
        // обновляем данные по игре
        gamesRepository.update(game);
        // сохраняем выстрел
        shotsRepository.save(shot);

    }

    /**
     * Метод, который выводит статистику по завершенной игре
     *
     * @param gameId - ID игры
     * @return - возвращает статистику
     */
    @Override
    public StatisticDto finishGame(Long gameId, String first, String second, long seconds) {
        final var BASE_ONE = 1;
        var game = gamesRepository.findById(gameId);
        var playerFirst = playersRepository.findByNickname(first);
        var playerSecond = playersRepository.findByNickname(second);
        var statistic = new StatisticDto();
        statistic.setGameId(gameId);
        statistic.setPlayerFirst(playerFirst.getNickname());
        statistic.setPlayerSecond(playerSecond.getNickname());
        statistic.setHitsCountFirst(game.getPlayerFirstShotsCount());
        statistic.setHitsCountSecond(game.getPlayerSecondShotsCount());
        statistic.setPointsCountFirst(statistic.getPointsCountFirst() + playerFirst.getPoints());
        statistic.setPointsCountSecond(statistic.getPointsCountSecond() + playerSecond.getPoints());

        if (game.getPlayerFirstShotsCount() > game.getPlayerSecondShotsCount()) {
            playerFirst.setMaxWinsCount(playerFirst.getMaxWinsCount() + BASE_ONE);
            playerSecond.setMaxLosesCount(playerSecond.getMaxLosesCount() + BASE_ONE);
            statistic.setVictoryLine(playerFirst.getNickname());
        } else if (game.getPlayerFirstShotsCount() < game.getPlayerSecondShotsCount()) {
            playerSecond.setMaxWinsCount(playerSecond.getMaxWinsCount() + BASE_ONE);
            playerFirst.setMaxLosesCount(playerFirst.getMaxLosesCount() + BASE_ONE);
            statistic.setVictoryLine(playerSecond.getNickname());
        }

        playersRepository.update(playerFirst);
        playersRepository.update(playerSecond);
        statistic.setTime(seconds);
        game.setSecondsGameTimeAmount(seconds);
        gamesRepository.update(game);
        return statistic;
    }
}





