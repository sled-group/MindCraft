package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class WorldGenFeaturePillagerOutpostPoolPiece extends StructurePiece {

    protected final WorldGenFeatureDefinedStructurePoolStructure a;
    protected BlockPosition b;
    private final int d;
    protected final EnumBlockRotation c;
    private final List<WorldGenFeatureDefinedStructureJigsawJunction> e = Lists.newArrayList();
    private final DefinedStructureManager f;

    public WorldGenFeaturePillagerOutpostPoolPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, DefinedStructureManager definedstructuremanager, WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure, BlockPosition blockposition, int i, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox) {
        super(worldgenfeaturestructurepiecetype, 0);
        this.f = definedstructuremanager;
        this.a = worldgenfeaturedefinedstructurepoolstructure;
        this.b = blockposition;
        this.d = i;
        this.c = enumblockrotation;
        this.n = structureboundingbox;
    }

    public WorldGenFeaturePillagerOutpostPoolPiece(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound, WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype) {
        super(worldgenfeaturestructurepiecetype, nbttagcompound);
        this.f = definedstructuremanager;
        this.b = new BlockPosition(nbttagcompound.getInt("PosX"), nbttagcompound.getInt("PosY"), nbttagcompound.getInt("PosZ"));
        this.d = nbttagcompound.getInt("ground_level_delta");
        this.a = (WorldGenFeatureDefinedStructurePoolStructure) DynamicDeserializer.a(new Dynamic(DynamicOpsNBT.a, nbttagcompound.getCompound("pool_element")), IRegistry.STRUCTURE_POOL_ELEMENT, "element_type", WorldGenFeatureDefinedStructurePoolEmpty.a);
        this.c = EnumBlockRotation.valueOf(nbttagcompound.getString("rotation"));
        this.n = this.a.a(definedstructuremanager, this.b, this.c);
        NBTTagList nbttaglist = nbttagcompound.getList("junctions", 10);

        this.e.clear();
        nbttaglist.forEach((nbtbase) -> {
            this.e.add(WorldGenFeatureDefinedStructureJigsawJunction.a(new Dynamic(DynamicOpsNBT.a, nbtbase)));
        });
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("PosX", this.b.getX());
        nbttagcompound.setInt("PosY", this.b.getY());
        nbttagcompound.setInt("PosZ", this.b.getZ());
        nbttagcompound.setInt("ground_level_delta", this.d);
        nbttagcompound.set("pool_element", (NBTBase) this.a.b(DynamicOpsNBT.a).getValue());
        nbttagcompound.setString("rotation", this.c.name());
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            WorldGenFeatureDefinedStructureJigsawJunction worldgenfeaturedefinedstructurejigsawjunction = (WorldGenFeatureDefinedStructureJigsawJunction) iterator.next();

            nbttaglist.add(worldgenfeaturedefinedstructurejigsawjunction.a((DynamicOps) DynamicOpsNBT.a).getValue());
        }

        nbttagcompound.set("junctions", nbttaglist);
    }

    @Override
    public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
        return this.a.a(this.f, generatoraccess, this.b, this.c, structureboundingbox, random);
    }

    @Override
    public void a(int i, int j, int k) {
        super.a(i, j, k);
        this.b = this.b.b(i, j, k);
    }

    @Override
    public EnumBlockRotation X_() {
        return this.c;
    }

    public String toString() {
        return String.format("<%s | %s | %s | %s>", this.getClass().getSimpleName(), this.b, this.c, this.a);
    }

    public WorldGenFeatureDefinedStructurePoolStructure b() {
        return this.a;
    }

    public BlockPosition c() {
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public void a(WorldGenFeatureDefinedStructureJigsawJunction worldgenfeaturedefinedstructurejigsawjunction) {
        this.e.add(worldgenfeaturedefinedstructurejigsawjunction);
    }

    public List<WorldGenFeatureDefinedStructureJigsawJunction> e() {
        return this.e;
    }
}
