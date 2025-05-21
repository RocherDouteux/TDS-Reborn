package ImageProcessing.Utils;

import CImage.CImageNG;
import CImage.CImageRGB;
import CImage.Exceptions.CImageNGException;
import CImage.Exceptions.CImageRGBException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static int clamp(int min, int max, int value){
        return Math.max(min, Math.min(max, value));
    }
    
    public static void print2DArray(int[][] array){
        System.out.println(Arrays.deepToString(array));
    }
    
    public static void write2DArrayToFile(String filename, int[][] array){
        try {
            try (FileWriter myWriter = new FileWriter(filename)) {
                myWriter.write(Arrays.deepToString(array));
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public static void write2DArrayToFile(String filename, double[][] array){
        try {
            try (FileWriter myWriter = new FileWriter(filename)) {
                myWriter.write(Arrays.deepToString(array));
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static int[][] normaliserImage(int[][] image, int minBorne, int maxBorne) {
        int M = image.length;
        int N = image[0].length;

        // Étape 1: Trouver les valeurs minimales et maximales de l'image
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                int valeur = image[m][n];
                if (valeur < min) min = valeur;
                if (valeur > max) max = valeur;
            }
        }

        // Étape 2: Normaliser l'image en fonction des bornes minBorne et maxBorne
        int[][] imageNormalisee = new int[M][N];
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                int valeur = image[m][n];
                // Normaliser et convertir en entier dans la plage [minBorne, maxBorne]
                int niveauGris = (int) ((double)(valeur - min) / (max - min) * (maxBorne - minBorne) + minBorne);
                // Limiter les valeurs dans la plage [minBorne, maxBorne]
                imageNormalisee[m][n] = Math.max(minBorne, Math.min(maxBorne, niveauGris));
            }
        }

        return imageNormalisee;
    }
    
    public static int[][] convertirBinaireVersNiveauxDeGris(int[][] imageBinaire) {
        int hauteur = imageBinaire.length;
        int largeur = imageBinaire[0].length;

        int[][] imageGris = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (imageBinaire[y][x] == 1) {
                    imageGris[y][x] = 255;
                } else {
                    imageGris[y][x] = 0;
                }
            }
        }

        return imageGris;
    }
    
    public static boolean estImageBinaire(int[][] image) {
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[0].length; x++) {
                int value = image[y][x];
                if (value != 0 && value != 1) {
                    return false; // != 0 et 1 donc nv de gris !
                }
            }
        }
        return true;
    }
    
    public static int[][] deepCopy(int[][] image) {
        int[][] copy = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            copy[i] = Arrays.copyOf(image[i], image[i].length);
        }

        return copy;
    }
    
    public static int[][] addition(int[][] image1, int[][] image2) {
        int imageHeight = image1.length;
        int imageWidth = image1[0].length;
        
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                output[i][j] = Math.min(255, image1[i][j] + image2[i][j]);
            }
        }
        return output;
    }
    
    public static int[][] soustraction(int[][] image1, int[][] image2) {
        int imageHeight = image1.length;
        int imageWidth = image1[0].length;
        
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                output[i][j] = Math.max(0, image1[i][j] - image2[i][j]);
            }
        }
        return output;
    }
    
    public static int[][] extraireCanal(CImageRGB imageRGB, String couleur) throws CImageRGBException {
        int width = imageRGB.getLargeur();
        int height = imageRGB.getHauteur();
        
        int[][] output = new int[width][height];
        
        switch (couleur.toLowerCase()) {
            case "red" -> imageRGB.getMatricesRGB(output, null, null);
            case "green" -> imageRGB.getMatricesRGB(null, output, null);
            case "blue" -> imageRGB.getMatricesRGB(null, null, output);
        }
        return output;
    }
    
    public static CImageRGB convertionNGToRGB(int[][] imageNG, String couleur) throws CImageRGBException {
        int width = imageNG.length;
        int height = imageNG[0].length;
        
        int[][] red = new int[width][height];
        int[][] green = new int[width][height];
        int[][] blue = new int[width][height];
        
        switch (couleur.toLowerCase()) {
            case "red" -> red = imageNG;
            case "green" -> green = imageNG;
            case "blue" -> blue = imageNG;
        }
        return new CImageRGB(red, green, blue);
    }
    
    public static CImageRGB additionRGB(CImageRGB image1, CImageRGB image2) throws CImageRGBException {
        int width = image1.getLargeur();
        int height = image1.getHauteur();

        if (width != image2.getLargeur() || height != image2.getHauteur()) {
            throw new IllegalArgumentException("Les images doivent avoir les mêmes dimensions");
        }

        int[][] red1 = new int[width][height];
        int[][] green1 = new int[width][height];
        int[][] blue1 = new int[width][height];

        int[][] red2 = new int[width][height];
        int[][] green2 = new int[width][height];
        int[][] blue2 = new int[width][height];

        image1.getMatricesRGB(red1, green1, blue1);
        image2.getMatricesRGB(red2, green2, blue2);

        int[][] redResult = new int[width][height];
        int[][] greenResult = new int[width][height];
        int[][] blueResult = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                redResult[i][j] = Math.min(255, red1[i][j] + red2[i][j]);
                greenResult[i][j] = Math.min(255, green1[i][j] + green2[i][j]);
                blueResult[i][j] = Math.min(255, blue1[i][j] + blue2[i][j]);
            }
        }

        return new CImageRGB(redResult, greenResult, blueResult);
    }
    
    public static CImageRGB andRGBWithMask(CImageRGB rgbImage, CImageNG binaryMask){
        int[][] mask;
        try {
            mask = binaryMask.getMatrice();
            int width = rgbImage.getLargeur();
            int height = rgbImage.getHauteur();

            int[][] red = new int[width][height];
            int[][] green = new int[width][height];
            int[][] blue = new int[width][height];

            rgbImage.getMatricesRGB(red, green, blue);

            int[][] redResult = new int[width][height];
            int[][] greenResult = new int[width][height];
            int[][] blueResult = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (mask[i][j] == 255) {
                        redResult[i][j] = red[i][j];
                        greenResult[i][j] = green[i][j];
                        blueResult[i][j] = blue[i][j];
                    } else {
                        redResult[i][j] = 0;
                        greenResult[i][j] = 0;
                        blueResult[i][j] = 0;
                    }
                }
            }

            return new CImageRGB(redResult, greenResult, blueResult);
        } catch (CImageNGException | CImageRGBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rgbImage;
    }
    
    public static CImageRGB replaceBlackWithWhite(CImageRGB image){
        return Utils.replaceColorWith(image, 0, 0, 0, 255, 255, 255);
    }
    
    public static CImageRGB replaceColorWith(CImageRGB image, int r, int g, int b, int dr, int dg, int db){
        try {
            int width = image.getLargeur();
            int height = image.getHauteur();

            int[][] red = new int[width][height];
            int[][] green = new int[width][height];
            int[][] blue = new int[width][height];

            image.getMatricesRGB(red, green, blue);

            int[][] redResult = new int[width][height];
            int[][] greenResult = new int[width][height];
            int[][] blueResult = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (red[i][j] == r && green[i][j] == g && blue[i][j] == b){
                        redResult[i][j] = dr;
                        greenResult[i][j] = dg;
                        blueResult[i][j] = db;
                    }else{
                        redResult[i][j] = red[i][j];
                        greenResult[i][j] = green[i][j];
                        blueResult[i][j] = blue[i][j];
                    }
                }
            }

            return new CImageRGB(redResult, greenResult, blueResult);
            
        } catch (CImageRGBException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
    }


}
