/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.Seuillage;

/**
 *
 * @author Admin
 */
public class Seuillage {
    public static int[][] seuillageSimple(int[][] image, int seuil){
        int rows = image.length;
        int cols = image[0].length;
        
        int[][] output = new int[rows][cols];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                output[i][j] = image[i][j] >= seuil ? 255 : 0;
            }
        }
        
        return output;
    }
    
    public static int[][] seuillageDouble(int[][] image, int seuil1, int seuil2) {
        int rows = image.length;
        int cols = image[0].length;

        int[][] output = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = image[i][j];

                if (value >= seuil2) {
                    output[i][j] = 255;
                } else if (value >= seuil1) {
                    output[i][j] = 128;
                } else {
                    output[i][j] = 0;
                }
            }
        }

        return output;
    }
    
    public static double moyenne(int[][] image) {
        int somme = 0;
        int total = 0;
        
        for (int[] ligne : image) {
            for (int pixel : ligne) {
                somme += pixel;
                total++;
            }
        }
        return (total == 0) ? 0 : somme / total;
    }
    
    public static int[][] seuillageAutomatique(int[][] image){
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        
        // Calcule de la moyenne
        int T = (int) moyenne(image);
        
        boolean converged = false;
        while (!converged) {
            int groupe1 = 0, groupe2 = 0;
            int count1 = 0 , count2 = 0;
            
            // Séparation des pixels en 2 groupes
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) {
                    int pixel = image[i][j];
                    if (pixel >= T) {
                        groupe1 += pixel;
                        count1++;
                    } else {
                        groupe2 += pixel;
                        count2++;
                    }
                }
            }
            
            // Calcule des moyennes
            int moyenne1 = (count1 == 0) ? 0 : groupe1 / count1;
            int moyenne2 = (count2 == 0) ? 0 : groupe2 / count2;
            
            // Mise à jour du T
            int newT = (moyenne1 + moyenne2) / 2;
            
            // Verification de la convergence
            if (newT == T) {
                converged = true;
            } else {
                T = newT;
            }
        }
        // Seuillage final
        return seuillageSimple(image, T);
    }
}
