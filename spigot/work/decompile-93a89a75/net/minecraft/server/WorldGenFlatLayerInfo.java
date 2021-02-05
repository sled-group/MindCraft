package net.minecraft.server;

public class WorldGenFlatLayerInfo {

    private final IBlockData a;
    private final int b;
    private int c;

    public WorldGenFlatLayerInfo(int i, Block block) {
        this.b = i;
        this.a = block.getBlockData();
    }

    public int a() {
        return this.b;
    }

    public IBlockData b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String toString() {
        return (this.b != 1 ? this.b + "*" : "") + IRegistry.BLOCK.getKey(this.a.getBlock());
    }
}
