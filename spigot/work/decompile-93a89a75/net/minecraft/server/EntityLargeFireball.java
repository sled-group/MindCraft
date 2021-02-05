package net.minecraft.server;

public class EntityLargeFireball extends EntityFireballFireball {

    public int yield = 1;

    public EntityLargeFireball(EntityTypes<? extends EntityLargeFireball> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntityLargeFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(EntityTypes.FIREBALL, entityliving, d0, d1, d2, world);
    }

    @Override
    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
                Entity entity = ((MovingObjectPositionEntity) movingobjectposition).getEntity();

                entity.damageEntity(DamageSource.fireball(this, this.shooter), 6.0F);
                this.a(this.shooter, entity);
            }

            boolean flag = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);

            this.world.createExplosion((Entity) null, this.locX, this.locY, this.locZ, (float) this.yield, flag, flag ? Explosion.Effect.DESTROY : Explosion.Effect.NONE);
            this.die();
        }

    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("ExplosionPower", this.yield);
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("ExplosionPower", 99)) {
            this.yield = nbttagcompound.getInt("ExplosionPower");
        }

    }
}
