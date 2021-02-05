package net.minecraft.server;

import java.util.Random;

public class WorldGenJunglePyramidPiece extends WorldGenScatteredPiece {

    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private static final WorldGenJunglePyramidPiece.a i = new WorldGenJunglePyramidPiece.a();

    public WorldGenJunglePyramidPiece(Random random, int i, int j) {
        super(WorldGenFeatureStructurePieceType.I, random, i, 64, j, 12, 10, 15);
    }

    public WorldGenJunglePyramidPiece(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
        super(WorldGenFeatureStructurePieceType.I, nbttagcompound);
        this.e = nbttagcompound.getBoolean("placedMainChest");
        this.f = nbttagcompound.getBoolean("placedHiddenChest");
        this.g = nbttagcompound.getBoolean("placedTrap1");
        this.h = nbttagcompound.getBoolean("placedTrap2");
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("placedMainChest", this.e);
        nbttagcompound.setBoolean("placedHiddenChest", this.f);
        nbttagcompound.setBoolean("placedTrap1", this.g);
        nbttagcompound.setBoolean("placedTrap2", this.h);
    }

    @Override
    public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
        if (!this.a(generatoraccess, structureboundingbox, 0)) {
            return false;
        } else {
            this.a(generatoraccess, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 1, 2, 9, 2, 2, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 1, 12, 9, 2, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 1, 3, 2, 2, 11, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 9, 1, 3, 9, 2, 11, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 1, 3, 1, 10, 6, 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 1, 3, 13, 10, 6, 13, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 1, 3, 2, 1, 6, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 10, 3, 2, 10, 6, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 3, 2, 9, 3, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 6, 2, 9, 6, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 3, 7, 3, 8, 7, 11, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 4, 8, 4, 7, 8, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.b(generatoraccess, structureboundingbox, 3, 1, 3, 8, 2, 11);
            this.b(generatoraccess, structureboundingbox, 4, 3, 6, 7, 3, 9);
            this.b(generatoraccess, structureboundingbox, 2, 4, 2, 9, 5, 12);
            this.b(generatoraccess, structureboundingbox, 4, 6, 5, 7, 6, 9);
            this.b(generatoraccess, structureboundingbox, 5, 7, 6, 6, 7, 8);
            this.b(generatoraccess, structureboundingbox, 5, 1, 2, 6, 2, 2);
            this.b(generatoraccess, structureboundingbox, 5, 2, 12, 6, 2, 12);
            this.b(generatoraccess, structureboundingbox, 5, 5, 1, 6, 5, 1);
            this.b(generatoraccess, structureboundingbox, 5, 5, 13, 6, 5, 13);
            this.a(generatoraccess, Blocks.AIR.getBlockData(), 1, 5, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, 5, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.AIR.getBlockData(), 1, 5, 9, structureboundingbox);
            this.a(generatoraccess, Blocks.AIR.getBlockData(), 10, 5, 9, structureboundingbox);

            int i;

            for (i = 0; i <= 14; i += 14) {
                this.a(generatoraccess, structureboundingbox, 2, 4, i, 2, 5, i, false, random, WorldGenJunglePyramidPiece.i);
                this.a(generatoraccess, structureboundingbox, 4, 4, i, 4, 5, i, false, random, WorldGenJunglePyramidPiece.i);
                this.a(generatoraccess, structureboundingbox, 7, 4, i, 7, 5, i, false, random, WorldGenJunglePyramidPiece.i);
                this.a(generatoraccess, structureboundingbox, 9, 4, i, 9, 5, i, false, random, WorldGenJunglePyramidPiece.i);
            }

            this.a(generatoraccess, structureboundingbox, 5, 6, 0, 6, 6, 0, false, random, WorldGenJunglePyramidPiece.i);

            for (i = 0; i <= 11; i += 11) {
                for (int j = 2; j <= 12; j += 2) {
                    this.a(generatoraccess, structureboundingbox, i, 4, j, i, 5, j, false, random, WorldGenJunglePyramidPiece.i);
                }

                this.a(generatoraccess, structureboundingbox, i, 6, 5, i, 6, 5, false, random, WorldGenJunglePyramidPiece.i);
                this.a(generatoraccess, structureboundingbox, i, 6, 9, i, 6, 9, false, random, WorldGenJunglePyramidPiece.i);
            }

            this.a(generatoraccess, structureboundingbox, 2, 7, 2, 2, 9, 2, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 9, 7, 2, 9, 9, 2, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 2, 7, 12, 2, 9, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 9, 7, 12, 9, 9, 12, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 4, 9, 4, 4, 9, 4, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 7, 9, 4, 7, 9, 4, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 4, 9, 10, 4, 9, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 7, 9, 10, 7, 9, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 5, 9, 7, 6, 9, 7, false, random, WorldGenJunglePyramidPiece.i);
            IBlockData iblockdata = (IBlockData) Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.EAST);
            IBlockData iblockdata1 = (IBlockData) Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.WEST);
            IBlockData iblockdata2 = (IBlockData) Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
            IBlockData iblockdata3 = (IBlockData) Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);

            this.a(generatoraccess, iblockdata3, 5, 9, 6, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 6, 9, 6, structureboundingbox);
            this.a(generatoraccess, iblockdata2, 5, 9, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata2, 6, 9, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 4, 0, 0, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 5, 0, 0, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 6, 0, 0, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 7, 0, 0, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 4, 1, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 4, 2, 9, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 4, 3, 10, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 7, 1, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 7, 2, 9, structureboundingbox);
            this.a(generatoraccess, iblockdata3, 7, 3, 10, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 4, 1, 9, 4, 1, 9, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 7, 1, 9, 7, 1, 9, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 4, 1, 10, 7, 2, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 5, 4, 5, 6, 4, 5, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, iblockdata, 4, 4, 5, structureboundingbox);
            this.a(generatoraccess, iblockdata1, 7, 4, 5, structureboundingbox);

            int k;

            for (k = 0; k < 4; ++k) {
                this.a(generatoraccess, iblockdata2, 5, 0 - k, 6 + k, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 6, 0 - k, 6 + k, structureboundingbox);
                this.b(generatoraccess, structureboundingbox, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
            }

            this.b(generatoraccess, structureboundingbox, 1, -3, 12, 10, -1, 13);
            this.b(generatoraccess, structureboundingbox, 1, -3, 1, 3, -1, 13);
            this.b(generatoraccess, structureboundingbox, 1, -3, 1, 9, -1, 5);

            for (k = 1; k <= 13; k += 2) {
                this.a(generatoraccess, structureboundingbox, 1, -3, k, 1, -2, k, false, random, WorldGenJunglePyramidPiece.i);
            }

            for (k = 2; k <= 12; k += 2) {
                this.a(generatoraccess, structureboundingbox, 1, -1, k, 3, -1, k, false, random, WorldGenJunglePyramidPiece.i);
            }

            this.a(generatoraccess, structureboundingbox, 2, -2, 1, 5, -2, 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 7, -2, 1, 9, -2, 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 6, -3, 1, 6, -3, 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 6, -1, 1, 6, -1, 1, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.EAST)).set(BlockTripwireHook.ATTACHED, true), 1, -3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.WEST)).set(BlockTripwireHook.ATTACHED, true), 4, -3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.EAST, true)).set(BlockTripwire.WEST, true)).set(BlockTripwire.ATTACHED, true), 2, -3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.EAST, true)).set(BlockTripwire.WEST, true)).set(BlockTripwire.ATTACHED, true), 3, -3, 8, structureboundingbox);
            IBlockData iblockdata4 = (IBlockData) ((IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE)).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE);

            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE), 5, -3, 7, structureboundingbox);
            this.a(generatoraccess, iblockdata4, 5, -3, 6, structureboundingbox);
            this.a(generatoraccess, iblockdata4, 5, -3, 5, structureboundingbox);
            this.a(generatoraccess, iblockdata4, 5, -3, 4, structureboundingbox);
            this.a(generatoraccess, iblockdata4, 5, -3, 3, structureboundingbox);
            this.a(generatoraccess, iblockdata4, 5, -3, 2, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE)).set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE), 5, -3, 1, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.EAST, BlockPropertyRedstoneSide.SIDE), 4, -3, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 3, -3, 1, structureboundingbox);
            if (!this.g) {
                this.g = this.a(generatoraccess, structureboundingbox, random, 3, -2, 1, EnumDirection.NORTH, LootTables.B);
            }

            this.a(generatoraccess, (IBlockData) Blocks.VINE.getBlockData().set(BlockVine.SOUTH, true), 3, -2, 2, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.NORTH)).set(BlockTripwireHook.ATTACHED, true), 7, -3, 1, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.SOUTH)).set(BlockTripwireHook.ATTACHED, true), 7, -3, 5, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, true)).set(BlockTripwire.SOUTH, true)).set(BlockTripwire.ATTACHED, true), 7, -3, 2, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, true)).set(BlockTripwire.SOUTH, true)).set(BlockTripwire.ATTACHED, true), 7, -3, 3, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, true)).set(BlockTripwire.SOUTH, true)).set(BlockTripwire.ATTACHED, true), 7, -3, 4, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.EAST, BlockPropertyRedstoneSide.SIDE), 8, -3, 6, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE)).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE), 9, -3, 6, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE)).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.UP), 9, -3, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 4, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE), 9, -2, 4, structureboundingbox);
            if (!this.h) {
                this.h = this.a(generatoraccess, structureboundingbox, random, 9, -2, 3, EnumDirection.WEST, LootTables.B);
            }

            this.a(generatoraccess, (IBlockData) Blocks.VINE.getBlockData().set(BlockVine.EAST, true), 8, -1, 3, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.VINE.getBlockData().set(BlockVine.EAST, true), 8, -2, 3, structureboundingbox);
            if (!this.e) {
                this.e = this.a(generatoraccess, structureboundingbox, random, 8, -3, 3, LootTables.A);
            }

            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 4, -3, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -2, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -1, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 6, -3, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -2, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -1, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 5, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 9, -1, 1, 9, -1, 5, false, random, WorldGenJunglePyramidPiece.i);
            this.b(generatoraccess, structureboundingbox, 8, -3, 8, 10, -1, 10);
            this.a(generatoraccess, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 8, -2, 11, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 9, -2, 11, structureboundingbox);
            this.a(generatoraccess, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 10, -2, 11, structureboundingbox);
            IBlockData iblockdata5 = (IBlockData) ((IBlockData) Blocks.LEVER.getBlockData().set(BlockLever.FACING, EnumDirection.NORTH)).set(BlockLever.FACE, BlockPropertyAttachPosition.WALL);

            this.a(generatoraccess, iblockdata5, 8, -2, 12, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 9, -2, 12, structureboundingbox);
            this.a(generatoraccess, iblockdata5, 10, -2, 12, structureboundingbox);
            this.a(generatoraccess, structureboundingbox, 8, -3, 8, 8, -3, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, structureboundingbox, 10, -3, 8, 10, -3, 10, false, random, WorldGenJunglePyramidPiece.i);
            this.a(generatoraccess, Blocks.MOSSY_COBBLESTONE.getBlockData(), 10, -2, 9, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE), 8, -2, 9, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE), 8, -2, 10, structureboundingbox);
            this.a(generatoraccess, Blocks.REDSTONE_WIRE.getBlockData(), 10, -1, 9, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.UP), 9, -2, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.WEST), 10, -2, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.WEST), 10, -1, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) Blocks.REPEATER.getBlockData().set(BlockRepeater.FACING, EnumDirection.NORTH), 10, -2, 10, structureboundingbox);
            if (!this.f) {
                this.f = this.a(generatoraccess, structureboundingbox, random, 9, -3, 10, LootTables.A);
            }

            return true;
        }
    }

    static class a extends StructurePiece.StructurePieceBlockSelector {

        private a() {}

        @Override
        public void a(Random random, int i, int j, int k, boolean flag) {
            if (random.nextFloat() < 0.4F) {
                this.a = Blocks.COBBLESTONE.getBlockData();
            } else {
                this.a = Blocks.MOSSY_COBBLESTONE.getBlockData();
            }

        }
    }
}
