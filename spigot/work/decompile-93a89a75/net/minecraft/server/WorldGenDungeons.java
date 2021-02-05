package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenDungeons extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final EntityTypes<?>[] aS = new EntityTypes[]{EntityTypes.SKELETON, EntityTypes.ZOMBIE, EntityTypes.ZOMBIE, EntityTypes.SPIDER};
    private static final IBlockData aT = Blocks.CAVE_AIR.getBlockData();

    public WorldGenDungeons(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        boolean flag = true;
        int i = random.nextInt(2) + 2;
        int j = -i - 1;
        int k = i + 1;
        boolean flag1 = true;
        boolean flag2 = true;
        int l = random.nextInt(2) + 2;
        int i1 = -l - 1;
        int j1 = l + 1;
        int k1 = 0;

        BlockPosition blockposition1;
        int l1;
        int i2;
        int j2;

        for (l1 = j; l1 <= k; ++l1) {
            for (i2 = -1; i2 <= 4; ++i2) {
                for (j2 = i1; j2 <= j1; ++j2) {
                    blockposition1 = blockposition.b(l1, i2, j2);
                    Material material = generatoraccess.getType(blockposition1).getMaterial();
                    boolean flag3 = material.isBuildable();

                    if (i2 == -1 && !flag3) {
                        return false;
                    }

                    if (i2 == 4 && !flag3) {
                        return false;
                    }

                    if ((l1 == j || l1 == k || j2 == i1 || j2 == j1) && i2 == 0 && generatoraccess.isEmpty(blockposition1) && generatoraccess.isEmpty(blockposition1.up())) {
                        ++k1;
                    }
                }
            }
        }

        if (k1 >= 1 && k1 <= 5) {
            for (l1 = j; l1 <= k; ++l1) {
                for (i2 = 3; i2 >= -1; --i2) {
                    for (j2 = i1; j2 <= j1; ++j2) {
                        blockposition1 = blockposition.b(l1, i2, j2);
                        if (l1 != j && i2 != -1 && j2 != i1 && l1 != k && i2 != 4 && j2 != j1) {
                            if (generatoraccess.getType(blockposition1).getBlock() != Blocks.CHEST) {
                                generatoraccess.setTypeAndData(blockposition1, WorldGenDungeons.aT, 2);
                            }
                        } else if (blockposition1.getY() >= 0 && !generatoraccess.getType(blockposition1.down()).getMaterial().isBuildable()) {
                            generatoraccess.setTypeAndData(blockposition1, WorldGenDungeons.aT, 2);
                        } else if (generatoraccess.getType(blockposition1).getMaterial().isBuildable() && generatoraccess.getType(blockposition1).getBlock() != Blocks.CHEST) {
                            if (i2 == -1 && random.nextInt(4) != 0) {
                                generatoraccess.setTypeAndData(blockposition1, Blocks.MOSSY_COBBLESTONE.getBlockData(), 2);
                            } else {
                                generatoraccess.setTypeAndData(blockposition1, Blocks.COBBLESTONE.getBlockData(), 2);
                            }
                        }
                    }
                }
            }

            l1 = 0;

            while (l1 < 2) {
                i2 = 0;

                while (true) {
                    if (i2 < 3) {
                        label98:
                        {
                            j2 = blockposition.getX() + random.nextInt(i * 2 + 1) - i;
                            int k2 = blockposition.getY();
                            int l2 = blockposition.getZ() + random.nextInt(l * 2 + 1) - l;
                            BlockPosition blockposition2 = new BlockPosition(j2, k2, l2);

                            if (generatoraccess.isEmpty(blockposition2)) {
                                int i3 = 0;
                                Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                                while (iterator.hasNext()) {
                                    EnumDirection enumdirection = (EnumDirection) iterator.next();

                                    if (generatoraccess.getType(blockposition2.shift(enumdirection)).getMaterial().isBuildable()) {
                                        ++i3;
                                    }
                                }

                                if (i3 == 1) {
                                    generatoraccess.setTypeAndData(blockposition2, StructurePiece.a((IBlockAccess) generatoraccess, blockposition2, Blocks.CHEST.getBlockData()), 2);
                                    TileEntityLootable.a(generatoraccess, random, blockposition2, LootTables.d);
                                    break label98;
                                }
                            }

                            ++i2;
                            continue;
                        }
                    }

                    ++l1;
                    break;
                }
            }

            generatoraccess.setTypeAndData(blockposition, Blocks.SPAWNER.getBlockData(), 2);
            TileEntity tileentity = generatoraccess.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawner().setMobName(this.a(random));
            } else {
                WorldGenDungeons.LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", blockposition.getX(), blockposition.getY(), blockposition.getZ());
            }

            return true;
        } else {
            return false;
        }
    }

    private EntityTypes<?> a(Random random) {
        return WorldGenDungeons.aS[random.nextInt(WorldGenDungeons.aS.length)];
    }
}
