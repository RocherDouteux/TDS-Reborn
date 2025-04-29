package ImageProcessing.Utils;
        
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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

}
