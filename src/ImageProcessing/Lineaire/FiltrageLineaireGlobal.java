package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utils.Utils;

public class FiltrageLineaireGlobal {

    public static int[][] filtrePasseBasIdeal(int[][] image, int frequenceCoupure) {
        int M = image.length;
        int N = image[0].length;

        double[][] imageDouble = new double[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                imageDouble[i][j] = image[i][j];
            }
        }

        MatriceComplexe F = Fourier.Fourier2D(imageDouble);
        F = Fourier.decroise(F);

        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                double du = u - M / 2.0;
                double dv = v - N / 2.0;
                double distance = Math.sqrt(du * du + dv * dv);

                if (distance >= frequenceCoupure) {
                    F.set(u, v, 0.0, 0.0);
                }
            }
        }

        F = Fourier.decroise(F);
        MatriceComplexe fComplexe = Fourier.InverseFourier2D(F);

        double[][] partieReelle = fComplexe.getPartieReelle();
        int[][] resultat = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                resultat[i][j] = Utils.clamp(0, 255, (int)Math.round(partieReelle[i][j]));
            }
        }

        return resultat;
    }

    public static int[][] filtrePasseHautIdeal(int[][] image, int frequenceCoupure) {
        int M = image.length;
        int N = image[0].length;

        double[][] imageDouble = new double[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                imageDouble[i][j] = image[i][j];
            }
        }

        MatriceComplexe F = Fourier.Fourier2D(imageDouble);
        F = Fourier.decroise(F);

        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                double du = u - M / 2.0;
                double dv = v - N / 2.0;
                double distance = Math.sqrt(du * du + dv * dv);

                if (distance <= frequenceCoupure) {
                    F.set(u, v, 0.0, 0.0);
                }
            }
        }

        F = Fourier.decroise(F);
        MatriceComplexe fComplexe = Fourier.InverseFourier2D(F);

        double[][] partieReelle = fComplexe.getPartieReelle();
        int[][] resultat = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                resultat[i][j] = Utils.clamp(0, 255, (int)Math.round(partieReelle[i][j]));
            }
        }

        return resultat;
    }

    public static int[][] filtrePasseBasButterworth(int[][] image, int frequenceCoupure, int ordre) {
        int M = image.length;
        int N = image[0].length;

        double[][] imageDouble = new double[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                imageDouble[i][j] = image[i][j];
            }
        }

        MatriceComplexe F = Fourier.Fourier2D(imageDouble);
        F = Fourier.decroise(F);

        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                double du = u - M / 2.0;
                double dv = v - N / 2.0;
                double distance = Math.sqrt(du * du + dv * dv);

                double H = 1.0 / (1.0 +  Math.pow(distance / frequenceCoupure, 2 * ordre));

                Complexe c = F.get(u, v);
                F.set(u, v, c.getPartieReelle() * H, c.getPartieImaginaire() * H);
            }
        }

        F = Fourier.decroise(F);
        MatriceComplexe fComplexe = Fourier.InverseFourier2D(F);

        double[][] partieReelle = fComplexe.getPartieReelle();
        int[][] resultat = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                resultat[i][j] = Utils.clamp(0, 255, (int)Math.round(partieReelle[i][j]));
            }
        }

        return resultat;
    }

    public static int[][] filtrePasseHautButterworth(int[][] image, int frequenceCoupure, int ordre) {
        int M = image.length;
        int N = image[0].length;

        double[][] imageDouble = new double[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                imageDouble[i][j] = image[i][j];
            }
        }

        MatriceComplexe F = Fourier.Fourier2D(imageDouble);
        F = Fourier.decroise(F);

        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                double du = u - M / 2.0;
                double dv = v - N / 2.0;
                double distance = Math.sqrt(du * du + dv * dv);

                double H = 1.0 / (1.0 +  Math.pow(frequenceCoupure / distance, 2 * ordre));

                Complexe c = F.get(u, v);
                F.set(u, v, c.getPartieReelle() * H, c.getPartieImaginaire() * H);
            }
        }

        F = Fourier.decroise(F);
        MatriceComplexe fComplexe = Fourier.InverseFourier2D(F);

        double[][] partieReelle = fComplexe.getPartieReelle();
        int[][] resultat = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                resultat[i][j] = Utils.clamp(0, 255, (int)Math.round(partieReelle[i][j]));
            }
        }

        return resultat;
    }
}
