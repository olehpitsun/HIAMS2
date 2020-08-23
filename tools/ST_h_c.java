package tools;

public class ST_h_c {

    private double ER_square;
    private double ER_intense_status;

    private double PR_square;

    private double HERN2_square;
    private double HERN2_intense_status;

    private double Ki67_square;

    private String G_value;

    public boolean isST_a_c;

    public ST_h_c(double HERN2_square, double ER_quare, double PR_square, String G_value) {
        this.HERN2_square = HERN2_square;
        this.PR_square = PR_square;
        this.PR_square = ER_quare;
        this.G_value = G_value;
    }

    private boolean condition_1(){

        System.out.println( "conditon1 :            this.HERN2_square = " + this.HERN2_square + "this.ER_square = " + this.ER_square + "this.PR_square = " + this.PR_square);
        if(this.HERN2_square > 0 && this.ER_square == 0 && this.PR_square == 0){
            return true;
        }

        return false;
    }


    public void calculateStatus(){

        this.isST_a_c = false;
        System.out.println(this.condition_1());

        if (this.condition_1()  &&
                this.G_value.equals("G3")){
            this.isST_a_c = true;
        }
    }

    public boolean getStatus(){
        return this.isST_a_c;
    }
}
