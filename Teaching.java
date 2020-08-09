import EvaluateMethods.FRAG;
import modules.ImageManagerModule;
import objects.Estimate.PixelValues;
import objects.SegmentationResults;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import tools.ImageSettings;
import tools.StartImageParams;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by oleh on 21.12.2016.
 */
public class Teaching {

    //private String expertImgPath = Main.pathToImg + "\\" + "1.jpg";/*** ЕКСПЕРТНЕ ЗОБРАЖЕННІ*/
    private Mat expertImgMat; // експертне зображення
    private Mat normalSegmentedImgMat; // підігнане по розміру оригінальне зображення
    private int getHistogramAverage = 0;
    private double squarePercent = 0;

    public Teaching(Mat originalMat, String expertImgPath){

        this.expertImgMat = Highgui.imread(expertImgPath); // зчитування експертного зображення
        this.normalImageSize(originalMat);

        Imgproc.cvtColor(this.expertImgMat, this.expertImgMat, Imgproc.COLOR_RGB2GRAY); // перетворення зобр в градації сірого
    }

    /**
     ПІДІГНАТИ РОЗМІРИ ЗОБРАЖЕНЬ
     * @param originalMat - оригінальне RGB зображення
     */
    private void normalImageSize(Mat originalMat){
        ImageSettings imageSettings = new ImageSettings(originalMat, this.expertImgMat);
        Mat normalSegmentedImgMat = imageSettings.getResizedImageMat();
        this.normalSegmentedImgMat = normalSegmentedImgMat;
    }


    public double calculatePercentage(double obtained, double total) {
        return obtained * 100/total;
    }

    /**
     * Генерація зображень з різними вхідними даними
     * @param stip - вхідні дані
     */
    public void generateImages(StartImageParams stip, Mat originalMat){

        //List<Integer> lowTreshValue = Arrays.asList(65,75,80,85,90,95, 100, 105);
        List<Integer> lowTreshValue = Arrays.asList( 75 );

        //int lastID = setInputValues(stip.getHistogramAverage(), stip.getRedValue(), stip.getGreenVlue(), stip.getBlueValue());

        for(int i = 0; i < lowTreshValue.size(); i++)
        {
            Mat newImageMat = new Mat();
            this.normalSegmentedImgMat.copyTo(newImageMat);

            /** ЗОБРАЖЕННЯ ПІСЛЯ ОБРОБКИ*/
            ImageManagerModule imageManagerModule = new ImageManagerModule();
            newImageMat = imageManagerModule.autoImageCorrection(newImageMat,lowTreshValue.get(i));

            /*** ПОРОГОВА СЕШМЕНТАЦІЯ
             * перетворення експертного зобр в градації сірого*/
            Imgproc.threshold(newImageMat, newImageMat, 200, 255, Imgproc.THRESH_BINARY);
            Highgui.imwrite(Main.pathToImg + "1" + i + "___" + lowTreshValue.get(i) + ".jpg", newImageMat);


            ArrayList<PixelValues> pixelValues = new ArrayList<>(1000);

            for (double ii=0; ii<newImageMat.rows(); ii++)
            {
                for (double j=0; j<newImageMat.cols(); j++)
                {
                    double[] data = newImageMat.get((int)ii, (int)j); //Stores element in an array
                    for (int k = 0; k < newImageMat.channels(); k++) //Runs for the available number of channels
                    {
                        if(data[k] != 255.0 && ii !=1535 && ii != 0 && j !=0 && j != 2047){
                            pixelValues.add(new PixelValues(ii, j , data[k]));
                        }
                     }
                }
            }


            this.squarePercent = this.calculatePercentage(pixelValues.size(), newImageMat.cols() * newImageMat.rows());
            //System.out.print(" Відношення до площі = " + squarePercent);



            StartImageParams stip1 = new StartImageParams();
            stip1.getStartValues1( originalMat, pixelValues);

            this.getHistogramAverage = stip1.getHistogramAverage();















            /*** ПОРІВНЯННЯ ЗОБРАЖЕНЬ*/
            //System.out.println("\n====================\n lowTreshValue = " + lowTreshValue.get(i) );
            //comparator.Main.main(newImageMat, this.expertImgMat);

            /** FRAG*/
            //FRAG frag = new FRAG(newImageMat, expertImgMat);
            //System.out.println("\nFRAG = " + frag.getResult());

            //Main.segmentationResults.add(new SegmentationResults(lastID, Main.imgName, lowTreshValue.get(i),
         //           comparator.Main.getDistance(),frag.getResult(),0));
        }

    }

    public double getSquarePercent(){
        return this.squarePercent;
    }

    public int getGetHistogramAverage(){
        return this.getHistogramAverage;
    }

    public void setOutputValues() {

        /**
         int index = 0; // індекс елемента в колекції
         double minDistance = Main.segmentationResults.get(0).distance;
         for(int i = 1; i < Main.segmentationResults.size(); i++ ){
         if(Main.segmentationResults.get(i).distance < minDistance){
         minDistance = Main.segmentationResults.get(i).distance;
         index = i;
         }
         }
         storeResultDataToDB (index);// зберігання запису в БД
         **/
        setHumanValue();
        Main.segmentationResults.clear();
    }

    /**
     * Вивід повідомлення для вводу номеру кращого зображення
     */
    private void setHumanValue(){
        System.out.println("Введіть номер найкращого зображення ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        storeResultDataToDB(num, num);
    }
    /**
     * анесення результатів до БД
     * @param index - індекс запису в колекції
     */
    private void storeResultDataToDB(int index){
        Main.storeData.insertOutputValues(Main.segmentationResults.get(index).lastID, Main.segmentationResults.get(index).imgName,
                Main.segmentationResults.get(index).lowThresh, Main.segmentationResults.get(index).distance,
                (double)Main.segmentationResults.get(index).FRAG,0 );
    }

    /**
     * @param index - індекс запису в колекції
     * @param humanV - оцінка зображення людиною
     */
    private void storeResultDataToDB(int index, int humanV){
        Main.storeData.insertOutputValues(Main.segmentationResults.get(index).lastID, Main.segmentationResults.get(index).imgName,
                Main.segmentationResults.get(index).lowThresh, Main.segmentationResults.get(index).distance,
                (double)Main.segmentationResults.get(index).FRAG,humanV );
    }
    /**
     * Додати вхідні параметри в БД
     * вивести останній ід
     */
    private static int setInputValues(double histogramAverage, double redValue, double greenVlue, double blueValue) {
        return Main.storeData.insertInputValues(histogramAverage, redValue, greenVlue, blueValue);
    }


}