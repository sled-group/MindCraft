package net.minecraft.server;

import javax.annotation.Nullable;

public class DragonControllerLandingFly extends AbstractDragonController {

    private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(128.0D);
    private PathEntity c;
    private Vec3D d;

    public DragonControllerLandingFly(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public DragonControllerPhase<DragonControllerLandingFly> getControllerPhase() {
        return DragonControllerPhase.LANDING_APPROACH;
    }

    @Override
    public void d() {
        this.c = null;
        this.d = null;
    }

    @Override
    public void c() {
        double d0 = this.d == null ? 0.0D : this.d.c(this.a.locX, this.a.locY, this.a.locZ);

        if (d0 < 100.0D || d0 > 22500.0D || this.a.positionChanged || this.a.y) {
            this.j();
        }

    }

    @Nullable
    @Override
    public Vec3D g() {
        return this.d;
    }

    private void j() {
        if (this.c == null || this.c.b()) {
            int i = this.a.l();
            BlockPosition blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a);
            EntityHuman entityhuman = this.a.world.a(DragonControllerLandingFly.b, (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ());
            int j;

            if (entityhuman != null) {
                Vec3D vec3d = (new Vec3D(entityhuman.locX, 0.0D, entityhuman.locZ)).d();

                j = this.a.l(-vec3d.x * 40.0D, 105.0D, -vec3d.z * 40.0D);
            } else {
                j = this.a.l(40.0D, (double) blockposition.getY(), 0.0D);
            }

            PathPoint pathpoint = new PathPoint(blockposition.getX(), blockposition.getY(), blockposition.getZ());

            this.c = this.a.a(i, j, pathpoint);
            if (this.c != null) {
                this.c.a();
            }
        }

        this.k();
        if (this.c != null && this.c.b()) {
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.LANDING);
        }

    }

    private void k() {
        if (this.c != null && !this.c.b()) {
            Vec3D vec3d = this.c.g();

            this.c.a();
            double d0 = vec3d.x;
            double d1 = vec3d.z;

            double d2;

            do {
                d2 = vec3d.y + (double) (this.a.getRandom().nextFloat() * 20.0F);
            } while (d2 < vec3d.y);

            this.d = new Vec3D(d0, d2, d1);
        }

    }
}
