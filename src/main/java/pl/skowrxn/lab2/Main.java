package pl.skowrxn.lab2;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2,2);
        matrix.set(0,0, 1.1);
        matrix.set(0,1, 1.2);
        matrix.set(1,0, 1.3);
        matrix.set(1,1, 1.4);
        System.out.println(matrix);
    }
}
