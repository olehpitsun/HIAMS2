package tools;

public class ST_z_c {

    private double ER_square;
    private double ER_intense_status;

    private double PR_square;

    private double HERN2_square;
    private double HERN2_intense_status;

    private double Ki67_square;
    private double KI67_intense_status;

    private String G_value;

    public boolean isST_a_c;

    public ST_z_c(double HERN2_square, double ER_quare, double PR_square, double KI67_intense_status, double Ki67_square, String G_value) {
        this.HERN2_square = HERN2_square;
        this.PR_square = PR_square;
        this.PR_square = ER_quare;
        this.KI67_intense_status = KI67_intense_status;
        this.G_value = G_value;
    }

    private boolean condition_1(){

        System.out.println( "conditon1 :    this.ER_square = " + this.ER_square + "this.PR_square = " + this.PR_square);
        if(this.ER_square == 0 && this.PR_square == 0){
            return true;
        }

        return false;
    }


    private boolean condition_2(){

        System.out.println( "conditon2 :    this.KI67_intense_status = " + this.KI67_intense_status );
        if(this.KI67_intense_status == 3){
            return true;
        }

        return false;
    }

    private boolean condition_3(){

        System.out.println( "conditon3 :    this.Ki67_square = " + this.Ki67_square );
        if(this.Ki67_square > 60){
            return true;
        }

        return false;
    }

    public void calculateStatus(){

        this.isST_a_c = false;
        System.out.println(this.condition_1());
        System.out.println(this.condition_2());
        System.out.println(this.condition_3());

        if (this.condition_1()  && this.condition_2() && this.condition_3()
                && this.G_value.equals("G3")){
            this.isST_a_c = true;
        }
    }

    public boolean getStatus(){
        return this.isST_a_c;
    }
}
