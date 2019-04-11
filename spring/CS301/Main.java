/*
 Name: Shayna Stewart
 CS301
 Project 1
 */
package cs301proj1;

import java.util.*;

public class Main {

    private static Scanner scanner;
    private static double matrix[][];

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        System.out.println("Please enter a number of equations (less than 10).");
        int n = scanner.nextInt();
        matrix = new double[n][n+1];
        while (n > 10 || n < 1) {
            System.out.println("Please enter a number of equations (less than 10). ");
            n = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            System.out.println("For equation " + (i + 1));
            for (int j = 0; j < n+1; j++) {
                if (j < n) {
                    System.out.println("Enter the value for variable x" + (j + 1) + ": ");
                    matrix[i][j] = scanner.nextFloat();
                } else {
                    System.out.println("Enter the b constant for this equation: ");
                    matrix[i][j] = scanner.nextFloat();
                }
            }
        }
        System.out.println("Your matrix:");
        printMatrix(matrix);
        Menu();

    }

    private static void Menu() {
        System.out.println("\nMenu:");
     //   System.out.println("1. Partial Pivoting Method for Gaussian Elimination.");
        System.out.println("2. Gauss-Seidel Method");
        System.out.println("3. Jacobi Iterative Method");
        System.out.println("4. Exit");

        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1: {
                //GaussianElimination(matrix);
                Menu();
            }
            break;
            case 2: {
                System.out.print("Please enter your desired error: ");
                double errorSeidel = scanner.nextFloat();
                GaussSeidel(matrix, errorSeidel);
                Menu();
            }
            break;
            case 3: {
                System.out.print("Please enter your desired error: ");
                double error = scanner.nextFloat();
                Jacobi(matrix, error);
            }
            Menu();

            case 4: {
                System.out.println("Exiting Program.");
                System.exit(0);
            }
            break;
            default: {
                System.out.println("\nInvalid entry.");
                Menu();
            }
            break;
        }
    }

    private static void Jacobi(double[][] matrix, double error) {
        int maxIterations = 50;
        double[] arrayOne = new double[matrix.length];
        double[] arrayTwo = new double[matrix.length];
        int n = matrix.length;
        double diagonal, sum;
        for (int i = 0; i < maxIterations; i++) {
            for (int z = 0; z < n; z++) {
                sum = matrix[z][n];
                diagonal = matrix[z][z];
                for (int j = 0; j < n; j++) {
                    if (j != z) {
                        sum -= matrix[z][j] * arrayOne[j];
                    }
                }
                arrayOne[z] = sum / diagonal;
            }

            System.out.println("\nIteration " + (i + 1));
            for (int x = 0; x < arrayOne.length; x++) {
                System.out.println("x" + (x + 1) + "=" + arrayTwo[x] + " ");
            }

            for (int y = 0; y < matrix.length; y++) {
                if (Math.abs(arrayOne[y] - arrayTwo[y] / arrayOne[y]) <= error) {
                    System.out.println("Desired error has been reached");
                    return;
                }
            }

            for (int k = 0; k < matrix.length; k++) {
                arrayTwo[k] = arrayOne[k];
            }
        }
        System.out.println("Max number of iterations has been reached. Ending process.");
    }

    private static void GaussSeidel(double[][] matrix, double error) {
        int maxIterations = 50;
        double[] arrayOne = new double[matrix.length];
        double[] arrayTwo = new double[matrix.length];
        int n = matrix.length;
        double diag, sum;
        for (int x = 0; x < maxIterations; x++) {
            for (int i = 0; i < n; i++) {
                sum = matrix[i][n];
                diag = matrix[i][i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= matrix[i][j] * arrayOne[j];
                    }
                }
                arrayOne[i] = sum / diag;
            }

            System.out.println("\nIteration " + (x + 1));
            for (int z = 0; z < arrayOne.length; z++) {
                System.out.println("x" + (z + 1) + "=" + arrayTwo[z] + " ");
            }

            for (int y = 0; y < matrix.length; y++) {
                if (Math.abs(arrayOne[y] - arrayTwo[y] / arrayOne[y]) <= error) {
                    System.out.println("Desired error has been reached.");
                    return;
                }
            }

            for (int k = 0; k < matrix.length; k++) {
                arrayTwo[k] = arrayOne[k];
            }
        }
        System.out.println("Max number of iterations has been reached. Ending process.");
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j] + "   ");
            }
            System.out.println();
        }
    }

}
