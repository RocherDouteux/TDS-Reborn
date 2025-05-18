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
                
                int[][] output = MorphoComplexe.filtreMedian(data, 5);
                sequence.add(new CImageNG(output));
                
                output = MorphoComplexe.filtreMedian(data, 9);
                sequence.add(new CImageNG(output));

            }
            
        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return sequence;
    }
    
    public static Queue<CImage> question1B(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question2A(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question2B(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question3(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question4(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question5(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question6A(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question6B(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
    
    public static Queue<CImage> question7(CImage image){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        return sequence;
    }
}
