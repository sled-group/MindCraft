package net.minecraft.server;

public class WorldMapFrame {

    private final BlockPosition a;
    private final int b;
    private final int c;

    public WorldMapFrame(BlockPosition blockposition, int i, int j) {
        this.a = blockposition;
        this.b = i;
        this.c = j;
    }

    public static WorldMapFrame a(NBTTagCompound nbttagcompound) {
        BlockPosition blockposition = GameProfileSerializer.c(nbttagcompound.getCompound("Pos"));
        int i = nbttagcompound.getInt("Rotation");
        int j = nbttagcompound.getInt("EntityId");

        return new WorldMapFrame(blockposition, i, j);
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.set("Pos", GameProfileSerializer.a(this.a));
        nbttagcompound.setInt("Rotation", this.b);
        nbttagcompound.setInt("EntityId", this.c);
        return nbttagcompound;
    }

    public BlockPosition b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public String e() {
        return a(this.a);
    }

    public static String a(BlockPosition blockposition) {
        return "frame-" + blockposition.getX() + "," + blockposition.getY() + "," + blockposition.getZ();
    }
}
