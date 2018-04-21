package de.bergwerklabs.framework.commons.misc.bitshifter;

/**
 * A class allowing low level bit operations with long values.
 *
 * @author Benedikt Wüller
 */
public class LongBitshifter extends Bitshifter<Long> {

  public LongBitshifter(Object... entries) {
    this(0L, entries);
  }

  public LongBitshifter(Long initialFlags, Object... entries) {
    super(initialFlags, entries);
  }

  @Override
  public boolean isFlagSet(Object entry) {
    return (flags & getFlagByEntry(entry)) != 0;
  }

  @Override
  public void setFlag(Object entry) {
    flags |= getFlagByEntry(entry);
  }

  @Override
  public void unsetFlag(Object entry) {
    flags &= ~getFlagByEntry(entry);
  }

  @Override
  protected Long getFlag(int shift) {
    return 1L << shift;
  }

  @Override
  public int getMaxEntryCount() {
    return 63; // We do not want number overflow
  }
}
