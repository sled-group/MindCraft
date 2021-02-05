package net.minecraft.server;

import javax.annotation.Nullable;

public class TileEntityJigsaw extends TileEntity {

    private MinecraftKey a;
    private MinecraftKey b;
    private String c;

    public TileEntityJigsaw(TileEntityTypes<?> tileentitytypes) {
        super(tileentitytypes);
        this.a = new MinecraftKey("empty");
        this.b = new MinecraftKey("empty");
        this.c = "minecraft:air";
    }

    public TileEntityJigsaw() {
        this(TileEntityTypes.JIGSAW);
    }

    public void a(MinecraftKey minecraftkey) {
        this.a = minecraftkey;
    }

    public void b(MinecraftKey minecraftkey) {
        this.b = minecraftkey;
    }

    public void a(String s) {
        this.c = s;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        nbttagcompound.setString("attachement_type", this.a.toString());
        nbttagcompound.setString("target_pool", this.b.toString());
        nbttagcompound.setString("final_state", this.c);
        return nbttagcompound;
    }

    @Override
    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.a = new MinecraftKey(nbttagcompound.getString("attachement_type"));
        this.b = new MinecraftKey(nbttagcompound.getString("target_pool"));
        this.c = nbttagcompound.getString("final_state");
    }

    @Nullable
    @Override
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return new PacketPlayOutTileEntityData(this.position, 12, this.b());
    }

    @Override
    public NBTTagCompound b() {
        return this.save(new NBTTagCompound());
    }
}
