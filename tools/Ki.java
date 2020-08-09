package tools;

public class Ki {

    private int intense;

    public Ki(int intense){
        this.intense = intense;
    }

    public int getStatus(){

        int status = 0;

        if(this.intense >= 0 && this.intense <= 30){
            status = 1;
        }else if (this.intense > 30 && this.intense <= 60){
            status = 2;
        }else if (this.intense > 60){
            status = 3;
        }

        return status;
    }
}
