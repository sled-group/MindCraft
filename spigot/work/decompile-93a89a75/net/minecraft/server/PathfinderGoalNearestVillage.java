package net.minecraft.server;

import java.util.EnumSet;
import java.util.Random;
import javax.annotation.Nullable;

public class PathfinderGoalNearestVillage extends PathfinderGoal {

    private final EntityCreature a;
    private final int b;
    @Nullable
    private BlockPosition c;

    public PathfinderGoalNearestVillage(EntityCreature entitycreature, int i) {
        this.a = entitycreature;
        this.b = i;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        if (this.a.isVehicle()) {
            return false;
        } else if (this.a.world.J()) {
            return false;
        } else if (this.a.getRandom().nextInt(this.b) != 0) {
            return false;
        } else {
            WorldServer worldserver = (WorldServer) this.a.world;
            BlockPosition blockposition = new BlockPosition(this.a);

            if (!worldserver.a(blockposition, 6)) {
                return false;
            } else {
                Vec3D vec3d = RandomPositionGenerator.a(this.a, 15, 7, (blockposition1) -> {
                    return (double) (-worldserver.b(SectionPosition.a(blockposition1)));
                });

                this.c = vec3d == null ? null : new BlockPosition(vec3d);
                return this.c != null;
            }
        }
    }

    @Override
    public boolean b() {
        return this.c != null && !this.a.getNavigation().n() && this.a.getNavigation().h().equals(this.c);
    }

    @Override
    public void e() {
        if (this.c != null) {
            NavigationAbstract navigationabstract = this.a.getNavigation();

            if (navigationabstract.n() && !this.c.a((IPosition) this.a.getPositionVector(), 10.0D)) {
                Vec3D vec3d = new Vec3D(this.c);
                Vec3D vec3d1 = new Vec3D(this.a.locX, this.a.locY, this.a.locZ);
                Vec3D vec3d2 = vec3d1.d(vec3d);

                vec3d = vec3d2.a(0.4D).e(vec3d);
                Vec3D vec3d3 = vec3d.d(vec3d1).d().a(10.0D).e(vec3d1);
                BlockPosition blockposition = new BlockPosition(vec3d3);

                blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, blockposition);
                if (!navigationabstract.a((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), 1.0D)) {
                    this.g();
                }
            }

        }
    }

    private void g() {
        Random random = this.a.getRandom();
        BlockPosition blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, (new BlockPosition(this.a)).b(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));

        this.a.getNavigation().a((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), 1.0D);
    }
}
