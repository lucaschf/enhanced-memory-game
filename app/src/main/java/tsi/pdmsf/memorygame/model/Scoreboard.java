package tsi.pdmsf.memorygame.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import tsi.pdmsf.memorygame.model.enums.GameLevel;

@Entity(tableName = "scoreboard")
public class Scoreboard {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String nameUser;
    private Double punctuation;
    private GameLevel difficulty;
    private Integer errors;
    private int time;


    public Scoreboard() {
        this.nameUser = "";
        this.punctuation = 0.00;
        this.difficulty = GameLevel.EASY;
        this.errors = 0;
        this.time = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Double getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Double punctuation) {
        this.punctuation = punctuation;
    }

    public GameLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameLevel difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Integer getErrors() {
        return errors;
    }

    public void setErrors(Integer errors) {
        this.errors = errors;
    }

}