package tools;

import objects.Estimate.PixelValues;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Oleh7 on 2/26/2016.
 */
public class StartImageParams {

    private int redValue, greenVlue, blueValue, histogramAverage;

    public StartImageParams(){}

    public void getStartValues(Mat inputImg ){


        System.out.println("SIZE = " + inputImg.size());
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        String timeStamp = generatedString + "temp.png";
        matToBufImg matToBufImg = new matToBufImg(inputImg,timeStamp);
        BufferedImage bi = matToBufImg.getImage();


        for (int i = 0; i < 256; i++) {}

        int x0 =0;
        int y0 = 0;
        int w = bi.getWidth();
        int h = bi.getHeight();

        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                //System.out.println(pixel.brighter());
               // System.out.println(pixel.darker() );

                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        int num = w * h;
        this.redValue = (int)sumr/num;
        this.greenVlue = (int)sumg/num;
        this.blueValue = (int)sumb/num;

        int bright = (int) (299 * sumr + 587 * sumg + 114 * sumb) / num;
        //bright = bright/1000;

        this.histogramAverage = Math.abs(bright);

    }


    public void getStartValues1(Mat inputImg, ArrayList<PixelValues> pixelValues ){
        String timeStamp = "temp.png";
        matToBufImg matToBufImg1 = new matToBufImg(inputImg,timeStamp);
        BufferedImage bi = matToBufImg1.getImage();


        for (int i = 0; i < 256; i++) {}

        int x0 =0;
        int y0 = 0;
        int w = bi.getWidth();
        int h = bi.getHeight();

        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;

        for (int p = 0 ; p < pixelValues.size(); p++){
            //System.out.println(pixelValues.get(p).Y.intValue() + " == " +  pixelValues.get(p).Y.intValue());
            Color pixel = new Color(bi.getRGB( pixelValues.get(p).Y.intValue(),  pixelValues.get(p).X.intValue()));

            sumr += pixel.getRed();
            sumg += pixel.getGreen();
            sumb += pixel.getBlue();
        }

        int num = w * h;
        this.redValue = (int)sumr/num;
        this.greenVlue = (int)sumg/num;
        this.blueValue = (int)sumb/num;

        int bright = (int) (299 * sumr + 587 * sumg + 114 * sumb) / num;
        //bright = bright/1000;

        this.histogramAverage = Math.abs(bright);
        //System.out.println( " ||| Середній рівень яскравості = " + this.histogramAverage );

    }

    public int getRedValue(){
        return this.redValue;
    }

    public int getGreenVlue(){
        return this.greenVlue;
    }

    public int getBlueValue(){
        return this.blueValue;
    }

    public int getHistogramAverage(){
        return histogramAverage;
    }
}

