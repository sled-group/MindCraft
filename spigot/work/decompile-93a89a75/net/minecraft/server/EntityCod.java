package net.minecraft.server;

public class EntityCod extends EntityFishSchool {

    public EntityCod(EntityTypes<? extends EntityCod> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    protected ItemStack l() {
        return new ItemStack(Items.COD_BUCKET);
    }

    @Override
    protected SoundEffect getSoundAmbient() {
        return SoundEffects.ENTITY_COD_AMBIENT;
    }

    @Override
    protected SoundEffect getSoundDeath() {
        return SoundEffects.ENTITY_COD_DEATH;
    }

    @Override
    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return SoundEffects.ENTITY_COD_HURT;
    }

    @Override
    protected SoundEffect getSoundFlop() {
        return SoundEffects.ENTITY_COD_FLOP;
    }
}
