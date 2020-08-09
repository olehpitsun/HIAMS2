package tools;

public class ST_a_c {

    private double ER_square;
    private int ER_intense_status;

    private double PR_square;

    private double HERN2_square;
    private int HERN2_intense_status;

    public ST_a_c(double ER_square, int ER_intense_status, double PR_square, double HERN2_square, int HERN2_intense_status) {
        this.ER_square = ER_square;
        this.ER_intense_status = ER_intense_status;
        this.PR_square = PR_square;
        this.HERN2_square = HERN2_square;
        this.HERN2_intense_status = HERN2_intense_status;
    }

    private boolean condition_1(){

        if(this.HERN2_square > 66 && this.HERN2_intense_status == 3){
            return true;
        }

        return false;
    }

    private boolean condition_2(){

        if(this.PR_square > 20 ){
            return true;
        }

        return false;
    }

    private boolean condition_3(){

        if(this.PR_square > 20 ){
            return true;
        }

        return false;
    }
}
