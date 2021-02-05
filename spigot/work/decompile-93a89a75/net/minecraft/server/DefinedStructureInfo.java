package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public class DefinedStructureInfo {

    private EnumBlockMirror a;
    private EnumBlockRotation b;
    private BlockPosition c;
    private boolean d;
    @Nullable
    private ChunkCoordIntPair e;
    @Nullable
    private StructureBoundingBox f;
    private boolean g;
    @Nullable
    private Random h;
    @Nullable
    private Integer i;
    private int j;
    private final List<DefinedStructureProcessor> k;
    private boolean l;

    public DefinedStructureInfo() {
        this.a = EnumBlockMirror.NONE;
        this.b = EnumBlockRotation.NONE;
        this.c = BlockPosition.ZERO;
        this.g = true;
        this.k = Lists.newArrayList();
    }

    public DefinedStructureInfo a() {
        DefinedStructureInfo definedstructureinfo = new DefinedStructureInfo();

        definedstructureinfo.a = this.a;
        definedstructureinfo.b = this.b;
        definedstructureinfo.c = this.c;
        definedstructureinfo.d = this.d;
        definedstructureinfo.e = this.e;
        definedstructureinfo.f = this.f;
        definedstructureinfo.g = this.g;
        definedstructureinfo.h = this.h;
        definedstructureinfo.i = this.i;
        definedstructureinfo.j = this.j;
        definedstructureinfo.k.addAll(this.k);
        definedstructureinfo.l = this.l;
        return definedstructureinfo;
    }

    public DefinedStructureInfo a(EnumBlockMirror enumblockmirror) {
        this.a = enumblockmirror;
        return this;
    }

    public DefinedStructureInfo a(EnumBlockRotation enumblockrotation) {
        this.b = enumblockrotation;
        return this;
    }

    public DefinedStructureInfo a(BlockPosition blockposition) {
        this.c = blockposition;
        return this;
    }

    public DefinedStructureInfo a(boolean flag) {
        this.d = flag;
        return this;
    }

    public DefinedStructureInfo a(ChunkCoordIntPair chunkcoordintpair) {
        this.e = chunkcoordintpair;
        return this;
    }

    public DefinedStructureInfo a(StructureBoundingBox structureboundingbox) {
        this.f = structureboundingbox;
        return this;
    }

    public DefinedStructureInfo a(@Nullable Random random) {
        this.h = random;
        return this;
    }

    public DefinedStructureInfo c(boolean flag) {
        this.l = flag;
        return this;
    }

    public DefinedStructureInfo b() {
        this.k.clear();
        return this;
    }

    public DefinedStructureInfo a(DefinedStructureProcessor definedstructureprocessor) {
        this.k.add(definedstructureprocessor);
        return this;
    }

    public DefinedStructureInfo b(DefinedStructureProcessor definedstructureprocessor) {
        this.k.remove(definedstructureprocessor);
        return this;
    }

    public EnumBlockMirror c() {
        return this.a;
    }

    public EnumBlockRotation d() {
        return this.b;
    }

    public BlockPosition e() {
        return this.c;
    }

    public Random b(@Nullable BlockPosition blockposition) {
        return this.h != null ? this.h : (blockposition == null ? new Random(SystemUtils.getMonotonicMillis()) : new Random(MathHelper.a((BaseBlockPosition) blockposition)));
    }

    public boolean g() {
        return this.d;
    }

    @Nullable
    public StructureBoundingBox h() {
        if (this.f == null && this.e != null) {
            this.k();
        }

        return this.f;
    }

    public boolean i() {
        return this.l;
    }

    public List<DefinedStructureProcessor> j() {
        return this.k;
    }

    void k() {
        if (this.e != null) {
            this.f = this.b(this.e);
        }

    }

    public boolean l() {
        return this.g;
    }

    public List<DefinedStructure.BlockInfo> a(List<List<DefinedStructure.BlockInfo>> list, @Nullable BlockPosition blockposition) {
        this.i = 8;
        if (this.i != null && this.i >= 0 && this.i < list.size()) {
            return (List) list.get(this.i);
        } else {
            this.i = this.b(blockposition).nextInt(list.size());
            return (List) list.get(this.i);
        }
    }

    @Nullable
    private StructureBoundingBox b(@Nullable ChunkCoordIntPair chunkcoordintpair) {
        if (chunkcoordintpair == null) {
            return this.f;
        } else {
            int i = chunkcoordintpair.x * 16;
            int j = chunkcoordintpair.z * 16;

            return new StructureBoundingBox(i, 0, j, i + 16 - 1, 255, j + 16 - 1);
        }
    }
}
