package ImageProcessing.Contours;

import ImageProcessing.NonLineaire.MorphoElementaire;

public class ContoursNonLineaire {    
    public static int[][] gradientErosion(int[][] image) {
        int[][] eroded = MorphoElementaire.erosion(image, 3);
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                output[i][j] = image[i][j] - eroded[i][j];
            }
        }
        return output;
    }
    
    public static int[][] gradientDilatation(int[][] image) {
        int[][] dilated = MorphoElementaire.dilatation(image, 3);
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                output[i][j] = dilated[i][j] - image[i][j];
            }
        }
        return output;
    }
    
    public static int[][] gradientBeucher(int[][] image, boolean inverse) {
        int[][] eroded = MorphoElementaire.erosion(image, 3);
        int[][] dilated = MorphoElementaire.dilatation(image, 3);
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        if (inverse) {
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) {
                    output[i][j] = eroded[i][j] - dilated[i][j];
                }
            }
        } else {
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) {
                    output[i][j] = dilated[i][j] - eroded[i][j];
                }
            }
        }
        return output;
    }
    
    public static int[][] laplacienNonLineaire(int[][] image, boolean inverse) {
        int[][] gradienterosion = gradientErosion(image);
        int[][] gradientdilatation = gradientDilatation(image);
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int[][] output = new int[imageHeight][imageWidth];
        
        if (inverse) {
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) {
                    output[i][j] = gradienterosion[i][j] - gradientdilatation[i][j];
                }
            }
        } else {
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) {
                    output[i][j] = gradientdilatation[i][j] - gradienterosion[i][j];
                }
            }
        }
        return output;
    }
}
