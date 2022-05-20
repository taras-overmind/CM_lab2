import java.util.*;

public class MatrixGauss {

static final double[][] E = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
};

static double[][] mtr = {
        {5, 1, 1, 0},
        {1, 2, 0, 0},
        {1, 0, 4, 2},
        {0, 0, 2, 3}
};

    static final int LENGTH = mtr.length;
    private static double[] b = {17, 8, 28, 23};
    private static double det;
    private static int prmtCount = 0;
    private static final double[] leadElem = new double[LENGTH];

    static double[][] M;
    static double[][] P;
    static final List<double[][]> MList = new ArrayList<>();
    static final List<double[][]> PList = new ArrayList<>();

    static void calcM(int i) {
        M[i][i] = 1 / mtr[i][i];                        // Обчислюємо перший елмемент стовбчику в М
        double[] column = new double[LENGTH];
        for (int j = i; j < LENGTH; j++) {
            column[j] = mtr[j][i];                      // Записуємо в масив стовбчик
        }                                               // основної матриці із яким працюємо

        for (int u = i + 1; u < M.length; u++) {
            M[u][i] = -column[u] / column[i];           // Обчислюємо інші елмементи стовбчику в М
        }
    }

    static void calcP(int i, int ind) {
        double[] tmp = P[ind];
        P[ind] = P[i];
        P[i] = tmp;
    }

    private static void calcDet() {
        det = Math.pow(-1, prmtCount);
        for (double v : leadElem) {
            det *= v;
        }
    }

    public static void showResult(double[] x, double det, double[][] invMtr) {
        System.out.printf("Детермінант: %.1f\n",det);
        System.out.println("\nКінцева матриця:");
        Operations.printMatrix(MatrixGauss.mtr);
        System.out.println("______________________________");
        System.out.println("Обернена матриця:");
        Operations.printMatrix(invMtr);
        System.out.println("______________________________");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d: %6.3f\n",i, x[i]);
        }

    }

    private static int findLeadElem(int i) {
        int ind = i;                                    // Діагональний елемент -> поч. макс. значення
        double max = mtr[i][i];                         // Індекс цього елемнту
        for (int j = i; j < LENGTH; j++) {              // Шукаємо в стовпчику максимальне значення
            if (max < mtr[j][i]) {                      // Знайшли більше ніж початкове ->
                max = mtr[j][i];                        // перезаписуємо його
                ind = j;                                // Перезаписуємо його індекс
            }
        }
        leadElem[i] = max;                              // Додаємо до списку ведучих елементів
        return ind;                                     // Повертаємо його індекс
    }

    private static double[] backStroke(double[][] m, double[] b1) {
        double[] res = new double[b1.length];
        for (int i = m.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < m.length; j++) {
                b1[i] = b1[i] - m[i][j] * res[j];
            }
            res[i] = b1[i];
        }
        return res;
    }

    private static double matrixNorma(double[][] m){
        double[] column_sum=new double[LENGTH];
        for (int i=0; i<LENGTH; i++)
            for(int j=0; j<LENGTH; j++)
                column_sum[i]+=Math.abs(m[j][i]);
        double max=column_sum[0];
        for (int i = 0; i < LENGTH; i++) {
            if(column_sum[i]>max)
                max=column_sum[i];
        }
        return max;
    }

    public static void runMethod() {
        System.out.println("Початкова матриця: ");
        Operations.printMatrix(mtr);
        for (int i = 0; i < LENGTH; i++) {
            M = Arrays.stream(E).map(double[]::clone).toArray(double[][]::new);
            P = Arrays.stream(E).map(double[]::clone).toArray(double[][]::new);
            int ind = findLeadElem(i);
            if (ind != i) {
                calcP(i, ind);
                prmtCount++;
            }
            calcM(i);
            MList.add(M);
            PList.add(P);
            System.out.println("М Матриця");
            Operations.printMatrix(M);
            System.out.println("P Матриця");
            Operations.printMatrix(P);
            System.out.println();
            mtr = Operations.multiply(P, mtr);
            mtr = Operations.multiply(M, mtr);
            b = Operations.multiply(P, b);
            b = Operations.multiply(M, b);
        }
        calcDet();
        showResult(backStroke(mtr, b), det, Operations.backInBlack(mtr, LENGTH));
        System.out.println("Норма матриці: "+matrixNorma(mtr));
        System.out.println("Норма оберненої матриці: "+matrixNorma(Operations.backInBlack(mtr, LENGTH)));
        System.out.println("Число обумовленості: "+matrixNorma(mtr)*matrixNorma(Operations.backInBlack(mtr, LENGTH)));
    }
}
