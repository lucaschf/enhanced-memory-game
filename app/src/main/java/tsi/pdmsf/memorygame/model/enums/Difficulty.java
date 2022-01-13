package tsi.pdmsf.memorygame.model.enums;

public enum Difficulty {

    EASY(1, "Fácil"),
    MEDIUM(2, "Médio"),
    HARD(3, "Difícil");

    private Integer code;
    private String description;

    Difficulty(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}//enum Difficulty
