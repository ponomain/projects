package ru.innopolis.stc37.ponomarev.game.dto;

/**
 * 26.03.2021
 * GameIntro
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
// информация об игре
public class StatisticDto {
    private Long gameId;
    private String playerFirst;
    private String playerSecond;
    private long time;
    private int hitsCountFirst;
    private int hitsCountSecond;
    private int pointsCountFirst;
    private int pointsCountSecond;
    private String victoryLine;


    @Override
    public String toString() {
        return "" + "Game statistic: \n" +
                " Game id = " + gameId +
                "\n Player 1: " + playerFirst +
                ", hits - " + hitsCountFirst +
                ", points - " + pointsCountFirst +
                "\n Player 2: " + playerSecond +
                ", hits - " + hitsCountSecond +
                ", points - " + pointsCountSecond +
                "\n Victory : " + victoryLine +
                "\n Game lasts: " + time + " seconds";
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setPlayerFirst(String playerFirst) {

        this.playerFirst = playerFirst;
    }

    public void setPlayerSecond(String playerSecond) {
        this.playerSecond = playerSecond;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setHitsCountFirst(int hitsCountFirst) {
        this.hitsCountFirst = hitsCountFirst;
    }

    public void setHitsCountSecond(int hitsCountSecond) {
        this.hitsCountSecond = hitsCountSecond;
    }

    public int getPointsCountFirst() {
        return pointsCountFirst;
    }

    public int getPointsCountSecond() {
        return pointsCountSecond;
    }

    public void setPointsCountFirst(int pointsCountFirst) {
        this.pointsCountFirst = pointsCountFirst;
    }

    public void setPointsCountSecond(int pointsCountSecond) {
        this.pointsCountSecond = pointsCountSecond;
    }

    public void setVictoryLine(String victoryLine) {
        this.victoryLine = victoryLine;
    }
}






