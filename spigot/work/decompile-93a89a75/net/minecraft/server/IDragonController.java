package net.minecraft.server;

import javax.annotation.Nullable;

public interface IDragonController {

    boolean a();

    void b();

    void c();

    void a(EntityEnderCrystal entityendercrystal, BlockPosition blockposition, DamageSource damagesource, @Nullable EntityHuman entityhuman);

    void d();

    void e();

    float f();

    float h();

    DragonControllerPhase<? extends IDragonController> getControllerPhase();

    @Nullable
    Vec3D g();

    float a(DamageSource damagesource, float f);
}
