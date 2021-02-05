package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class MobSpawnerCat {

    private int a;

    public MobSpawnerCat() {}

    public int a(WorldServer worldserver, boolean flag, boolean flag1) {
        if (flag1 && worldserver.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            --this.a;
            if (this.a > 0) {
                return 0;
            } else {
                this.a = 1200;
                EntityPlayer entityplayer = worldserver.l_();

                if (entityplayer == null) {
                    return 0;
                } else {
                    Random random = worldserver.random;
                    int i = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                    int j = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                    BlockPosition blockposition = (new BlockPosition(entityplayer)).b(i, 0, j);

                    if (!worldserver.isAreaLoaded(blockposition.getX() - 10, blockposition.getY() - 10, blockposition.getZ() - 10, blockposition.getX() + 10, blockposition.getY() + 10, blockposition.getZ() + 10)) {
                        return 0;
                    } else {
                        if (SpawnerCreature.a(EntityPositionTypes.Surface.ON_GROUND, (IWorldReader) worldserver, blockposition, EntityTypes.CAT)) {
                            if (worldserver.a(blockposition, 2)) {
                                return this.a(worldserver, blockposition);
                            }

                            if (WorldGenerator.SWAMP_HUT.b(worldserver, blockposition)) {
                                return this.a((World) worldserver, blockposition);
                            }
                        }

                        return 0;
                    }
                }
            }
        } else {
            return 0;
        }
    }

    private int a(WorldServer worldserver, BlockPosition blockposition) {
        boolean flag = true;

        if (worldserver.B().a(VillagePlaceType.q.c(), blockposition, 48, VillagePlace.Occupancy.IS_OCCUPIED) > 4L) {
            List<EntityCat> list = worldserver.a(EntityCat.class, (new AxisAlignedBB(blockposition)).grow(48.0D, 8.0D, 48.0D));

            if (list.size() < 5) {
                return this.a(blockposition, (World) worldserver);
            }
        }

        return 0;
    }

    private int a(World world, BlockPosition blockposition) {
        boolean flag = true;
        List<EntityCat> list = world.a(EntityCat.class, (new AxisAlignedBB(blockposition)).grow(16.0D, 8.0D, 16.0D));

        return list.size() < 1 ? this.a(blockposition, world) : 0;
    }

    private int a(BlockPosition blockposition, World world) {
        EntityCat entitycat = (EntityCat) EntityTypes.CAT.a(world);

        if (entitycat == null) {
            return 0;
        } else {
            entitycat.prepare(world, world.getDamageScaler(blockposition), EnumMobSpawn.NATURAL, (GroupDataEntity) null, (NBTTagCompound) null);
            entitycat.setPositionRotation(blockposition, 0.0F, 0.0F);
            world.addEntity(entitycat);
            return 1;
        }
    }
}
