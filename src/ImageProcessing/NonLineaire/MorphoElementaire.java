/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageProcessing.NonLineaire;

import ImageProcessing.Lineaire.FiltrageLineaireLocal;

/**
 *
 * @author fred
 */
public class MorphoElementaire {
    // Si tous les pixels voisins sont blancs, alors on reste blanc sinon noir
    public static int[][] erosion(int[][] image, int tailleMasque){
        if(tailleMasque % 2 == 0){
            System.out.println("Le masque doit être de taille impaire !");
            return image;
        }
        
        double[][] masque = new double[tailleMasque][tailleMasque];
        
        for(int i = 0; i < tailleMasque; i++){
            for(int j = 0; j < tailleMasque; j++){
                masque[i][j] = 1.0;
            }
        }
        
        int[][] convolutionImage = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);
        
        int seuil = tailleMasque * tailleMasque * 255;
        int[][] outputImage = new int[image.length][image[0].length];
        
        for(int i = 0; i < convolutionImage.length; i++){
            for(int j = 0; j < convolutionImage[i].length; j++){
                outputImage[i][j] = (convolutionImage[i][j] >= seuil) ? 255 : 0;
            }
        }
        
        return outputImage;
    }
    
    // Si tous les pixels voisins sont noirs, alors on reste noir sinon blanc
    public static int[][] dilatation(int[][] image, int tailleMasque){
        if(tailleMasque % 2 == 0){
            System.out.println("Le masque doit être de taille impaire !");
            return image;
        }
        
        double[][] masque = new double[tailleMasque][tailleMasque];
        
        for(int i = 0; i < tailleMasque; i++){
            for(int j = 0; j < tailleMasque; j++){
                masque[i][j] = 1.0;
            }
        }
        
        int[][] convolutionImage = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);
        
        int seuil = 255;
        int[][] outputImage = new int[image.length][image[0].length];
        
        for(int i = 0; i < convolutionImage.length; i++){
            for(int j = 0; j < convolutionImage[i].length; j++){
                outputImage[i][j] = (convolutionImage[i][j] >= seuil) ? 255 : 0;
            }
        }
        
        return outputImage;
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
