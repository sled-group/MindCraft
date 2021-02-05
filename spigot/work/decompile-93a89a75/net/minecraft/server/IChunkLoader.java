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

    public NBTTagCompound getChunkData(DimensionManager dimensionmanager, Supplier<WorldPersistentData> supplier, NBTTagCompound nbttagcompound) {
        int i = a(nbttagcompound);
        boolean flag = true;

        if (i < 1493) {
            nbttagcompound = GameProfileSerializer.a(this.b, DataFixTypes.CHUNK, nbttagcompound, i, 1493);
            if (nbttagcompound.getCompound("Level").getBoolean("hasLegacyStructureData")) {
                if (this.a == null) {
                    this.a = PersistentStructureLegacy.a(dimensionmanager, (WorldPersistentData) supplier.get());
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
