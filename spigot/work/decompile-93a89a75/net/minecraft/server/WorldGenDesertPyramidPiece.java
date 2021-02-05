package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class WorldGenDesertPyramidPiece extends WorldGenScatteredPiece {

    private final boolean[] e = new boolean[4];

    public WorldGenDesertPyramidPiece(Random random, int i, int j) {
        super(WorldGenFeatureStructurePieceType.M, random, i, 64, j, 21, 15, 21);
    }

    public WorldGenDesertPyramidPiece(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
        super(WorldGenFeatureStructurePieceType.M, nbttagcompound);
        this.e[0] = nbttagcompound.getBoolean("hasPlacedChest0");
        this.e[1] = nbttagcompound.getBoolean("hasPlacedChest1");
        this.e[2] = nbttagcompound.getBoolean("hasPlacedChest2");
        this.e[3] = nbttagcompound.getBoolean("hasPlacedChest3");
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("hasPlacedChest0", this.e[0]);
        nbttagcompound.setBoolean("hasPlacedChest1", this.e[1]);
        nbttagcompound.setBoolean("hasPlacedChest2", this.e[2]);
        nbttagcompound.setBoolean("hasPlacedChest3", this.e[3]);
    }

    @Override
    public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
        this.a(generatoraccess, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);

        int i;

        for (i = 1; i <= 9; ++i) {
            this.a(generatoraccess, structureboundingbox, i, i, i, this.a - 1 - i, i, this.c - 1 - i, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, i + 1, i, i + 1, this.a - 2 - i, i, this.c - 2 - i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        }

        for (i = 0; i < this.a; ++i) {
            for (int j = 0; j < this.c; ++j) {
                boolean flag = true;

                this.b(generatoraccess, Blocks.SANDSTONE.getBlockData(), i, -5, j, structureboundingbox);
            }
        }

        IBlockData iblockdata = (IBlockData) Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);
        IBlockData iblockdata1 = (IBlockData) Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
        IBlockData iblockdata2 = (IBlockData) Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.EAST);
        IBlockData iblockdata3 = (IBlockData) Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.WEST);

        this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 1, 10, 1, 3, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, iblockdata, 2, 10, 0, structureboundingbox);
        this.a(generatoraccess, iblockdata1, 2, 10, 4, structureboundingbox);
        this.a(generatoraccess, iblockdata2, 0, 10, 2, structureboundingbox);
        this.a(generatoraccess, iblockdata3, 4, 10, 2, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, this.a - 5, 0, 0, this.a - 1, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 4, 10, 1, this.a - 2, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, iblockdata, this.a - 3, 10, 0, structureboundingbox);
        this.a(generatoraccess, iblockdata1, this.a - 3, 10, 4, structureboundingbox);
        this.a(generatoraccess, iblockdata2, this.a - 5, 10, 2, structureboundingbox);
        this.a(generatoraccess, iblockdata3, this.a - 1, 10, 2, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 8, 0, 0, 12, 4, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 9, 1, 0, 11, 3, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 9, 1, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 9, 2, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 9, 3, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 10, 3, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 11, 3, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 11, 2, 1, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 11, 1, 1, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 4, 1, 1, 8, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 4, 1, 2, 8, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 12, 1, 1, 16, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 12, 1, 2, 16, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 5, 4, 5, this.a - 6, 4, this.c - 6, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 9, 4, 9, 11, 4, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 8, 1, 8, 8, 3, 8, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 12, 1, 8, 12, 3, 8, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 8, 1, 12, 8, 3, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 12, 1, 12, 12, 3, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 1, 1, 5, 4, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 5, 1, 5, this.a - 2, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 6, 7, 9, 6, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 7, 7, 9, this.a - 7, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 5, 5, 9, 5, 7, 11, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 6, 5, 9, this.a - 6, 7, 11, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 5, 5, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 5, 6, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 6, 6, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), this.a - 6, 5, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), this.a - 6, 6, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), this.a - 7, 6, 10, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 2, 4, 4, 2, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 3, 4, 4, this.a - 3, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, iblockdata, 2, 4, 5, structureboundingbox);
        this.a(generatoraccess, iblockdata, 2, 3, 4, structureboundingbox);
        this.a(generatoraccess, iblockdata, this.a - 3, 4, 5, structureboundingbox);
        this.a(generatoraccess, iblockdata, this.a - 3, 3, 4, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 1, 1, 3, 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 3, 1, 3, this.a - 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, Blocks.SANDSTONE.getBlockData(), 1, 1, 2, structureboundingbox);
        this.a(generatoraccess, Blocks.SANDSTONE.getBlockData(), this.a - 2, 1, 2, structureboundingbox);
        this.a(generatoraccess, Blocks.SANDSTONE_SLAB.getBlockData(), 1, 2, 2, structureboundingbox);
        this.a(generatoraccess, Blocks.SANDSTONE_SLAB.getBlockData(), this.a - 2, 2, 2, structureboundingbox);
        this.a(generatoraccess, iblockdata3, 2, 1, 2, structureboundingbox);
        this.a(generatoraccess, iblockdata2, this.a - 3, 1, 2, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 4, 3, 5, 4, 3, 17, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 5, 3, 5, this.a - 5, 3, 17, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 3, 1, 5, 4, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, this.a - 6, 1, 5, this.a - 5, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);

        int k;

        for (k = 5; k <= 17; k += 2) {
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 4, 1, k, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 4, 2, k, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), this.a - 5, 1, k, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), this.a - 5, 2, k, structureboundingbox);
        }

        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 7, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 8, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 0, 9, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 0, 9, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 8, 0, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 12, 0, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 7, 0, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 13, 0, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 0, 11, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 0, 11, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 12, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 13, structureboundingbox);
        this.a(generatoraccess, Blocks.BLUE_TERRACOTTA.getBlockData(), 10, 0, 10, structureboundingbox);

        for (k = 0; k <= this.a - 1; k += this.a - 1) {
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 2, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 2, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 2, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 3, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 3, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 3, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 4, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), k, 4, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 4, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 5, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 5, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 5, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 6, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), k, 6, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 6, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 7, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 7, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 7, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 8, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 8, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 8, 3, structureboundingbox);
        }

        for (k = 2; k <= this.a - 3; k += this.a - 3 - 2) {
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k - 1, 2, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 2, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k + 1, 2, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k - 1, 3, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 3, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k + 1, 3, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k - 1, 4, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), k, 4, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k + 1, 4, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k - 1, 5, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 5, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k + 1, 5, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k - 1, 6, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), k, 6, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k + 1, 6, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k - 1, 7, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k, 7, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), k + 1, 7, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k - 1, 8, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k, 8, 0, structureboundingbox);
            this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), k + 1, 8, 0, structureboundingbox);
        }

        this.a(generatoraccess, structureboundingbox, 8, 4, 0, 12, 6, 0, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 8, 6, 0, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 12, 6, 0, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 5, 0, structureboundingbox);
        this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, 5, 0, structureboundingbox);
        this.a(generatoraccess, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 5, 0, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 8, -14, 8, 12, -11, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 8, -10, 8, 12, -10, 12, Blocks.CHISELED_SANDSTONE.getBlockData(), Blocks.CHISELED_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 8, -9, 8, 12, -9, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 8, -8, 8, 12, -1, 12, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(generatoraccess, structureboundingbox, 9, -11, 9, 11, -1, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, Blocks.STONE_PRESSURE_PLATE.getBlockData(), 10, -11, 10, structureboundingbox);
        this.a(generatoraccess, structureboundingbox, 9, -13, 9, 11, -13, 11, Blocks.TNT.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 8, -11, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 8, -10, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 7, -10, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 7, -11, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 12, -11, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 12, -10, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 13, -10, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 13, -11, 10, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, -11, 8, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, -10, 8, structureboundingbox);
        this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, -10, 7, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 10, -11, 7, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, -11, 12, structureboundingbox);
        this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, -10, 12, structureboundingbox);
        this.a(generatoraccess, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, -10, 13, structureboundingbox);
        this.a(generatoraccess, Blocks.CUT_SANDSTONE.getBlockData(), 10, -11, 13, structureboundingbox);
        Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();

            if (!this.e[enumdirection.get2DRotationValue()]) {
                int l = enumdirection.getAdjacentX() * 2;
                int i1 = enumdirection.getAdjacentZ() * 2;

                this.e[enumdirection.get2DRotationValue()] = this.a(generatoraccess, structureboundingbox, random, 10 + l, -11, 10 + i1, LootTables.z);
            }
        }

        return true;
    }
}
