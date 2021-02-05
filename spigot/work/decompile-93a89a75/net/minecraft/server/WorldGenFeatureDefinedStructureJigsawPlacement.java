package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenFeatureDefinedStructureJigsawPlacement {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final WorldGenFeatureDefinedStructurePoolTemplates a = new WorldGenFeatureDefinedStructurePoolTemplates();

    public static void a(MinecraftKey minecraftkey, int i, WorldGenFeatureDefinedStructureJigsawPlacement.a worldgenfeaturedefinedstructurejigsawplacement_a, ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, BlockPosition blockposition, List<StructurePiece> list, Random random) {
        WorldGenFactory.a();
        new WorldGenFeatureDefinedStructureJigsawPlacement.c(minecraftkey, i, worldgenfeaturedefinedstructurejigsawplacement_a, chunkgenerator, definedstructuremanager, blockposition, list, random);
    }

    static {
        WorldGenFeatureDefinedStructureJigsawPlacement.a.a(WorldGenFeatureDefinedStructurePoolTemplate.a);
    }

    public interface a {

        WorldGenFeaturePillagerOutpostPoolPiece create(DefinedStructureManager definedstructuremanager, WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure, BlockPosition blockposition, int i, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox);
    }

    static final class c {

        private final int a;
        private final WorldGenFeatureDefinedStructureJigsawPlacement.a b;
        private final ChunkGenerator<?> c;
        private final DefinedStructureManager d;
        private final List<StructurePiece> e;
        private final Random f;
        private final Deque<WorldGenFeatureDefinedStructureJigsawPlacement.b> g = Queues.newArrayDeque();

        public c(MinecraftKey minecraftkey, int i, WorldGenFeatureDefinedStructureJigsawPlacement.a worldgenfeaturedefinedstructurejigsawplacement_a, ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, BlockPosition blockposition, List<StructurePiece> list, Random random) {
            this.a = i;
            this.b = worldgenfeaturedefinedstructurejigsawplacement_a;
            this.c = chunkgenerator;
            this.d = definedstructuremanager;
            this.e = list;
            this.f = random;
            EnumBlockRotation enumblockrotation = EnumBlockRotation.a(random);
            WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate = WorldGenFeatureDefinedStructureJigsawPlacement.a.a(minecraftkey);
            WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure = worldgenfeaturedefinedstructurepooltemplate.a(random);
            WorldGenFeaturePillagerOutpostPoolPiece worldgenfeaturepillageroutpostpoolpiece = worldgenfeaturedefinedstructurejigsawplacement_a.create(definedstructuremanager, worldgenfeaturedefinedstructurepoolstructure, blockposition, worldgenfeaturedefinedstructurepoolstructure.d(), enumblockrotation, worldgenfeaturedefinedstructurepoolstructure.a(definedstructuremanager, blockposition, enumblockrotation));
            StructureBoundingBox structureboundingbox = worldgenfeaturepillageroutpostpoolpiece.g();
            int j = (structureboundingbox.d + structureboundingbox.a) / 2;
            int k = (structureboundingbox.f + structureboundingbox.c) / 2;
            int l = chunkgenerator.b(j, k, HeightMap.Type.WORLD_SURFACE_WG);

            worldgenfeaturepillageroutpostpoolpiece.a(0, l - (structureboundingbox.b + worldgenfeaturepillageroutpostpoolpiece.d()), 0);
            list.add(worldgenfeaturepillageroutpostpoolpiece);
            if (i > 0) {
                boolean flag = true;
                AxisAlignedBB axisalignedbb = new AxisAlignedBB((double) (j - 80), (double) (l - 80), (double) (k - 80), (double) (j + 80 + 1), (double) (l + 80 + 1), (double) (k + 80 + 1));

                this.g.addLast(new WorldGenFeatureDefinedStructureJigsawPlacement.b(worldgenfeaturepillageroutpostpoolpiece, new AtomicReference(VoxelShapes.a(VoxelShapes.a(axisalignedbb), VoxelShapes.a(AxisAlignedBB.a(structureboundingbox)), OperatorBoolean.ONLY_FIRST)), l + 80, 0));

                while (!this.g.isEmpty()) {
                    WorldGenFeatureDefinedStructureJigsawPlacement.b worldgenfeaturedefinedstructurejigsawplacement_b = (WorldGenFeatureDefinedStructureJigsawPlacement.b) this.g.removeFirst();

                    this.a(worldgenfeaturedefinedstructurejigsawplacement_b.a, worldgenfeaturedefinedstructurejigsawplacement_b.b, worldgenfeaturedefinedstructurejigsawplacement_b.c, worldgenfeaturedefinedstructurejigsawplacement_b.d);
                }

            }
        }

        private void a(WorldGenFeaturePillagerOutpostPoolPiece worldgenfeaturepillageroutpostpoolpiece, AtomicReference<VoxelShape> atomicreference, int i, int j) {
            WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure = worldgenfeaturepillageroutpostpoolpiece.b();
            BlockPosition blockposition = worldgenfeaturepillageroutpostpoolpiece.c();
            EnumBlockRotation enumblockrotation = worldgenfeaturepillageroutpostpoolpiece.X_();
            WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching = worldgenfeaturedefinedstructurepoolstructure.c();
            boolean flag = worldgenfeaturedefinedstructurepooltemplate_matching == WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID;
            AtomicReference<VoxelShape> atomicreference1 = new AtomicReference();
            StructureBoundingBox structureboundingbox = worldgenfeaturepillageroutpostpoolpiece.g();
            int k = structureboundingbox.b;
            Iterator iterator = worldgenfeaturedefinedstructurepoolstructure.a(this.d, blockposition, enumblockrotation, this.f).iterator();

            label117:
            while (iterator.hasNext()) {
                DefinedStructure.BlockInfo definedstructure_blockinfo = (DefinedStructure.BlockInfo) iterator.next();
                EnumDirection enumdirection = (EnumDirection) definedstructure_blockinfo.b.get(BlockJigsaw.FACING);
                BlockPosition blockposition1 = definedstructure_blockinfo.a;
                BlockPosition blockposition2 = blockposition1.shift(enumdirection);
                int l = blockposition1.getY() - k;
                int i1 = -1;
                WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate = WorldGenFeatureDefinedStructureJigsawPlacement.a.a(new MinecraftKey(definedstructure_blockinfo.c.getString("target_pool")));
                WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate1 = WorldGenFeatureDefinedStructureJigsawPlacement.a.a(worldgenfeaturedefinedstructurepooltemplate.a());

                if (worldgenfeaturedefinedstructurepooltemplate != WorldGenFeatureDefinedStructurePoolTemplate.b && (worldgenfeaturedefinedstructurepooltemplate.c() != 0 || worldgenfeaturedefinedstructurepooltemplate == WorldGenFeatureDefinedStructurePoolTemplate.a)) {
                    boolean flag1 = structureboundingbox.b((BaseBlockPosition) blockposition2);
                    AtomicReference atomicreference2;
                    int j1;

                    if (flag1) {
                        atomicreference2 = atomicreference1;
                        j1 = k;
                        if (atomicreference1.get() == null) {
                            atomicreference1.set(VoxelShapes.a(AxisAlignedBB.a(structureboundingbox)));
                        }
                    } else {
                        atomicreference2 = atomicreference;
                        j1 = i;
                    }

                    List<WorldGenFeatureDefinedStructurePoolStructure> list = Lists.newArrayList();

                    if (j != this.a) {
                        list.addAll(worldgenfeaturedefinedstructurepooltemplate.b(this.f));
                    }

                    list.addAll(worldgenfeaturedefinedstructurepooltemplate1.b(this.f));
                    Iterator iterator1 = list.iterator();

                    while (iterator1.hasNext()) {
                        WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure1 = (WorldGenFeatureDefinedStructurePoolStructure) iterator1.next();

                        if (worldgenfeaturedefinedstructurepoolstructure1 == WorldGenFeatureDefinedStructurePoolEmpty.a) {
                            break;
                        }

                        Iterator iterator2 = EnumBlockRotation.b(this.f).iterator();

                        while (iterator2.hasNext()) {
                            EnumBlockRotation enumblockrotation1 = (EnumBlockRotation) iterator2.next();
                            List<DefinedStructure.BlockInfo> list1 = worldgenfeaturedefinedstructurepoolstructure1.a(this.d, BlockPosition.ZERO, enumblockrotation1, this.f);
                            StructureBoundingBox structureboundingbox1 = worldgenfeaturedefinedstructurepoolstructure1.a(this.d, BlockPosition.ZERO, enumblockrotation1);
                            int k1;

                            if (structureboundingbox1.d() > 16) {
                                k1 = 0;
                            } else {
                                k1 = list1.stream().mapToInt((definedstructure_blockinfo1) -> {
                                    if (!structureboundingbox1.b((BaseBlockPosition) definedstructure_blockinfo1.a.shift((EnumDirection) definedstructure_blockinfo1.b.get(BlockJigsaw.FACING)))) {
                                        return 0;
                                    } else {
                                        MinecraftKey minecraftkey = new MinecraftKey(definedstructure_blockinfo1.c.getString("target_pool"));
                                        WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate2 = WorldGenFeatureDefinedStructureJigsawPlacement.a.a(minecraftkey);
                                        WorldGenFeatureDefinedStructurePoolTemplate worldgenfeaturedefinedstructurepooltemplate3 = WorldGenFeatureDefinedStructureJigsawPlacement.a.a(worldgenfeaturedefinedstructurepooltemplate2.a());

                                        return Math.max(worldgenfeaturedefinedstructurepooltemplate2.a(this.d), worldgenfeaturedefinedstructurepooltemplate3.a(this.d));
                                    }
                                }).max().orElse(0);
                            }

                            Iterator iterator3 = list1.iterator();

                            while (iterator3.hasNext()) {
                                DefinedStructure.BlockInfo definedstructure_blockinfo1 = (DefinedStructure.BlockInfo) iterator3.next();

                                if (BlockJigsaw.a(definedstructure_blockinfo, definedstructure_blockinfo1)) {
                                    BlockPosition blockposition3 = definedstructure_blockinfo1.a;
                                    BlockPosition blockposition4 = new BlockPosition(blockposition2.getX() - blockposition3.getX(), blockposition2.getY() - blockposition3.getY(), blockposition2.getZ() - blockposition3.getZ());
                                    StructureBoundingBox structureboundingbox2 = worldgenfeaturedefinedstructurepoolstructure1.a(this.d, blockposition4, enumblockrotation1);
                                    int l1 = structureboundingbox2.b;
                                    WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching1 = worldgenfeaturedefinedstructurepoolstructure1.c();
                                    boolean flag2 = worldgenfeaturedefinedstructurepooltemplate_matching1 == WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID;
                                    int i2 = blockposition3.getY();
                                    int j2 = l - i2 + ((EnumDirection) definedstructure_blockinfo.b.get(BlockJigsaw.FACING)).getAdjacentY();
                                    int k2;

                                    if (flag && flag2) {
                                        k2 = k + j2;
                                    } else {
                                        if (i1 == -1) {
                                            i1 = this.c.b(blockposition1.getX(), blockposition1.getZ(), HeightMap.Type.WORLD_SURFACE_WG);
                                        }

                                        k2 = i1 - i2;
                                    }

                                    int l2 = k2 - l1;
                                    StructureBoundingBox structureboundingbox3 = structureboundingbox2.b(0, l2, 0);
                                    BlockPosition blockposition5 = blockposition4.b(0, l2, 0);
                                    int i3;

                                    if (k1 > 0) {
                                        i3 = Math.max(k1 + 1, structureboundingbox3.e - structureboundingbox3.b);
                                        structureboundingbox3.e = structureboundingbox3.b + i3;
                                    }

                                    if (!VoxelShapes.c((VoxelShape) atomicreference2.get(), VoxelShapes.a(AxisAlignedBB.a(structureboundingbox3).shrink(0.25D)), OperatorBoolean.ONLY_SECOND)) {
                                        atomicreference2.set(VoxelShapes.b((VoxelShape) atomicreference2.get(), VoxelShapes.a(AxisAlignedBB.a(structureboundingbox3)), OperatorBoolean.ONLY_FIRST));
                                        i3 = worldgenfeaturepillageroutpostpoolpiece.d();
                                        int j3;

                                        if (flag2) {
                                            j3 = i3 - j2;
                                        } else {
                                            j3 = worldgenfeaturedefinedstructurepoolstructure1.d();
                                        }

                                        WorldGenFeaturePillagerOutpostPoolPiece worldgenfeaturepillageroutpostpoolpiece1 = this.b.create(this.d, worldgenfeaturedefinedstructurepoolstructure1, blockposition5, j3, enumblockrotation1, structureboundingbox3);
                                        int k3;

                                        if (flag) {
                                            k3 = k + l;
                                        } else if (flag2) {
                                            k3 = k2 + i2;
                                        } else {
                                            if (i1 == -1) {
                                                i1 = this.c.b(blockposition1.getX(), blockposition1.getZ(), HeightMap.Type.WORLD_SURFACE_WG);
                                            }

                                            k3 = i1 + j2 / 2;
                                        }

                                        worldgenfeaturepillageroutpostpoolpiece.a(new WorldGenFeatureDefinedStructureJigsawJunction(blockposition2.getX(), k3 - l + i3, blockposition2.getZ(), j2, worldgenfeaturedefinedstructurepooltemplate_matching1));
                                        worldgenfeaturepillageroutpostpoolpiece1.a(new WorldGenFeatureDefinedStructureJigsawJunction(blockposition1.getX(), k3 - i2 + j3, blockposition1.getZ(), -j2, worldgenfeaturedefinedstructurepooltemplate_matching));
                                        this.e.add(worldgenfeaturepillageroutpostpoolpiece1);
                                        if (j + 1 <= this.a) {
                                            this.g.addLast(new WorldGenFeatureDefinedStructureJigsawPlacement.b(worldgenfeaturepillageroutpostpoolpiece1, atomicreference2, j1, j + 1));
                                        }
                                        continue label117;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    WorldGenFeatureDefinedStructureJigsawPlacement.LOGGER.warn("Empty or none existent pool: {}", definedstructure_blockinfo.c.getString("target_pool"));
                }
            }

        }
    }

    static final class b {

        private final WorldGenFeaturePillagerOutpostPoolPiece a;
        private final AtomicReference<VoxelShape> b;
        private final int c;
        private final int d;

        private b(WorldGenFeaturePillagerOutpostPoolPiece worldgenfeaturepillageroutpostpoolpiece, AtomicReference<VoxelShape> atomicreference, int i, int j) {
            this.a = worldgenfeaturepillageroutpostpoolpiece;
            this.b = atomicreference;
            this.c = i;
            this.d = j;
        }
    }
}
