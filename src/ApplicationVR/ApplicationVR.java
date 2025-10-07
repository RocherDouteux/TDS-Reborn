package ApplicationVR;

import Application.Application;
import CImage.CImage;
import CImage.CImageNG;
import CImage.CImageRGB;
import java.io.File;
import java.io.IOException;

public class ApplicationVR {
    public static void RunAllApplicationVR() throws IOException {
        // Question 1A
        {
        System.out.println("Question 1A begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/imageBruitee1.png"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question1A(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 1A Global : " + totalSecond + " s");
        System.out.println("Question 1A Avg : " + avgSecond + " s");
        }
        
        // Question 1B
        {
        System.out.println("Question 1B begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/imageBruitee2.png"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question1B(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 1B Global : " + totalSecond + " s");
        System.out.println("Question 1B Avg : " + avgSecond + " s");
        }
        
        // Question 2A
        {
        System.out.println("Question 2A begin");
        CImage source = new CImageRGB(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question2A(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 2A Global : " + totalSecond + " s");
        System.out.println("Question 2A Avg : " + avgSecond + " s");
        }
        
        // Question 2B
        {
        System.out.println("Question 2B begin");
        CImageNG source = new CImageNG(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
        CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question2B(sourceColor, source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 2B Global : " + totalSecond + " s");
        System.out.println("Question 2B Avg : " + avgSecond + " s");
        }
        
        // Question 3
        {
        System.out.println("Question 3 begin");
        CImage source = new CImageRGB(new File("res/Images/ImagesEtape5/petitsPois.png"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question3(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 3 Global : " + totalSecond + " s");
        System.out.println("Question 3 Avg : " + avgSecond + " s");
        }
        
        // Question 4
        {
        System.out.println("Question 4 begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/balanes.png"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question4(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 4 Global : " + totalSecond + " s");
        System.out.println("Question 4 Avg : " + avgSecond + " s");
        }
        
        // Question 5
        {
        System.out.println("Question 5 begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/tools.png"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question5(source);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 5 Global : " + totalSecond + " s");
        System.out.println("Question 5 Avg : " + avgSecond + " s");
        }
        
        // Question 6A
        {
        System.out.println("Question 6A begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
        CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
        CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/planete.jpg"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question6A(source, sourceColor, second);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 6A Global : " + totalSecond + " s");
        System.out.println("Question 6A Avg : " + avgSecond + " s");
        }
        
        // Question 6B
        {
        System.out.println("Question 6B begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
        CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
        CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/planete.jpg"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question6B(source, sourceColor, second);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 6B Global : " + totalSecond + " s");
        System.out.println("Question 6B Avg : " + avgSecond + " s");
        }
        
        // QUESTION 7
        {
        System.out.println("Question 7 begin");
        CImage source = new CImageNG(new File("res/Images/ImagesEtape5/Tartines.jpg"));
        CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/Tartines.jpg"));
        
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Application.question7(source, second);
        }
        long end = System.nanoTime();
        
        double totalSecond = (end - start) / 1000000000.0;
        double avgSecond = totalSecond / 1000.0;
        System.out.println("Question 7 Global : " + totalSecond + " s");
        System.out.println("Question 7 Avg : " + avgSecond + " s");
        }
    }
}
