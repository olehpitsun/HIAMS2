package tools;

public class Kp {

    private double square;

    public  Kp(double square){
        this.square = square;
    }

    public int getKp(){

        int kp = 0;

        if(this.square == 0){
            kp = 0;
        }else if (this.square <= 1){
            kp = 1;
        }else if (this.square > 1 && this.square <= 10){
            kp = 2;
        }else if(this.square >= 11 && this.square <= 33 ){
            kp = 3;
        }else if(this.square >= 34 && this.square <= 66 ){
            kp = 4;
        }else if(this.square >= 67 && this.square <= 100 ){
            kp = 5;
        }

        return kp;
    }
}
