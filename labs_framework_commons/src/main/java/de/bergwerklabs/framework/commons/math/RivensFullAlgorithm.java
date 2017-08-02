package de.bergwerklabs.framework.commons.math;

/**
 * Created by Yannic Rieger on 21.07.2017.
 * <p> A very fast, but also more inaccurate algorithm for calculating sin and cos.
 *
 * @author Yannic Rieger
 */
public final class RivensFullAlgorithm {

    private static final float BF_SIN_TO_COS = (float) (Math.PI * 0.5f);
    private static final int BF_SIN_BITS = 12;
    private static final int BF_SIN_MASK = ~(-1 << BF_SIN_BITS);
    private static final int BF_SIN_COUNT = BF_SIN_MASK + 1;
    private static final float BF_radFull = (float) (Math.PI * 2.0);
    private static final float BF_radToIndex = BF_SIN_COUNT / BF_radFull;
    private static final float[] BF_sinFull = new float[BF_SIN_COUNT];

    static
    {
        for (int i = 0; i < BF_SIN_COUNT; i++)
        {
            BF_sinFull[i] = (float) Math.sin((i + Math.min(1, i % (BF_SIN_COUNT / 4)) * 0.5) / BF_SIN_COUNT * BF_radFull);
        }
    }

    public static float sin(float rad)
    {
        return BF_sinFull[(int)(rad * BF_radToIndex) & BF_SIN_MASK];
    }

    public static float cos(float rad)
    {
        return sin(rad + BF_SIN_TO_COS);
    }

}
