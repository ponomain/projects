package ru.innopolis.stc37.ponomarev.game.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 11.05.2021
 * game-intro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class RepositoriesUtils {
    private RepositoriesUtils() {
        throw new IllegalStateException();
    }

    /**
     * Метод генерирует id для объекта
     * @param statement запрос в SQL
     * @return id
     */
    public static Long generateId(PreparedStatement statement) {
        long id;
        try (
                ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                // мы попросили сгенерированный базой данных id-шник
                id = generatedKeys.getLong("id");
            } else {
                throw new SQLException(("Can't retrieve id"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return id;
    }
}

