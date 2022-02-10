package tsi.pdmsf.memorygame.model;

public class Score {
    private String name;
    private int level;
    private int score;
    private int time;
    private int error;

    public Score(String name, int level, int score, int time, int error){
        this.name = name;
        this.level = level;
        this.score = score;
        this.time = time;
        this.error = error;
    }

    public String getName() { return name; }

    public int getScore() { return score; }

    public int getLevel() { return level; }

    public int getTime() { return time;  }

    public int getError() { return error; }
}
