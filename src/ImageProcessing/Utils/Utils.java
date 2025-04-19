package ImageProcessing.Utils;
        
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Utils {
    public static int clamp(int min, int max, int value){
        return Math.max(min, Math.min(max, value));
    }
    
    public static void print2DArray(int[][] array){
        System.out.println(Arrays.deepToString(array));
    }
    
    public static void write2DArrayToFile(String filename, int[][] array){
        try {
            try (FileWriter myWriter = new FileWriter(filename)) {
                myWriter.write(Arrays.deepToString(array));
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public static void write2DArrayToFile(String filename, double[][] array){
        try {
            try (FileWriter myWriter = new FileWriter(filename)) {
                myWriter.write(Arrays.deepToString(array));
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
