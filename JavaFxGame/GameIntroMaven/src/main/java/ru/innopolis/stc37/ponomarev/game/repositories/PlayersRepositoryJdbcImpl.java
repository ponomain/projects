package ru.innopolis.stc37.ponomarev.game.repositories;

import ru.innopolis.stc37.ponomarev.game.models.Player;

import javax.sql.DataSource;
import java.sql.*;

import static ru.innopolis.stc37.ponomarev.game.repositories.RepositoriesUtils.generateId;


/**
 * 03.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class PlayersRepositoryJdbcImpl implements PlayersRepository {
    //Запрос на поиск игрока по имени
    // language=SQL
    private static final String SQL_FIND_BY_NICKNAME = "select * from player where nickname = ?";
    //Запрос на сохранение игрока в базу данных
    //language=SQL
    private static final String SQL_INSERT_PLAYER = "insert into player (ip, nickname, points, max_wins_count, max_loses_count ) " +
            " values (?, ?, ?, ?, ?);";
    //Запрос на обновление игрока в базе данных
    //language=SQL
    private static final String SQL_UPDATE_PLAYER = "update player set" +
            " points = ?, max_wins_count = ?, max_loses_count = ?, ip = ? where id = ?";

    private final DataSource dataSource;

    //Преобразование строки таблицы из базы данных в объект Player
    private static final RowMapper<Player> playerRowMapper = row -> Player.builder()
            .id(row.getLong("id"))
            .ip(row.getString("ip"))
            .nickname(row.getString("nickname"))
            .points(row.getInt("points"))
            .maxWinsCount(row.getInt("max_wins_count"))
            .maxLosesCount(row.getInt("max_loses_count"))
            .build();

    public PlayersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Метод нахождения игрока по имени
     *
     * @param nickname - искомое имя
     * @return - возвращается объект преобразованный из строки таблицы баз данных
     */
    @Override
    public Player findByNickname(String nickname) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NICKNAME)) {
            statement.setString(1, nickname);
            try (ResultSet rows = statement.executeQuery()) {
                if (rows.next()) {
                    return playerRowMapper.mapRow(rows);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Метод сохранения игрока в базе данных
     *
     * @param player - сохраняемый игрок
     */
    @Override
    public void save(Player player) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PLAYER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, player.getIp());
            statement.setString(2, player.getNickname());
            statement.setInt(3, player.getPoints());
            statement.setInt(4, player.getMaxWinsCount());
            statement.setInt(5, player.getMaxLosesCount());

            //смотрим, сколько строк было обновлено
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Can't insert");
            }

            // запросили сгенерированные базой данных ключи
            player.setId(generateId(statement));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Метод обновления игрока в базе данных
     *
     * @param player - обновляемый игрок
     */
    @Override
    public void update(Player player) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PLAYER)) {
            statement.setInt(1, player.getPoints());
            statement.setInt(2, player.getMaxWinsCount());
            statement.setInt(3, player.getMaxLosesCount());
            statement.setString(4, player.getIp());
            statement.setLong(5, player.getId());

            //смотрим, сколько строк было обновлено
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Can't insert");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}




