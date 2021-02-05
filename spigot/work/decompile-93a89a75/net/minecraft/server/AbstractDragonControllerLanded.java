package net.minecraft.server;

public abstract class AbstractDragonControllerLanded extends AbstractDragonController {

    public AbstractDragonControllerLanded(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public float a(DamageSource damagesource, float f) {
        if (damagesource.j() instanceof EntityArrow) {
            damagesource.j().setOnFire(1);
            return 0.0F;
        } else {
            return super.a(damagesource, f);
        }
    }
}
