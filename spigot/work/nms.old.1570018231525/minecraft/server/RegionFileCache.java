package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

public abstract class RegionFileCache implements AutoCloseable {

    public final Long2ObjectLinkedOpenHashMap<RegionFile> cache = new Long2ObjectLinkedOpenHashMap();
    private final File a;

    protected RegionFileCache(File file) {
        this.a = file;
    }

    private RegionFile a(ChunkCoordIntPair chunkcoordintpair, boolean existingOnly) throws IOException { // CraftBukkit
        long i = ChunkCoordIntPair.pair(chunkcoordintpair.getRegionX(), chunkcoordintpair.getRegionZ());
        RegionFile regionfile = (RegionFile) this.cache.getAndMoveToFirst(i);

        if (regionfile != null) {
            return regionfile;
        } else {
            if (this.cache.size() >= 256) {
                ((RegionFile) this.cache.removeLast()).close();
            }

            if (!this.a.exists()) {
                this.a.mkdirs();
            }

            File file = new File(this.a, "r." + chunkcoordintpair.getRegionX() + "." + chunkcoordintpair.getRegionZ() + ".mca");
            if (existingOnly && !file.exists()) return null; // CraftBukkit
            RegionFile regionfile1 = new RegionFile(file);

            this.cache.putAndMoveToFirst(i, regionfile1);
            return regionfile1;
        }
    }

    @Nullable
    public NBTTagCompound read(ChunkCoordIntPair chunkcoordintpair) throws IOException {
        RegionFile regionfile = this.a(chunkcoordintpair, false); // CraftBukkit
        DataInputStream datainputstream = regionfile.a(chunkcoordintpair);
        Throwable throwable = null;

        NBTTagCompound nbttagcompound;

        try {
            if (datainputstream != null) {
                nbttagcompound = NBTCompressedStreamTools.a(datainputstream);
                return nbttagcompound;
            }

            nbttagcompound = null;
        } catch (Throwable throwable1) {
            throwable = throwable1;
            throw throwable1;
        } finally {
            if (datainputstream != null) {
                if (throwable != null) {
                    try {
                        datainputstream.close();
                    } catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                } else {
                    datainputstream.close();
                }
            }

        }

        return nbttagcompound;
    }

    protected void write(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
        RegionFile regionfile = this.a(chunkcoordintpair, false); // CraftBukkit
        DataOutputStream dataoutputstream = regionfile.c(chunkcoordintpair);
        Throwable throwable = null;

        try {
            NBTCompressedStreamTools.a(nbttagcompound, (DataOutput) dataoutputstream);
        } catch (Throwable throwable1) {
            throwable = throwable1;
            throw throwable1;
        } finally {
            if (dataoutputstream != null) {
                if (throwable != null) {
                    try {
                        dataoutputstream.close();
                    } catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                } else {
                    dataoutputstream.close();
                }
            }

        }

    }

    public void close() throws IOException {
        ObjectIterator objectiterator = this.cache.values().iterator();

        while (objectiterator.hasNext()) {
            RegionFile regionfile = (RegionFile) objectiterator.next();

            regionfile.close();
        }

    }

    // CraftBukkit start
    public boolean chunkExists(ChunkCoordIntPair pos) throws IOException {
        RegionFile regionfile = a(pos, true);

        return regionfile != null ? regionfile.d(pos) : false;
    }
    // CraftBukkit end
}
