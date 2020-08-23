package tools;

public class ST_a_c {

    private double ER_square;
    private double ER_intense_status;

    private double PR_square;

    private double HERN2_square;
    private double HERN2_intense_status;

    private double Ki67_square;

    private String G_value;

    public boolean isST_a_c;

    public ST_a_c(double ER_square, double ER_intense_status, double PR_square, double HERN2_square, double HERN2_intense_status,
                  double KI67_square,String G_value) {
        this.ER_square = ER_square;
        this.ER_intense_status = ER_intense_status;
        this.PR_square = PR_square;
        this.HERN2_square = HERN2_square;
        this.HERN2_intense_status = HERN2_intense_status;
        this.Ki67_square = KI67_square;
        this.G_value = G_value;
    }

    private boolean condition_1(){

        System.out.println( "conditon1 :            this.ER_square = " + this.ER_square + "this.ER_intense_status = " + this.ER_intense_status);
        if(this.ER_square > 66 && this.ER_intense_status == 3.0){
            return true;
        }

        return false;
    }

    private boolean condition_2(){

        System.out.println("conditon 2 :             this.PR_square" + this.PR_square);
        if(this.PR_square > 20 ){
            return true;
        }

        return false;
    }

    private boolean condition_3(){

        System.out.println("conditon 3 :             this.Ki67_square" + this.Ki67_square);

        if(this.Ki67_square < 20){
            return  true;
        }

        return false;
    }

    public void calculateStatus(){

        this.isST_a_c = false;
        System.out.println(this.condition_1());
        System.out.println(this.condition_2());
        System.out.println(this.condition_3());
        if (this.condition_1() &&  this.condition_2() && this.condition_3()
                && (this.G_value.equals("G2") || this.G_value.equals("G3"))){
            this.isST_a_c = true;
        }
    }

    public boolean getStatus(){
        return this.isST_a_c;
    }
}
