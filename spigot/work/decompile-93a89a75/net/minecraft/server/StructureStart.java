package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructureStart {

    public static final StructureStart a = new StructureStart(WorldGenerator.MINESHAFT, 0, 0, Biomes.PLAINS, StructureBoundingBox.a(), 0, 0L) {
        @Override
        public void a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, int i, int j, BiomeBase biomebase) {}
    };
    private final StructureGenerator<?> e;
    protected final List<StructurePiece> b = Lists.newArrayList();
    protected StructureBoundingBox c;
    private final int f;
    private final int g;
    private final BiomeBase h;
    private int i;
    protected final SeededRandom d;

    public StructureStart(StructureGenerator<?> structuregenerator, int i, int j, BiomeBase biomebase, StructureBoundingBox structureboundingbox, int k, long l) {
        this.e = structuregenerator;
        this.f = i;
        this.g = j;
        this.i = k;
        this.h = biomebase;
        this.d = new SeededRandom();
        this.d.c(l, i, j);
        this.c = structureboundingbox;
    }

    public abstract void a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, int i, int j, BiomeBase biomebase);

    public StructureBoundingBox c() {
        return this.c;
    }

    public List<StructurePiece> d() {
        return this.b;
    }

    public void a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
        List list = this.b;

        synchronized (this.b) {
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                StructurePiece structurepiece = (StructurePiece) iterator.next();

                if (structurepiece.g().b(structureboundingbox) && !structurepiece.a(generatoraccess, random, structureboundingbox, chunkcoordintpair)) {
                    iterator.remove();
                }
            }

            this.b();
        }
    }

    protected void b() {
        this.c = StructureBoundingBox.a();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            this.c.c(structurepiece.g());
        }

    }

    public NBTTagCompound a(int i, int j) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (this.e()) {
            nbttagcompound.setString("id", IRegistry.STRUCTURE_FEATURE.getKey(this.k()).toString());
            nbttagcompound.setString("biome", IRegistry.BIOME.getKey(this.h).toString());
            nbttagcompound.setInt("ChunkX", i);
            nbttagcompound.setInt("ChunkZ", j);
            nbttagcompound.setInt("references", this.i);
            nbttagcompound.set("BB", this.c.g());
            NBTTagList nbttaglist = new NBTTagList();
            List list = this.b;

            synchronized (this.b) {
                Iterator iterator = this.b.iterator();

                while (iterator.hasNext()) {
                    StructurePiece structurepiece = (StructurePiece) iterator.next();

                    nbttaglist.add(structurepiece.f());
                }
            }

            nbttagcompound.set("Children", nbttaglist);
            return nbttagcompound;
        } else {
            nbttagcompound.setString("id", "INVALID");
            return nbttagcompound;
        }
    }

    protected void a(int i, Random random, int j) {
        int k = i - j;
        int l = this.c.d() + 1;

        if (l < k) {
            l += random.nextInt(k - l);
        }

        int i1 = l - this.c.e;

        this.c.a(0, i1, 0);
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            structurepiece.a(0, i1, 0);
        }

    }

    protected void a(Random random, int i, int j) {
        int k = j - i + 1 - this.c.d();
        int l;

        if (k > 1) {
            l = i + random.nextInt(k);
        } else {
            l = i;
        }

        int i1 = l - this.c.b;

        this.c.a(0, i1, 0);
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            StructurePiece structurepiece = (StructurePiece) iterator.next();

            structurepiece.a(0, i1, 0);
        }

    }

    public boolean e() {
        return !this.b.isEmpty();
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public BlockPosition a() {
        return new BlockPosition(this.f << 4, 0, this.g << 4);
    }

    public boolean h() {
        return this.i < this.j();
    }

    public void i() {
        ++this.i;
    }

    protected int j() {
        return 1;
    }

    public StructureGenerator<?> k() {
        return this.e;
    }
}
