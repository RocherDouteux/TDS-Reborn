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

}
