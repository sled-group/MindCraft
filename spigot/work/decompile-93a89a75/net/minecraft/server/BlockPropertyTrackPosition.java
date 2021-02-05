package net.minecraft.server;

public enum BlockPropertyTrackPosition implements INamable {

    NORTH_SOUTH(0, "north_south"), EAST_WEST(1, "east_west"), ASCENDING_EAST(2, "ascending_east"), ASCENDING_WEST(3, "ascending_west"), ASCENDING_NORTH(4, "ascending_north"), ASCENDING_SOUTH(5, "ascending_south"), SOUTH_EAST(6, "south_east"), SOUTH_WEST(7, "south_west"), NORTH_WEST(8, "north_west"), NORTH_EAST(9, "north_east");

    private final int k;
    private final String l;

    private BlockPropertyTrackPosition(int i, String s) {
        this.k = i;
        this.l = s;
    }

    public int a() {
        return this.k;
    }

    public String toString() {
        return this.l;
    }

    public boolean c() {
        return this == BlockPropertyTrackPosition.ASCENDING_NORTH || this == BlockPropertyTrackPosition.ASCENDING_EAST || this == BlockPropertyTrackPosition.ASCENDING_SOUTH || this == BlockPropertyTrackPosition.ASCENDING_WEST;
    }

    @Override
    public String getName() {
        return this.l;
    }
}
