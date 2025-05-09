/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.Contours;

/**
 *
 * @author Axel
 */
public class ContoursLineaire {
    public static int[][] gradientPrewitt(int[][] image, int dir){
        int[][] masque;
        
        if (dir == 1) {
            // Masque Horizontale
            masque = new int[][]{
                {1, 1, 1},
                {0, 0, 0},
                {-1, -1, -1}
            };
        } else if (dir == 2) {
            // Masque Verticale
            masque = new int[][]{
                {1, 0, -1},
                {1, 0, -1},
                {1, 0, -1}
            };
        } else {
            throw new IllegalArgumentException("dir doit etre 1 (horizontal) ou 2 (vertical)");
        }
        
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int y = 1; y < imageHeight - 1; y++) {
            for (int x = 1; x < imageWidth - 1; x++) {
                int somme = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        somme += image[y + i][x + j] * masque[i + 1][j + 1];
                    }
                }
                
                output[y][x] = Math.abs(somme);
            }
        }
        return output;
    }
    
    public static int[][] gradientSobel(int[][] image, int dir){
        int[][] masque;
        
        if (dir == 1) {
            // Masque Horizontale
            masque = new int[][]{
                {1, 2, 1},
                {0, 0, 0},
                {-1, -2, -1}
            };
        } else if (dir == 2) {
            // Masque Verticale
            masque = new int[][]{
                {1, 0, -1},
                {2, 0, -2},
                {1, 0, -1}
            };
        } else {
            throw new IllegalArgumentException("dir doit etre 1 (horizontal) ou 2 (vertical)");
        }
        
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int y = 1; y < imageHeight - 1; y++) {
            for (int x = 1; x < imageWidth - 1; x++) {
                int somme = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        somme += image[y + i][x + j] * masque[i + 1][j + 1];
                    }
                }
                
                output[y][x] = Math.abs(somme);
            }
        }
        return output;
    }
    
    public static int[][] laplacien4(int[][] image){
        int[][] masque;
        
        masque = new int[][]{
                {0, 1, 0},
                {1, -4, 1},
                {0, 1, 0}
        };
        
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int y = 1; y < imageHeight - 1; y++) {
            for (int x = 1; x < imageWidth - 1; x++) {
                int somme = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        somme += image[y + i][x + j] * masque[i + 1][j + 1];
                    }
                }
                
                output[y][x] = Math.abs(somme);
            }
        }
        return output;
    }
    
    public static int[][] laplacien8(int[][] image){
        int[][] masque;
        
        masque = new int[][]{
                {0, 1, 0},
                {1, -8, 1},
                {0, 1, 0}
        };
        
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        for (int y = 1; y < imageHeight - 1; y++) {
            for (int x = 1; x < imageWidth - 1; x++) {
                int somme = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        somme += image[y + i][x + j] * masque[i + 1][j + 1];
                    }
                }
                
                output[y][x] = Math.abs(somme);
            }
        }
        return output;
    }
}
