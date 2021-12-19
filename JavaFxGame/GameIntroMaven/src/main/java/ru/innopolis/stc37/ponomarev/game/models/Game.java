package ru.innopolis.stc37.ponomarev.game.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 23.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private Long id;
    private LocalDateTime dateTime;
    protected Player playerFirst;
    private Player playerSecond;
    private Integer playerFirstShotsCount;
    private Integer playerSecondShotsCount;
    private Long secondsGameTimeAmount;
}



