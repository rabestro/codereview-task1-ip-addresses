package lv.id.jc.ipcounter.collector;

import java.util.Arrays;

public class LongArrayContainer implements IntContainer {
    private static final int VALUE_SIZE = 6;
    private static final int INDEX_MASK = 0xFFFFFFC0;
    private static final int VALUE_MASK = 0x3F;
    private static final int STORAGE_SIZE = 1 << (Integer.SIZE - VALUE_SIZE + 1);

    private final long[] storage = new long[STORAGE_SIZE];

    @Override
    public void add(int number) {
        int index = (number & INDEX_MASK) >>> VALUE_SIZE;
        storage[index] |= 1L << (number & VALUE_MASK);
    }

    @Override
    public long countUnique() {
        return Arrays.stream(storage).map(Long::bitCount).sum();
    }
}