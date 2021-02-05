package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenShipwreck {

    private static final BlockPosition a = new BlockPosition(4, 0, 15);
    private static final MinecraftKey[] b = new MinecraftKey[]{new MinecraftKey("shipwreck/with_mast"), new MinecraftKey("shipwreck/sideways_full"), new MinecraftKey("shipwreck/sideways_fronthalf"), new MinecraftKey("shipwreck/sideways_backhalf"), new MinecraftKey("shipwreck/rightsideup_full"), new MinecraftKey("shipwreck/rightsideup_fronthalf"), new MinecraftKey("shipwreck/rightsideup_backhalf"), new MinecraftKey("shipwreck/with_mast_degraded"), new MinecraftKey("shipwreck/rightsideup_full_degraded"), new MinecraftKey("shipwreck/rightsideup_fronthalf_degraded"), new MinecraftKey("shipwreck/rightsideup_backhalf_degraded")};
    private static final MinecraftKey[] c = new MinecraftKey[]{new MinecraftKey("shipwreck/with_mast"), new MinecraftKey("shipwreck/upsidedown_full"), new MinecraftKey("shipwreck/upsidedown_fronthalf"), new MinecraftKey("shipwreck/upsidedown_backhalf"), new MinecraftKey("shipwreck/sideways_full"), new MinecraftKey("shipwreck/sideways_fronthalf"), new MinecraftKey("shipwreck/sideways_backhalf"), new MinecraftKey("shipwreck/rightsideup_full"), new MinecraftKey("shipwreck/rightsideup_fronthalf"), new MinecraftKey("shipwreck/rightsideup_backhalf"), new MinecraftKey("shipwreck/with_mast_degraded"), new MinecraftKey("shipwreck/upsidedown_full_degraded"), new MinecraftKey("shipwreck/upsidedown_fronthalf_degraded"), new MinecraftKey("shipwreck/upsidedown_backhalf_degraded"), new MinecraftKey("shipwreck/sideways_full_degraded"), new MinecraftKey("shipwreck/sideways_fronthalf_degraded"), new MinecraftKey("shipwreck/sideways_backhalf_degraded"), new MinecraftKey("shipwreck/rightsideup_full_degraded"), new MinecraftKey("shipwreck/rightsideup_fronthalf_degraded"), new MinecraftKey("shipwreck/rightsideup_backhalf_degraded")};

    public static void a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, List<StructurePiece> list, Random random, WorldGenFeatureShipwreckConfiguration worldgenfeatureshipwreckconfiguration) {
        MinecraftKey minecraftkey = worldgenfeatureshipwreckconfiguration.a ? WorldGenShipwreck.b[random.nextInt(WorldGenShipwreck.b.length)] : WorldGenShipwreck.c[random.nextInt(WorldGenShipwreck.c.length)];

        list.add(new WorldGenShipwreck.a(definedstructuremanager, minecraftkey, blockposition, enumblockrotation, worldgenfeatureshipwreckconfiguration.a));
    }

    public static class a extends DefinedStructurePiece {

        private final EnumBlockRotation d;
        private final MinecraftKey e;
        private final boolean f;

        public a(DefinedStructureManager definedstructuremanager, MinecraftKey minecraftkey, BlockPosition blockposition, EnumBlockRotation enumblockrotation, boolean flag) {
            super(WorldGenFeatureStructurePieceType.ac, 0);
            this.c = blockposition;
            this.d = enumblockrotation;
            this.e = minecraftkey;
            this.f = flag;
            this.a(definedstructuremanager);
        }

        public a(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.ac, nbttagcompound);
            this.e = new MinecraftKey(nbttagcompound.getString("Template"));
            this.f = nbttagcompound.getBoolean("isBeached");
            this.d = EnumBlockRotation.valueOf(nbttagcompound.getString("Rot"));
            this.a(definedstructuremanager);
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setString("Template", this.e.toString());
            nbttagcompound.setBoolean("isBeached", this.f);
            nbttagcompound.setString("Rot", this.d.name());
        }

        private void a(DefinedStructureManager definedstructuremanager) {
            DefinedStructure definedstructure = definedstructuremanager.a(this.e);
            DefinedStructureInfo definedstructureinfo = (new DefinedStructureInfo()).a(this.d).a(EnumBlockMirror.NONE).a(WorldGenShipwreck.a).a((DefinedStructureProcessor) DefinedStructureProcessorBlockIgnore.c);

            this.a(definedstructure, this.c, definedstructureinfo);
        }

        @Override
        protected void a(String s, BlockPosition blockposition, GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox) {
            if ("map_chest".equals(s)) {
                TileEntityLootable.a(generatoraccess, random, blockposition.down(), LootTables.H);
            } else if ("treasure_chest".equals(s)) {
                TileEntityLootable.a(generatoraccess, random, blockposition.down(), LootTables.J);
            } else if ("supply_chest".equals(s)) {
                TileEntityLootable.a(generatoraccess, random, blockposition.down(), LootTables.I);
            }

        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            int i = 256;
            int j = 0;
            BlockPosition blockposition = this.c.b(this.a.a().getX() - 1, 0, this.a.a().getZ() - 1);

            int k;

            for (Iterator iterator = BlockPosition.a(this.c, blockposition).iterator(); iterator.hasNext(); i = Math.min(i, k)) {
                BlockPosition blockposition1 = (BlockPosition) iterator.next();

                k = generatoraccess.a(this.f ? HeightMap.Type.WORLD_SURFACE_WG : HeightMap.Type.OCEAN_FLOOR_WG, blockposition1.getX(), blockposition1.getZ());
                j += k;
            }

            j /= this.a.a().getX() * this.a.a().getZ();
            int l = this.f ? i - this.a.a().getY() / 2 - random.nextInt(3) : j;

            this.c = new BlockPosition(this.c.getX(), l, this.c.getZ());
            return super.a(generatoraccess, random, structureboundingbox, chunkcoordintpair);
        }
    }
}
