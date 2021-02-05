package net.minecraft.server;

import com.mojang.datafixers.DataFixer;
import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import javax.annotation.Nullable;

public class IChunkLoader extends RegionFileCache {

    protected final DataFixer b;
    @Nullable
    private PersistentStructureLegacy a;

    public IChunkLoader(File file, DataFixer datafixer) {
        super(file);
        this.b = datafixer;
    }

    // CraftBukkit start
    private boolean check(ChunkProviderServer cps, int x, int z) throws IOException {
        ChunkCoordIntPair pos = new ChunkCoordIntPair(x, z);
        if (cps != null) {
            com.google.common.base.Preconditions.checkState(org.bukkit.Bukkit.isPrimaryThread(), "primary thread");
            if (cps.isLoaded(x, z)) {
                return true;
            }
        }

        if (this.chunkExists(pos)) {
            NBTTagCompound nbt = read(pos);
            if (nbt != null) {
                NBTTagCompound level = nbt.getCompound("Level");
                if (level.getBoolean("TerrainPopulated")) {
                    return true;
                }

                ChunkStatus status = ChunkStatus.a(level.getString("Status"));
                if (status != null && status.b(ChunkStatus.FEATURES)) {
                    return true;
                }
            }
        }

        return false;
    }
    // CraftBukkit end

    public NBTTagCompound getChunkData(DimensionManager dimensionmanager, Supplier<WorldPersistentData> supplier, NBTTagCompound nbttagcompound, ChunkCoordIntPair pos, @Nullable GeneratorAccess generatoraccess) throws IOException {
        int i = a(nbttagcompound);
        boolean flag = true;

        // CraftBukkit start
        if (i < 1466) {
            NBTTagCompound level = nbttagcompound.getCompound("Level");
            if (level.getBoolean("TerrainPopulated") && !level.getBoolean("LightPopulated")) {
                ChunkProviderServer cps = (generatoraccess == null) ? null : ((WorldServer) generatoraccess).getChunkProvider();
                if (check(cps, pos.x - 1, pos.z) && check(cps, pos.x - 1, pos.z - 1) && check(cps, pos.x, pos.z - 1)) {
                    level.setBoolean("LightPopulated", true);
                }
            }
        }
        // CraftBukkit end

        if (i < 1493) {
            nbttagcompound = GameProfileSerializer.a(this.b, DataFixTypes.CHUNK, nbttagcompound, i, 1493);
            if (nbttagcompound.getCompound("Level").getBoolean("hasLegacyStructureData")) {
                if (this.a == null) {
                    this.a = PersistentStructureLegacy.a(dimensionmanager.getType(), (WorldPersistentData) supplier.get()); // CraftBukkit - getType
                }

                nbttagcompound = this.a.a(nbttagcompound);
            }
        }

        nbttagcompound = GameProfileSerializer.a(this.b, DataFixTypes.CHUNK, nbttagcompound, Math.max(1493, i));
        if (i < SharedConstants.a().getWorldVersion()) {
            nbttagcompound.setInt("DataVersion", SharedConstants.a().getWorldVersion());
        }

        return nbttagcompound;
    }

    public static int a(NBTTagCompound nbttagcompound) {
        return nbttagcompound.hasKeyOfType("DataVersion", 99) ? nbttagcompound.getInt("DataVersion") : -1;
    }

    @Override
    public void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
        super.write(chunkcoordintpair, nbttagcompound);
        if (this.a != null) {
            this.a.a(chunkcoordintpair.pair());
        }

    }
}
