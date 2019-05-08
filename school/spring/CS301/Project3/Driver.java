/*
Name: Shayna Stewart
Class: CS3010
Project # 3
Due: 5/11/19
 */
public class Driver {

  
    public static void main(String[] args) {
        Interpolation interpolation = new Interpolation("src\\input.txt");
        interpolation.createTable();
        interpolation.divideDifference();
        interpolation.printTable();
        interpolation.interpolatedPolynomial();
        interpolation.simplifiedPolynomial();
    }
}
