package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenStrongholdPieces {

    private static final WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight[] a = new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight[]{new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdStairs.class, 40, 0), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdPrison.class, 5, 5), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.class, 20, 0), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.class, 20, 0), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.class, 10, 6), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdStairsStraight.class, 5, 5), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdStairs2.class, 5, 5), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdCrossing.class, 5, 4), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdChestCorridor.class, 5, 4), new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdLibrary.class, 10, 2) {
                @Override
                public boolean a(int i) {
                    return super.a(i) && i > 4;
                }
            }, new WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight(WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom.class, 20, 1) {
                @Override
                public boolean a(int i) {
                    return super.a(i) && i > 5;
                }
            }};
    private static List<WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight> b;
    private static Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> c;
    private static int d;
    private static final WorldGenStrongholdPieces.WorldGenStrongholdStones e = new WorldGenStrongholdPieces.WorldGenStrongholdStones();

    public static void a() {
        WorldGenStrongholdPieces.b = Lists.newArrayList();
        WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight[] aworldgenstrongholdpieces_worldgenstrongholdpieceweight = WorldGenStrongholdPieces.a;
        int i = aworldgenstrongholdpieces_worldgenstrongholdpieceweight.length;

        for (int j = 0; j < i; ++j) {
            WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight worldgenstrongholdpieces_worldgenstrongholdpieceweight = aworldgenstrongholdpieces_worldgenstrongholdpieceweight[j];

            worldgenstrongholdpieces_worldgenstrongholdpieceweight.c = 0;
            WorldGenStrongholdPieces.b.add(worldgenstrongholdpieces_worldgenstrongholdpieceweight);
        }

        WorldGenStrongholdPieces.c = null;
    }

    private static boolean c() {
        boolean flag = false;

        WorldGenStrongholdPieces.d = 0;

        WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight worldgenstrongholdpieces_worldgenstrongholdpieceweight;

        for (Iterator iterator = WorldGenStrongholdPieces.b.iterator(); iterator.hasNext(); WorldGenStrongholdPieces.d += worldgenstrongholdpieces_worldgenstrongholdpieceweight.b) {
            worldgenstrongholdpieces_worldgenstrongholdpieceweight = (WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight) iterator.next();
            if (worldgenstrongholdpieces_worldgenstrongholdpieceweight.d > 0 && worldgenstrongholdpieces_worldgenstrongholdpieceweight.c < worldgenstrongholdpieces_worldgenstrongholdpieceweight.d) {
                flag = true;
            }
        }

        return flag;
    }

    private static WorldGenStrongholdPieces.WorldGenStrongholdPiece a(Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> oclass, List<StructurePiece> list, Random random, int i, int j, int k, @Nullable EnumDirection enumdirection, int l) {
        Object object = null;

        if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdStairs.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdStairs.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdPrison.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdPrison.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdStairsStraight.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdStairsStraight.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdStairs2.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdStairs2.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdCrossing.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdCrossing.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdChestCorridor.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdChestCorridor.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdLibrary.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdLibrary.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom.class) {
            object = WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom.a(list, i, j, k, enumdirection, l);
        }

        return (WorldGenStrongholdPieces.WorldGenStrongholdPiece) object;
    }

    private static WorldGenStrongholdPieces.WorldGenStrongholdPiece b(WorldGenStrongholdPieces.WorldGenStrongholdStart worldgenstrongholdpieces_worldgenstrongholdstart, List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        if (!c()) {
            return null;
        } else {
            if (WorldGenStrongholdPieces.c != null) {
                WorldGenStrongholdPieces.WorldGenStrongholdPiece worldgenstrongholdpieces_worldgenstrongholdpiece = a(WorldGenStrongholdPieces.c, list, random, i, j, k, enumdirection, l);

                WorldGenStrongholdPieces.c = null;
                if (worldgenstrongholdpieces_worldgenstrongholdpiece != null) {
                    return worldgenstrongholdpieces_worldgenstrongholdpiece;
                }
            }

            int i1 = 0;

            while (i1 < 5) {
                ++i1;
                int j1 = random.nextInt(WorldGenStrongholdPieces.d);
                Iterator iterator = WorldGenStrongholdPieces.b.iterator();

                while (iterator.hasNext()) {
                    WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight worldgenstrongholdpieces_worldgenstrongholdpieceweight = (WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight) iterator.next();

                    j1 -= worldgenstrongholdpieces_worldgenstrongholdpieceweight.b;
                    if (j1 < 0) {
                        if (!worldgenstrongholdpieces_worldgenstrongholdpieceweight.a(l) || worldgenstrongholdpieces_worldgenstrongholdpieceweight == worldgenstrongholdpieces_worldgenstrongholdstart.a) {
                            break;
                        }

                        WorldGenStrongholdPieces.WorldGenStrongholdPiece worldgenstrongholdpieces_worldgenstrongholdpiece1 = a(worldgenstrongholdpieces_worldgenstrongholdpieceweight.a, list, random, i, j, k, enumdirection, l);

                        if (worldgenstrongholdpieces_worldgenstrongholdpiece1 != null) {
                            ++worldgenstrongholdpieces_worldgenstrongholdpieceweight.c;
                            worldgenstrongholdpieces_worldgenstrongholdstart.a = worldgenstrongholdpieces_worldgenstrongholdpieceweight;
                            if (!worldgenstrongholdpieces_worldgenstrongholdpieceweight.a()) {
                                WorldGenStrongholdPieces.b.remove(worldgenstrongholdpieces_worldgenstrongholdpieceweight);
                            }

                            return worldgenstrongholdpieces_worldgenstrongholdpiece1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = WorldGenStrongholdPieces.WorldGenStrongholdCorridor.a(list, random, i, j, k, enumdirection);

            if (structureboundingbox != null && structureboundingbox.b > 1) {
                return new WorldGenStrongholdPieces.WorldGenStrongholdCorridor(l, structureboundingbox, enumdirection);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece c(WorldGenStrongholdPieces.WorldGenStrongholdStart worldgenstrongholdpieces_worldgenstrongholdstart, List<StructurePiece> list, Random random, int i, int j, int k, @Nullable EnumDirection enumdirection, int l) {
        if (l > 50) {
            return null;
        } else if (Math.abs(i - worldgenstrongholdpieces_worldgenstrongholdstart.g().a) <= 112 && Math.abs(k - worldgenstrongholdpieces_worldgenstrongholdstart.g().c) <= 112) {
            WorldGenStrongholdPieces.WorldGenStrongholdPiece worldgenstrongholdpieces_worldgenstrongholdpiece = b(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, i, j, k, enumdirection, l + 1);

            if (worldgenstrongholdpieces_worldgenstrongholdpiece != null) {
                list.add(worldgenstrongholdpieces_worldgenstrongholdpiece);
                worldgenstrongholdpieces_worldgenstrongholdstart.c.add(worldgenstrongholdpieces_worldgenstrongholdpiece);
            }

            return worldgenstrongholdpieces_worldgenstrongholdpiece;
        } else {
            return null;
        }
    }

    static class WorldGenStrongholdStones extends StructurePiece.StructurePieceBlockSelector {

        private WorldGenStrongholdStones() {}

        @Override
        public void a(Random random, int i, int j, int k, boolean flag) {
            if (flag) {
                float f = random.nextFloat();

                if (f < 0.2F) {
                    this.a = Blocks.CRACKED_STONE_BRICKS.getBlockData();
                } else if (f < 0.5F) {
                    this.a = Blocks.MOSSY_STONE_BRICKS.getBlockData();
                } else if (f < 0.55F) {
                    this.a = Blocks.INFESTED_STONE_BRICKS.getBlockData();
                } else {
                    this.a = Blocks.STONE_BRICKS.getBlockData();
                }
            } else {
                this.a = Blocks.CAVE_AIR.getBlockData();
            }

        }
    }

    public static class WorldGenStrongholdPortalRoom extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private boolean a;

        public WorldGenStrongholdPortalRoom(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.A, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdPortalRoom(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.A, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Mob");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Mob", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            if (structurepiece != null) {
                ((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece).b = this;
            }

        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom a(List<StructurePiece> list, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 8, 16, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom(l, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 10, 7, 15, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.GRATES, 4, 1, 0);
            byte b0 = 6;

            this.a(generatoraccess, structureboundingbox, 1, b0, 1, 1, b0, 14, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 9, b0, 1, 9, b0, 14, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 2, b0, 1, 8, b0, 2, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 2, b0, 14, 8, b0, 14, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 1, 1, 1, 2, 1, 4, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 8, 1, 1, 9, 1, 4, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 3, 1, 8, 7, 1, 12, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
            IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true)).set(BlockIronBars.EAST, true);

            int i;

            for (i = 3; i < 14; i += 2) {
                this.a(generatoraccess, structureboundingbox, 0, 3, i, 0, 4, i, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, 10, 3, i, 10, 4, i, iblockdata, iblockdata, false);
            }

            for (i = 2; i < 9; i += 2) {
                this.a(generatoraccess, structureboundingbox, i, 3, 15, i, 4, 15, iblockdata1, iblockdata1, false);
            }

            IBlockData iblockdata2 = (IBlockData) Blocks.STONE_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);

            this.a(generatoraccess, structureboundingbox, 4, 1, 5, 6, 1, 7, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 2, 6, 6, 2, 7, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 3, 7, 6, 3, 7, false, random, WorldGenStrongholdPieces.e);

            for (int j = 4; j <= 6; ++j) {
                this.a(generatoraccess, iblockdata2, j, 1, 4, structureboundingbox);
                this.a(generatoraccess, iblockdata2, j, 2, 5, structureboundingbox);
                this.a(generatoraccess, iblockdata2, j, 3, 6, structureboundingbox);
            }

            IBlockData iblockdata3 = (IBlockData) Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.NORTH);
            IBlockData iblockdata4 = (IBlockData) Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.SOUTH);
            IBlockData iblockdata5 = (IBlockData) Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.EAST);
            IBlockData iblockdata6 = (IBlockData) Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.WEST);
            boolean flag = true;
            boolean[] aboolean = new boolean[12];

            for (int k = 0; k < aboolean.length; ++k) {
                aboolean[k] = random.nextFloat() > 0.9F;
                flag &= aboolean[k];
            }

            this.a(generatoraccess, (IBlockData) iblockdata3.set(BlockEnderPortalFrame.EYE, aboolean[0]), 4, 3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata3.set(BlockEnderPortalFrame.EYE, aboolean[1]), 5, 3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata3.set(BlockEnderPortalFrame.EYE, aboolean[2]), 6, 3, 8, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata4.set(BlockEnderPortalFrame.EYE, aboolean[3]), 4, 3, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata4.set(BlockEnderPortalFrame.EYE, aboolean[4]), 5, 3, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata4.set(BlockEnderPortalFrame.EYE, aboolean[5]), 6, 3, 12, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata5.set(BlockEnderPortalFrame.EYE, aboolean[6]), 3, 3, 9, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata5.set(BlockEnderPortalFrame.EYE, aboolean[7]), 3, 3, 10, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata5.set(BlockEnderPortalFrame.EYE, aboolean[8]), 3, 3, 11, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata6.set(BlockEnderPortalFrame.EYE, aboolean[9]), 7, 3, 9, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata6.set(BlockEnderPortalFrame.EYE, aboolean[10]), 7, 3, 10, structureboundingbox);
            this.a(generatoraccess, (IBlockData) iblockdata6.set(BlockEnderPortalFrame.EYE, aboolean[11]), 7, 3, 11, structureboundingbox);
            if (flag) {
                IBlockData iblockdata7 = Blocks.END_PORTAL.getBlockData();

                this.a(generatoraccess, iblockdata7, 4, 3, 9, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 5, 3, 9, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 6, 3, 9, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 4, 3, 10, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 5, 3, 10, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 6, 3, 10, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 4, 3, 11, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 5, 3, 11, structureboundingbox);
                this.a(generatoraccess, iblockdata7, 6, 3, 11, structureboundingbox);
            }

            if (!this.a) {
                int l = this.d(3);
                BlockPosition blockposition = new BlockPosition(this.a(5, 6), l, this.b(5, 6));

                if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                    this.a = true;
                    generatoraccess.setTypeAndData(blockposition, Blocks.SPAWNER.getBlockData(), 2);
                    TileEntity tileentity = generatoraccess.getTileEntity(blockposition);

                    if (tileentity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner) tileentity).getSpawner().setMobName(EntityTypes.SILVERFISH);
                    }
                }
            }

            return true;
        }
    }

    public static class WorldGenStrongholdCrossing extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private final boolean a;
        private final boolean b;
        private final boolean c;
        private final boolean e;

        public WorldGenStrongholdCrossing(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.x, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
            this.a = random.nextBoolean();
            this.b = random.nextBoolean();
            this.c = random.nextBoolean();
            this.e = random.nextInt(3) > 0;
        }

        public WorldGenStrongholdCrossing(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.x, nbttagcompound);
            this.a = nbttagcompound.getBoolean("leftLow");
            this.b = nbttagcompound.getBoolean("leftHigh");
            this.c = nbttagcompound.getBoolean("rightLow");
            this.e = nbttagcompound.getBoolean("rightHigh");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("leftLow", this.a);
            nbttagcompound.setBoolean("leftHigh", this.b);
            nbttagcompound.setBoolean("rightLow", this.c);
            nbttagcompound.setBoolean("rightHigh", this.e);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            int i = 3;
            int j = 5;
            EnumDirection enumdirection = this.i();

            if (enumdirection == EnumDirection.WEST || enumdirection == EnumDirection.NORTH) {
                i = 8 - i;
                j = 8 - j;
            }

            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 5, 1);
            if (this.a) {
                this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, i, 1);
            }

            if (this.b) {
                this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, j, 7);
            }

            if (this.c) {
                this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, i, 1);
            }

            if (this.e) {
                this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, j, 7);
            }

        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdCrossing a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -3, 0, 10, 9, 11, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdCrossing(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 9, 8, 10, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 4, 3, 0);
            if (this.a) {
                this.a(generatoraccess, structureboundingbox, 0, 3, 1, 0, 5, 3, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, false);
            }

            if (this.c) {
                this.a(generatoraccess, structureboundingbox, 9, 3, 1, 9, 5, 3, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, false);
            }

            if (this.b) {
                this.a(generatoraccess, structureboundingbox, 0, 5, 7, 0, 7, 9, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, false);
            }

            if (this.e) {
                this.a(generatoraccess, structureboundingbox, 9, 5, 7, 9, 7, 9, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, false);
            }

            this.a(generatoraccess, structureboundingbox, 5, 1, 10, 7, 3, 10, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdCrossing.m, false);
            this.a(generatoraccess, structureboundingbox, 1, 2, 1, 8, 2, 6, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 1, 5, 4, 4, 9, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 8, 1, 5, 8, 4, 9, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 1, 4, 7, 3, 4, 9, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 1, 3, 5, 3, 3, 6, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 1, 3, 4, 3, 3, 4, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 1, 4, 6, 3, 4, 6, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 1, 7, 7, 1, 8, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 5, 1, 9, 7, 1, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 2, 7, 7, 2, 7, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 4, 5, 7, 4, 5, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 8, 5, 7, 8, 5, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
            this.a(generatoraccess, structureboundingbox, 5, 5, 7, 7, 5, 9, (IBlockData) Blocks.SMOOTH_STONE_SLAB.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE), (IBlockData) Blocks.SMOOTH_STONE_SLAB.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE), false);
            this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.SOUTH), 6, 5, 6, structureboundingbox);
            return true;
        }
    }

    public static class WorldGenStrongholdLibrary extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private final boolean a;

        public WorldGenStrongholdLibrary(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.z, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
            this.a = structureboundingbox.d() > 6;
        }

        public WorldGenStrongholdLibrary(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.z, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Tall");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Tall", this.a);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdLibrary a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 11, 15, enumdirection);

            if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
                structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 6, 15, enumdirection);
                if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
                    return null;
                }
            }

            return new WorldGenStrongholdPieces.WorldGenStrongholdLibrary(l, random, structureboundingbox, enumdirection);
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            byte b0 = 11;

            if (!this.a) {
                b0 = 6;
            }

            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 13, b0 - 1, 14, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 4, 1, 0);
            this.a(generatoraccess, structureboundingbox, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.COBWEB.getBlockData(), Blocks.COBWEB.getBlockData(), false, false);
            boolean flag = true;
            boolean flag1 = true;

            int i;

            for (i = 1; i <= 13; ++i) {
                if ((i - 1) % 4 == 0) {
                    this.a(generatoraccess, structureboundingbox, 1, 1, i, 1, 4, i, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                    this.a(generatoraccess, structureboundingbox, 12, 1, i, 12, 4, i, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST), 2, 3, i, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST), 11, 3, i, structureboundingbox);
                    if (this.a) {
                        this.a(generatoraccess, structureboundingbox, 1, 6, i, 1, 9, i, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                        this.a(generatoraccess, structureboundingbox, 12, 6, i, 12, 9, i, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                    }
                } else {
                    this.a(generatoraccess, structureboundingbox, 1, 1, i, 1, 4, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    this.a(generatoraccess, structureboundingbox, 12, 1, i, 12, 4, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    if (this.a) {
                        this.a(generatoraccess, structureboundingbox, 1, 6, i, 1, 9, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                        this.a(generatoraccess, structureboundingbox, 12, 6, i, 12, 9, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    }
                }
            }

            for (i = 3; i < 12; i += 2) {
                this.a(generatoraccess, structureboundingbox, 3, 1, i, 4, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 6, 1, i, 7, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 9, 1, i, 10, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
            }

            if (this.a) {
                this.a(generatoraccess, structureboundingbox, 1, 5, 1, 3, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 10, 5, 1, 12, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 4, 5, 1, 9, 5, 2, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                this.a(generatoraccess, structureboundingbox, 4, 5, 12, 9, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
                this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 9, 5, 11, structureboundingbox);
                this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 8, 5, 11, structureboundingbox);
                this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 9, 5, 10, structureboundingbox);
                IBlockData iblockdata = (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.WEST, true)).set(BlockFence.EAST, true);
                IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.SOUTH, true);

                this.a(generatoraccess, structureboundingbox, 3, 6, 3, 3, 6, 11, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, structureboundingbox, 10, 6, 3, 10, 6, 9, iblockdata1, iblockdata1, false);
                this.a(generatoraccess, structureboundingbox, 4, 6, 2, 9, 6, 2, iblockdata, iblockdata, false);
                this.a(generatoraccess, structureboundingbox, 4, 6, 12, 7, 6, 12, iblockdata, iblockdata, false);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.EAST, true), 3, 6, 2, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.EAST, true), 3, 6, 12, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.WEST, true), 10, 6, 2, structureboundingbox);

                for (int j = 0; j <= 2; ++j) {
                    this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.SOUTH, true)).set(BlockFence.WEST, true), 8 + j, 6, 12 - j, structureboundingbox);
                    if (j != 2) {
                        this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, true)).set(BlockFence.EAST, true), 8 + j, 6, 11 - j, structureboundingbox);
                    }
                }

                IBlockData iblockdata2 = (IBlockData) Blocks.LADDER.getBlockData().set(BlockLadder.FACING, EnumDirection.SOUTH);

                this.a(generatoraccess, iblockdata2, 10, 1, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 2, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 3, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 4, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 5, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 6, 13, structureboundingbox);
                this.a(generatoraccess, iblockdata2, 10, 7, 13, structureboundingbox);
                boolean flag2 = true;
                boolean flag3 = true;
                IBlockData iblockdata3 = (IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.EAST, true);

                this.a(generatoraccess, iblockdata3, 6, 9, 7, structureboundingbox);
                IBlockData iblockdata4 = (IBlockData) Blocks.OAK_FENCE.getBlockData().set(BlockFence.WEST, true);

                this.a(generatoraccess, iblockdata4, 7, 9, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata3, 6, 8, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata4, 7, 8, 7, structureboundingbox);
                IBlockData iblockdata5 = (IBlockData) ((IBlockData) iblockdata1.set(BlockFence.WEST, true)).set(BlockFence.EAST, true);

                this.a(generatoraccess, iblockdata5, 6, 7, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata5, 7, 7, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata3, 5, 7, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata4, 8, 7, 7, structureboundingbox);
                this.a(generatoraccess, (IBlockData) iblockdata3.set(BlockFence.NORTH, true), 6, 7, 6, structureboundingbox);
                this.a(generatoraccess, (IBlockData) iblockdata3.set(BlockFence.SOUTH, true), 6, 7, 8, structureboundingbox);
                this.a(generatoraccess, (IBlockData) iblockdata4.set(BlockFence.NORTH, true), 7, 7, 6, structureboundingbox);
                this.a(generatoraccess, (IBlockData) iblockdata4.set(BlockFence.SOUTH, true), 7, 7, 8, structureboundingbox);
                IBlockData iblockdata6 = Blocks.TORCH.getBlockData();

                this.a(generatoraccess, iblockdata6, 5, 8, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata6, 8, 8, 7, structureboundingbox);
                this.a(generatoraccess, iblockdata6, 6, 8, 6, structureboundingbox);
                this.a(generatoraccess, iblockdata6, 6, 8, 8, structureboundingbox);
                this.a(generatoraccess, iblockdata6, 7, 8, 6, structureboundingbox);
                this.a(generatoraccess, iblockdata6, 7, 8, 8, structureboundingbox);
            }

            this.a(generatoraccess, structureboundingbox, random, 3, 3, 5, LootTables.w);
            if (this.a) {
                this.a(generatoraccess, WorldGenStrongholdPieces.WorldGenStrongholdLibrary.m, 12, 9, 1, structureboundingbox);
                this.a(generatoraccess, structureboundingbox, random, 12, 8, 1, LootTables.w);
            }

            return true;
        }
    }

    public static class WorldGenStrongholdPrison extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        public WorldGenStrongholdPrison(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.B, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdPrison(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.B, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdPrison a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 9, 5, 11, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdPrison(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 8, 4, 10, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 1, 0);
            this.a(generatoraccess, structureboundingbox, 1, 1, 10, 3, 3, 10, WorldGenStrongholdPieces.WorldGenStrongholdPrison.m, WorldGenStrongholdPieces.WorldGenStrongholdPrison.m, false);
            this.a(generatoraccess, structureboundingbox, 4, 1, 1, 4, 3, 1, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 1, 3, 4, 3, 3, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 1, 7, 4, 3, 7, false, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, structureboundingbox, 4, 1, 9, 4, 3, 9, false, random, WorldGenStrongholdPieces.e);

            for (int i = 1; i <= 3; ++i) {
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true), 4, i, 4, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true)).set(BlockIronBars.EAST, true), 4, i, 5, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true), 4, i, 6, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true)).set(BlockIronBars.EAST, true), 5, i, 5, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true)).set(BlockIronBars.EAST, true), 6, i, 5, structureboundingbox);
                this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true)).set(BlockIronBars.EAST, true), 7, i, 5, structureboundingbox);
            }

            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true), 4, 3, 2, structureboundingbox);
            this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, true)).set(BlockIronBars.SOUTH, true), 4, 3, 8, structureboundingbox);
            IBlockData iblockdata = (IBlockData) Blocks.IRON_DOOR.getBlockData().set(BlockDoor.FACING, EnumDirection.WEST);
            IBlockData iblockdata1 = (IBlockData) ((IBlockData) Blocks.IRON_DOOR.getBlockData().set(BlockDoor.FACING, EnumDirection.WEST)).set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER);

            this.a(generatoraccess, iblockdata, 4, 1, 2, structureboundingbox);
            this.a(generatoraccess, iblockdata1, 4, 2, 2, structureboundingbox);
            this.a(generatoraccess, iblockdata, 4, 1, 8, structureboundingbox);
            this.a(generatoraccess, iblockdata1, 4, 2, 8, structureboundingbox);
            return true;
        }
    }

    public static class WorldGenStrongholdRoomCrossing extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        protected final int a;

        public WorldGenStrongholdRoomCrossing(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.D, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
            this.a = random.nextInt(5);
        }

        public WorldGenStrongholdRoomCrossing(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.D, nbttagcompound);
            this.a = nbttagcompound.getInt("Type");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setInt("Type", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 4, 1);
            this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 4);
            this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 4);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 7, 11, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 10, 6, 10, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 4, 1, 0);
            this.a(generatoraccess, structureboundingbox, 4, 1, 10, 6, 3, 10, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, false);
            this.a(generatoraccess, structureboundingbox, 0, 1, 4, 0, 3, 6, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, false);
            this.a(generatoraccess, structureboundingbox, 10, 1, 4, 10, 3, 6, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, WorldGenStrongholdPieces.WorldGenStrongholdRoomCrossing.m, false);
            int i;

            switch (this.a) {
                case 0:
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 2, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 3, 5, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST), 4, 3, 5, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST), 6, 3, 5, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.SOUTH), 5, 3, 4, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.NORTH), 5, 3, 6, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 4, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 6, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 4, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 6, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 5, 1, 4, structureboundingbox);
                    this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 5, 1, 6, structureboundingbox);
                    break;
                case 1:
                    for (i = 0; i < 5; ++i) {
                        this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 1, 3 + i, structureboundingbox);
                        this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 7, 1, 3 + i, structureboundingbox);
                        this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3 + i, 1, 3, structureboundingbox);
                        this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3 + i, 1, 7, structureboundingbox);
                    }

                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 2, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 5, 3, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.WATER.getBlockData(), 5, 4, 5, structureboundingbox);
                    break;
                case 2:
                    for (i = 1; i <= 9; ++i) {
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 1, 3, i, structureboundingbox);
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 9, 3, i, structureboundingbox);
                    }

                    for (i = 1; i <= 9; ++i) {
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), i, 3, 1, structureboundingbox);
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), i, 3, 9, structureboundingbox);
                    }

                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 5, 1, 4, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 5, 1, 6, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 5, 3, 4, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 5, 3, 6, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 4, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 6, 1, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 4, 3, 5, structureboundingbox);
                    this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 6, 3, 5, structureboundingbox);

                    for (i = 1; i <= 3; ++i) {
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 4, i, 4, structureboundingbox);
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 6, i, 4, structureboundingbox);
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 4, i, 6, structureboundingbox);
                        this.a(generatoraccess, Blocks.COBBLESTONE.getBlockData(), 6, i, 6, structureboundingbox);
                    }

                    this.a(generatoraccess, Blocks.TORCH.getBlockData(), 5, 3, 5, structureboundingbox);

                    for (i = 2; i <= 8; ++i) {
                        this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 2, 3, i, structureboundingbox);
                        this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 3, 3, i, structureboundingbox);
                        if (i <= 3 || i >= 7) {
                            this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 4, 3, i, structureboundingbox);
                            this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 5, 3, i, structureboundingbox);
                            this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 6, 3, i, structureboundingbox);
                        }

                        this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 7, 3, i, structureboundingbox);
                        this.a(generatoraccess, Blocks.OAK_PLANKS.getBlockData(), 8, 3, i, structureboundingbox);
                    }

                    IBlockData iblockdata = (IBlockData) Blocks.LADDER.getBlockData().set(BlockLadder.FACING, EnumDirection.WEST);

                    this.a(generatoraccess, iblockdata, 9, 1, 3, structureboundingbox);
                    this.a(generatoraccess, iblockdata, 9, 2, 3, structureboundingbox);
                    this.a(generatoraccess, iblockdata, 9, 3, 3, structureboundingbox);
                    this.a(generatoraccess, structureboundingbox, random, 3, 4, 8, LootTables.x);
            }

            return true;
        }
    }

    public static class WorldGenStrongholdRightTurn extends WorldGenStrongholdPieces.q {

        public WorldGenStrongholdRightTurn(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.C, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdRightTurn(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.C, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.EAST) {
                this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
            } else {
                this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
            }

        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdRightTurn a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdRightTurn(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 1, 0);
            EnumDirection enumdirection = this.i();

            if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.EAST) {
                this.a(generatoraccess, structureboundingbox, 0, 1, 1, 0, 3, 3, WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.m, WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.m, false);
            } else {
                this.a(generatoraccess, structureboundingbox, 4, 1, 1, 4, 3, 3, WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.m, WorldGenStrongholdPieces.WorldGenStrongholdRightTurn.m, false);
            }

            return true;
        }
    }

    public static class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPieces.q {

        public WorldGenStrongholdLeftTurn(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.y, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdLeftTurn(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.y, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.EAST) {
                this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
            } else {
                this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
            }

        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 1, 0);
            EnumDirection enumdirection = this.i();

            if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.EAST) {
                this.a(generatoraccess, structureboundingbox, 4, 1, 1, 4, 3, 3, WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.m, WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.m, false);
            } else {
                this.a(generatoraccess, structureboundingbox, 0, 1, 1, 0, 3, 3, WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.m, WorldGenStrongholdPieces.WorldGenStrongholdLeftTurn.m, false);
            }

            return true;
        }
    }

    public abstract static class q extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        protected q(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, int i) {
            super(worldgenfeaturestructurepiecetype, i);
        }

        public q(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
            super(worldgenfeaturestructurepiecetype, nbttagcompound);
        }
    }

    public static class WorldGenStrongholdStairsStraight extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        public WorldGenStrongholdStairsStraight(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.H, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdStairsStraight(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.H, nbttagcompound);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdStairsStraight a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 8, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdStairsStraight(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 10, 7, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 7, 0);
            this.a(generatoraccess, random, structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 7);
            IBlockData iblockdata = (IBlockData) Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);

            for (int i = 0; i < 6; ++i) {
                this.a(generatoraccess, iblockdata, 1, 6 - i, 1 + i, structureboundingbox);
                this.a(generatoraccess, iblockdata, 2, 6 - i, 1 + i, structureboundingbox);
                this.a(generatoraccess, iblockdata, 3, 6 - i, 1 + i, structureboundingbox);
                if (i < 5) {
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 5 - i, 1 + i, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 5 - i, 1 + i, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 5 - i, 1 + i, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class WorldGenStrongholdChestCorridor extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private boolean a;

        public WorldGenStrongholdChestCorridor(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.v, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdChestCorridor(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.v, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Chest");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Chest", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdChestCorridor a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdChestCorridor(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 1, 0);
            this.a(generatoraccess, random, structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 6);
            this.a(generatoraccess, structureboundingbox, 3, 1, 2, 3, 1, 4, Blocks.STONE_BRICKS.getBlockData(), Blocks.STONE_BRICKS.getBlockData(), false);
            this.a(generatoraccess, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 1, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 1, 5, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 2, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 2, 4, structureboundingbox);

            for (int i = 2; i <= 4; ++i) {
                this.a(generatoraccess, Blocks.STONE_BRICK_SLAB.getBlockData(), 2, 1, i, structureboundingbox);
            }

            if (!this.a && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(3, 3), this.d(2), this.b(3, 3))))) {
                this.a = true;
                this.a(generatoraccess, structureboundingbox, random, 3, 2, 3, LootTables.y);
            }

            return true;
        }
    }

    public static class WorldGenStrongholdStairs extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private final boolean a;
        private final boolean b;

        public WorldGenStrongholdStairs(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.G, i);
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
            this.a = random.nextInt(2) == 0;
            this.b = random.nextInt(2) == 0;
        }

        public WorldGenStrongholdStairs(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.G, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Left");
            this.b = nbttagcompound.getBoolean("Right");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Left", this.a);
            nbttagcompound.setBoolean("Right", this.b);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
            if (this.a) {
                this.b((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 2);
            }

            if (this.b) {
                this.c((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 2);
            }

        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdStairs a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdStairs(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 1, 0);
            this.a(generatoraccess, random, structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 6);
            IBlockData iblockdata = (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST);
            IBlockData iblockdata1 = (IBlockData) Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST);

            this.a(generatoraccess, structureboundingbox, random, 0.1F, 1, 2, 1, iblockdata);
            this.a(generatoraccess, structureboundingbox, random, 0.1F, 3, 2, 1, iblockdata1);
            this.a(generatoraccess, structureboundingbox, random, 0.1F, 1, 2, 5, iblockdata);
            this.a(generatoraccess, structureboundingbox, random, 0.1F, 3, 2, 5, iblockdata1);
            if (this.a) {
                this.a(generatoraccess, structureboundingbox, 0, 1, 2, 0, 3, 4, WorldGenStrongholdPieces.WorldGenStrongholdStairs.m, WorldGenStrongholdPieces.WorldGenStrongholdStairs.m, false);
            }

            if (this.b) {
                this.a(generatoraccess, structureboundingbox, 4, 1, 2, 4, 3, 4, WorldGenStrongholdPieces.WorldGenStrongholdStairs.m, WorldGenStrongholdPieces.WorldGenStrongholdStairs.m, false);
            }

            return true;
        }
    }

    public static class WorldGenStrongholdStart extends WorldGenStrongholdPieces.WorldGenStrongholdStairs2 {

        public WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight a;
        @Nullable
        public WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom b;
        public final List<StructurePiece> c = Lists.newArrayList();

        public WorldGenStrongholdStart(Random random, int i, int j) {
            super(WorldGenFeatureStructurePieceType.F, 0, random, i, j);
        }

        public WorldGenStrongholdStart(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.F, nbttagcompound);
        }
    }

    public static class WorldGenStrongholdStairs2 extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private final boolean a;

        public WorldGenStrongholdStairs2(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, int i, Random random, int j, int k) {
            super(worldgenfeaturestructurepiecetype, i);
            this.a = true;
            this.a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random));
            this.d = WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING;
            if (this.i().k() == EnumDirection.EnumAxis.Z) {
                this.n = new StructureBoundingBox(j, 64, k, j + 5 - 1, 74, k + 5 - 1);
            } else {
                this.n = new StructureBoundingBox(j, 64, k, j + 5 - 1, 74, k + 5 - 1);
            }

        }

        public WorldGenStrongholdStairs2(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.E, i);
            this.a = false;
            this.a(enumdirection);
            this.d = this.a(random);
            this.n = structureboundingbox;
        }

        public WorldGenStrongholdStairs2(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
            super(worldgenfeaturestructurepiecetype, nbttagcompound);
            this.a = nbttagcompound.getBoolean("Source");
        }

        public WorldGenStrongholdStairs2(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            this(WorldGenFeatureStructurePieceType.E, nbttagcompound);
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Source", this.a);
        }

        @Override
        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            if (this.a) {
                WorldGenStrongholdPieces.c = WorldGenStrongholdPieces.WorldGenStrongholdCrossing.class;
            }

            this.a((WorldGenStrongholdPieces.WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }

        public static WorldGenStrongholdPieces.WorldGenStrongholdStairs2 a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPieces.WorldGenStrongholdStairs2(l, random, structureboundingbox, enumdirection) : null;
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            this.a(generatoraccess, structureboundingbox, 0, 0, 0, 4, 10, 4, true, random, WorldGenStrongholdPieces.e);
            this.a(generatoraccess, random, structureboundingbox, this.d, 1, 7, 0);
            this.a(generatoraccess, random, structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 4);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 6, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 5, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 6, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 5, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 4, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 5, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 4, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 3, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 3, 4, 3, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 3, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 2, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 3, 3, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 2, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 1, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 2, 1, structureboundingbox);
            this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 1, 2, structureboundingbox);
            this.a(generatoraccess, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 1, 3, structureboundingbox);
            return true;
        }
    }

    public static class WorldGenStrongholdCorridor extends WorldGenStrongholdPieces.WorldGenStrongholdPiece {

        private final int a;

        public WorldGenStrongholdCorridor(int i, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(WorldGenFeatureStructurePieceType.w, i);
            this.a(enumdirection);
            this.n = structureboundingbox;
            this.a = enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH ? structureboundingbox.c() : structureboundingbox.e();
        }

        public WorldGenStrongholdCorridor(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
            super(WorldGenFeatureStructurePieceType.w, nbttagcompound);
            this.a = nbttagcompound.getInt("Steps");
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setInt("Steps", this.a);
        }

        public static StructureBoundingBox a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection) {
            boolean flag = true;
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 4, enumdirection);
            StructurePiece structurepiece = StructurePiece.a(list, structureboundingbox);

            if (structurepiece == null) {
                return null;
            } else {
                if (structurepiece.g().b == structureboundingbox.b) {
                    for (int l = 3; l >= 1; --l) {
                        structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, l - 1, enumdirection);
                        if (!structurepiece.g().b(structureboundingbox)) {
                            return StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, l, enumdirection);
                        }
                    }
                }

                return null;
            }
        }

        @Override
        public boolean a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            for (int i = 0; i < this.a; ++i) {
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 0, 0, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 0, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 0, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 0, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 4, 0, i, structureboundingbox);

                for (int j = 1; j <= 3; ++j) {
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 0, j, i, structureboundingbox);
                    this.a(generatoraccess, Blocks.CAVE_AIR.getBlockData(), 1, j, i, structureboundingbox);
                    this.a(generatoraccess, Blocks.CAVE_AIR.getBlockData(), 2, j, i, structureboundingbox);
                    this.a(generatoraccess, Blocks.CAVE_AIR.getBlockData(), 3, j, i, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 4, j, i, structureboundingbox);
                }

                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 0, 4, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 1, 4, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 2, 4, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 3, 4, i, structureboundingbox);
                this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), 4, 4, i, structureboundingbox);
            }

            return true;
        }
    }

    abstract static class WorldGenStrongholdPiece extends StructurePiece {

        protected WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType d;

        protected WorldGenStrongholdPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, int i) {
            super(worldgenfeaturestructurepiecetype, i);
            this.d = WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING;
        }

        public WorldGenStrongholdPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
            super(worldgenfeaturestructurepiecetype, nbttagcompound);
            this.d = WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING;
            this.d = WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.valueOf(nbttagcompound.getString("EntryDoor"));
        }

        @Override
        protected void a(NBTTagCompound nbttagcompound) {
            nbttagcompound.setString("EntryDoor", this.d.name());
        }

        protected void a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType worldgenstrongholdpieces_worldgenstrongholdpiece_worldgenstrongholddoortype, int i, int j, int k) {
            switch (worldgenstrongholdpieces_worldgenstrongholdpiece_worldgenstrongholddoortype) {
                case OPENING:
                    this.a(generatoraccess, structureboundingbox, i, j, k, i + 3 - 1, j + 3 - 1, k, WorldGenStrongholdPieces.WorldGenStrongholdPiece.m, WorldGenStrongholdPieces.WorldGenStrongholdPiece.m, false);
                    break;
                case WOOD_DOOR:
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 1, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.OAK_DOOR.getBlockData(), i + 1, j, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.OAK_DOOR.getBlockData().set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER), i + 1, j + 1, k, structureboundingbox);
                    break;
                case GRATES:
                    this.a(generatoraccess, Blocks.CAVE_AIR.getBlockData(), i + 1, j, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.CAVE_AIR.getBlockData(), i + 1, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true), i, j, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, true), i, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, true)).set(BlockIronBars.WEST, true), i, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, true)).set(BlockIronBars.WEST, true), i + 1, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, true)).set(BlockIronBars.WEST, true), i + 2, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, true), i + 2, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, true), i + 2, j, k, structureboundingbox);
                    break;
                case IRON_DOOR:
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 1, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j + 2, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.STONE_BRICKS.getBlockData(), i + 2, j, k, structureboundingbox);
                    this.a(generatoraccess, Blocks.IRON_DOOR.getBlockData(), i + 1, j, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.IRON_DOOR.getBlockData().set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER), i + 1, j + 1, k, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.STONE_BUTTON.getBlockData().set(BlockButtonAbstract.FACING, EnumDirection.NORTH), i + 2, j + 1, k + 1, structureboundingbox);
                    this.a(generatoraccess, (IBlockData) Blocks.STONE_BUTTON.getBlockData().set(BlockButtonAbstract.FACING, EnumDirection.SOUTH), i + 2, j + 1, k - 1, structureboundingbox);
            }

        }

        protected WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType a(Random random) {
            int i = random.nextInt(5);

            switch (i) {
                case 0:
                case 1:
                default:
                    return WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING;
                case 2:
                    return WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.WOOD_DOOR;
                case 3:
                    return WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.GRATES;
                case 4:
                    return WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.IRON_DOOR;
            }
        }

        @Nullable
        protected StructurePiece a(WorldGenStrongholdPieces.WorldGenStrongholdStart worldgenstrongholdpieces_worldgenstrongholdstart, List<StructurePiece> list, Random random, int i, int j) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + i, this.n.b + j, this.n.c - 1, enumdirection, this.h());
                    case SOUTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + i, this.n.b + j, this.n.f + 1, enumdirection, this.h());
                    case WEST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a - 1, this.n.b + j, this.n.c + i, enumdirection, this.h());
                    case EAST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.d + 1, this.n.b + j, this.n.c + i, enumdirection, this.h());
                }
            }

            return null;
        }

        @Nullable
        protected StructurePiece b(WorldGenStrongholdPieces.WorldGenStrongholdStart worldgenstrongholdpieces_worldgenstrongholdstart, List<StructurePiece> list, Random random, int i, int j) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a - 1, this.n.b + i, this.n.c + j, EnumDirection.WEST, this.h());
                    case SOUTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a - 1, this.n.b + i, this.n.c + j, EnumDirection.WEST, this.h());
                    case WEST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + j, this.n.b + i, this.n.c - 1, EnumDirection.NORTH, this.h());
                    case EAST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + j, this.n.b + i, this.n.c - 1, EnumDirection.NORTH, this.h());
                }
            }

            return null;
        }

        @Nullable
        protected StructurePiece c(WorldGenStrongholdPieces.WorldGenStrongholdStart worldgenstrongholdpieces_worldgenstrongholdstart, List<StructurePiece> list, Random random, int i, int j) {
            EnumDirection enumdirection = this.i();

            if (enumdirection != null) {
                switch (enumdirection) {
                    case NORTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.d + 1, this.n.b + i, this.n.c + j, EnumDirection.EAST, this.h());
                    case SOUTH:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.d + 1, this.n.b + i, this.n.c + j, EnumDirection.EAST, this.h());
                    case WEST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + j, this.n.b + i, this.n.f + 1, EnumDirection.SOUTH, this.h());
                    case EAST:
                        return WorldGenStrongholdPieces.c(worldgenstrongholdpieces_worldgenstrongholdstart, list, random, this.n.a + j, this.n.b + i, this.n.f + 1, EnumDirection.SOUTH, this.h());
                }
            }

            return null;
        }

        protected static boolean a(StructureBoundingBox structureboundingbox) {
            return structureboundingbox != null && structureboundingbox.b > 10;
        }

        public static enum WorldGenStrongholdDoorType {

            OPENING, WOOD_DOOR, GRATES, IRON_DOOR;

            private WorldGenStrongholdDoorType() {}
        }
    }

    static class WorldGenStrongholdPieceWeight {

        public final Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> a;
        public final int b;
        public int c;
        public final int d;

        public WorldGenStrongholdPieceWeight(Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> oclass, int i, int j) {
            this.a = oclass;
            this.b = i;
            this.d = j;
        }

        public boolean a(int i) {
            return this.d == 0 || this.c < this.d;
        }

        public boolean a() {
            return this.d == 0 || this.c < this.d;
        }
    }
}
