/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.NonLineaire;

/**
 *
 * @author fred
 */
public class MorphoElementaire {
    public static int[][] erosion(int[][] image, int tailleMasque){
        return image;
    }
    
    public static int[][] dilatation(int[][] image, int tailleMasque){
        return image;
    }
    
    public static int[][] ouverture(int[][] image, int tailleMasque){
        // Fermeture = dilatation + erosion
        int[][] output = MorphoElementaire.erosion(image, tailleMasque);
        return MorphoElementaire.dilatation(output, tailleMasque);
    }
    
    public static int[][] fermeture(int[][] image, int tailleMasque){
        // Fermeture = dilatation + erosion
        int[][] output = MorphoElementaire.dilatation(image, tailleMasque);
        return MorphoElementaire.erosion(output, tailleMasque);
    }
}
