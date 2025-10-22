package pl.skowron.test.lab3;

import org.junit.jupiter.api.Test;
import pl.skowron.lab2.Matrix;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    // !!! JUNIT W WERSJI 5

    @Test
    void testMatrixSizeIsCorrect(){
        Matrix matrix = new Matrix(3,4);
        assertArrayEquals(new int[]{3,4}, matrix.shape());
    }

    @Test
    void testMatrixRowSize(){
        double[][] d = new double[][]{{0.0, 1.0}, {0.0, 1.0, 2.0}, {0.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertArrayEquals(new int[]{3,3}, matrix.shape());
    }

    @Test
    void testMatrixRowsSizeAndDefaultFill() {
        Matrix matrix = new Matrix(new double[2][3]);
        assertArrayEquals(new int[]{2, 3}, matrix.shape());
        assertArrayEquals(new double[][]{{0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}}, matrix.asArray());
    }

    @Test
    void testMatrixGetCellValue() {
        double[][] d = new double[][]{{0.0, 1.0, 2.0}, {0.0, 1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertEquals(matrix.get(0, 0), matrix.get(1, 0));
        assertEquals(matrix.get(0, 1), matrix.get(1, 1));
        assertEquals(matrix.get(0, 2), matrix.get(1, 2));
    }
    
    @Test
    void testMatrixGet() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertEquals(0.0, matrix.get(0,0));
        assertEquals(2.0, matrix.get(1,1));
    }

    @Test
    void testMatrixGet_shouldThrowException(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.get(2,0));
        assertThrows(RuntimeException.class, () -> matrix.get(0,-1));
    }

    @Test
    void testMatrixSet() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        matrix.set(0,0,5.0);
        assertEquals(5.0, matrix.get(0,0));
    }

    @Test
    void testMatrixSet_shouldThrowException(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.set(2,0, 1));
        assertThrows(RuntimeException.class, () -> matrix.set(0,-1, 1));
    }

    @Test
    void testMatrixToString() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertEquals("[[0.0, 1.0]\n[1.0, 2.0]]", matrix.toString());
    }

    @Test
    void testMatrixReshape() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        matrix.reshape(1,4);
        assertArrayEquals(new double[][]{{0.0, 1.0, 1.0, 2.0}}, matrix.asArray());
    }

    @Test
    void testMatrixReshape_shouldThrow() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.reshape(2,4));
    }

    @Test
    void testMatrixAddToMatrix(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        Matrix matrix = new Matrix(d).add(new Matrix(d2));
        assertArrayEquals(new double[][]{{1.0, 3.0}, {4.0, 6.0}}, matrix.asArray());
    }

    @Test
    void testMatrixAddToMatrix_shouldThrow(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0, 5.0}, {3.0, 4.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.add(new Matrix(d2)));
    }

    @Test
    void testMatrixAddToDouble(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d).add(2);
        assertArrayEquals(new double[][]{{2.0, 3.0}, {3.0, 4.0}}, matrix.asArray());
    }

    @Test
    void testMatrixSubFromDouble(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d).sub(2);
        assertArrayEquals(new double[][]{{-2.0, -1.0}, {-1.0, 0.0}}, matrix.asArray());
    }

    @Test
    void testMatrixSubFromMatrix(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        Matrix matrix = new Matrix(d).sub(new Matrix(d2));
        assertArrayEquals(new double[][]{{-1.0, -1.0}, {-2.0, -2.0}}, matrix.asArray());
    }

    @Test
    void testMatrixSubFromMatrix_shouldThrow(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0}, {3.0, 4.0, 5.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.sub(new Matrix(d2)));
    }

    @Test
    void testMatrixMulByDouble() {
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d).mul(2);
        assertArrayEquals(new double[][]{{0.0, 2.0}, {2.0, 4.0}}, matrix.asArray());
    }

    @Test
    void testMatrixMulByMatrix(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        Matrix matrix = new Matrix(d).mul(new Matrix(d2));
        assertArrayEquals(new double[][]{{0.0, 2.0}, {3.0, 8.0}}, matrix.asArray());
    }

    @Test
    void testMatrixMulByMatrix_shouldThrow(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0, 4.0}, {3.0, 4.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.mul(new Matrix(d2)));
    }

    @Test
    void testMatrixAdd(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{-1.0, 2.0}, {1.0, 4.0}};
        Matrix matrix = new Matrix(d).add(new Matrix(d2));
        assertArrayEquals(new double[][]{{-1.0, 3.0}, {2.0, 6.0}}, matrix.asArray());
    }

    @Test
    void testMatrixDivByDouble(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        Matrix matrix = new Matrix(d).div(2);
        assertArrayEquals(new double[][]{{0.0, 0.5}, {0.5, 1.0}}, matrix.asArray());
    }

    @Test
    void testMatrixDivByMatrix(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0}, {2.0, 4.0}};
        Matrix matrix = new Matrix(d).div(new Matrix(d2));
        assertArrayEquals(new double[][]{{0.0, 0.5}, {0.5, 0.5}}, matrix.asArray());
    }

    @Test
    void testMatrixDivByMatrix_shouldThrow(){
        double[][] d = new double[][]{{0.0, 1.0}, {1.0, 2.0}};
        double[][] d2 = new double[][]{{1.0, 2.0, 3.0}, {2.0, 4.0}};
        Matrix matrix = new Matrix(d);
        assertThrows(RuntimeException.class, () -> matrix.div(new Matrix(d2)));
    }

    @Test
    void testMatrixEye() {
        Matrix matrix = Matrix.eye(5);
        assertEquals(Math.sqrt(5.0), matrix.frobenius());
    }

    @Test
    void testMatrixInverse(){
        double[][] d = new double[][]{{0.0, 1.0, 3.0}};
        Matrix matrix = new Matrix(d);

    }

}
