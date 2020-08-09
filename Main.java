import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.ImageParams;
import objects.SegmentationResults;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import tools.*;
import lib.DB.storeData.storeData;

import java.util.*;

/**
 * Created by oleh on 21.12.2016.
 */
public class Main {

    public static String pathToImg = "F:\\flash_02052020\\TNEU\\091921\\Pr\\";
    public static String imgName = "1";
    public static storeData storeData;
    public static ObservableList<SegmentationResults> segmentationResults = FXCollections.observableArrayList();
    public static ArrayList<ImageParams> imageParamsArrayList = new ArrayList<>();
    public static String pathKeys[] = {"DЕr1", "Ki67", "Pr", "Her2n", "Histology"};

    public static double kp = 0;
    public static double Kintensiive = 2;
    public static double Ktotal = 0;

    public static double HERN2Square = 0;
    public static double HERN2_intense_status = 0;

    public static double ER_quare = 0;
    public static double ER_intense_status = 0;

    public static double PR_square = 0;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Map<String,String> myMap1 = new HashMap<String, String>();
        List<Map<String , String>> myMap  = new ArrayList<Map<String,String>>();

        myMap1.put("Pr", "E:\\tmp\\Newimg\\189-20\\Pr\\1.jpg");
        myMap1.put("Her2n", "E:\\tmp\\Newimg\\189-20\\Her2n\\1n.jpg");
        myMap1.put("Ki67", "E:\\tmp\\Newimg\\189-20\\Ki67\\1.jpg");
        myMap1.put("DЕr1","E:\\tmp\\Newimg\\189-20\\Er\\2.jpg");
        myMap1.put("Histology", "E:\\tmp\\Newimg\\189-20\\G2x400.jpg");
        myMap.add(0, myMap1);

        for (String pathKey: pathKeys) {

            String imagePath = myMap.get(0).get(pathKey);
            System.out.println(pathKey + " = > " + imagePath);

            Mat originalMat = new Mat();
            originalMat = Highgui.imread(imagePath); // зчитування зображення

            StartImageParams stip = new StartImageParams();
            stip.getStartValues(originalMat);

            Teaching teaching = new Teaching(originalMat, imagePath);
            teaching.generateImages(stip, originalMat);

            System.out.println("Відношення до площі   " + teaching.getSquarePercent() +
                                "  =-----= Середній рівень яскравості  " + teaching.getGetHistogramAverage());

            if(pathKey == "Her2n"){
                HERN2Square = teaching.getSquarePercent();
                Ki ki = new Ki(teaching.getGetHistogramAverage());
                HERN2_intense_status = ki.getStatus();
            }
            if(pathKey == "DЕr1"){
                ER_quare = teaching.getSquarePercent();
                Ki ki = new Ki(teaching.getGetHistogramAverage());
                ER_intense_status = ki.getStatus();
            }
            if(pathKey == "PR"){
                PR_square = teaching.getSquarePercent();
            }


            originalMat.release();originalMat = new Mat();

            imageParamsArrayList.add(new ImageParams(pathKey, teaching.getSquarePercent(), teaching.getGetHistogramAverage()));


        }

        System.out.println("+===================================================================================");
        System.out.println("RULES");
        System.out.println("+===================================================================================");

        for (int i = 0; i < imageParamsArrayList.size(); i++) {
            Kp kp1 = new Kp(imageParamsArrayList.get(i).deltaS);
            System.out.println(imageParamsArrayList.get(i).name +  " Kp = " + kp1.getKp());



            kp = kp1.getKp();
        }

        Ktotal = Kintensiive + kp;
        System.out.println("Kt = " + Ktotal);

        Kt kt = new Kt(Ktotal);
        System.out.println("Kt status = " + kt.getStatus());

        System.out.println("+===================================HERN 2================================================");
        System.out.println(" hern2 intense status = " + HERN2_intense_status + " square " + HERN2Square );
        System.out.println("+===================================================================================\n");

        System.out.println("+=================================== ER ================================================");
        System.out.println("ER_intense_status = " + ER_intense_status + " => ER Square = " + ER_quare);


        System.out.println("+=================================== PR ================================================");
        System.out.println("PR square = " + PR_square  );

        System.out.println("+===================================================================================\n");

    }
}
