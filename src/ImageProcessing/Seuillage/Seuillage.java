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
                output[i][j] = image[i][j] >= seuil ? 1 : 0;
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
    
    public static int[][] seuillageAutomatique(int[][] image){
        // TODO: implement this
        throw new UnsupportedOperationException("seuillageAutomatique n'est pas encore implémenté.");
    }
}
