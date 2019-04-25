/*
Name: Shayna Stewart
CS3010 Project 2
Description: Runs 5 methods to find roots of polynomial equation. Outputs status at each step, and 
             tells whether solution converges or diverges.



 */
package cs301_project2;

public class Main {

    public static void main(String[] args) {
        double errorThreshold = .01;
        int nmax = 100;

        System.out.println("Bisection Method:");
        bisectionMethod(0, 4, nmax, errorThreshold, 1);

        System.out.println("Newton Method:");
        newtonMethod(0, nmax, errorThreshold, .01, 1);
        newtonMethod(1, nmax, errorThreshold, .01, 1);
        newtonMethod(2, nmax, errorThreshold, .01, 1);
        newtonMethod(3, nmax, errorThreshold, .01, 1);

        System.out.println("Secant Method:");
        secantMethod(0, 1, nmax, errorThreshold, 1);
        secantMethod(1, 2, nmax, errorThreshold, 1);
        secantMethod(2, 3, nmax, errorThreshold, 1);
        secantMethod(3, 4, nmax, errorThreshold, 1);

        System.out.println("False Position Method:");
        falsePositionMethod(0, 1, nmax, errorThreshold, 1);
        falsePositionMethod(1, 2, nmax, errorThreshold, 1);
        falsePositionMethod(2, 3, nmax, errorThreshold, 1);
        falsePositionMethod(3, 4, nmax, errorThreshold, 1);

        System.out.println("Modified Secant Method:");
        secantMethodModified(0, 0.01, nmax, errorThreshold, 1);
        secantMethodModified(1, 0.01, nmax, errorThreshold, 1);
        secantMethodModified(2, 0.01, nmax, errorThreshold, 1);
        secantMethodModified(3, 0.01, nmax, errorThreshold, 1);

        bisectionMethod(120, 130, nmax, errorThreshold, 2);
        newtonMethod(125, nmax, errorThreshold, .01, 2);
        secantMethod(120, 130, nmax, errorThreshold, 2);
        falsePositionMethod(120, 130, nmax, errorThreshold, 2);
        secantMethodModified(125, 0.01, nmax, errorThreshold, 2);
    }

    private static double function1(double x) {
        return 2 * Math.pow(x, 3) - 11.7 * Math.pow(x, 2) + 17.7 * x - 5;
    }

    private static double function1Derivative(double x) {
        return 6 * Math.pow(x, 2) - 23.4 * x + 17.7;
    }

    private static double function2(double x) {
        return x + 10 - x * Math.cosh((50 / x));
    }

    private static double function2Derivative(double x) {
        return (59 * Math.sinh(50 / x)) / x - Math.cosh(50 / x) + 1;
    }

    private static void bisectionMethod(double a, double b, int nmax, double errorThreshold, int functionToUse) {
        double error = 0;
        double fa, fb, fc, c = 0, current_a, current_b;
        current_a = a;
        current_b = a + 1;
        double solutions_arr[] = new double[(int) b - (int) a]; //create a possible solutions list

        for (int i = 0; i < b - a; i++) {
            int n = 0;
            boolean foundSolution = false;
            current_a = a + i;
            current_b = a + i + 1;

            if (functionToUse == 1) {
                fa = function1(current_a);
                fb = function1(current_b);
            } else {
                fa = function2(current_a);
                fb = function2(current_b);
            }

            error = current_b - current_a;

            while ((current_b - current_a) > errorThreshold && n < nmax) {

                if (Math.signum(fa) == Math.signum(fb)) {
                    break;
                }
                c = (current_a + current_b) / 2;           // Mid point

                if (functionToUse == 1) {
                    fc = function1(c);

                } else {
                    fc = function2(c);
                }
                error = current_b - current_a;
                
                System.out.printf("n: %2d  a: %6.3f  b: %6.3f  c: %6.3f  f(a): %6.3f  f(b): %6.3f  f(c): %6.3f  error: %6.3f\n",n,a,b,c,fa,fb,fc, error);
                n++;
                if ((fc > 0 && fa < 0) || (fc < 0 && fa > 0)) {  // f(a) and f(m) have different signs: move b
                    current_b = c;
                } else {  // f(a) and f(m) have same signs: move a
                    current_a = c;
                }
                foundSolution = true;
                if (n == nmax) {
                    foundSolution = false;
                }

            }

            if (foundSolution == false) {

                solutions_arr[i] = Double.POSITIVE_INFINITY;
            } else {
                solutions_arr[i] = c;
            }

            if (solutions_arr[i] != Double.POSITIVE_INFINITY) {
                System.out.println("Convergence at x = " + solutions_arr[i] + "\n");

            }

        }

    }

