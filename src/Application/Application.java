package Application;

import CImage.CImage;
import CImage.CImageNG;
import CImage.CImageRGB;
import CImage.Exceptions.CImageNGException;
import CImage.Exceptions.CImageRGBException;
import ImageProcessing.Histogramme.Histogramme;
import ImageProcessing.NonLineaire.MorphoComplexe;
import ImageProcessing.NonLineaire.MorphoElementaire;
import ImageProcessing.Seuillage.Seuillage;
import ImageProcessing.Utils.Utils;
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
        
        try {
            if(image instanceof CImageRGB imageRGB){
                CImageNG selectGreen = new CImageNG(Utils.convertionRGBToNG(imageRGB, "green"));
                sequence.add(selectGreen);
                
                int[] courbeTonaleNegative = Histogramme.creeCourbeTonaleNegatif();
                CImageNG negative = new CImageNG(Histogramme.rehaussement(selectGreen.getMatrice(), courbeTonaleNegative));
                sequence.add(negative);
                
                CImageNG seuillage255 = new CImageNG(Seuillage.seuillageSimple(negative.getMatrice(), 255));
                sequence.add(seuillage255);
                
                CImageNG erosion5 = new CImageNG(MorphoElementaire.erosion(seuillage255.getMatrice(), 5));
                sequence.add(erosion5);
                
                CImageRGB combinaison = Utils.andRGBWithMask(imageRGB, erosion5);
                sequence.add(combinaison);
                
                CImageNG selectBlue = new CImageNG(Utils.convertionRGBToNG(combinaison, "blue"));
                CImageNG selectRed = new CImageNG(Utils.convertionRGBToNG(combinaison, "red"));
                
                sequence.add(selectBlue);
                sequence.add(selectRed);
                
                // CImageRGB maskedBlue = Utils.andRGBWithMask(combinaison, selectBlue);
                // CImageRGB maskedRed = Utils.andRGBWithMask(combinaison, selectRed);
                
                // sequence.add(maskedBlue);
                // sequence.add(maskedRed);
                
            }
        }catch(CImageRGBException | CImageNGException ex){
            logger.log(Level.SEVERE, null, ex);
        }

        
        return sequence;
    }
    
    public static Queue<CImage> question4(CImage image){
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if (image instanceof CImageNG imageNG) {

                // Step 1 – Seuillage automatique
                int[][] seuil = Seuillage.seuillageAutomatique(imageNG.getMatrice());
                CImageNG imgSeuil = new CImageNG(seuil);
                sequence.add(imgSeuil);

                // Step 2 – Érosion (filtre 13)
                int[][] eroded = MorphoElementaire.erosion(seuil, 13);
                CImageNG imgEroded = new CImageNG(eroded);
                sequence.add(imgEroded);

                // Step 3 – Reconstruction géodésique (grandes balanes binaires)
                int[][] grandesBin = MorphoComplexe.reconstructionGeodesique(eroded, seuil);
                CImageNG imgGrandesBin = new CImageNG(grandesBin);
                sequence.add(imgGrandesBin);

                // Step 4 – Soustraction = petites balanes brutes
                int[][] petitesBin = Utils.soustraction(seuil, grandesBin);
                CImageNG imgPetitesRaw = new CImageNG(petitesBin);
                sequence.add(imgPetitesRaw);

                // Step 5 – Nettoyage (ouverture légère, filtre 3)
                petitesBin = MorphoElementaire.ouverture(petitesBin, 3);
                CImageNG imgPetitesClean = new CImageNG(petitesBin);
                sequence.add(imgPetitesClean);

                // Step 6 – Reconstruction géodésique en niveaux de gris
                int[][] grandesNG = MorphoComplexe.reconstructionGeodesique(imageNG.getMatrice(), grandesBin);
                int[][] petitesNG = MorphoComplexe.reconstructionGeodesique(imageNG.getMatrice(), petitesBin);

                CImageNG imgGrandesNG = new CImageNG(grandesNG);
                CImageNG imgPetitesNG = new CImageNG(petitesNG);

                sequence.add(imgGrandesNG);
                sequence.add(imgPetitesNG);
            }

        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return sequence;
    }
    
    public static Queue<CImage> question5(CImage image) {
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if (image instanceof CImageNG imageNG) {

                // Step 1 – Histogram equalization
                int[] courbe = Histogramme.creeCourbeTonaleEgalisation(imageNG.getMatrice());
                int[][] eq = Histogramme.rehaussement(imageNG.getMatrice(), courbe);
                CImageNG imgEq = new CImageNG(eq);
                sequence.add(imgEq);

                // Step 2 – Seuillage 220 (zones très claires)
                int[][] seuil220 = Seuillage.seuillageSimple(eq, 220);
                CImageNG imgSeuil220 = new CImageNG(seuil220);
                sequence.add(imgSeuil220);

                // Step 3 – Seuillage 160 (pour la clé)
                int[][] seuil160 = Seuillage.seuillageSimple(eq, 160);
                CImageNG imgSeuil160 = new CImageNG(seuil160);
                sequence.add(imgSeuil160);

                // Step 4 – Ouverture géodésique (filtre 15)
                int[][] ouvert = MorphoElementaire.ouverture(seuil160, 15);
                CImageNG imgOuvert = new CImageNG(ouvert);
                sequence.add(imgOuvert);

                // Step 5 – Reconstruction géodésique
                int[][] recon = MorphoComplexe.reconstructionGeodesique(ouvert, seuil160);
                CImageNG imgRecon = new CImageNG(recon);
                sequence.add(imgRecon);

                // Step 6 – Soustraction pour isoler la clé
                int[][] cle = Utils.soustraction(seuil160, recon);
                CImageNG imgCle = new CImageNG(cle);
                sequence.add(imgCle);

                // Step 7 – Fusion outils = clé + seuil220
                int[][] outils = Utils.addition(seuil220, cle);
                CImageNG imgFusion = new CImageNG(outils);
                sequence.add(imgFusion);

                // Step 8 – Seuillage à 20 (binarisation)
                int[][] binarise = Seuillage.seuillageSimple(outils, 20);
                //CImageNG imgBinarise = new CImageNG(binarise);
                //sequence.add(imgBinarise);

                // Step 9 – Ouverture 3 (nettoyage final)
                int[][] nettoye = MorphoElementaire.ouverture(binarise, 3);
                CImageNG imgFinal = new CImageNG(nettoye);
                sequence.add(imgFinal);

            }

        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

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
