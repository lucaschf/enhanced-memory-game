package tsi.pdmsf.memorygame.ui.activity;

public class PointControl {
    private static final int POINT = 10000;
    private static final int ERROR_VALUE = 2;
    private int timeStart;
    private int timeFinish;
    private int errors;

    public int ev(){
        return this.ERROR_VALUE;
    }

    public int pt(){
        return this.POINT;
    }

    public static int getPOINT() {
        return POINT;
    }

    public static int getErrorValue() {
        return ERROR_VALUE;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public PointControl() {
        this.errors = 0;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(int timeFinish) {
        this.timeFinish = timeFinish;
    }

    public int getErrors() {
        return errors;
    }

    public void addErrors() {
        this.errors++;
    }

    public int calcularPontos() {
        return POINT-((ERROR_VALUE*getErrors())+gameTimeInSeconds()/100)*100;
    }

    public int gameTimeInSeconds(){
        return (getTimeFinish()-getTimeStart())/1000;
    }
}
