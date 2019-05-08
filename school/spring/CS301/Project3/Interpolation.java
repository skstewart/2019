
import java.io.*;
import java.util.ArrayList;

public class Interpolation {

    public String filename;
    public double[][] dTable;
    public ArrayList<Double> storeY = new ArrayList<>();

    public Interpolation(String filename) {
        this.filename = filename;
    }

    public void createTable() {
        String[] x = null;
        String[] fx = null;
        FileInputStream fstream = null;
        //reading in file: first check whether file exists. then read file. then store x and y values

        try {
            fstream = new FileInputStream(this.filename);
        } catch (FileNotFoundException err) {
            System.out.println("The file does not exist");
        }

        DataInputStream dis = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));

        try {
            x = br.readLine().split("\\s+");
            fx = br.readLine().split("\\s+");
        } catch (IOException io) {
            System.out.println("There is nothing to read");
        }

        dTable = new double[x.length][fx.length + 1];

        for (int i = 0; i < x.length; ++i) {
            dTable[i][0] = Double.parseDouble(x[i]);
        }

        for (int i = 0; i < fx.length; ++i) {
            dTable[i][1] = Double.parseDouble(fx[i]);
        }

    }

    public ArrayList<Double> polyFunction(double value, ArrayList<Double> array, int size) {
        ArrayList<ArrayList<Double>> narray = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> storeY = new ArrayList<Double>();

        for (int i = 0; i < array.size() + 1; i++) {
            storeY.add(0.0);
        }

        storeY.add(0, value);

        for (int i = 0; i < array.size(); i++) {

            // multiply by x
            ArrayList<Double> temp = new ArrayList<Double>();
            temp.add(0.0);

            for (int x = 0; x < storeY.size() - 1; x++) {
                temp.add(storeY.get(x));
            }

            narray.add(temp);
            //end mult

            //multiply array * (-array.get(i))
            temp = new ArrayList<Double>();
            for (double z : storeY) {
                temp.add(z * (-array.get(i)));
            }
            narray.add(temp);

            //end mult
            //combine like terms
            ArrayList temp2 = new ArrayList<Double>();
            for (int y = 0; y < narray.get(0).size(); y++) {
                double sum = 0;
                for (int j = 0; j < narray.size(); j++) {
                    sum = sum + narray.get(j).get(y);
                }
                temp2.add(sum);
            }

            storeY = temp2;

            //
            narray.clear();
        }

        int ySize = storeY.size();

        for (int i = 0; i < size - ySize; i++) {
            storeY.add(0.0);
        }

        return storeY;
    }

    public void divideDifference() {
        int n = dTable[0].length;

        for (int j = 2; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dTable[i][j] = (dTable[i + 1][j - 1] - dTable[i][j - 1]) / (dTable[i + (j - 1)][0] - dTable[i][0]);
            }
        }

        for (int i = 1; i < dTable[0].length; ++i) {
            storeY.add(dTable[0][i]);
        }
    }

    public void printTable() {
        int n = dTable[0].length;
        System.out.printf("\t x \t f[] \t f[,] \t f[,,] \t f[,,,] ");
        System.out.printf("\n__________________________________________________________\n");
        for (int i = 0; i < n - 1; i++) {
            System.out.printf("\n");
            for (int j = 0; j < n - i; j++) {
                System.out.printf(" ");
                System.out.printf("\t %.3f", dTable[i][j]);

            }
        }
    }

//Making the interpolation function
    public void interpolatedPolynomial() {
        ArrayList<String> x = new ArrayList<String>();
        String sign = "";
        for (int i = 0; i < this.dTable.length - 1; ++i) {
            double xValue = this.dTable[i][0];

            if (xValue < 0) {
                sign = "+";
            } else if (xValue > 0) {
                sign = "-";
            }
            if (((xValue * 1000) / 1000) == 0) { //if rounded to 0
                x.add("(x)");
            } else {
                x.add(String.format("(x%s%.3f)", sign, xValue));
            }
        }

        String polynomial = String.format("%.3f", storeY.get(0));

        for (int i = 1; i < x.size() + 1; ++i) {
            double yValue = storeY.get(i);
            if (yValue != 0) {
                if (yValue > 0) {
                    sign = "+";
                } else {
                    sign = "-";
                }
                String combine = "";
                for (int j = 0; j < i; ++j) {
                    combine += x.get(j);
                }
                polynomial += String.format(" %s %.3f%s", sign, Math.abs(yValue), combine);
            }
        }
        System.out.println(" ");
        System.out.println("\nThe interpolating polynomial is: " + polynomial);
    }

//Simplified Polynomial
    public void simplifiedPolynomial() {
        ArrayList<Double> value = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();

        for (int i = 0; i < dTable[0].length - 1; i++) {
            value.add(0.0);
        }

        value.add(0, storeY.get(0));
        array.add(0, value);

        for (int i = 1; i < storeY.size(); i++) {
            value = new ArrayList<Double>();
            double yvalue = storeY.get(i);
            for (int j = 0; j < i; j++) {
                value.add(dTable[j][0]);
            }
            array.add(polyFunction(yvalue, value, dTable[0].length));
        }

        //value = combineLike(array);
        //combine like terms
        ArrayList temp2 = new ArrayList<Double>();
        for (int y = 0; y < array.get(0).size(); y++) {
            double sum = 0;
            for (int j = 0; j < array.size(); j++) {
                sum = sum + array.get(j).get(y);
            }
            temp2.add(sum);
        }

        value = temp2;

        String polynomial = "";
        String power = "";
        for (int i = value.size() - 1; i > -1; i--) {
            Double j = value.get(i);
            power = String.format("x^%d", i);
            if (j != 0) {
                if (i == 0) {
                    polynomial += String.format(" + %.3f", j);
                } else {
                    polynomial += String.format(" %+.3f%s", j, power);
                }
            }
        }
