package net.minecraft.server;

import javax.annotation.Nullable;

public abstract class AbstractDragonController implements IDragonController {

    protected final EntityEnderDragon a;

    public AbstractDragonController(EntityEnderDragon entityenderdragon) {
        this.a = entityenderdragon;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public void b() {}

    @Override
    public void c() {}

    @Override
    public void a(EntityEnderCrystal entityendercrystal, BlockPosition blockposition, DamageSource damagesource, @Nullable EntityHuman entityhuman) {}

    @Override
    public void d() {}

    @Override
    public void e() {}

    @Override
    public float f() {
        return 0.6F;
    }

    @Nullable
    @Override
    public Vec3D g() {
        return null;
    }

    @Override
    public float a(DamageSource damagesource, float f) {
        return f;
    }

    @Override
    public float h() {
        float f = MathHelper.sqrt(Entity.b(this.a.getMot())) + 1.0F;
        float f1 = Math.min(f, 40.0F);

        return 0.7F / f1 / f;
    }
}
