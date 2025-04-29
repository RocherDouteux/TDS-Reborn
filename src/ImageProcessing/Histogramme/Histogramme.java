package ImageProcessing.Histogramme;

public class Histogramme 
{
    public static int[] Histogramme256(int mat[][])
    {
        int M = mat.length;
        int N = mat[0].length;
        int histo[] = new int[256];
        
        for(int i=0 ; i<256 ; i++) histo[i] = 0;
        
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if ((mat[i][j] >= 0) && (mat[i][j]<=255)) histo[mat[i][j]]++;
        
        return histo;
    }
    
    public static int minimum(int[][] image){
        int height = image.length;
        int width = image[0].length;
        
        int min = 256;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                min = Math.min(min, image[i][j]);
            }
        }
        return min;
    }
    
    public static int maximum(int[][] image){
        int height = image.length;
        int width = image[0].length;
        
        int max = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                max = Math.max(max, image[i][j]);
            }
        }
        return max;
    }
    
    // Moyenne des pixels
    public static int luminance(int[][] image){
        int height = image.length;
        int width = image[0].length;
        
        int sum = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                sum += image[i][j];
            }
        }
        return sum / (height * width);
    }
    
    // Ecart quadratic à la moyenne
    public static double contraste1(int[][] image){
        int lum = Histogramme.luminance(image);
        
        int height = image.length;
        int width = image[0].length;
        
        double sum = 0.0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                sum += Math.pow(image[i][j] - lum, 2);
            }
        }
        return Math.sqrt(sum / (height * width));
    }
    
    public static double contraste2(int[][] image){
        double min = (double) Histogramme.minimum(image);
        double max = (double) Histogramme.maximum(image);
        
        return (max - min) / (max + min);
    }
    
    public static int[][] rehaussement(int[][] image, int[] courbeTonale){
        int height = image.length;
        int width = image[0].length;
        
        int[][] result = new int[height][width];
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int pixel = image[i][j];
                
                result[i][j] = courbeTonale[pixel];
            }
        }
        
        return result;
    }
    
    public static int[] creeCourbeTonaleLineaireSaturation(int smin, int smax){
        int[] courbeTonale = new int[256];
        
        for(int i = 0; i < 256; i++){
            if(i < smin){
                courbeTonale[i] = 0;
                continue;
            }
            
            if(i > smax){
                courbeTonale[i] = 255;
                continue;
            }
            
            courbeTonale[i] = (int) Math.round(255.0 * (i - smin) / (smax - smin));
        }
        
        return courbeTonale;
    }
    
    
    public static int[] creeCourbeTonaleGamma(double gamma){
        int[] courbeTonale = new int[256];
        
        for(int i = 0; i < 256; i++){
            courbeTonale[i] = (int) Math.round(255.0 * Math.pow(i / 255.0, 1.0 / gamma));
        }
        
        return courbeTonale;
    }
    
    public static int[] creeCourbeTonaleNegatif(){
        int[] courbeTonale = new int[256];
        
        for(int i = 0; i < 256; i++){
            courbeTonale[i] = 255 - i;
        }
        
        return courbeTonale;
    }
    
}
