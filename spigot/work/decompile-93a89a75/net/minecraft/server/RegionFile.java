package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import javax.annotation.Nullable;

public class RegionFile implements AutoCloseable {

    private static final byte[] a = new byte[4096];
    private final RandomAccessFile b;
    private final int[] c = new int[1024];
    private final int[] d = new int[1024];
    private final List<Boolean> e;

    public RegionFile(File file) throws IOException {
        this.b = new RandomAccessFile(file, "rw");
        if (this.b.length() < 4096L) {
            this.b.write(RegionFile.a);
            this.b.write(RegionFile.a);
        }

        int i;

        if ((this.b.length() & 4095L) != 0L) {
            for (i = 0; (long) i < (this.b.length() & 4095L); ++i) {
                this.b.write(0);
            }
        }

        i = (int) this.b.length() / 4096;
        this.e = Lists.newArrayListWithCapacity(i);

        int j;

        for (j = 0; j < i; ++j) {
            this.e.add(true);
        }

        this.e.set(0, false);
        this.e.set(1, false);
        this.b.seek(0L);

        int k;

        for (j = 0; j < 1024; ++j) {
            k = this.b.readInt();
            this.c[j] = k;
            if (k != 0 && (k >> 8) + (k & 255) <= this.e.size()) {
                for (int l = 0; l < (k & 255); ++l) {
                    this.e.set((k >> 8) + l, false);
                }
            }
        }

        for (j = 0; j < 1024; ++j) {
            k = this.b.readInt();
            this.d[j] = k;
        }

    }

    @Nullable
    public synchronized DataInputStream a(ChunkCoordIntPair chunkcoordintpair) throws IOException {
        int i = this.getOffset(chunkcoordintpair);

        if (i == 0) {
            return null;
        } else {
            int j = i >> 8;
            int k = i & 255;

            if (j + k > this.e.size()) {
                return null;
            } else {
                this.b.seek((long) (j * 4096));
                int l = this.b.readInt();

                if (l > 4096 * k) {
                    return null;
                } else if (l <= 0) {
                    return null;
                } else {
                    byte b0 = this.b.readByte();
                    byte[] abyte;

                    if (b0 == 1) {
                        abyte = new byte[l - 1];
                        this.b.read(abyte);
                        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte))));
                    } else if (b0 == 2) {
                        abyte = new byte[l - 1];
                        this.b.read(abyte);
                        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(abyte))));
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public boolean b(ChunkCoordIntPair chunkcoordintpair) {
        int i = this.getOffset(chunkcoordintpair);

        if (i == 0) {
            return false;
        } else {
            int j = i >> 8;
            int k = i & 255;

            if (j + k > this.e.size()) {
                return false;
            } else {
                try {
                    this.b.seek((long) (j * 4096));
                    int l = this.b.readInt();

                    return l > 4096 * k ? false : l > 0;
                } catch (IOException ioexception) {
                    return false;
                }
            }
        }
    }

    public DataOutputStream c(ChunkCoordIntPair chunkcoordintpair) {
        return new DataOutputStream(new BufferedOutputStream(new DeflaterOutputStream(new RegionFile.ChunkBuffer(chunkcoordintpair))));
    }

    protected synchronized void a(ChunkCoordIntPair chunkcoordintpair, byte[] abyte, int i) throws IOException {
        int j = this.getOffset(chunkcoordintpair);
        int k = j >> 8;
        int l = j & 255;
        int i1 = (i + 5) / 4096 + 1;

        if (i1 >= 256) {
            throw new RuntimeException(String.format("Too big to save, %d > 1048576", i));
        } else {
            if (k != 0 && l == i1) {
                this.a(k, abyte, i);
            } else {
                int j1;

                for (j1 = 0; j1 < l; ++j1) {
                    this.e.set(k + j1, true);
                }

                j1 = this.e.indexOf(true);
                int k1 = 0;
                int l1;

                if (j1 != -1) {
                    for (l1 = j1; l1 < this.e.size(); ++l1) {
                        if (k1 != 0) {
                            if ((Boolean) this.e.get(l1)) {
                                ++k1;
                            } else {
                                k1 = 0;
                            }
                        } else if ((Boolean) this.e.get(l1)) {
                            j1 = l1;
                            k1 = 1;
                        }

                        if (k1 >= i1) {
                            break;
                        }
                    }
                }

                if (k1 >= i1) {
                    k = j1;
                    this.a(chunkcoordintpair, j1 << 8 | i1);

                    for (l1 = 0; l1 < i1; ++l1) {
                        this.e.set(k + l1, false);
                    }

                    this.a(k, abyte, i);
                } else {
                    this.b.seek(this.b.length());
                    k = this.e.size();

                    for (l1 = 0; l1 < i1; ++l1) {
                        this.b.write(RegionFile.a);
                        this.e.add(false);
                    }

                    this.a(k, abyte, i);
                    this.a(chunkcoordintpair, k << 8 | i1);
                }
            }

            this.b(chunkcoordintpair, (int) (SystemUtils.getTimeMillis() / 1000L));
        }
    }

    private void a(int i, byte[] abyte, int j) throws IOException {
        this.b.seek((long) (i * 4096));
        this.b.writeInt(j + 1);
        this.b.writeByte(2);
        this.b.write(abyte, 0, j);
    }

    private int getOffset(ChunkCoordIntPair chunkcoordintpair) {
        return this.c[this.f(chunkcoordintpair)];
    }

    public boolean d(ChunkCoordIntPair chunkcoordintpair) {
        return this.getOffset(chunkcoordintpair) != 0;
    }

    private void a(ChunkCoordIntPair chunkcoordintpair, int i) throws IOException {
        int j = this.f(chunkcoordintpair);

        this.c[j] = i;
        this.b.seek((long) (j * 4));
        this.b.writeInt(i);
    }

    private int f(ChunkCoordIntPair chunkcoordintpair) {
        return chunkcoordintpair.j() + chunkcoordintpair.k() * 32;
    }

    private void b(ChunkCoordIntPair chunkcoordintpair, int i) throws IOException {
        int j = this.f(chunkcoordintpair);

        this.d[j] = i;
        this.b.seek((long) (4096 + j * 4));
        this.b.writeInt(i);
    }

    public void close() throws IOException {
        this.b.close();
    }

    class ChunkBuffer extends ByteArrayOutputStream {

        private final ChunkCoordIntPair b;

        public ChunkBuffer(ChunkCoordIntPair chunkcoordintpair) {
            super(8096);
            this.b = chunkcoordintpair;
        }

        public void close() throws IOException {
            RegionFile.this.a(this.b, this.buf, this.count);
        }
    }
}
