package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class DragonControllerLanding extends AbstractDragonController {

    private Vec3D b;

    public DragonControllerLanding(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public void b() {
        Vec3D vec3d = this.a.u(1.0F).d();

        vec3d.b(-0.7853982F);
        double d0 = this.a.bA.locX;
        double d1 = this.a.bA.locY + (double) (this.a.bA.getHeight() / 2.0F);
        double d2 = this.a.bA.locZ;

        for (int i = 0; i < 8; ++i) {
            Random random = this.a.getRandom();
            double d3 = d0 + random.nextGaussian() / 2.0D;
            double d4 = d1 + random.nextGaussian() / 2.0D;
            double d5 = d2 + random.nextGaussian() / 2.0D;
            Vec3D vec3d1 = this.a.getMot();

            this.a.world.addParticle(Particles.DRAGON_BREATH, d3, d4, d5, -vec3d.x * 0.07999999821186066D + vec3d1.x, -vec3d.y * 0.30000001192092896D + vec3d1.y, -vec3d.z * 0.07999999821186066D + vec3d1.z);
            vec3d.b(0.19634955F);
        }

    }

    @Override
    public void c() {
        if (this.b == null) {
            this.b = new Vec3D(this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a));
        }

        if (this.b.c(this.a.locX, this.a.locY, this.a.locZ) < 1.0D) {
            ((DragonControllerLandedFlame) this.a.getDragonControllerManager().b(DragonControllerPhase.SITTING_FLAMING)).j();
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_SCANNING);
        }

    }

    @Override
    public float f() {
        return 1.5F;
    }

    @Override
    public float h() {
        float f = MathHelper.sqrt(Entity.b(this.a.getMot())) + 1.0F;
        float f1 = Math.min(f, 40.0F);

        return f1 / f;
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Nullable
    @Override
    public Vec3D g() {
        return this.b;
    }

    @Override
    public DragonControllerPhase<DragonControllerLanding> getControllerPhase() {
        return DragonControllerPhase.LANDING;
    }
}
