package net.minecraft.server;

public class DragonControllerLandedAttack extends AbstractDragonControllerLanded {

    private int b;

    public DragonControllerLandedAttack(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public void b() {
        this.a.world.a(this.a.locX, this.a.locY, this.a.locZ, SoundEffects.ENTITY_ENDER_DRAGON_GROWL, this.a.getSoundCategory(), 2.5F, 0.8F + this.a.getRandom().nextFloat() * 0.3F, false);
    }

    @Override
    public void c() {
        if (this.b++ >= 40) {
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_FLAMING);
        }

    }

    @Override
    public void d() {
        this.b = 0;
    }

    @Override
    public DragonControllerPhase<DragonControllerLandedAttack> getControllerPhase() {
        return DragonControllerPhase.SITTING_ATTACKING;
    }
}
