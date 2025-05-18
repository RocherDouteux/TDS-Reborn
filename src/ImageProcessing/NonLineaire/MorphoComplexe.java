package ImageProcessing.NonLineaire;

import ImageProcessing.Utils.Utils;

public class MorphoComplexe {
    
    
    /**
     * Effectue la dilatation géodésique d'une image selon un masque géodésique
     * donné.
     *
     * @param image L'image initiale (matrice de niveaux de gris).
     * @param masqueGeodesique Le masque géodésique qui limite la croissance.
     * @param nbIter Le nombre d'itérations de dilatation géodésique à
     * effectuer.
     * @return L'image résultante après dilatations géodésiques.
     */
    public static int[][] dilatationGeodesique(int[][] image, int[][] masqueGeodesique, int nbIter) {
        // Deep copy pour ne pas modifier l'image d'origine
        int[][] outputImage = Utils.deepCopy(image);

        // Vérifie que les 2 images sont de même tailles, si pas le cas rejete
        if (image.length != masqueGeodesique.length || image[0].length != masqueGeodesique[0].length) {
            throw new IllegalArgumentException("Les deux images doivent avoir la même taille !");
        }
        
        // Boucle sur le nombre d'itérations demandé
        for (int iter = 0; iter < nbIter; iter++) {
            // Dilatation élémentaire de l'image courante avec un élément structurant 3x3
            int[][] dilatedImage = MorphoElementaire.dilatation(outputImage, 3);

            // Limitation géodésique : on prend le minimum entre l'image dilatée et le masque
            for (int i = 0; i < outputImage.length; i++) {
                for (int j = 0; j < outputImage[0].length; j++) {
                    outputImage[i][j] = Math.min(dilatedImage[i][j], masqueGeodesique[i][j]);
                }
            }
        }

        // Retourne l'image après nbIter dilatations géodésiques
        return outputImage;
    }

    


        /**
         * Effectue la reconstruction géodésique par dilatation jusqu'à
         * stabilisation.
         *
         * @param image Le marqueur (image à reconstruire)
         * @param masqueGeodesique Le masque géodésique (limitation)
         * @return L'image reconstruite
         */
    public static int[][] reconstructionGeodesique(int[][] image, int[][] masqueGeodesique) {
        // Vérifie que les dimensions sont compatibles
        if (image.length != masqueGeodesique.length || image[0].length != masqueGeodesique[0].length) {
            throw new IllegalArgumentException("Les deux images doivent avoir la même taille !");
        }

        int[][] previous = Utils.deepCopy(image);
        int[][] current;

        boolean changed;

        do {
            // Dilate l'image précédente avec un élément structurant 3x3
            int[][] dilated = MorphoElementaire.dilatation(previous, 3);

            // Applique la contrainte du masque : pixel = min(dilaté, masque)
            current = new int[image.length][image[0].length];
            changed = false;

            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    current[i][j] = Math.min(dilated[i][j], masqueGeodesique[i][j]);
                    if (current[i][j] != previous[i][j]) {
                        changed = true;
                    }
                }
            }

            // Prépare pour la prochaine itération
            previous = current;

        } while (changed); // On s'arrête quand l'image ne change plus

        return current;
    }
    

        /**
         * Applique un filtre médian sur une image en niveaux de gris.
         *
         * @param image L'image d'entrée (niveaux de gris).
         * @param tailleMasque Taille impaire du masque (ex: 3, 5, 7...).
         * @return L'image filtrée.
         */
    public static int[][] filtreMedian(int[][] image, int tailleMasque) {
        if (tailleMasque % 2 == 0) {
            System.out.println("Le masque doit avoir une taille impaire !");
            return image;
        }

        int height = image.length;
        int width = image[0].length;
        int offset = tailleMasque / 2;
        int[][] result = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                // Collecte des pixels voisins dans la fenêtre
                java.util.List<Integer> voisins = new java.util.ArrayList<>();

                for (int di = -offset; di <= offset; di++) {
                    for (int dj = -offset; dj <= offset; dj++) {
                        int ni = i + di;
                        int nj = j + dj;

                        // Vérifie que les voisins sont dans les bornes de l'image
                        if (ni >= 0 && ni < height && nj >= 0 && nj < width) {
                            voisins.add(image[ni][nj]);
                        }
                    }
                }

                // Trie les voisins pour extraire la médiane
                java.util.Collections.sort(voisins);
                int mediane = voisins.get(voisins.size() / 2);

                result[i][j] = mediane;
            }
        }

        return result;
    }
}
