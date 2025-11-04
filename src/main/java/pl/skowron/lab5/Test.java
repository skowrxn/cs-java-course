package pl.skowron.lab5;

import java.util.Locale;

public class Test {

    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }

    static void defineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);
        System.out.println(circle.toString());

        int count = 1;
        while (count <= 100) {
            double xv = 100 * (Math.random() - .5);
            double yv = 100 * (Math.random() - .5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if (fv < 0) {
                System.out.printf(count + ". Punkt (%f,%f) leży wewnątrz koła %s\n", xv, yv, circle.toString());
                count++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing buildAndEvaluate:");
        buildAndEvaluate();
        System.out.println("\nTesting buildAndPrint:");
        buildAndPrint();
        System.out.println("\nTesting defineCircle:");
        defineCircle();
    }
}
