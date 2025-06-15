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
import ImageProcessing.Contours.ContoursLineaire;
import ImageProcessing.Utils.Utils;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {
    
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    
    
    public static Queue<CImage> question1A(CImage image){
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if(image instanceof CImageNG cImageNG){
                int[][] data = cImageNG.getMatrice();

                // Step 1 – Application du filtre médian 5×5
                int[][] output = MorphoComplexe.filtreMedian(data, 5);
                sequence.add(new CImageNG(output));

                // Step 2 – Application du filtre médian 9×9
                output = MorphoComplexe.filtreMedian(data, 9);
                sequence.add(new CImageNG(output));
            }

        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return sequence;
    }

    
    public static Queue<CImage> question1B(CImage image) {
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if (image instanceof CImageNG imageNG) {

                // Step 1 – Application du filtre médian 3×3 une première fois
                int[][] median3 = MorphoComplexe.filtreMedian(imageNG.getMatrice(), 3);
                sequence.add(new CImageNG(median3));

                // Step 2 – Application du filtre médian 3×3 une seconde fois
                median3 = MorphoComplexe.filtreMedian(median3, 3);
                sequence.add(new CImageNG(median3));
            }

        } catch (CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return sequence;
    }

    
    public static Queue<CImage> question2A(CImage image) {
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if (image instanceof CImageRGB imageRGB) {

                // Step 1 – Extraction des canaux R, G et B
                int[][] redChannel = Utils.extraireCanal(imageRGB, "red");
                int[][] greenChannel = Utils.extraireCanal(imageRGB, "green");
                int[][] blueChannel = Utils.extraireCanal(imageRGB, "blue");

                // Step 2 – Création des courbes tonales pour égalisation (R, G, B)
                int[] redCurve = Histogramme.creeCourbeTonaleEgalisation(redChannel);
                int[] greenCurve = Histogramme.creeCourbeTonaleEgalisation(greenChannel);
                int[] blueCurve = Histogramme.creeCourbeTonaleEgalisation(blueChannel);

                // Step 3 – Application des courbes sur chaque canal (rehaussement)
                int[][] egalisationRed = Histogramme.rehaussement(redChannel, redCurve);
                egalisationRed = Utils.normaliserImage(egalisationRed, 0, 255);

                int[][] egalisationGreen = Histogramme.rehaussement(greenChannel, greenCurve);
                egalisationGreen = Utils.normaliserImage(egalisationGreen, 0, 255);

                int[][] egalisationBlue = Histogramme.rehaussement(blueChannel, blueCurve);
                egalisationBlue = Utils.normaliserImage(egalisationBlue, 0, 255);

                // Step 4 – Ajout des canaux égalisés (niveaux de gris) à la séquence
                sequence.add(new CImageNG(egalisationRed));
                sequence.add(new CImageNG(egalisationGreen));
                sequence.add(new CImageNG(egalisationBlue));

                // Step 5 – Conversion des canaux égalisés en images RGB colorées
                CImageRGB backToRed = Utils.convertionNGToRGB(egalisationRed, "red");
                CImageRGB backToGreen = Utils.convertionNGToRGB(egalisationGreen, "green");
                CImageRGB backToBlue = Utils.convertionNGToRGB(egalisationBlue, "blue");

                sequence.add(backToRed);
                sequence.add(backToGreen);
                sequence.add(backToBlue);

                // Step 6 – Fusion du rouge et du vert pour obtenir du jaune
                CImageRGB fusionRedAndGreen = Utils.additionRGB(backToRed, backToGreen);
                sequence.add(fusionRedAndGreen);

                // Step 7 – Fusion du jaune et du bleu pour reconstituer une image RGB égalisée
                CImageRGB fusionYellowAndBlue = Utils.additionRGB(fusionRedAndGreen, backToBlue);
                sequence.add(fusionYellowAndBlue);
            }

        } catch (CImageRGBException | CImageNGException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return sequence;
    }

    
    public static Queue<CImage> question2B(CImage image, CImageNG imageNG){
        Queue<CImage> sequence = new LinkedList();
        sequence.add(image);
        
        try {
            if(image instanceof CImageRGB imageRGB){
                // Extraction RGB en NG
                int[][] redChannel = Utils.extraireCanal(imageRGB, "red");
                int[][] greenChannel = Utils.extraireCanal(imageRGB, "green");
                int[][] blueChannel = Utils.extraireCanal(imageRGB, "blue");
                
                // Importation de l'image en niveau de gris (luminance)
                int[][] lenaGrayImport = imageNG.getMatrice();
                
                // Egalisation de l'image niveau de gris importé
                int[] lenaGray = Histogramme.creeCourbeTonaleEgalisation(lenaGrayImport);
                int[][] lenaGrayEgalise = Histogramme.rehaussement(lenaGrayImport, lenaGray);
                lenaGrayEgalise = Utils.normaliserImage(lenaGrayEgalise, 0, 255);
                
                // Addition de l'image en niveau de gris avec RGB devenu NG
                int[][] redAddition = Utils.addition(redChannel, lenaGrayEgalise);
                sequence.add(new CImageNG(redAddition));
                int[][] greenAddition = Utils.addition(greenChannel, lenaGrayEgalise);
                sequence.add(new CImageNG(greenAddition));
                int[][] blueAddition = Utils.addition(blueChannel, lenaGrayEgalise);
                sequence.add(new CImageNG(blueAddition));
                
                // Passage NG vers RGB pour chaque channel
                CImageRGB backToRed = Utils.convertionNGToRGB(redAddition, "red");
                sequence.add(backToRed);
                CImageRGB backToGreen = Utils.convertionNGToRGB(greenAddition, "green");
                sequence.add(backToGreen);
                CImageRGB backToBlue = Utils.convertionNGToRGB(blueAddition, "blue");
                sequence.add(backToBlue);
                
                // Fusion des channels RGB pour devenir 1 seul image
                CImageRGB fusionRedAndGreen = Utils.additionRGB(backToRed, backToGreen);
                sequence.add(fusionRedAndGreen);
                
                CImageRGB fusionYellowAndBlue = Utils.additionRGB(fusionRedAndGreen, backToBlue);
                sequence.add(fusionYellowAndBlue);

            }
        }catch(CImageRGBException | CImageNGException ex){
            logger.log(Level.SEVERE, null, ex);
        }
        
        return sequence;
    }
    
    public static Queue<CImage> question3(CImage image) {
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);

        try {
            if (image instanceof CImageRGB imageRGB) {

                // Step 1 – Extraction du canal vert de l’image RGB
                CImageNG selectGreen = new CImageNG(Utils.extraireCanal(imageRGB, "green"));
                sequence.add(selectGreen);

                // Step 2 – Application d’une courbe tonale négative sur le canal vert
                int[] courbeTonaleNegative = Histogramme.creeCourbeTonaleNegatif();
                CImageNG negative = new CImageNG(Histogramme.rehaussement(selectGreen.getMatrice(), courbeTonaleNegative));
                sequence.add(negative);

                // Step 3 – Seuillage à 255 sur l’image négative
                CImageNG seuillage255 = new CImageNG(Seuillage.seuillageSimple(negative.getMatrice(), 255));
                sequence.add(seuillage255);

                // Step 4 – Érosion avec un structurant de taille 5
                CImageNG erosion5 = new CImageNG(MorphoElementaire.erosion(seuillage255.getMatrice(), 5));
                sequence.add(erosion5);

                // Step 5 – Application du masque érodé sur l’image RGB originale
                CImageRGB combinaison = Utils.andRGBWithMask(imageRGB, erosion5);
                sequence.add(combinaison);

                // Step 6 – Extraction du canal bleu de l’image masquée
                CImageNG selectBlue = new CImageNG(Utils.extraireCanal(combinaison, "blue"));
                sequence.add(selectBlue);

                // Step 7 – Extraction du canal rouge de l’image masquée
                CImageNG selectRed = new CImageNG(Utils.extraireCanal(combinaison, "red"));
                sequence.add(selectRed);

                // (Pas important)
                // CImageRGB maskedBlue = Utils.andRGBWithMask(combinaison, selectBlue);
                // CImageRGB maskedRed = Utils.andRGBWithMask(combinaison, selectRed);
                // sequence.add(maskedBlue);
                // sequence.add(maskedRed);
            }
        } catch (CImageRGBException | CImageNGException ex) {
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
                int[][] grandesNG = MorphoComplexe.reconstructionGeodesique(grandesBin,imageNG.getMatrice());
                int[][] petitesNG = MorphoComplexe.reconstructionGeodesique(petitesBin, imageNG.getMatrice());

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



public static Queue<CImage> question6A(CImage image, CImage RGBImage, CImage second) {
    Queue<CImage> sequence = new LinkedList<>();
    sequence.add(image);
    sequence.add(RGBImage);
    sequence.add(second);

    try {
        if (image instanceof CImageNG imageNG && RGBImage instanceof CImageRGB shipRGB && second instanceof CImageRGB planetRGB) {
            
            // Step 1 – Extraction de la matrice en niveaux de gris
            int[][] vaissGray = imageNG.getMatrice();

            // Step 2 – Seuillage à 60 pour détecter les zones claires (vaisseaux)
            int[][] seuil60 = Seuillage.seuillageSimple(vaissGray, 60);
            // sequence.add(new CImageNG(seuil60));

            // Step 3 – Érosion avec un structurant large pour éliminer le petit vaisseau
            int[][] eroded = MorphoElementaire.erosion(seuil60, 21);
            sequence.add(new CImageNG(eroded));

            // Step 4 – Reconstruction géodésique pour récupérer uniquement le gros vaisseau
            int[][] recon = MorphoComplexe.reconstructionGeodesique(eroded, seuil60);
            sequence.add(new CImageNG(recon));

            // Step 5 – Soustraction pour isoler le petit vaisseau
            int[][] sub = Utils.soustraction(seuil60, recon);
            // sequence.add(new CImageNG(sub));

            // Step 6 – Ouverture morphologique pour nettoyer la forme du petit vaisseau
            int[][] opened = MorphoElementaire.ouverture(sub, 13);
            sequence.add(new CImageNG(opened));

            // Step 7 – Reconstruction géodésique finale pour lisser le masque
            int[][] finalMask = MorphoComplexe.reconstructionGeodesique(opened, vaissGray);
            CImageNG mask = new CImageNG(finalMask);
            sequence.add(mask);

            // Step 8 – Détection des contours avec Prewitt (H + V)
            int[][] prewittH = ContoursLineaire.gradientPrewitt(finalMask, 1);
            int[][] prewittV = ContoursLineaire.gradientPrewitt(finalMask, 2);
            int[][] additionPrewitt = Utils.addition(prewittH, prewittV);

            // Step 9 – Seuillage des contours à 50
            additionPrewitt = Seuillage.seuillageSimple(additionPrewitt, 50);

            // Step 10 – Fermeture pour combler les trous dans le contour
            additionPrewitt = MorphoElementaire.fermeture(additionPrewitt, 7);

            // Step 11 – Érosion pour affiner les contours
            additionPrewitt = MorphoElementaire.erosion(additionPrewitt, 3);
            // sequence.add(new CImageNG(additionPrewitt));

            // Step 12 – Inversion tonale des contours pour créer un trou dans la planète
            int[] negative = Histogramme.creeCourbeTonaleNegatif();
            int[][] additionPrewittNegative = Histogramme.rehaussement(additionPrewitt, negative);

            // Step 13 – Masquage de la planète avec les contours inversés (trou)
            CImageRGB holed = Utils.andRGBWithMask(planetRGB, new CImageNG(additionPrewittNegative));
            // sequence.add(holed);

            // Step 14 – Recoloration du petit vaisseau à partir du masque
            int[][] shipMask = Seuillage.seuillageSimple(mask.getMatrice(), 60);
            CImageRGB shipOnly = Utils.andRGBWithMask(shipRGB, new CImageNG(shipMask));
            sequence.add(shipOnly);

            // Step 15 – Fusion du petit vaisseau recoloré avec la planète trouée
            CImageRGB fusion = Utils.additionRGB(holed, shipOnly);
            sequence.add(fusion);
        }

    } catch (CImageNGException | CImageRGBException ex) {
        logger.log(Level.SEVERE, null, ex);
    }

    return sequence;
}

    public static Queue<CImage> question6B(CImage image, CImage RGBImage, CImage second) {
        Queue<CImage> sequence = new LinkedList<>();
        sequence.add(image);
        sequence.add(RGBImage);
        sequence.add(second);

        try {
            if (image instanceof CImageNG imageNG && RGBImage instanceof CImageRGB shipRGB && second instanceof CImageRGB planetRGB) {

                // Done Before, don't worry about it brothers
                int[][] vaissGray = imageNG.getMatrice();
                int[][] seuil60 = Seuillage.seuillageSimple(vaissGray, 60);
                int[][] eroded = MorphoElementaire.erosion(seuil60, 21);
                int[][] recon = MorphoComplexe.reconstructionGeodesique(eroded, seuil60);
                int[][] sub = Utils.soustraction(seuil60, recon);
                int[][] opened = MorphoElementaire.ouverture(sub, 11);
                int[][] finalMask = MorphoComplexe.reconstructionGeodesique(opened, vaissGray);

                // Step 1 – Calcul des contours du masque (Prewitt H + V)
                int[][] gradH = ContoursLineaire.gradientPrewitt(finalMask, 1);
                int[][] gradV = ContoursLineaire.gradientPrewitt(finalMask, 2);
                int[][] edge = Utils.addition(gradH, gradV);
                edge = Utils.normaliserImage(edge, 0, 255);

                // Step 2 – Seuillage des contours à 50
                int[][] contour = Seuillage.seuillageSimple(edge, 50);
                CImageNG contourNG = new CImageNG(contour);
                sequence.add(contourNG);

                // Step 3 – Fermeture pour uniformiser les contours du vaisseau
                int[][] uniShip = MorphoElementaire.fermeture(contour, 7);

                // Step 4 – Calcul du gradient Prewitt sur le contour fermé
                int[][] prewittH = ContoursLineaire.gradientPrewitt(uniShip, 1);
                int[][] prewittV = ContoursLineaire.gradientPrewitt(uniShip, 2);
                int[][] additionPrewitt = Utils.addition(prewittH, prewittV);
                sequence.add(new CImageNG(additionPrewitt));

                // Step 5 – Fermeture pour remplir les trous dans le contour
                int[][] filledContourNG = MorphoElementaire.fermeture(contour, 9);
                // sequence.add(new CImageNG(filledContourNG));

                // Step 6 – Création d’une courbe de négatif et application sur le contour rempli
                int[] negativeCurve = Histogramme.creeCourbeTonaleNegatif();
                int[][] negative = Histogramme.rehaussement(filledContourNG, negativeCurve);

                // Step 7 – Extraction du vaisseau depuis l’image RGB via masque
                CImageRGB smallShip = Utils.andRGBWithMask(shipRGB, new CImageNG(filledContourNG));

                // Step 8 – Application du négatif pour créer un "trou" dans la planète
                CImageRGB holed = Utils.andRGBWithMask(planetRGB, new CImageNG(negative));

                // Step 9 – Conversion des contours en rouge
                CImageRGB redContour = Utils.convertionNGToRGB(additionPrewitt, "red");
                // sequence.add(redContour);

                // Step 10 – Fusion du vaisseau et de la planète trouée
                CImageRGB fusion = Utils.additionRGB(holed, smallShip);
                sequence.add(fusion);

                // Step 11 – Ajout des contours rouges à l’image finale
                CImageRGB finalResult = Utils.additionRGB(fusion, redContour);
                sequence.add(finalResult);
            }

        } catch (CImageNGException | CImageRGBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return sequence;
    }


public static Queue<CImage> question7(CImage image, CImage output){
    Queue<CImage> sequence = new LinkedList<>();
    sequence.add(image);

    try {
        if(image instanceof CImageNG imageNG && output instanceof CImageRGB imageRGB){

            // Step 1 – Calcul Prewitt horizontal (Prewitt H)
            int[][] prewittH = ContoursLineaire.gradientPrewitt(imageNG.getMatrice(), 1);
            prewittH = Utils.normaliserImage(prewittH, 0, 255);
            sequence.add(new CImageNG(prewittH));

            // Step 2 – Calcul Prewitt vertical (Prewitt V)
            int[][] prewittV = ContoursLineaire.gradientPrewitt(imageNG.getMatrice(), 2);
            prewittV = Utils.normaliserImage(prewittV, 0, 255);
            sequence.add(new CImageNG(prewittV));

            // Step 3 – Addition des deux Prewitt (H + V)
            int[][] addition = Utils.addition(prewittH, prewittV);
            sequence.add(new CImageNG(addition));

            // Step 4 – Seuillage double (détection des contours)
            int[][] seuillage = Seuillage.seuillageDouble(addition, 80, 90);
            sequence.add(new CImageNG(seuillage));

            // Step 5 – Dilatation des contours détectés
            int[][] dilatation = MorphoElementaire.dilatation(seuillage, 3);
            sequence.add(new CImageNG(dilatation));

            // Step 6 – Conversion en image RGB avec contours en vert
            CImageRGB contoursVerts = Utils.convertionNGToRGB(dilatation, "green");
            sequence.add(contoursVerts);

            // Step 7 – Fusion de l’image RGB d’origine avec les contours verts
            CImageRGB fusion = Utils.additionRGB(imageRGB, contoursVerts);
            sequence.add(fusion);

        }
    } catch(CImageNGException | CImageRGBException ex){
        logger.log(Level.SEVERE, null, ex);
    }

    return sequence;
}

}
