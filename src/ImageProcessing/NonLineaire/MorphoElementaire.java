    package ImageProcessing.NonLineaire;

    import ImageProcessing.Lineaire.FiltrageLineaireLocal;


    public class MorphoElementaire {
        
        public static int[][] erosion(int[][] image, int tailleMasque){
            if(tailleMasque % 2 == 0){
                System.out.println("Le masque doit être de taille impaire !");
                return image;
            }

            int imageHeight = image.length;
            int imageWidth = image[0].length;
            int offset = tailleMasque / 2;

            int[][] output = new int[imageHeight][imageWidth];

            for (int imageY = 0; imageY < imageHeight; imageY++) {
                for (int imageX = 0; imageX < imageWidth; imageX++) {
                    int minValue = 255;

                    for (int offsetY = -offset; offsetY <= offset; offsetY++) {
                        for (int offsetX = -offset; offsetX <= offset; offsetX++) {
                            int pixelY = imageY + offsetY;
                            int pixelX = imageX + offsetX;

                            if (pixelY >= 0 && pixelY < imageHeight && pixelX >= 0 && pixelX < imageWidth) {
                                minValue = Math.min(minValue, image[pixelY][pixelX]);
                            }
                        }
                    }

                    output[imageY][imageX] = minValue;
                }
            }

            return output;
        }

        
        
        public static int[][] dilatation(int[][] image, int tailleMasque){
            if(tailleMasque % 2 == 0){
                System.out.println("Le masque doit être de taille impaire !");
                return image;
            }

            int imageHeight = image.length;
            int imageWidth = image[0].length;
            int offset = tailleMasque / 2;

            int[][] output = new int[imageHeight][imageWidth];

            for (int imageY = 0; imageY < imageHeight; imageY++) {
                for (int imageX = 0; imageX < imageWidth; imageX++) {
                    int maxVal = 0;

                    for (int offsetY = -offset; offsetY <= offset; offsetY++) {
                        for (int offsetX = -offset; offsetX <= offset; offsetX++) {
                            int pixelY = imageY + offsetY;
                            int pixelX = imageX + offsetX;

                            if (pixelY >= 0 && pixelY < imageHeight && pixelX >= 0 && pixelX < imageWidth) {
                                maxVal = Math.max(maxVal, image[pixelY][pixelX]);
                            }
                        }
                    }

                    output[imageY][imageX] = maxVal;
                }
            }

            return output;
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
