package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public abstract class WorldGenTreeAbstract<T extends WorldGenFeatureConfiguration> extends WorldGenerator<T> {

    public WorldGenTreeAbstract(Function<Dynamic<?>, ? extends T> function, boolean flag) {
        super(function, flag);
    }

    protected static boolean a(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            Block block = iblockdata.getBlock();

            return iblockdata.isAir() || iblockdata.a(TagsBlock.LEAVES) || block == Blocks.GRASS_BLOCK || Block.c(block) || block.a(TagsBlock.LOGS) || block.a(TagsBlock.SAPLINGS) || block == Blocks.VINE;
        });
    }

    protected static boolean b(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, IBlockData::isAir);
    }

    protected static boolean c(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            return Block.c(iblockdata.getBlock());
        });
    }

    protected static boolean e(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            return iblockdata.getBlock() == Blocks.WATER;
        });
    }

    protected static boolean f(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            return iblockdata.a(TagsBlock.LEAVES);
        });
    }

    protected static boolean g(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            return iblockdata.isAir() || iblockdata.a(TagsBlock.LEAVES);
        });
    }

    protected static boolean h(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            Block block = iblockdata.getBlock();

            return Block.c(block) || block == Blocks.GRASS_BLOCK;
        });
    }

    protected static boolean i(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            Block block = iblockdata.getBlock();

            return Block.c(block) || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND;
        });
    }

    protected static boolean j(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition) {
        return virtuallevelreadable.a(blockposition, (iblockdata) -> {
            Material material = iblockdata.getMaterial();

            return material == Material.REPLACEABLE_PLANT;
        });
    }

    protected void a(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition) {
        if (!c(virtuallevelwritable, blockposition)) {
            this.a(virtuallevelwritable, blockposition, Blocks.DIRT.getBlockData());
        }

    }

    @Override
    protected void a(IWorldWriter iworldwriter, BlockPosition blockposition, IBlockData iblockdata) {
        this.b(iworldwriter, blockposition, iblockdata);
    }

    protected final void a(Set<BlockPosition> set, IWorldWriter iworldwriter, BlockPosition blockposition, IBlockData iblockdata, StructureBoundingBox structureboundingbox) {
        this.b(iworldwriter, blockposition, iblockdata);
        structureboundingbox.c(new StructureBoundingBox(blockposition, blockposition));
        if (TagsBlock.LOGS.isTagged(iblockdata.getBlock())) {
            set.add(blockposition.immutableCopy());
        }

    }

    private void b(IWorldWriter iworldwriter, BlockPosition blockposition, IBlockData iblockdata) {
        if (this.aR) {
            iworldwriter.setTypeAndData(blockposition, iblockdata, 19);
        } else {
            iworldwriter.setTypeAndData(blockposition, iblockdata, 18);
        }

    }

    @Override
    public final boolean generate(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, T t0) {
        Set<BlockPosition> set = Sets.newHashSet();
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a();
        boolean flag = this.a(set, (VirtualLevelWritable) generatoraccess, random, blockposition, structureboundingbox);

        if (structureboundingbox.a > structureboundingbox.d) {
            return false;
        } else {
            List<Set<BlockPosition>> list = Lists.newArrayList();
            boolean flag1 = true;

            for (int i = 0; i < 6; ++i) {
                list.add(Sets.newHashSet());
            }

            VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(structureboundingbox.c(), structureboundingbox.d(), structureboundingbox.e());
            BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.r();
            Throwable throwable = null;

            try {
                if (flag && !set.isEmpty()) {
                    Iterator iterator = Lists.newArrayList(set).iterator();

                    while (iterator.hasNext()) {
                        BlockPosition blockposition1 = (BlockPosition) iterator.next();

                        if (structureboundingbox.b((BaseBlockPosition) blockposition1)) {
                            voxelshapebitset.a(blockposition1.getX() - structureboundingbox.a, blockposition1.getY() - structureboundingbox.b, blockposition1.getZ() - structureboundingbox.c, true, true);
                        }

                        EnumDirection[] aenumdirection = EnumDirection.values();
                        int j = aenumdirection.length;

                        for (int k = 0; k < j; ++k) {
                            EnumDirection enumdirection = aenumdirection[k];

                            blockposition_pooledblockposition.g(blockposition1).c(enumdirection);
                            if (!set.contains(blockposition_pooledblockposition)) {
                                IBlockData iblockdata = generatoraccess.getType(blockposition_pooledblockposition);

                                if (iblockdata.b((IBlockState) BlockProperties.ah)) {
                                    ((Set) list.get(0)).add(blockposition_pooledblockposition.immutableCopy());
                                    this.b(generatoraccess, blockposition_pooledblockposition, (IBlockData) iblockdata.set(BlockProperties.ah, 1));
                                    if (structureboundingbox.b((BaseBlockPosition) blockposition_pooledblockposition)) {
                                        voxelshapebitset.a(blockposition_pooledblockposition.getX() - structureboundingbox.a, blockposition_pooledblockposition.getY() - structureboundingbox.b, blockposition_pooledblockposition.getZ() - structureboundingbox.c, true, true);
                                    }
                                }
                            }
                        }
                    }
                }

                int l = 1;

                while (l < 6) {
                    Set<BlockPosition> set1 = (Set) list.get(l - 1);
                    Set<BlockPosition> set2 = (Set) list.get(l);
                    Iterator iterator1 = set1.iterator();

                    label213:
                    while (true) {
                        if (iterator1.hasNext()) {
                            BlockPosition blockposition2 = (BlockPosition) iterator1.next();

                            if (structureboundingbox.b((BaseBlockPosition) blockposition2)) {
                                voxelshapebitset.a(blockposition2.getX() - structureboundingbox.a, blockposition2.getY() - structureboundingbox.b, blockposition2.getZ() - structureboundingbox.c, true, true);
                            }

                            EnumDirection[] aenumdirection1 = EnumDirection.values();
                            int i1 = aenumdirection1.length;
                            int j1 = 0;

                            while (true) {
                                if (j1 >= i1) {
                                    continue label213;
                                }

                                EnumDirection enumdirection1 = aenumdirection1[j1];

                                blockposition_pooledblockposition.g(blockposition2).c(enumdirection1);
                                if (!set1.contains(blockposition_pooledblockposition) && !set2.contains(blockposition_pooledblockposition)) {
                                    IBlockData iblockdata1 = generatoraccess.getType(blockposition_pooledblockposition);

                                    if (iblockdata1.b((IBlockState) BlockProperties.ah)) {
                                        int k1 = (Integer) iblockdata1.get(BlockProperties.ah);

                                        if (k1 > l + 1) {
                                            IBlockData iblockdata2 = (IBlockData) iblockdata1.set(BlockProperties.ah, l + 1);

                                            this.b(generatoraccess, blockposition_pooledblockposition, iblockdata2);
                                            if (structureboundingbox.b((BaseBlockPosition) blockposition_pooledblockposition)) {
                                                voxelshapebitset.a(blockposition_pooledblockposition.getX() - structureboundingbox.a, blockposition_pooledblockposition.getY() - structureboundingbox.b, blockposition_pooledblockposition.getZ() - structureboundingbox.c, true, true);
                                            }

                                            set2.add(blockposition_pooledblockposition.immutableCopy());
                                        }
                                    }
                                }

                                ++j1;
                            }
                        }

                        ++l;
                        break;
                    }
                }
            } catch (Throwable throwable1) {
                throwable = throwable1;
                throw throwable1;
            } finally {
                if (blockposition_pooledblockposition != null) {
                    if (throwable != null) {
                        try {
                            blockposition_pooledblockposition.close();
                        } catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    } else {
                        blockposition_pooledblockposition.close();
                    }
                }

            }

            DefinedStructure.a(generatoraccess, 3, voxelshapebitset, structureboundingbox.a, structureboundingbox.b, structureboundingbox.c);
            return flag;
        }
    }

    protected abstract boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox);
}
