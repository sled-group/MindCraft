package net.minecraft.server;

public class EntitySmallFireball extends EntityFireballFireball {

    public EntitySmallFireball(EntityTypes<? extends EntitySmallFireball> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntitySmallFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(EntityTypes.SMALL_FIREBALL, entityliving, d0, d1, d2, world);
    }

    public EntitySmallFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(EntityTypes.SMALL_FIREBALL, d0, d1, d2, d3, d4, d5, world);
    }

    @Override
    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
                Entity entity = ((MovingObjectPositionEntity) movingobjectposition).getEntity();

                if (!entity.isFireProof()) {
                    int i = entity.ad();

                    entity.setOnFire(5);
                    boolean flag = entity.damageEntity(DamageSource.fireball(this, this.shooter), 5.0F);

                    if (flag) {
                        this.a(this.shooter, entity);
                    } else {
                        entity.g(i);
                    }
                }
            } else if (this.shooter == null || !(this.shooter instanceof EntityInsentient) || this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
                MovingObjectPositionBlock movingobjectpositionblock = (MovingObjectPositionBlock) movingobjectposition;
                BlockPosition blockposition = movingobjectpositionblock.getBlockPosition().shift(movingobjectpositionblock.getDirection());

                if (this.world.isEmpty(blockposition)) {
                    this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
                }
            }

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
}