    private static void newtonMethod(double x, int nmax, double errorThreshold, double delta, int functionToUse) {
        double fx, fp, error;

        //Initialize function value,
        if (functionToUse == 1) {
            fx = function1(x);
        } else {
            fx = function2(x);
        }
        if (functionToUse == 1) {
            fp = function1Derivative(x);
        } else {
            fp = function2Derivative(x);
        }
        System.out.printf("n: %2d  x: %6.3f  f(x): %6.3f  f'(x): %6.3f\n", 0, x, fx, fp);
        double lastError = Double.POSITIVE_INFINITY;
        int divergeCount = 0;
        for (int i = 1; i <= nmax; i++) {
            if (functionToUse == 1) {
                fp = function1Derivative(x);
            } else {
                fp = function2Derivative(x);
            }

            if (Math.abs(fp) < delta) {
                System.out.println("Small Derivative");
                return;
            }

            error = fx / fp;
            x = x - error;
            if (functionToUse == 1) {
                fx = function1(x);
            } else {
                fx = function2(x);
            }

            System.out.printf("n: %2d  x: %6.3f  f(x): %6.3f  f'(x): %6.3f  error: %6.3f\n", i, x, fx, fp, error);

            if (Math.abs(error) < errorThreshold) {
                System.out.println("Convergence at " + x + ".\n");
                return;
            }
            lastError = error;
        }
        System.out.println("Does not converge after " + nmax + " iterations.\n");
    }

    private static void secantMethod(double a, double b, int nmax, double errorThreshold, int functionToUse) {
        double fa, fb, error = b - a, c = 0, fc = 0;
        int n = 1;

        //Initialize function values,
        if (functionToUse == 1) {
            fa = function1(a);
            fb = function1(b);
            fc = function1(c);
        } else {
            fa = function2(a);
            fb = function2(b);
            fc = function2(c);
        }
        System.out.printf("n: %2d  a: %6.3f  f(a): %6.3f b: %6.3f  f(b): %6.3f c: %6.3f  f(c): %6.3f  error: %6.3f\n", 0, a, fa, b, fb, c, fc, error);

        
        while ((error > errorThreshold) && (n < nmax)) {
            double d = fb - fa;
            c = b - fb * (b - a) / d;
            if (functionToUse == 1) {
                fc = function1(c);
            } else {
                fc = function2(c);
            }
            a = b;
            b = c;
            error = b - a;
            System.out.printf("n: %2d  a: %6.3f  f(a): %6.3f b: %6.3f  f(b): %6.3f c: %6.3f  f(c): %6.3f  error: %6.3f\n", n, a, fa, b, fb, c, fc, error);

            n++;

        }
        if (n == nmax) {
            System.out.println("Solution does not converge.\n");
        }
        System.out.println("Convergence at " + b + "\n");

    }

    private static void falsePositionMethod(double a, double b, int nmax, double errorThreshold, int functionToUse) {
        double error = 0;
        double c;
        double fa, fb, fc;

        if (functionToUse == 1) {
            fa = function1(a);
            fb = function1(b);
        } else {
            fa = function2(a);
            fb = function2(b);
        }

        if (Math.signum(fa) == Math.signum(fb)) {
            return;
        }

        double lastc = 0;
        double lastError = Double.POSITIVE_INFINITY;
        int divergeCount = 0;
        for (int i = 0; i <= nmax; i++) {
            c = (a * fb - b * fa) / (fb - fa);
            error = (c - lastc) / c;

            if (Math.abs(error) > Math.abs(lastError)) {
                System.out.println("Solution is divergent.");
                break;
            }
            
            if (functionToUse == 1) {
                fc = function1(c);

            } else {
                fc = function2(c);
            }
            System.out.printf("n: %2d  a: %6.3f  b: %6.3f  c: %6.3f  f(a): %6.3f  fb: %6.3f  fc: %6.3f  error: %6.3f\n", i, a, b, c, fa, fb, fc, error);

            if (Math.abs(error) <= errorThreshold) {
                System.out.println("Convergence at " + c + ".\n");
                return;
            }

            lastc = c;
            if ((fa < 0 && fc > 0)||(fa > 0 && fc < 0)) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }

            lastError = error;
        }
        System.out.println("Does not converge after " + nmax + " iterations.\n");
    }

    private static void secantMethodModified(double x, double delta, int nmax, double errorThreshold, int functionToUse) {
        double fx, fdxPlusx, error; //f(x), f(deltax+x), error

        
        if (functionToUse == 1) { //initializing f(x)
            fx = function1(x);
            fdxPlusx = function1(x + (delta * x));
        } else {
            fx = function2(x);
            fdxPlusx = function2(x + (delta * x));
        }

        System.out.printf("n: %2d  x: %6.3f  f(x): %6.3f  delta: %6.3f  f(x + delta*x): %6.3f\n", 0, x, fx, delta, fdxPlusx); //print initial

        double lastError = Double.POSITIVE_INFINITY; //initialize as infinity

        for (int i = 1; i <= nmax; i++) {
            error = fx * ((delta * x) / (fdxPlusx - fx));

            if (Double.isNaN(x)) {   //
                System.out.println("Solution does not converge.\n"); //if x is NaN, the solution does not converge. --> typically if deltaxPlusx = f(x)
                return;
            }

            if (Math.abs(error) <= errorThreshold) { // if the error is less than the error Threshhold given, then we have found a solution
                System.out.println("Convergence at " + x + ".\n");
                return;
            }
            x = x - error;
            if (functionToUse == 1) {
                fx = function1(x);
                fdxPlusx = function1(x + (delta * x));
            } else {
                fx = function2(x);
                fdxPlusx = function2(x + (delta * x));
            }
            error = fx * ((delta * x) / (fdxPlusx - fx));
            System.out.printf("n: %2d  x: %6.3f  f(x): %6.3f  error: %6.3f\n", i, x, fx, error);    //print progress
            lastError = error;
        }
        System.out.println("Does not converge after " + nmax + " iterations.\n");
    }

}
