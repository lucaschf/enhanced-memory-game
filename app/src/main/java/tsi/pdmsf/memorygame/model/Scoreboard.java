package tsi.pdmsf.memorygame.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import tsi.pdmsf.memorygame.model.enums.Difficulty;

@Entity(tableName = "scoreboard")
public class Scoreboard {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String nameUser;
    private Double punctuaction;
    private Difficulty difficulty;
    private Integer errors;
    private LocalDateTime time;


    public Scoreboard(Integer id, String nameUser, Double punctuaction, Difficulty difficulty, Integer errors, LocalDateTime time) {
        this.id = id;
        this.nameUser = nameUser;
        this.punctuaction = punctuaction;
        this.difficulty = difficulty;
        this.errors = errors;
        this.time = time;
    }

    public Scoreboard() {
        this.id = 0;
        this.nameUser = "";
        this.punctuaction = 0.00;
        this.difficulty = Difficulty.EASY;
        this.errors = 0;
        this.time = LocalDateTime.now();
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

    public Double getPunctuaction() {
        return punctuaction;
    }

    public void setPunctuaction(Double punctuaction) {
        this.punctuaction = punctuaction;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Integer getErrors() {
        return errors;
    }

    public void setErrors(Integer erros) {
        this.errors = erros;
    }

    /**
     * Necessário para o CRUD.
     */
    public static Difficulty toEnum(Integer cod){
        if(cod == null)
            return null;

        for(Difficulty tip : Difficulty.values()){
            if(cod.equals(tip.getCode())){
                return tip;
            }
        }
        throw new IllegalArgumentException("Id Iválido : " + cod);
    }

}//class Placar
