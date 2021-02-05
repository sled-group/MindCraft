package net.minecraft.server;

public class EntityWitherSkull extends EntityFireball {

    private static final DataWatcherObject<Boolean> f = DataWatcher.a(EntityWitherSkull.class, DataWatcherRegistry.i);

    public EntityWitherSkull(EntityTypes<? extends EntityWitherSkull> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntityWitherSkull(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(EntityTypes.WITHER_SKULL, entityliving, d0, d1, d2, world);
    }

    @Override
    protected float k() {
        return this.isCharged() ? 0.73F : super.k();
    }

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    public float a(Explosion explosion, IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid, float f) {
        return this.isCharged() && EntityWither.b(iblockdata) ? Math.min(0.8F, f) : f;
    }

    @Override
    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
                Entity entity = ((MovingObjectPositionEntity) movingobjectposition).getEntity();

                if (this.shooter != null) {
                    if (entity.damageEntity(DamageSource.mobAttack(this.shooter), 8.0F)) {
                        if (entity.isAlive()) {
                            this.a(this.shooter, entity);
                        } else {
                            this.shooter.heal(5.0F);
                        }
                    }
                } else {
                    entity.damageEntity(DamageSource.MAGIC, 5.0F);
                }

                if (entity instanceof EntityLiving) {
                    byte b0 = 0;

                    if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                        b0 = 10;
                    } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                        b0 = 40;
                    }

                    if (b0 > 0) {
                        ((EntityLiving) entity).addEffect(new MobEffect(MobEffects.WITHER, 20 * b0, 1));
                    }
                }
            }

            Explosion.Effect explosion_effect = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Effect.DESTROY : Explosion.Effect.NONE;

            this.world.createExplosion(this, this.locX, this.locY, this.locZ, 1.0F, false, explosion_effect);
            this.die();
        }

    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }

    @Override
    protected void initDatawatcher() {
        this.datawatcher.register(EntityWitherSkull.f, false);
    }

    public boolean isCharged() {
        return (Boolean) this.datawatcher.get(EntityWitherSkull.f);
    }

    public void setCharged(boolean flag) {
        this.datawatcher.set(EntityWitherSkull.f, flag);
    }

    @Override
    protected boolean K_() {
        return false;
    }
}
