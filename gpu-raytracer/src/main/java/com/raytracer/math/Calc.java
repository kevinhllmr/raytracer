package com.raytracer.math;

public class Calc {
  public static final double EPSILON = 0.00001d;

  public static boolean approxEqual(double a, double b) {
    return (Math.abs(a - b) < EPSILON);
  }

  public static <T extends Comparable<T>> T clamp(T value, T min, T max) {
    if (value.compareTo(min) < 0) {
        return min;
    } else if (value.compareTo(max) > 0) {
        return max;
    } else {
        return value;
    }
}
}