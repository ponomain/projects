package ru.innopolis.stc37.ponomarev.game.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 12.04.2021
 * lesson26
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}



