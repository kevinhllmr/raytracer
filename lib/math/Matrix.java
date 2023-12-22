package lib.math;

public class Matrix {
  private final int width, height;
  private final Double[][] data;
  private static final Matrix IDENTITY4 = new Matrix(new Double[][]{{1.0, 0.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}, {0.0, 0.0, 0.0, 1.0}});
  private Matrix inverse;

  public Matrix(Double[][] cells) {
    this.width = cells[0].length;
    this.height = cells.length;
    data = cells.clone();

    if (width != cells[0].length || height != cells.length) {
      throw new ArithmeticException("Incorrectly sized data for this quadratic matrix's dimension");
    }
  }

  public final Double get(int row, int column) {
    return data[row][column];
  }

  public void set(int row, int column, Double value) {
    data[row][column] = value;
  }

  // Matrix transponieren / Zeilen und Spalten vertauschen
  public Matrix transpose() {
    Double[][] newData = new Double[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        newData[row][col] = this.get(col, row);
      }
    }
    
    return new Matrix(newData);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Matrix) {
      return contentsAreEqual((Matrix) obj);
    }

    return false;
  }

  // Matrizen auf Gleichheit überprüfen
  protected boolean contentsAreEqual(Matrix other) {
    if (height != other.height || width != other.width)
      return false;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Double thisCell = this.get(y, x);
        Double otherCell = other.get(y, x);

        if (!Calc.approxEqual(thisCell, otherCell))
          return false;
      }
    }

    return true;
  }

  // Untermatrix
  public final Matrix submatrix(int rowToRemove, int colToRemove) {
    if (rowToRemove + 1 > height || rowToRemove < 0 || colToRemove + 1 > width || colToRemove < 0) {
      throw new ArithmeticException("Cannot delete row/column outside the bounds of this matrix");
    }

    Double[][] newData = new Double[width - 1][height - 1];

    int oldRowOffset = 0;
    for (int row = 0; row < height - 1; row++) {
      if (row == rowToRemove) {
        oldRowOffset = 1;
      }

      int oldColOffset = 0;
      for (int col = 0; col < width - 1; col++) {
        if (col == colToRemove) {
          oldColOffset = 1;
        }

        Double thisCell = this.get(row + oldRowOffset, col + oldColOffset);
        newData[row][col] = thisCell;
      }
    }

    return new Matrix(newData);
  }

  // Determinante der Untermatrix
  public final Double minor(int row, int col) {
    return submatrix(row, col).getDeterminant();
  }

  // Kofaktoren für Berechnung der Determinante
  public final Double cofactor(int row, int col) {
    Double minor = minor(row, col);

    if ((row + col) % 2 == 0) {
      return minor;
    } else {
      return -minor;
    }
  }

  public final boolean isInvertible() {
    return getDeterminant() != 0;
  }

  // Matrix invertieren
  public final Matrix invert() {
    if (!isInvertible()) {
      throw new ArithmeticException("This matrix cannot be inverted");
    }

    if(inverse != null) return inverse;

    double determinant = getDeterminant();

    Double[][] newData = new Double[width][height];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        double cofac = cofactor(row, col);

        double inverted = cofac / determinant;
        newData[col][row] = inverted;
      }
    }

    inverse = new Matrix(newData);

    return inverse;
  }

  // Einheitsmatrix berechnen
  public static Matrix getIdentity() {
      return IDENTITY4.clone();
  }
  // protected Matrix getIdentity() {
  //   Double[][] data = new Double[height][width];

  //   for (int i = 0; i < height; i++) {
  //     for (int j = 0; j < width; j++) {
  //       if (i == j)
  //         data[i][j] = 1.0;
  //       else
  //         data[i][j] = 0.0;
  //     }
  //   }

  //   return new Matrix(data);
  // }

  // Matrizen multiplizieren
  public Matrix multiply(Matrix other) {
    Double[][] newData = new Double[width][height];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double data = 0;

        for (int i = 0; i < height; i++) {
          data += this.get(row, i) * other.get(i, col);
        }

        newData[row][col] = data;
      }
    }

    return new Matrix(newData);
  }

  // Matrix mit Vektor multiplizieren
  public Vector multiply(Vector v) {
    if (width < 2 || height < 2) {
        throw new ArithmeticException("Dimension of matrix has to be at least 3");
    }

    double x = get(0, 0) * v.x() + get(0, 1) * v.y() + get(0, 2) * v.z();
    double y = get(1, 0) * v.x() + get(1, 1) * v.y() + get(1, 2) * v.z();
    double z = get(2, 0) * v.x() + get(2, 1) * v.y() + get(2, 2) * v.z();

    return new Vector(x, y, z);
}

  // public Matrix multiply(Vector vector) {

  //   if (width != 3 || height != 3) {
  //     throw new ArithmeticException("Can only multiply a 3x3 matrix with a point");
  //   }

  //   Double[][] newData = new Double[width][height];
  //   int vectorComp = 1;

  //   for (int row = 0; row < height; row++) {
  //     for (int col = 0; col < width; col++) {
  //       double data = 0;

  //       if(vectorComp == 1)
  //         data = this.get(row, col) * vector.getX();
  //       else if(vectorComp == 2)
  //         data = this.get(row, col) * vector.getY();
  //       else if(vectorComp == 3)
  //         data = this.get(row, col) * vector.getZ();

  //       newData[row][col] = data;
  //     }

  //     vectorComp++;
  //   }

  //   return new Matrix(newData);
  // }

  // Matrix mit Punkt multiplizieren
  public Point multiply(Point p) {
    if (width < 3 || height < 3) {
        throw new ArithmeticException("Dimension of matrix has to be at least 3x3");
    }

    double x = p.x() * get(0, 0) + p.y() * get(0, 1) + p.z() * get(0, 2) + get(0, 3);
    double y = p.x() * get(1, 0) + p.y() * get(1, 1) + p.z() * get(1, 2) + get(1, 3);
    double z = p.x() * get(2, 0) + p.y() * get(2, 1) + p.z() * get(2, 2) + get(2, 3);

    return new Point(x, y, z);
}

  // Determinante berechnen
  public double getDeterminant() {
    if (height == 2)
      return get(0, 0) * get(1, 1) - get(1, 0) * get(0, 1);
    else
      return determinantForNon2XMatrices();
  }

  // Determinante für Matrizen die mind. 3x3 groß sind
  protected final double determinantForNon2XMatrices() {
    double total = 0d;

    for (int col = 0; col < width; col++) {
      double element = get(0, col);
      double cofactor = cofactor(0, col);
      total += (element * cofactor);
    }

    return total;
  }

  // Deep Copy von Matrix
  public final Matrix clone() {
    Double[][] newData = new Double[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        newData[col][row] = this.get(col, row);
      }
    }

    return new Matrix(newData);
  }

  public static Matrix shearing(double xy, double xz, double yx, double yz, double zx, double zy) {
    Matrix temp = getIdentity();
    temp.set(0, 1, xy);
    temp.set(0, 2, xz);
    temp.set(1, 0, yx);
    temp.set(1, 2, yz);
    temp.set(2, 0, zx);
    temp.set(2, 1, zy);
    return temp;
  }

  public static Matrix rotationX(double radians) {
    Matrix temp = getIdentity();
    temp.set(1, 1, Math.cos(radians));
    temp.set(1, 2, -Math.sin(radians));
    temp.set(2, 1, Math.sin(radians));
    temp.set(2, 2, Math.cos(radians));
    return temp;
  }

  public static Matrix rotationY(double radians) {
    Matrix temp = getIdentity();
    temp.set(0, 0, Math.cos(radians));
    temp.set(0, 2, Math.sin(radians));
    temp.set(2, 0, -Math.sin(radians));
    temp.set(2, 2, Math.cos(radians));
    return temp;
  }

  public static Matrix rotationZ(double radians) {
    Matrix temp = getIdentity();
    temp.set(0, 0, Math.cos(radians));
    temp.set(0, 1, -Math.sin(radians));
    temp.set(1, 0, Math.sin(radians));
    temp.set(1, 1, Math.cos(radians));
    return temp;
  }

  public static Matrix translation(double x, double y, double z) {
    Matrix temp = getIdentity();
    temp.set(0, 3, x);
    temp.set(1, 3, y);
    temp.set(2, 3, z);
    return temp;
  }

  public static Matrix scaling(double x, double y, double z) {
    Matrix temp = getIdentity();
    temp.set(0, 0, x);
    temp.set(1, 1, y);
    temp.set(2, 2, z);
    return temp;
  }

  public static Matrix scaling(double factor) {
    Matrix temp = getIdentity();
    temp.set(0, 0, factor);
    temp.set(1, 1, factor);
    temp.set(2, 2, factor);
    return temp;
  }

  public static Matrix viewTransform(Point position, Point lookAt, Vector up) {
    Vector n = position.subtract(lookAt).normalized();
    Vector u = up.normalized().cross(n);
    Vector v = n.cross(u);

    Double[][] data = {
      {u.x(), u.y(), u.z(), 0.0},
      {v.x(), v.y(), v.z(), 0.0},
      {n.x(), n.y(), n.z(), 0.0},
      {0.0, 0.0, 0.0, 1.0}
    };

    Matrix orientation = new Matrix(data);

    return orientation.multiply(Matrix.translation(-position.x(), -position.y(), -position.z()));
  }

  // Matrix als String ausgeben
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int col = 0; col < height; col++) {
      sb.append("{");
      for (int row = 0; row < width; row++) {
        sb.append(get(col, row));

        if (row + 1 < width) {
          sb.append(",");
        }
      }

      sb.append("}");
      if (col + 1 < height) {
        sb.append("\n");
      }
    }

    return sb.toString();
  }
}