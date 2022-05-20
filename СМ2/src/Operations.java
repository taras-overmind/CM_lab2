

public class Operations {

    static public double[][] multiply(double[][] matrix1, double[][] matrix2) {
        double[][] res = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

    static public double[] multiply(double[][] matrix, double[] column) {
        double[] res = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[i] += matrix[i][j] * column[j];
            }
        }
        return res;
    }

    static public void printMatrix(double[][] matrix) {
        for (double[] rows : matrix) {
            for (double column : rows) {
                System.out.printf("%8.3f", column);
            }
            System.out.println();
        }
    }

    static public void printMatrix(double[] matrix) {
        for (double column : matrix) {
            System.out.printf("%8.4f", column);
        }
    }

    static public double getDeterminant(double[][] matrix) {
        double[][] res = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[i][j] = matrix[i][j];
            }
        }
        double determ = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length - 1; j > i; j--) {
                double coef = res[j][i];
                for (int k = 0; k < matrix[0].length; k++) {
                    res[j][k] = res[j][k] - res[i][k] * coef / res[i][i];
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            determ *= res[i][i];
        }
        return determ;
    }

     static double[][] backInBlack(double[][] matr,int length) {
        double[][] backMatr = new double[length] [length];
        for (int i=0; i<length; i++)
            for (int j = 0; j < length; j++)
            {
                if (i == j)
                    backMatr[i][j] = 1;
                else
                    backMatr[i][j] = 0;
            }

        double p = 0, q = 0, s =0;
        for (int i = 0; i < length; i++)
        {
            p = matr[i] [i];
            for (int j = i + 1; j < length; j++)
            {
                q = matr[j] [i];
                for (int k = 0; k < length; k++)
                {
                    matr[j] [k] = (double) (matr[i] [k] * q - matr[j] [k] * p);
                    backMatr[j] [k] = (double) (backMatr[i][k] * q - backMatr[j][k] * p);
                }
            }
        }
        for (int i = 0; i < length; i++)
        {
            for (int j = length - 1; j >= 0; j--)
            {
                s = 0;
                for (int k = length - 1; k > j; k--)
                    s += matr[j][ k] * backMatr[k][i];
                backMatr[j][i] = (double) ((backMatr[j][i] - s) / matr[j][j]);
            }
        }
        return backMatr;
    }

    //static public double matrixNorma()



}