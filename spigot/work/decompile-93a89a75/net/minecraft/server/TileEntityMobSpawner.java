package net.minecraft.server;

import javax.annotation.Nullable;

public class TileEntityMobSpawner extends TileEntity implements ITickable {

    private final MobSpawnerAbstract a = new MobSpawnerAbstract() {
        @Override
        public void a(int i) {
            TileEntityMobSpawner.this.world.playBlockAction(TileEntityMobSpawner.this.position, Blocks.SPAWNER, i, 0);
        }

        @Override
        public World a() {
            return TileEntityMobSpawner.this.world;
        }

        @Override
        public BlockPosition b() {
            return TileEntityMobSpawner.this.position;
        }

        @Override
        public void setSpawnData(MobSpawnerData mobspawnerdata) {
            super.setSpawnData(mobspawnerdata);
            if (this.a() != null) {
                IBlockData iblockdata = this.a().getType(this.b());

                this.a().notify(TileEntityMobSpawner.this.position, iblockdata, iblockdata, 4);
            }

        }
    };

    public TileEntityMobSpawner() {
        super(TileEntityTypes.MOB_SPAWNER);
    }

    @Override
    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    @Override
    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        this.a.b(nbttagcompound);
        return nbttagcompound;
    }

    @Override
    public void tick() {
        this.a.c();
    }

    @Nullable
    @Override
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return new PacketPlayOutTileEntityData(this.position, 1, this.b());
    }

    @Override
    public NBTTagCompound b() {
        NBTTagCompound nbttagcompound = this.save(new NBTTagCompound());

        nbttagcompound.remove("SpawnPotentials");
        return nbttagcompound;
    }

    @Override
    public boolean setProperty(int i, int j) {
        return this.a.b(i) ? true : super.setProperty(i, j);
    }

    @Override
    public boolean isFilteredNBT() {
        return true;
    }

    public MobSpawnerAbstract getSpawner() {
        return this.a;
    }
}
