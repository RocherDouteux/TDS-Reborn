/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.NonLineaire;

import java.util.Arrays;

/**
 *
 * @author lizardman
 */
public class MorphoComplexe {
    
    
    public static int[][] dilatationGeodesique(int[][] image, int[][] masqueGeodesique, int nbIter) {
        // Deep copy pour ne pas modifier l'image initial
        int[][] outputImage = deepCopy(image);
        boolean changed;
        
        do {
        
            changed = false;
            System.out.println("DilatationGeodesique");

        } while (changed);
        return outputImage;
    }
        


    public static int[][] reconstructionGeodesique(int[][] image, int[][] masqueGeodesique) {
        int[][] outputImage = deepCopy(image);
        boolean changed;

        do {
            changed = false;
            int[][] temp = dilatation(outputImage);

            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    int newValue = Math.min(temp[i][j], masqueGeodesique[i][j]);
                    if (newValue != outputImage[i][j]) {
                        outputImage[i][j] = newValue;
                        changed = true;
                    }
                }
            }
        } while (changed);

        return outputImage;
    }

    public static int[][] filtreMedian(int[][] image, int tailleMasque) {
        System.out.println("Not Implemented");
        return  image;
    }

    // Petite dilatation simple pour la géodésique
    private static int[][] dilatation(int[][] image) {
        System.out.println("Not Implemented");
        return image;
    }

    // Permet de créer une nouvelle image indépendante de celle copier != shallow copy
    private static int[][] deepCopy(int[][] image) {
        int[][] copy = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            copy[i] = Arrays.copyOf(image[i], image[i].length);
        }

        return copy;
    }
    
}
