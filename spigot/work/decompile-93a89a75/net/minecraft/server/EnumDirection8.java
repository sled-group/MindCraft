package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Set;

public enum EnumDirection8 {

    NORTH(new EnumDirection[]{EnumDirection.NORTH}), NORTH_EAST(new EnumDirection[]{EnumDirection.NORTH, EnumDirection.EAST}), EAST(new EnumDirection[]{EnumDirection.EAST}), SOUTH_EAST(new EnumDirection[]{EnumDirection.SOUTH, EnumDirection.EAST}), SOUTH(new EnumDirection[]{EnumDirection.SOUTH}), SOUTH_WEST(new EnumDirection[]{EnumDirection.SOUTH, EnumDirection.WEST}), WEST(new EnumDirection[]{EnumDirection.WEST}), NORTH_WEST(new EnumDirection[]{EnumDirection.NORTH, EnumDirection.WEST});

    private static final int i = 1 << EnumDirection8.NORTH_WEST.ordinal();
    private static final int j = 1 << EnumDirection8.WEST.ordinal();
    private static final int k = 1 << EnumDirection8.SOUTH_WEST.ordinal();
    private static final int l = 1 << EnumDirection8.SOUTH.ordinal();
    private static final int m = 1 << EnumDirection8.SOUTH_EAST.ordinal();
    private static final int n = 1 << EnumDirection8.EAST.ordinal();
    private static final int o = 1 << EnumDirection8.NORTH_EAST.ordinal();
    private static final int p = 1 << EnumDirection8.NORTH.ordinal();
    private final Set<EnumDirection> q;

    private EnumDirection8(EnumDirection... aenumdirection) {
        this.q = Sets.immutableEnumSet(Arrays.asList(aenumdirection));
    }

    public Set<EnumDirection> a() {
        return this.q;
    }
}
