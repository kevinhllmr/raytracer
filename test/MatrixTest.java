package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.math.Matrix;

public class MatrixTest {

    @Test
    public void testMatrixTranspose() {
        Matrix original = new Matrix(new Double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        });

        Matrix transposed = new Matrix(new Double[][]{
                {1.0, 4.0, 7.0},
                {2.0, 5.0, 8.0},
                {3.0, 6.0, 9.0}
        });

        assertEquals(transposed.toString(), original.transpose().toString());
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix matrixA = new Matrix(new Double[][]{
                {1.0, 2.0},
                {3.0, 4.0}
        });

        Matrix matrixB = new Matrix(new Double[][]{
                {5.0, 6.0},
                {7.0, 8.0}
        });

        Matrix expected = new Matrix(new Double[][]{
                {19.0, 22.0},
                {43.0, 50.0}
        });

        assertEquals(expected.toString(), matrixA.multiply(matrixB).toString());
    }

    @Test
    public void testMatrixToString() {
        Matrix matrix = new Matrix(new Double[][]{
                {1.0, 2.0},
                {3.0, 4.0}
        });

        String expectedString = "{1.0,2.0}\n{3.0,4.0}";

        assertEquals(expectedString, matrix.toString());
    }

    @Test
    public void testMatrixEquals() {
        Matrix matrixA = new Matrix(new Double[][]{
                {1.0, 2.0},
                {3.0, 4.0}
        });

        Matrix matrixB = new Matrix(new Double[][]{
                {1.0, 2.0},
                {3.0, 4.0}
        });

        assertTrue(matrixA.equals(matrixB));
    }
}
