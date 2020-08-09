package tools;

public class Kt {

    private double kt;

    public Kt(double kt){
        this.kt = kt;
    }

    public boolean getStatus(){

        boolean status = false;

        if(this.kt >= 0 && this.kt <= 2){
            status = false;
        }else if (this.kt >= 3 && this.kt <= 8){
            status = true;
        }
        return status;
    }
}
