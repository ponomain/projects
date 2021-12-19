package ru.innopolis.stc37.ponomarev.game.repositories;

import ru.innopolis.stc37.ponomarev.game.models.Shot;

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
public class ShotsRepositoryJdbcImpl implements ShotsRepository {
    //Запрос на сохранение выстрела в базе данных
    //language=SQL
    private static final String SQL_INSERT_SHOT = "insert into shots(datetime, game_id, shooter_id, target_id)" +
            "values (?, ?, ?, ?)";

    private final DataSource dataSource;

    public ShotsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Метод сохраняет выстрел в базе данных
     *
     * @param shot - сохраняемый выстрел
     */
    @Override
    public void save(Shot shot) {
        try (var connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SHOT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, shot.getDateTime().toString());
            statement.setLong(2, shot.getGame().getId());
            statement.setLong(3, shot.getShooter().getId());
            statement.setLong(4, shot.getTarget().getId());

            //смотрим, сколько строк было обновлено
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Can't insert");
            }

            // запросили сгенерированные базой данных ключи
            shot.setId(generateId(statement));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}



