package de.bergwerklabs.framework.commons.math;

/**
 * Created by Yannic Rieger on 21.07.2017.
 *
 * <p>A collection of less costly, but also less accurate square root algorithms.
 *
 * @author Yannic Rieger
 */
public final class SQRT {
  /**
   * Very inaccurate square root calculation. About 5x as fast as {@link java.lang.Math#sqrt(double)
   * Math.sqrt()}.
   *
   * @param d Squared value
   * @return Square root
   */
  public static double fastest(double d) {
    return Double.longBitsToDouble(((Double.doubleToLongBits(d) - (1L << 52)) >> 1) + (1L << 61));
  }

  /**
   * Inaccurate square root calculation. About 1.5x as fast as {@link java.lang.Math#sqrt(double)
   * Math.sqrt()}. Should be optimal for less important vector math.
   *
   * @param d Squared value
   * @return Square root
   */
  public static double fast(double d) {
    double s =
        Double.longBitsToDouble(((Double.doubleToLongBits(d) - (1L << 52)) >> 1) + (1L << 61));
    s = (s + d / s) / 2.0D; // Newton's
    return s;
  }
}
