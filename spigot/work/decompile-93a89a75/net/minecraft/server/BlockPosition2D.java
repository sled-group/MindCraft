package net.minecraft.server;

public class BlockPosition2D {

    public final int a;
    public final int b;

    public BlockPosition2D(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public BlockPosition2D(BlockPosition blockposition) {
        this.a = blockposition.getX();
        this.b = blockposition.getZ();
    }

    public long b() {
        return a(this.a, this.b);
    }

    public static long a(int i, int j) {
        return (long) i & 4294967295L | ((long) j & 4294967295L) << 32;
    }

    public String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }

    public int hashCode() {
        int i = 1664525 * this.a + 1013904223;
        int j = 1664525 * (this.b ^ -559038737) + 1013904223;

        return i ^ j;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof BlockPosition2D)) {
            return false;
        } else {
            BlockPosition2D blockposition2d = (BlockPosition2D) object;

            return this.a == blockposition2d.a && this.b == blockposition2d.b;
        }
    }
}
