/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import CImage.CImage;
import CImage.CImageNG;
import CImage.CImageRGB;
import CImage.Exceptions.CImageNGException;
import CImage.Exceptions.CImageRGBException;
import ImageProcessing.NonLineaire.MorphoComplexe;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel
 */
public class Application {
    
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    
    private static CImage questionTemplate(CImage image){
        try{
            if(image instanceof CImageNG cImageNG){
                int[][] data = cImageNG.getMatrice();
                
                return new CImageNG(data);
            }
            
            if(image instanceof CImageRGB cImageRGB){
                int height = cImageRGB.getHauteur();
                int width = cImageRGB.getLargeur();
                
                int[][] red = new int[height][width];
                int[][] green = new int[height][width];
                int[][] blue = new int[height][width];
                
                cImageRGB.getMatricesRGB(red, green, blue);
                
                return new CImageRGB(red, green, blue);
            }
        } catch (CImageNGException | CImageRGBException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return image;
    }

    public static Queue<CImage> question1A(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        try{
            if(image instanceof CImageNG cImageNG){
                int[][] data = cImageNG.getMatrice();
                
                
                int[][] output = MorphoComplexe.filtreMedian(data, 9);
                sequence.add(new CImageNG(output));
            }
            
        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return sequence;
    }
    
    public static int[][] question1B(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question2A(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question2B(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question3(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question4(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question5(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question6A(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question6B(int[][] image){
        System.out.println("TO DO");
        return image;
    }
    
    public static int[][] question7(int[][] image){
        System.out.println("TO DO");
        return image;
    }
}
