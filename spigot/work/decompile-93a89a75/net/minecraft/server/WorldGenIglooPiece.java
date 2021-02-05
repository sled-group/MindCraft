package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldGenIglooPiece {

    private static final MinecraftKey a = new MinecraftKey("igloo/top");
    private static final MinecraftKey b = new MinecraftKey("igloo/middle");
    private static final MinecraftKey c = new MinecraftKey("igloo/bottom");
    private static final Map<MinecraftKey, BlockPosition> d = ImmutableMap.of(WorldGenIglooPiece.a, new BlockPosition(3, 5, 5), WorldGenIglooPiece.b, new BlockPosition(1, 3, 1), WorldGenIglooPiece.c, new BlockPosition(3, 6, 7));
    private static final Map<MinecraftKey, BlockPosition> e = ImmutableMap.of(WorldGenIglooPiece.a, BlockPosition.ZERO, WorldGenIglooPiece.b, new BlockPosition(2, -3, 4), WorldGenIglooPiece.c, new BlockPosition(0, -3, -2));

    public static void a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, List<StructurePiece> list, Random random, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        if (random.nextDouble() < 0.5D) {
            int i = random.nextInt(8) + 4;

            list.add(new WorldGenIglooPiece.a(definedstructuremanager, WorldGenIglooPiece.c, blockposition, enumblockrotation, i * 3));

            for (int j = 0; j < i - 1; ++j) {
                list.add(new WorldGenIglooPiece.a(definedstructuremanager, WorldGenIglooPiece.b, blockposition, enumblockrotation, j * 3));
            }
        }

        list.add(new WorldGenIglooPiece.a(definedstructuremanager, WorldGenIglooPiece.a, blockposition, enumblockrotation, 0));
    }

    public static class a extends DefinedStructurePiece {

        private final MinecraftKey d;
        private final EnumBlockRotation e;

        public a(DefinedStructureManager definedstructuremanager, MinecraftKey minecraftkey, BlockPosition blockposition, EnumBlockRotation enumblockrotation, int i) {
            super(WorldGenFeatureStructurePieceType.K, 0);
            this.d = minecraftkey;
            BlockPosition blockposition1 = (BlockPosition) WorldGenIglooPiece.e.get(minecraftkey);

            this.c = blockposition.b(blockposition1.getX(), blockposition1.getY() - i, blockposition1.getZ());
            this.e = enumblockrotation;
            this.a(definedstructuremanager);
        }

        public a(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.K, nbttagcompound);
            this.d = new MinecraftKey(nbttagcompound.getString("Template"));
            this.e = EnumBlockRotation.valueOf(nbttagcompound.getString("Rot"));
            this.a(definedstructuremanager);
        }

        private void a(DefinedStructureManager definedstructuremanager) {
            DefinedStructure definedstructure = definedstructuremanager.a(this.d);
            DefinedStructureInfo definedstructureinfo = (new DefinedStructureInfo()).a(this.e).a(EnumBlockMirror.NONE).a((BlockPosition) WorldGenIglooPiece.d.get(this.d)).a((DefinedStructureProcessor) DefinedStructureProcessorBlockIgnore.a);

            this.a(definedstructure, this.c, definedstructureinfo);
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setString("Template", this.d.toString());
            nbttagcompound.setString("Rot", this.e.name());
        }

        @Override
        protected void a(String s, BlockPosition blockposition, GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox) {
            if ("chest".equals(s)) {
                generatoraccess.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
                TileEntity tileentity = generatoraccess.getTileEntity(blockposition.down());

                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(LootTables.C, random.nextLong());
                }

            }
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            DefinedStructureInfo definedstructureinfo = (new DefinedStructureInfo()).a(this.e).a(EnumBlockMirror.NONE).a((BlockPosition) WorldGenIglooPiece.d.get(this.d)).a((DefinedStructureProcessor) DefinedStructureProcessorBlockIgnore.a);
            BlockPosition blockposition = (BlockPosition) WorldGenIglooPiece.e.get(this.d);
            BlockPosition blockposition1 = this.c.a((BaseBlockPosition) DefinedStructure.a(definedstructureinfo, new BlockPosition(3 - blockposition.getX(), 0, 0 - blockposition.getZ())));
            int i = generatoraccess.a(HeightMap.Type.WORLD_SURFACE_WG, blockposition1.getX(), blockposition1.getZ());
            BlockPosition blockposition2 = this.c;

            this.c = this.c.b(0, i - 90 - 1, 0);
            boolean flag = super.a(generatoraccess, random, structureboundingbox, chunkcoordintpair);

            if (this.d.equals(WorldGenIglooPiece.a)) {
                BlockPosition blockposition3 = this.c.a((BaseBlockPosition) DefinedStructure.a(definedstructureinfo, new BlockPosition(3, 0, 5)));
                IBlockData iblockdata = generatoraccess.getType(blockposition3.down());

                if (!iblockdata.isAir() && iblockdata.getBlock() != Blocks.LADDER) {
                    generatoraccess.setTypeAndData(blockposition3, Blocks.SNOW_BLOCK.getBlockData(), 3);
                }
            }

            this.c = blockposition2;
            return flag;
        }
    }
}
