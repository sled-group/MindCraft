package net.minecraft.server;

public class DragonControllerLandedSearch extends AbstractDragonControllerLanded {

    private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(150.0D);
    private final PathfinderTargetCondition c;
    private int d;

    public DragonControllerLandedSearch(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
        this.c = (new PathfinderTargetCondition()).a(20.0D).a((entityliving) -> {
            return Math.abs(entityliving.locY - entityenderdragon.locY) <= 10.0D;
        });
    }

    @Override
    public void c() {
        ++this.d;
        EntityHuman entityhuman = this.a.world.a(this.c, this.a, this.a.locX, this.a.locY, this.a.locZ);

        if (entityhuman != null) {
            if (this.d > 25) {
                this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_ATTACKING);
            } else {
                Vec3D vec3d = (new Vec3D(entityhuman.locX - this.a.locX, 0.0D, entityhuman.locZ - this.a.locZ)).d();
                Vec3D vec3d1 = (new Vec3D((double) MathHelper.sin(this.a.yaw * 0.017453292F), 0.0D, (double) (-MathHelper.cos(this.a.yaw * 0.017453292F)))).d();
                float f = (float) vec3d1.b(vec3d);
                float f1 = (float) (Math.acos((double) f) * 57.2957763671875D) + 0.5F;

                if (f1 < 0.0F || f1 > 10.0F) {
                    double d0 = entityhuman.locX - this.a.bA.locX;
                    double d1 = entityhuman.locZ - this.a.bA.locZ;
                    double d2 = MathHelper.a(MathHelper.g(180.0D - MathHelper.d(d0, d1) * 57.2957763671875D - (double) this.a.yaw), -100.0D, 100.0D);

                    this.a.be *= 0.8F;
                    float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) + 1.0F;
                    float f3 = f2;

                    if (f2 > 40.0F) {
                        f2 = 40.0F;
                    }

                    this.a.be = (float) ((double) this.a.be + d2 * (double) (0.7F / f2 / f3));
                    this.a.yaw += this.a.be;
                }
            }
        } else if (this.d >= 100) {
            entityhuman = this.a.world.a(DragonControllerLandedSearch.b, this.a, this.a.locX, this.a.locY, this.a.locZ);
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.TAKEOFF);
            if (entityhuman != null) {
                this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.CHARGING_PLAYER);
                ((DragonControllerCharge) this.a.getDragonControllerManager().b(DragonControllerPhase.CHARGING_PLAYER)).a(new Vec3D(entityhuman.locX, entityhuman.locY, entityhuman.locZ));
            }
        }

    }

    @Override
    public void d() {
        this.d = 0;
    }

    @Override
    public DragonControllerPhase<DragonControllerLandedSearch> getControllerPhase() {
        return DragonControllerPhase.SITTING_SCANNING;
    }
}
