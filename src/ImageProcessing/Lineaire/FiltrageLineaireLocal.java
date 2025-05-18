package ImageProcessing.Lineaire;

/**
 * 
 * À chaque pixel (imageX, imageY) :
 *   - On place le centre du masque (centerX, centerY) sur (imageX, imageY)
 *   - On applique tous les poids du masque autour de ce centre
 *   - On additionne les produits des pixels voisins et du masque
 *   - Si un voisin sort de l'image (dépasse en X ou en Y), on l'ignore (=0)
 *
 * Vérifications limites :
 *   - pixelX = imageX + offsetX
 *   - pixelY = imageY + offsetY
 *   
 *   Si pixelX < 0 ou pixelX >= imageWidth → en dehors
 *   Si pixelY < 0 ou pixelY >= imageHeight → en dehors
 * 
 * Repère (axe d'image) :
 *
 *        (0,0)
 *          |
 *          v  Y croissant vers le bas
 *          --> X croissant vers la droite
 * 
 *
 */

public class FiltrageLineaireLocal {
    public static int[][] filtreMasqueConvolution(int[][] image, double[][] masque){
        int imageHeight = image.length;
        int imageWidth  = image[0].length;

        int masqueHeight = masque.length;
        int masqueWidth  = masque[0].length;

        int centerY = masqueHeight / 2;
        int centerX = masqueWidth / 2;

        int[][] output = new int[imageHeight][imageWidth];

        for(int imageY = 0; imageY < imageHeight; imageY++){
            for(int imageX = 0; imageX < imageWidth; imageX++){
                double somme = 0.0;

                for(int offsetY = -centerY; offsetY <= centerY; offsetY++){
                    for(int offsetX = -centerX; offsetX <= centerX; offsetX++){
                        int pixelY = imageY + offsetY;
                        int pixelX = imageX + offsetX;

                        if(pixelY >= 0 && pixelY < imageHeight && pixelX >= 0 && pixelX < imageWidth){
                            somme += image[pixelY][pixelX] * masque[centerY + offsetY][centerX + offsetX];
                        }
                    }
                }

                output[imageY][imageX] = (int)Math.round(somme);
            }
        }

        return output;
    }

    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque){
        if(tailleMasque % 2 == 0){
            System.out.println("Le masque doit être de taille impaire !");
            return image;
        }

        double[][] masque = new double[tailleMasque][tailleMasque];
        double valeur = 1.0 / (tailleMasque * tailleMasque);

        for(int i = 0; i < tailleMasque; i++){
            for(int j = 0; j < tailleMasque; j++){
                masque[i][j] = valeur;
            }
        }

        return filtreMasqueConvolution(image, masque);
    }
}