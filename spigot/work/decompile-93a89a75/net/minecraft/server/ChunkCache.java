package net.minecraft.server;

import javax.annotation.Nullable;

public class ChunkCache implements IWorldReader {

    protected final int a;
    protected final int b;
    protected final IChunkAccess[][] c;
    protected boolean d;
    protected final World e;

    public ChunkCache(World world, BlockPosition blockposition, BlockPosition blockposition1) {
        this.e = world;
        this.a = blockposition.getX() >> 4;
        this.b = blockposition.getZ() >> 4;
        int i = blockposition1.getX() >> 4;
        int j = blockposition1.getZ() >> 4;

        this.c = new IChunkAccess[i - this.a + 1][j - this.b + 1];
        this.d = true;

        int k;
        int l;

        for (k = this.a; k <= i; ++k) {
            for (l = this.b; l <= j; ++l) {
                this.c[k - this.a][l - this.b] = world.getChunkAt(k, l, ChunkStatus.FULL, false);
            }
        }

        for (k = blockposition.getX() >> 4; k <= blockposition1.getX() >> 4; ++k) {
            for (l = blockposition.getZ() >> 4; l <= blockposition1.getZ() >> 4; ++l) {
                IChunkAccess ichunkaccess = this.c[k - this.a][l - this.b];

                if (ichunkaccess != null && !ichunkaccess.a(blockposition.getY(), blockposition1.getY())) {
                    this.d = false;
                    return;
                }
            }
        }

    }

    @Override
    public int getLightLevel(BlockPosition blockposition, int i) {
        return this.e.getLightLevel(blockposition, i);
    }

    @Nullable
    @Override
    public IChunkAccess getChunkAt(int i, int j, ChunkStatus chunkstatus, boolean flag) {
        int k = i - this.a;
        int l = j - this.b;

        if (k >= 0 && k < this.c.length && l >= 0 && l < this.c[k].length) {
            IChunkAccess ichunkaccess = this.c[k][l];

            return (IChunkAccess) (ichunkaccess != null ? ichunkaccess : new ChunkEmpty(this.e, new ChunkCoordIntPair(i, j)));
        } else {
            return new ChunkEmpty(this.e, new ChunkCoordIntPair(i, j));
        }
    }

    @Override
    public boolean isChunkLoaded(int i, int j) {
        int k = i - this.a;
        int l = j - this.b;

        return k >= 0 && k < this.c.length && l >= 0 && l < this.c[k].length;
    }

    @Override
    public BlockPosition getHighestBlockYAt(HeightMap.Type heightmap_type, BlockPosition blockposition) {
        return this.e.getHighestBlockYAt(heightmap_type, blockposition);
    }

    @Override
    public int a(HeightMap.Type heightmap_type, int i, int j) {
        return this.e.a(heightmap_type, i, j);
    }

    @Override
    public int c() {
        return this.e.c();
    }

    @Override
    public WorldBorder getWorldBorder() {
        return this.e.getWorldBorder();
    }

    @Override
    public boolean a(@Nullable Entity entity, VoxelShape voxelshape) {
        return true;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public int getSeaLevel() {
        return this.e.getSeaLevel();
    }

    @Override
    public WorldProvider getWorldProvider() {
        return this.e.getWorldProvider();
    }

    @Nullable
    @Override
    public TileEntity getTileEntity(BlockPosition blockposition) {
        IChunkAccess ichunkaccess = this.w(blockposition);

        return ichunkaccess.getTileEntity(blockposition);
    }

    @Override
    public IBlockData getType(BlockPosition blockposition) {
        if (World.isOutsideWorld(blockposition)) {
            return Blocks.AIR.getBlockData();
        } else {
            IChunkAccess ichunkaccess = this.w(blockposition);

            return ichunkaccess.getType(blockposition);
        }
    }

    @Override
    public Fluid getFluid(BlockPosition blockposition) {
        if (World.isOutsideWorld(blockposition)) {
            return FluidTypes.EMPTY.i();
        } else {
            IChunkAccess ichunkaccess = this.w(blockposition);

            return ichunkaccess.getFluid(blockposition);
        }
    }

    @Override
    public BiomeBase getBiome(BlockPosition blockposition) {
        IChunkAccess ichunkaccess = this.w(blockposition);

        return ichunkaccess.getBiome(blockposition);
    }

    @Override
    public int getBrightness(EnumSkyBlock enumskyblock, BlockPosition blockposition) {
        return this.e.getBrightness(enumskyblock, blockposition);
    }
}
