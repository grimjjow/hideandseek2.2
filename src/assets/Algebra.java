package assets;

public class Algebra {

    public static Vector rotation(Vector v, double[][] m) {
        double[] var = {v.x, v.y};
        double[] res = new double[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i] += m[i][j] * var[j];
            }
        }
        return new Vector(res[0], res[1]);
    }

    // instantiate Z rotating linear transformation
    public static double[][] instRotTrZ(double angle) {
        double radAngle = Math.toRadians(angle);
        return new double[][]{
                {Math.cos(radAngle), -Math.sin(radAngle)},
                {Math.sin(radAngle), Math.cos(radAngle)},
        };
    }

    public static double[][] addMatrices(double[][] m1, double[][] m2) {
        assert m1.length == m2.length : "not equal column lengths";
        assert m1[0].length == m2[0].length : "not equal2 row lengths";
        double[][] res = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                res[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return res;
    }

    public static double[][] multiplyMatrices(double[][] m1, double[][] m2) {
        assert m1[0].length == m2.length : "can not multiply in those dimensions";
        double[][] res = new double[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static Vector multiplyMatrixByVector(double[][] m1, Vector v1) {
        double[] v = v1.toArray();
        assert v.length == m1[0].length : "can not multiply in those dimensions";
        double x = 0;
        double y = 0;
        for (int i = 0; i < m1[0].length; i++) {
            x += m1[0][i] * v[i];
            y += m1[1][i] * v[i];
        }
        return new Vector(x, y);
    }

    public static String toString(double[][] m) {
        String res = "";
        for (double[] row : m) {
            for (double col : row) {
                res += col + " ";
            }
            res += "\n";

        }
        return res;
    }

    public static double[][] multiplyMatrixByConstant(double[][] m, double c) {
        double[][] res = m.clone();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] *= c;
            }
        }
        return res;
    }

}
