package ru.innopolis.stc37.ponomarev.game.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
public class Player {
        private Long id;
        private String ip;
        private String nickname;
        private Integer points;
        private Integer maxWinsCount;
        private Integer maxLosesCount;
}



