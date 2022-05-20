
public class Seidel {
    private static double[][] matrix = {
            {5, 1, 1, 0},
            {1, 2, 0, 0},
            {1, 0, 4, 2},
            {0, 0, 2, 3}
    };

    private static double[] result = {17, 8, 28, 23};
    private static double[] x0 = {0, 0, 0, 0};
    private static double[] x1 = {0, 0, 0, 0};
    public static void seidelMethod(double e) {
        System.out.println("Початкова матриця:");
        Operations.printMatrix(matrix);
        System.out.println("\n\tВизначник: "+Operations.getDeterminant(matrix));
        double norma_sum=0;

        System.out.print("\t");
        for (int i = 0; i < x1.length; i++) {
            System.out.printf(" x"+i+'\t'+'\t');
        }
        System.out.println();
        System.out.print(0+". ");
        Operations.printMatrix(x1);
        System.out.println('\n'+"_________________________________");
        int counter =1;
        while (true) {
            for (int i = 0; i < x1.length; i++) {
                double sum = result[i];
                for (int j = 0; j < x1.length; j++) {
                    if(j!=i){
                        sum -= matrix[i][j]*x1[j];
                    }
                }
                x1[i] = (1.0 / matrix[i][i])* sum;
            }
            if ((Math.abs(x1[0] - x0[0]) < e) && (Math.abs(x1[1] - x0[1]) < e) && (Math.abs(x1[2] - x0[2]) < e)
                    && (Math.abs(x1[3] - x0[3]) < e)) {
                break;
            }
            for (int i = 0; i < x0.length; i++) {
                x0[i] = x1[i];
            }
            System.out.print(counter+". ");
            Operations.printMatrix(x1);
            System.out.println('\n'+"_________________________________");
            if(counter==1){
                for (int i = 0; i < x1.length; i++) {
                    norma_sum+=x1[i]*x1[i];
                }
                norma_sum=Math.sqrt(norma_sum);
            }
            counter++;
        }
        System.out.println("Початкова норма вектору(Евклідова): "+norma_sum);
        System.out.print("Кінцева норма вектору(Евклідова):");
        norma_sum=0;
        for (int i = 0; i < x1.length; i++) {
            norma_sum+=x1[i]*x1[i];
        }
        System.out.println(Math.sqrt(norma_sum));
    }


}