package net.minecraft.server;

import javax.annotation.Nullable;

public class DragonControllerDying extends AbstractDragonController {

    private Vec3D b;
    private int c;

    public DragonControllerDying(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public void b() {
        if (this.c++ % 10 == 0) {
            float f = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.a.getRandom().nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;

            this.a.world.addParticle(Particles.EXPLOSION_EMITTER, this.a.locX + (double) f, this.a.locY + 2.0D + (double) f1, this.a.locZ + (double) f2, 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    public void c() {
        ++this.c;
        if (this.b == null) {
            BlockPosition blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, WorldGenEndTrophy.a);

            this.b = new Vec3D((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ());
        }

        double d0 = this.b.c(this.a.locX, this.a.locY, this.a.locZ);

        if (d0 >= 100.0D && d0 <= 22500.0D && !this.a.positionChanged && !this.a.y) {
            this.a.setHealth(1.0F);
        } else {
            this.a.setHealth(0.0F);
        }

    }

    @Override
    public void d() {
        this.b = null;
        this.c = 0;
    }

    @Override
    public float f() {
        return 3.0F;
    }

    @Nullable
    @Override
    public Vec3D g() {
        return this.b;
    }

    @Override
    public DragonControllerPhase<DragonControllerDying> getControllerPhase() {
        return DragonControllerPhase.DYING;
    }
}
