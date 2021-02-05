package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.BitSet;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class ProtoChunkExtension extends ProtoChunk {

    private final Chunk a;

    public ProtoChunkExtension(Chunk chunk) {
        super(chunk.getPos(), ChunkConverter.a);
        this.a = chunk;
    }

    @Nullable
    @Override
    public TileEntity getTileEntity(BlockPosition blockposition) {
        return this.a.getTileEntity(blockposition);
    }

    @Nullable
    @Override
    public IBlockData getType(BlockPosition blockposition) {
        return this.a.getType(blockposition);
    }

    @Override
    public Fluid getFluid(BlockPosition blockposition) {
        return this.a.getFluid(blockposition);
    }

    @Override
    public int H() {
        return this.a.H();
    }

    @Nullable
    @Override
    public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return null;
    }

    @Override
    public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {}

    @Override
    public void a(Entity entity) {}

    @Override
    public void a(ChunkStatus chunkstatus) {}

    @Override
    public ChunkSection[] getSections() {
        return this.a.getSections();
    }

    @Nullable
    @Override
    public LightEngine e() {
        return this.a.e();
    }

    @Override
    public void a(HeightMap.Type heightmap_type, long[] along) {}

    private HeightMap.Type d(HeightMap.Type heightmap_type) {
        return heightmap_type == HeightMap.Type.WORLD_SURFACE_WG ? HeightMap.Type.WORLD_SURFACE : (heightmap_type == HeightMap.Type.OCEAN_FLOOR_WG ? HeightMap.Type.OCEAN_FLOOR : heightmap_type);
    }

    @Override
    public int a(HeightMap.Type heightmap_type, int i, int j) {
        return this.a.a(this.d(heightmap_type), i, j);
    }

    @Override
    public ChunkCoordIntPair getPos() {
        return this.a.getPos();
    }

    @Override
    public void setLastSaved(long i) {}

    @Nullable
    @Override
    public StructureStart a(String s) {
        return this.a.a(s);
    }

    @Override
    public void a(String s, StructureStart structurestart) {}

    @Override
    public Map<String, StructureStart> h() {
        return this.a.h();
    }

    @Override
    public void a(Map<String, StructureStart> map) {}

    @Override
    public LongSet b(String s) {
        return this.a.b(s);
    }

    @Override
    public void a(String s, long i) {}

    @Override
    public Map<String, LongSet> v() {
        return this.a.v();
    }

    @Override
    public void b(Map<String, LongSet> map) {}

    @Override
    public BiomeBase[] getBiomeIndex() {
        return this.a.getBiomeIndex();
    }

    @Override
    public void setNeedsSaving(boolean flag) {}

    @Override
    public boolean isNeedsSaving() {
        return false;
    }

    @Override
    public ChunkStatus getChunkStatus() {
        return this.a.getChunkStatus();
    }

    @Override
    public void removeTileEntity(BlockPosition blockposition) {}

    @Override
    public void f(BlockPosition blockposition) {}

    @Override
    public void a(NBTTagCompound nbttagcompound) {}

    @Nullable
    @Override
    public NBTTagCompound i(BlockPosition blockposition) {
        return this.a.i(blockposition);
    }

    @Nullable
    @Override
    public NBTTagCompound j(BlockPosition blockposition) {
        return this.a.j(blockposition);
    }

    @Override
    public void a(BiomeBase[] abiomebase) {}

    @Override
    public Stream<BlockPosition> m() {
        return this.a.m();
    }

    @Override
    public ProtoChunkTickList<Block> n() {
        return new ProtoChunkTickList<>((block) -> {
            return block.getBlockData().isAir();
        }, this.getPos());
    }

    @Override
    public ProtoChunkTickList<FluidType> o() {
        return new ProtoChunkTickList<>((fluidtype) -> {
            return fluidtype == FluidTypes.EMPTY;
        }, this.getPos());
    }

    @Override
    public BitSet a(WorldGenStage.Features worldgenstage_features) {
        return this.a.a(worldgenstage_features);
    }

    public Chunk u() {
        return this.a;
    }

    @Override
    public boolean r() {
        return this.a.r();
    }

    @Override
    public void b(boolean flag) {
        this.a.b(flag);
    }
}
