package ru.innopolis.stc37.ponomarev.game.repositories;

import ru.innopolis.stc37.ponomarev.game.models.Game;
import ru.innopolis.stc37.ponomarev.game.models.Player;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

import static ru.innopolis.stc37.ponomarev.game.repositories.RepositoriesUtils.generateId;

/**
 * 01.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class GamesRepositoryJdbcImpl implements GamesRepository {
    //Запрос на поиск игры по ID в базе данных
    // language=SQL
    private static final String SQL_FIND_BY_ID = "select * from game where id = ?";
    //Запрос на сохранение игры в базу данный
    //language=SQL
    private static final String SQL_INSERT_GAME = "insert into game (datetime, player_first, player_second" +
            ", player_first_shots_count, player_second_shots_count, seconds_game_time_amount) " +
            " values (?, ?, ?, ?, ?, ?);";
    //Запрос на обновление игры в базе данных
    //language=SQL
    private static final String SQL_UPDATE_GAME = "update game set " +
            " player_first_shots_count = ?, player_second_shots_count = ?, seconds_game_time_amount = ? where id = ?";

    // Преобразование строки таблицы из базы данных в объект Game
    private static final RowMapper<Game> gameRowMapper = row -> Game.builder()
            .id(row.getLong("id"))
            .dateTime(LocalDateTime.parse(row.getString("datetime")))
            .playerFirst(Player.builder()
                    .id(row.getLong("player_first"))
                    .build())
            .playerSecond(Player.builder()
                    .id(row.getLong("player_second"))
                    .build())
            .playerFirstShotsCount(row.getInt("player_first_shots_count"))
            .playerSecondShotsCount(row.getInt("player_second_shots_count"))
            .secondsGameTimeAmount(row.getLong("seconds_game_time_amount"))
            .build();

    private final DataSource dataSource;

    public GamesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Метод сохранения игры в базу
     *
     * @param game - объект для сохранения
     */
    @Override
    public void save(Game game) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GAME, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, game.getDateTime().toString());
            statement.setLong(2, game.getPlayerFirst().getId());
            statement.setLong(3, game.getPlayerSecond().getId());
            statement.setInt(4, game.getPlayerFirstShotsCount());
            statement.setInt(5, game.getPlayerSecondShotsCount());
            statement.setLong(6, game.getSecondsGameTimeAmount());

            //смотрим, сколько строк было обновлено
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Can't insert");
            }
            // запросили сгенерированные базой данных ключи
            game.setId(generateId(statement));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * Метод нахождения игры по ID
     *
     * @param gameId - искомый объект
     * @return - возвращается объект преобразованный из строки таблицы баз данных
     */
    @Override
    public Game findById(Long gameId) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setLong(1, gameId);
            try (ResultSet rows = statement.executeQuery()) {
                if (rows.next()) {
                    return gameRowMapper.mapRow(rows);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Метод обновления игры в базе данных
     *
     * @param game - обновляемый объект
     */
    @Override
    public void update(Game game) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_GAME)) {
            statement.setInt(1, game.getPlayerFirstShotsCount());
            statement.setInt(2, game.getPlayerSecondShotsCount());
            statement.setLong(3, game.getSecondsGameTimeAmount());
            statement.setLong(4, game.getId());

            //смотрим, сколько строк было обновлено
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Can't update");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}




