package pl.skowron.lab2;

import java.util.Arrays;
import java.util.Random;

public class Matrix {

    double[]data;
    int rows;
    int cols;

    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    public Matrix(double[][] d){
        this.rows = d.length;
        int maxCols = 0;
        for (int row = 0; row < this.rows; row++){
            int rowLen = d[row].length;
            if (rowLen > maxCols) {
                maxCols = rowLen;
            }
        }
        this.cols = maxCols;
        data = new double[rows*cols];
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                data[row*cols + col] = col >= d[row].length ? 0 : d[row][col];
            }
        }
    }

    public double[][] asArray() {
        double[][] result = new double[rows][cols];
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                result[row][col] = data[row*cols + col];
            }
        }
        return result;
    }

    public double get(int row, int col){
        if(row < 0 || row >= rows || col < 0 || col >= cols)
            throw new RuntimeException(String.format("Invalid index [%d, %d] for %d x %d matrix", row, col, rows, cols));
        return this.data[row*cols + col];
    }

    public void set(int row, int col, double value){
        if(row < 0 || row >= rows || col < 0 || col >= cols)
            throw new RuntimeException(String.format("Invalid index [%d, %d] for %d x %d matrix", row, col, rows, cols));
        data[row*cols + col] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int row = 0; row < rows; row++){
            buf.append("[").append(this.data[row * cols]);
            for (int col = 1; col < cols; col++) {
                buf.append(", ").append(this.data[row * cols + col]);
            }
            buf.append("]" + (row != rows-1 ? "\n" : ""));
        }
        buf.append("]");
        return buf.toString();
    }

    public void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        if(newRows == this.rows && newCols == this.cols) return;
        rows = newRows;
        cols = newCols;
    }

    public Matrix add(Matrix m) {
        Matrix matrix = new Matrix(rows, cols);
        if(rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be added to %d x %d",rows,cols,m.rows,m.cols));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++){
                matrix.set(row, col,data[row*cols + col] + m.get(row, col));
            }
        }
        return matrix;
    }

    public Matrix add(double w) {
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix.set(row, col, data[row * cols + col] + w);
            }
        }
        return matrix;
    }

    public int[] shape(){
        return new int[]{rows, cols};
    }

    public Matrix sub(double w){
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++){
                matrix.set(row, col, data[row*cols + col] - w);
            }
        }
        return matrix;
    }

    public Matrix sub(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be subtracted from %d x %d", rows, cols, m.rows, m.cols));
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix.set(row, col, data[row * cols + col] - m.get(row, col));
            }
        }
        return matrix;
    }


    public Matrix mul(double w){
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++){
                matrix.set(row, col,data[row*cols + col] * w);
            }
        }
        return matrix;
    }

    public Matrix mul(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplied by %d x %d", rows, cols, m.rows, m.cols));
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix.set(row, col, data[row * cols + col] * m.get(row, col));
            }
        }
        return matrix;
    }
    
    

    public Matrix div(double w) {
        Matrix matrix= new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++){
                matrix.set(row, col, data[row*cols + col] / w);
            }
        }
        return matrix;
    }

    public Matrix div(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("%d x %d matrix can't be divided by %d x %d", rows, cols, m.rows, m.cols));
        Matrix matrix = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix.set(row, col, data[row * cols + col] / m.get(row, col));
            }
        }
        return matrix;
    }

    public double frobenius() {
        double sumOfRoots = Arrays.stream(this.data).map(x -> x * x).sum();
        return Math.sqrt(sumOfRoots);
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                m.set(row, col, r.nextDouble());
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for(int i = 0; i < n; i ++){
            m.set(i,i,1);
        }
        return m;
    }

    public Matrix dot(Matrix m) {
        if (this.cols != m.rows)
            throw new RuntimeException(String.format("Cannot multiply %dx%d matrix by %dx%d matrix",
                    this.rows, this.cols, m.rows, m.cols));
        double[][] result = new double[this.rows][m.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.get(i, k) * m.get(k, j);
                }
                result[i][j] = sum;
            }
        }
        return new Matrix(result);
    }

}
