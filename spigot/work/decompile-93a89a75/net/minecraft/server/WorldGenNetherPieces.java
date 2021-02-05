package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenNetherPieces {

    private static final WorldGenNetherPieces.WorldGenNetherPieceWeight[] a = new WorldGenNetherPieces.WorldGenNetherPieceWeight[]{new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece3.class, 30, 0, true), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece1.class, 10, 4), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece13.class, 10, 4), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece14.class, 10, 3), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece12.class, 5, 2), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece6.class, 5, 1)};
    private static final WorldGenNetherPieces.WorldGenNetherPieceWeight[] b = new WorldGenNetherPieces.WorldGenNetherPieceWeight[]{new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece9.class, 25, 0, true), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece7.class, 15, 5), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece10.class, 5, 10), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece8.class, 5, 10), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece4.class, 10, 3, true), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece5.class, 7, 2), new WorldGenNetherPieces.WorldGenNetherPieceWeight(WorldGenNetherPieces.WorldGenNetherPiece11.class, 5, 2)};

    private static WorldGenNetherPieces.WorldGenNetherPiece b(WorldGenNetherPieces.WorldGenNetherPieceWeight worldgennetherpieces_worldgennetherpieceweight, List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> oclass = worldgennetherpieces_worldgennetherpieceweight.a;
        Object object = null;

        if (oclass == WorldGenNetherPieces.WorldGenNetherPiece3.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece3.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece1.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece1.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece13.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece13.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece14.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece14.a(list, i, j, k, l, enumdirection);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece12.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece12.a(list, i, j, k, l, enumdirection);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece6.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece6.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece9.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece9.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece10.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece10.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece8.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece8.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece4.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece4.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece5.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece5.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece7.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece7.a(list, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.WorldGenNetherPiece11.class) {
            object = WorldGenNetherPieces.WorldGenNetherPiece11.a(list, i, j, k, enumdirection, l);
        }

        return (WorldGenNetherPieces.WorldGenNetherPiece) object;
    }

    public static class WorldGenNetherPiece5 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece5(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.k, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece5(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.k, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            byte b0 = 1;
            EnumDirection enumdirection = this.i();

            if (enumdirection == EnumDirection.WEST || enumdirection == EnumDirection.NORTH) {
                b0 = 5;
            }

            this.b((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece5 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -3, 0, 0, 9, 7, 9, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece5(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);

            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 8, 5, 8, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 0, 1, 4, 0, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 7, 3, 0, 7, 4, 0, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 1, 4, 2, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 1, 4, 7, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 8, 7, 3, 8, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true)).set(BlockFence.SOUTH, true), 0, 3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.SOUTH, true), 8, 3, 8, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 0, 3, 6, 0, 3, 7, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 8, 3, 6, 8, 3, 7, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 4, 5, 1, 5, 5, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 7, 4, 5, 7, 5, 5, iblockdata1, iblockdata1, false);

            for (int i = 0; i <= 5; ++i) {
                for (int j = 0; j <= 8; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), j, -1, i, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece4 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece4(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.j, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece4(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.j, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece4 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 14, 10, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece4(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            IBlockData iblockdata = (IBlockData) Blocks.NETHER_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            for (int i = 0; i <= 9; ++i) {
                int j = Math.max(1, 7 - i);
                int k = Math.min(Math.max(j + 5, 14 - i), 13);
                int l = i;

                this.a(generatoraccess, structureboundingbox, 0, 0, i, 4, j, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 1, j + 1, i, 3, k - 1, i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                if (i <= 6) {
                    this.a(generatoraccess, iblockdata, 1, j + 1, i, structureboundingbox);
                    this.a(generatoraccess, iblockdata, 2, j + 1, i, structureboundingbox);
                    this.a(generatoraccess, iblockdata, 3, j + 1, i, structureboundingbox);
                }

                this.a(generatoraccess, structureboundingbox, 0, k, i, 4, k, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 0, j + 1, i, 0, k - 1, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 4, j + 1, i, 4, k - 1, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                if ((i & 1) == 0) {
                    this.a(generatoraccess, structureboundingbox, 0, j + 2, i, 0, j + 3, i, iblockdata1, iblockdata1, false);
                    this.a(generatoraccess, structureboundingbox, 4, j + 2, i, 4, j + 3, i, iblockdata1, iblockdata1, false);
                }

                for (int i1 = 0; i1 <= 4; ++i1) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i1, -1, l, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece8 extends WorldGenNetherPieces.WorldGenNetherPiece {

        private boolean a;

        public WorldGenNetherPiece8(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.n, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
            this.a = random.nextInt(3) == 0;
        }

        public WorldGenNetherPiece8(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.n, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Chest");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Chest", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.b((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece8 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece8(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 1, 4, 4, 1, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 3, 4, 4, 3, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 4, 1, 4, 4, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 3, 3, 4, 3, 4, 4, iblockdata, iblockdata, false);
            if (this.a && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(3, 3), this.d(2), this.b(3, 3))))) {
                this.a = false;
                this.a(generatoraccess, structureboundingbox, random, 3, 2, 3, LootTables.v);
            }

            this.a(generatoraccess, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece10 extends WorldGenNetherPieces.WorldGenNetherPiece {

        private boolean a;

        public WorldGenNetherPiece10(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.p, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
            this.a = random.nextInt(3) == 0;
        }

        public WorldGenNetherPiece10(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.p, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Chest");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Chest", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece10 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece10(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 1, 0, 4, 1, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 3, 0, 4, 3, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 4, 1, 4, 4, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 3, 3, 4, 3, 4, 4, iblockdata, iblockdata, false);
            if (this.a && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(1, 3), this.d(2), this.b(1, 3))))) {
                this.a = false;
                this.a(generatoraccess, structureboundingbox, random, 1, 2, 3, LootTables.v);
            }

            this.a(generatoraccess, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece7 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece7(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.m, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece7(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.m, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
            this.b((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece7 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece7(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece9 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece9(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.o, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece9(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.o, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece9 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece9(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 1, 0, 4, 1, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 3, 0, 4, 3, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 1, 4, 4, 1, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 3, 4, 4, 3, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece11 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece11(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.q, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece11(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.q, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 5, 3, true);
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 5, 11, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece11 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece11(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);
            IBlockData iblockdata2 = (IBlockData) iblockdata1.set(BlockFence.WEST, true);
            IBlockData iblockdata3 = (IBlockData) iblockdata1.set(BlockFence.EAST, true);

            int i;

            for (i = 1; i <= 11; i += 2) {
                this.a(generatoraccess, structureboundingbox, i, 10, 0, i, 11, 0, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, i, 10, 12, i, 11, 12, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, 0, 10, i, 0, 11, i, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, structureboundingbox, 12, 10, i, 12, 11, i, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 0, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 12, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 0, 13, i, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 12, 13, i, structureboundingbox);
                if (i != 11) {
                    this.a(generatoraccess, iblockdata, i + 1, 13, 0, structureboundingbox);
                    this.a(generatoraccess, iblockdata, i + 1, 13, 12, structureboundingbox);
                    this.a(generatoraccess, iblockdata1, 0, 13, i + 1, structureboundingbox);
                    this.a(generatoraccess, iblockdata1, 12, 13, i + 1, structureboundingbox);
                }
            }

            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.EAST, true), 0, 13, 0, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.EAST, true), 0, 13, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.WEST, true), 12, 13, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.WEST, true), 12, 13, 0, structureboundingbox);

            for (i = 3; i <= 9; i += 2) {
                this.a(generatoraccess, structureboundingbox, 1, 7, i, 1, 8, i, iblockdata2, iblockdata2, false);
                this.a(generatoraccess, structureboundingbox, 11, 7, i, 11, 8, i, iblockdata3, iblockdata3, false);
            }

            IBlockData iblockdata4 = (IBlockData) Blocks.NETHER_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);

            int j;
            int k;

            for (j = 0; j <= 6; ++j) {
                int l = j + 4;

                for (k = 5; k <= 7; ++k) {
                    this.a(generatoraccess, iblockdata4, k, 5 + j, l, structureboundingbox);
                }

                if (l >= 5 && l <= 8) {
                    this.a(generatoraccess, structureboundingbox, 5, 5, l, 7, j + 4, l, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                } else if (l >= 9 && l <= 10) {
                    this.a(generatoraccess, structureboundingbox, 5, 8, l, 7, j + 4, l, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                }

                if (j >= 1) {
                    this.a(generatoraccess, structureboundingbox, 5, 6 + j, l, 7, 9 + j, l, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                }
            }

            for (j = 5; j <= 7; ++j) {
                this.a(generatoraccess, iblockdata4, j, 12, 11, structureboundingbox);
            }

            this.a(generatoraccess, structureboundingbox, 5, 6, 7, 5, 7, 7, iblockdata3, iblockdata3, false);
            this.a(generatoraccess, structureboundingbox, 7, 6, 7, 7, 7, 7, iblockdata2, iblockdata2, false);
            this.a(generatoraccess, structureboundingbox, 5, 13, 12, 7, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            IBlockData iblockdata5 = (IBlockData) iblockdata4.set(BlockStairs.FACING, EnumDirection.EAST);
            IBlockData iblockdata6 = (IBlockData) iblockdata4.set(BlockStairs.FACING, EnumDirection.WEST);

            this.a(generatoraccess, iblockdata6, 4, 5, 2, structureboundingbox);
            this.a(generatoraccess, iblockdata6, 4, 5, 3, structureboundingbox);
            this.a(generatoraccess, iblockdata6, 4, 5, 9, structureboundingbox);
            this.a(generatoraccess, iblockdata6, 4, 5, 10, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 8, 5, 2, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 8, 5, 3, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 8, 5, 9, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 8, 5, 10, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            int i1;

            for (k = 4; k <= 8; ++k) {
                for (i1 = 0; i1 <= 2; ++i1) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), k, -1, i1, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), k, -1, 12 - i1, structureboundingbox);
                }
            }

            for (k = 0; k <= 2; ++k) {
                for (i1 = 4; i1 <= 8; ++i1) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), k, -1, i1, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 12 - k, -1, i1, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece6 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece6(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.l, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece6(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.l, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 5, 3, true);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece6 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece6(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            int i;

            for (i = 1; i <= 11; i += 2) {
                this.a(generatoraccess, structureboundingbox, i, 10, 0, i, 11, 0, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, i, 10, 12, i, 11, 12, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, 0, 10, i, 0, 11, i, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, structureboundingbox, 12, 10, i, 12, 11, i, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 0, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 12, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 0, 13, i, structureboundingbox);
                this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 12, 13, i, structureboundingbox);
                if (i != 11) {
                    this.a(generatoraccess, iblockdata, i + 1, 13, 0, structureboundingbox);
                    this.a(generatoraccess, iblockdata, i + 1, 13, 12, structureboundingbox);
                    this.a(generatoraccess, iblockdata1, 0, 13, i + 1, structureboundingbox);
                    this.a(generatoraccess, iblockdata1, 12, 13, i + 1, structureboundingbox);
                }
            }

            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.EAST, true), 0, 13, 0, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.EAST, true), 0, 13, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.WEST, true), 12, 13, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.WEST, true), 12, 13, 0, structureboundingbox);

            for (i = 3; i <= 9; i += 2) {
                this.a(generatoraccess, structureboundingbox, 1, 7, i, 1, 8, i, (IBlockData) iblockdata1.set(BlockFence.WEST, true), (IBlockData) iblockdata1.set(BlockFence.WEST, true), false);
                this.a(generatoraccess, structureboundingbox, 11, 7, i, 11, 8, i, (IBlockData) iblockdata1.set(BlockFence.EAST, true), (IBlockData) iblockdata1.set(BlockFence.EAST, true), false);
            }

            this.a(generatoraccess, structureboundingbox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            int j;

            for (i = 4; i <= 8; ++i) {
                for (j = 0; j <= 2; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, 12 - j, structureboundingbox);
                }
            }

            for (i = 0; i <= 2; ++i) {
                for (j = 4; j <= 8; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 12 - i, -1, j, structureboundingbox);
                }
            }

            this.a(generatoraccess, structureboundingbox, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 1, 6, 6, 4, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 6, 0, 6, structureboundingbox);
            this.a(generatoraccess, Blocks.LAVA.getBlockData(), 6, 5, 6, structureboundingbox);
            BlockPosition blockposition = new BlockPosition(this.a(6, 6), this.d(5), this.b(6, 6));

            if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                generatoraccess.getFluidTickList().a(blockposition, FluidTypes.LAVA, 0);
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece12 extends WorldGenNetherPieces.WorldGenNetherPiece {

        private boolean a;

        public WorldGenNetherPiece12(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.r, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece12(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.r, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Mob");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Mob", this.a);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece12 a(List<StructurePiece> list, int i, int j, int k, int l, EnumDirection enumdirection) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 8, 9, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece12(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 6, 7, 7, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true), 1, 6, 3, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true), 5, 6, 3, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true)).set(BlockFence.NORTH, true), 0, 6, 3, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.NORTH, true), 6, 6, 3, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 0, 6, 4, 0, 6, 7, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 6, 6, 4, 6, 6, 7, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true)).set(BlockFence.SOUTH, true), 0, 6, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.SOUTH, true), 6, 6, 8, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 1, 6, 8, 5, 6, 8, iblockdata, iblockdata, false);
            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true), 1, 7, 8, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 2, 7, 8, 4, 7, 8, iblockdata, iblockdata, false);
            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true), 5, 7, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, true), 2, 8, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata, 3, 8, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true), 4, 8, 8, structureboundingbox);
            if (!this.a) {
                BlockPosition blockposition = new BlockPosition(this.a(3, 5), this.d(5), this.b(3, 5));

                if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                    this.a = true;
                    generatoraccess.setTypeAndData(blockposition, Blocks.SPAWNER.getBlockData(), 2);
                    TileEntity tileentity = generatoraccess.getTileEntity(blockposition);

                    if (tileentity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner) tileentity).getSpawner().setMobName(EntityTypes.BLAZE);
                    }
                }
            }

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece14 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece14(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.t, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece14(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.t, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 6, 2, false);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece14 a(List<StructurePiece> list, int i, int j, int k, int l, EnumDirection enumdirection) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 11, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece14(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 6, 10, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, structureboundingbox, 0, 3, 2, 0, 5, 4, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 6, 3, 2, 6, 5, 2, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 6, 3, 4, 6, 5, 4, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 5, 2, 5, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 8, 2, 6, 8, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 0, 4, 5, 0, iblockdata, iblockdata, false);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece13 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece13(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.s, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece13(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.s, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 2, 0, false);
            this.b((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 0, 2, false);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece13 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 9, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece13(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 6, 7, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

            this.a(generatoraccess, structureboundingbox, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 0, 4, 5, 0, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 2, 5, 6, 4, 5, 6, iblockdata, iblockdata, false);
            this.a(generatoraccess, structureboundingbox, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 2, 0, 5, 4, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 6, 5, 2, 6, 5, 4, iblockdata1, iblockdata1, false);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece1 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece1(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.g, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        protected WorldGenNetherPiece1(Random random, int i, int j) {
            super(WorldGenFeatureStructurePieceType.g, 0);
            this.a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random));
            if (this.i().k() == EnumDirection.EnumAxis.Z) {
                this.n = new StructureBoundingBox(i, 64, j, i + 19 - 1, 73, j + 19 - 1);
            } else {
                this.n = new StructureBoundingBox(i, 64, j, i + 19 - 1, 73, j + 19 - 1);
            }

        }

        protected WorldGenNetherPiece1(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
            super(worldgenfeaturestructurepiecetype, nbttagcompound);
        }

        public WorldGenNetherPiece1(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            this(WorldGenFeatureStructurePieceType.g, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 8, 3, false);
            this.b((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 3, 8, false);
            this.c((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 3, 8, false);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece1 a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -8, -3, 0, 19, 10, 19, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece1(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 0, 10, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 8, 18, 7, 10, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            int i;
            int j;

            for (i = 7; i <= 11; ++i) {
                for (j = 0; j <= 2; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, 18 - j, structureboundingbox);
                }
            }

            this.a(generatoraccess, structureboundingbox, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (i = 0; i <= 2; ++i) {
                for (j = 7; j <= 11; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), 18 - i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece2 extends WorldGenNetherPieces.WorldGenNetherPiece {

        private final int a;

        public WorldGenNetherPiece2(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.h, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
            this.a = random.nextInt();
        }

        public WorldGenNetherPiece2(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.h, nbttagcompound);
            this.a = nbttagcompound.getInt("Seed");
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece2 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 8, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece2(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setInt("Seed", this.a);
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            Random random1 = new Random((long) this.a);

            int i;
            int j;
            int k;

            for (j = 0; j <= 4; ++j) {
                for (k = 3; k <= 4; ++k) {
                    i = random1.nextInt(8);
                    this.a(generatoraccess, structureboundingbox, j, k, 0, j, k, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                }
            }

            j = random1.nextInt(8);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 0, 5, j, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            j = random1.nextInt(8);
            this.a(generatoraccess, structureboundingbox, 4, 5, 0, 4, 5, j, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (j = 0; j <= 4; ++j) {
                k = random1.nextInt(5);
                this.a(generatoraccess, structureboundingbox, j, 2, 0, j, 2, k, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            }

            for (j = 0; j <= 4; ++j) {
                for (k = 0; k <= 1; ++k) {
                    i = random1.nextInt(3);
                    this.a(generatoraccess, structureboundingbox, j, k, 0, j, k, i, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
                }
            }

            return true;
        }
    }

    public static class WorldGenNetherPiece3 extends WorldGenNetherPieces.WorldGenNetherPiece {

        public WorldGenNetherPiece3(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.i, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenNetherPiece3(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.i, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.WorldGenNetherPiece15) structurepiece, list, random, 1, 3, false);
        }

        public static WorldGenNetherPieces.WorldGenNetherPiece3 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 19, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.WorldGenNetherPiece3(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 5, 0, 3, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(generatoraccess, Blocks.NETHER_BRICKS.getBlockData(), i, -1, 18 - j, structureboundingbox);
                }
            }

            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);
            IBlockData iblockdata1 = (IBlockData) iblockdata.set(BlockFence.EAST, true);
            IBlockData iblockdata2 = (IBlockData) iblockdata.set(BlockFence.WEST, true);

            this.a(generatoraccess, structureboundingbox, 0, 1, 1, 0, 4, 1, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 4, 0, 4, 4, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 3, 14, 0, 4, 14, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 0, 1, 17, 0, 4, 17, iblockdata1, iblockdata1, false);
            this.a(generatoraccess, structureboundingbox, 4, 1, 1, 4, 4, 1, iblockdata2, iblockdata2, false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 4, 4, 4, 4, iblockdata2, iblockdata2, false);
            this.a(generatoraccess, structureboundingbox, 4, 3, 14, 4, 4, 14, iblockdata2, iblockdata2, false);
            this.a(generatoraccess, structureboundingbox, 4, 1, 17, 4, 4, 17, iblockdata2, iblockdata2, false);
            return true;
        }
    }

    public static class WorldGenNetherPiece15 extends WorldGenNetherPieces.WorldGenNetherPiece1 {

        public WorldGenNetherPieces.WorldGenNetherPieceWeight a;
        public List<WorldGenNetherPieces.WorldGenNetherPieceWeight> b;
        public List<WorldGenNetherPieces.WorldGenNetherPieceWeight> c;
        public final List<StructurePiece> d = Lists.newArrayList();

        public WorldGenNetherPiece15(Random random, int i, int j) {
            super(random, i, j);
            this.b = Lists.newArrayList();
            WorldGenNetherPieces.WorldGenNetherPieceWeight[] aworldgennetherpieces_worldgennetherpieceweight = WorldGenNetherPieces.a;
            int k = aworldgennetherpieces_worldgennetherpieceweight.length;

            WorldGenNetherPieces.WorldGenNetherPieceWeight worldgennetherpieces_worldgennetherpieceweight;
            int l;

            for (l = 0; l < k; ++l) {
                worldgennetherpieces_worldgennetherpieceweight = aworldgennetherpieces_worldgennetherpieceweight[l];
                worldgennetherpieces_worldgennetherpieceweight.c = 0;
                this.b.add(worldgennetherpieces_worldgennetherpieceweight);
            }

            this.c = Lists.newArrayList();
            aworldgennetherpieces_worldgennetherpieceweight = WorldGenNetherPieces.b;
            k = aworldgennetherpieces_worldgennetherpieceweight.length;

            for (l = 0; l < k; ++l) {
                worldgennetherpieces_worldgennetherpieceweight = aworldgennetherpieces_worldgennetherpieceweight[l];
                worldgennetherpieces_worldgennetherpieceweight.c = 0;
                this.c.add(worldgennetherpieces_worldgennetherpieceweight);
            }

        }

        public WorldGenNetherPiece15(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.u, nbttagcompound);
        }
    }

    abstract static class WorldGenNetherPiece extends StructurePiece {

        protected WorldGenNetherPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, int i) {
            super(worldgenfeaturestructurepiecetype, i);
        }

        public WorldGenNetherPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
            super(worldgenfeaturestructurepiecetype, nbttagcompound);
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {}

        private int a(List<WorldGenNetherPieces.WorldGenNetherPieceWeight> list) {
            boolean flag = false;
            int i = 0;

            WorldGenNetherPieces.WorldGenNetherPieceWeight worldgennetherpieces_worldgennetherpieceweight;

            for (Iterator iterator = list.iterator(); iterator.hasNext(); i += worldgennetherpieces_worldgennetherpieceweight.b) {
                worldgennetherpieces_worldgennetherpieceweight = (WorldGenNetherPieces.WorldGenNetherPieceWeight) iterator.next();
                if (worldgennetherpieces_worldgennetherpieceweight.d > 0 && worldgennetherpieces_worldgennetherpieceweight.c < worldgennetherpieces_worldgennetherpieceweight.d) {
                    flag = true;
                }
            }

            return flag ? i : -1;
        }

        private WorldGenNetherPieces.WorldGenNetherPiece a(WorldGenNetherPieces.WorldGenNetherPiece15 worldgennetherpieces_worldgennetherpiece15, List<WorldGenNetherPieces.WorldGenNetherPieceWeight> list, List<StructurePiece> list1, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            int i1 = this.a(list);
            boolean flag = i1 > 0 && l <= 30;
            int j1 = 0;

            while (j1 < 5 && flag) {
                ++j1;
                int k1 = random.nextInt(i1);
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    WorldGenNetherPieces.WorldGenNetherPieceWeight worldgennetherpieces_worldgennetherpieceweight = (WorldGenNetherPieces.WorldGenNetherPieceWeight) iterator.next();

                    k1 -= worldgennetherpieces_worldgennetherpieceweight.b;
                    if (k1 < 0) {
                        if (!worldgennetherpieces_worldgennetherpieceweight.a(l) || worldgennetherpieces_worldgennetherpieceweight == worldgennetherpieces_worldgennetherpiece15.a && !worldgennetherpieces_worldgennetherpieceweight.e) {
                            break;
                        }

                        WorldGenNetherPieces.WorldGenNetherPiece worldgennetherpieces_worldgennetherpiece = WorldGenNetherPieces.b(worldgennetherpieces_worldgennetherpieceweight, list1, random, i, j, k, enumdirection, l);

                        if (worldgennetherpieces_worldgennetherpiece != null) {
                            ++worldgennetherpieces_worldgennetherpieceweight.c;
                            worldgennetherpieces_worldgennetherpiece15.a = worldgennetherpieces_worldgennetherpieceweight;
                            if (!worldgennetherpieces_worldgennetherpieceweight.a()) {
                                list.remove(worldgennetherpieces_worldgennetherpieceweight);
                            }

                            return worldgennetherpieces_worldgennetherpiece;
                        }
                    }
                }
            }

            return WorldGenNetherPieces.WorldGenNetherPiece2.a(list1, random, i, j, k, enumdirection, l);
        }

        private StructurePiece a(WorldGenNetherPieces.WorldGenNetherPiece15 worldgennetherpieces_worldgennetherpiece15, List<StructurePiece> list, Random random, int i, int j, int k, @Nullable EnumDirection enumdirection, int l, boolean flag) {
            if (Math.abs(i - worldgennetherpieces_worldgennetherpiece15.g().a) <= 112 && Math.abs(k - worldgennetherpieces_worldgennetherpiece15.g().c) <= 112) {
                List<WorldGenNetherPieces.WorldGenNetherPieceWeight> list1 = worldgennetherpieces_worldgennetherpiece15.b;

                if (flag) {
                    list1 = worldgennetherpieces_worldgennetherpiece15.c;
                }

                WorldGenNetherPieces.WorldGenNetherPiece worldgennetherpieces_worldgennetherpiece = this.a(worldgennetherpieces_worldgennetherpiece15, list1, list, random, i, j, k, enumdirection, l + 1);

                if (worldgennetherpieces_worldgennetherpiece != null) {
                    list.add(worldgennetherpieces_worldgennetherpiece);
                    worldgennetherpieces_worldgennetherpiece15.d.add(worldgennetherpieces_worldgennetherpiece);
                }

                return worldgennetherpieces_worldgennetherpiece;
            } else {
                return WorldGenNetherPieces.WorldGenNetherPiece2.a(list, random, i, j, k, enumdirection, l);
            }
        }

        @Nullable
        protected StructurePiece a(WorldGenNetherPieces.WorldGenNetherPiece15 worldgennetherpieces_worldgennetherpiece15, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + i, this.n.b + j, this.n.c - 1, enumdirection, this.h(), flag);
                    case SOUTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + i, this.n.b + j, this.n.f + 1, enumdirection, this.h(), flag);
                    case WEST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a - 1, this.n.b + j, this.n.c + i, enumdirection, this.h(), flag);
                    case EAST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.d + 1, this.n.b + j, this.n.c + i, enumdirection, this.h(), flag);
                }
            }

            return null;
        }

        @Nullable
        protected StructurePiece b(WorldGenNetherPieces.WorldGenNetherPiece15 worldgennetherpieces_worldgennetherpiece15, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a - 1, this.n.b + i, this.n.c + j, EnumDirection.WEST, this.h(), flag);
                    case SOUTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a - 1, this.n.b + i, this.n.c + j, EnumDirection.WEST, this.h(), flag);
                    case WEST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + j, this.n.b + i, this.n.c - 1, EnumDirection.NORTH, this.h(), flag);
                    case EAST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + j, this.n.b + i, this.n.c - 1, EnumDirection.NORTH, this.h(), flag);
                }
            }

            return null;
        }

        @Nullable
        protected StructurePiece c(WorldGenNetherPieces.WorldGenNetherPiece15 worldgennetherpieces_worldgennetherpiece15, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.d + 1, this.n.b + i, this.n.c + j, EnumDirection.EAST, this.h(), flag);
                    case SOUTH:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.d + 1, this.n.b + i, this.n.c + j, EnumDirection.EAST, this.h(), flag);
                    case WEST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + j, this.n.b + i, this.n.f + 1, EnumDirection.SOUTH, this.h(), flag);
                    case EAST:
                        return this.a(worldgennetherpieces_worldgennetherpiece15, list, random, this.n.a + j, this.n.b + i, this.n.f + 1, EnumDirection.SOUTH, this.h(), flag);
                }
            }

            return null;
        }

        protected static boolean a(StructureBoundingBox structureboundingbox) {
            return structureboundingbox != null && structureboundingbox.b > 10;
        }
    }

    static class WorldGenNetherPieceWeight {

        public final Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> a;
        public final int b;
        public int c;
        public final int d;
        public final boolean e;

        public WorldGenNetherPieceWeight(Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> oclass, int i, int j, boolean flag) {
            this.a = oclass;
            this.b = i;
            this.d = j;
            this.e = flag;
        }

        public WorldGenNetherPieceWeight(Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> oclass, int i, int j) {
            this(oclass, i, j, false);
        }

        public boolean a(int i) {
            return this.d == 0 || this.c < this.d;
        }

        public boolean a() {
            return this.d == 0 || this.c < this.d;
        }
    }
}
