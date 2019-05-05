public class Driver {

  
    public static void main(String[] args) {
        Interpolation interpolation = new Interpolation("src\\input.txt");
        interpolation.createTable();
        interpolation.divideDifference();
        interpolation.print();
        interpolation.interpolation();
        interpolation.simplifiedPolynomial();
    }
}
