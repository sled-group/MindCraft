package net.minecraft.server;

import javax.annotation.Nullable;

public class DragonControllerHold extends AbstractDragonController {

    private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(64.0D);
    private PathEntity c;
    private Vec3D d;
    private boolean e;

    public DragonControllerHold(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public DragonControllerPhase<DragonControllerHold> getControllerPhase() {
        return DragonControllerPhase.HOLDING_PATTERN;
    }

    @Override
    public void c() {
        double d0 = this.d == null ? 0.0D : this.d.c(this.a.locX, this.a.locY, this.a.locZ);

        if (d0 < 100.0D || d0 > 22500.0D || this.a.positionChanged || this.a.y) {
            this.j();
        }

    }

    @Override
    public void d() {
        this.c = null;
        this.d = null;
    }

    @Nullable
    @Override
    public Vec3D g() {
        return this.d;
    }

    private void j() {
        int i;

        if (this.c != null && this.c.b()) {
            BlockPosition blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPosition(WorldGenEndTrophy.a));

            i = this.a.getEnderDragonBattle() == null ? 0 : this.a.getEnderDragonBattle().c();
            if (this.a.getRandom().nextInt(i + 3) == 0) {
                this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.LANDING_APPROACH);
                return;
            }

            double d0 = 64.0D;
            EntityHuman entityhuman = this.a.world.a(DragonControllerHold.b, (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ());

            if (entityhuman != null) {
                d0 = blockposition.a(entityhuman.getPositionVector(), true) / 512.0D;
            }

            if (entityhuman != null && !entityhuman.abilities.isInvulnerable && (this.a.getRandom().nextInt(MathHelper.a((int) d0) + 2) == 0 || this.a.getRandom().nextInt(i + 2) == 0)) {
                this.a(entityhuman);
                return;
            }
        }

        if (this.c == null || this.c.b()) {
            int j = this.a.l();

            i = j;
            if (this.a.getRandom().nextInt(8) == 0) {
                this.e = !this.e;
                i = j + 6;
            }

            if (this.e) {
                ++i;
            } else {
                --i;
            }

            if (this.a.getEnderDragonBattle() != null && this.a.getEnderDragonBattle().c() >= 0) {
                i %= 12;
                if (i < 0) {
                    i += 12;
                }
            } else {
                i -= 12;
                i &= 7;
                i += 12;
            }

            this.c = this.a.a(j, i, (PathPoint) null);
            if (this.c != null) {
                this.c.a();
            }
        }

        this.k();
    }

    private void a(EntityHuman entityhuman) {
        this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.STRAFE_PLAYER);
        ((DragonControllerStrafe) this.a.getDragonControllerManager().b(DragonControllerPhase.STRAFE_PLAYER)).a(entityhuman);
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

    @Override
    public void a(EntityEnderCrystal entityendercrystal, BlockPosition blockposition, DamageSource damagesource, @Nullable EntityHuman entityhuman) {
        if (entityhuman != null && !entityhuman.abilities.isInvulnerable) {
            this.a(entityhuman);
        }

    }
}
