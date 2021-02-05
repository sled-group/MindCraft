package net.minecraft.server;

import java.util.EnumSet;

public class EntityBlaze extends EntityMonster {

    private float b = 0.5F;
    private int c;
    private static final DataWatcherObject<Byte> d = DataWatcher.a(EntityBlaze.class, DataWatcherRegistry.a);

    public EntityBlaze(EntityTypes<? extends EntityBlaze> entitytypes, World world) {
        super(entitytypes, world);
        this.a(PathType.WATER, -1.0F);
        this.a(PathType.LAVA, 8.0F);
        this.a(PathType.DANGER_FIRE, 0.0F);
        this.a(PathType.DAMAGE_FIRE, 0.0F);
        this.f = 10;
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(4, new EntityBlaze.PathfinderGoalBlazeFireball(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a());
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(48.0D);
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.register(EntityBlaze.d, (byte) 0);
    }

    @Override
    protected SoundEffect getSoundAmbient() {
        return SoundEffects.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return SoundEffects.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEffect getSoundDeath() {
        return SoundEffects.ENTITY_BLAZE_DEATH;
    }

    @Override
    public float aF() {
        return 1.0F;
    }

    @Override
    public void movementTick() {
        if (!this.onGround && this.getMot().y < 0.0D) {
            this.setMot(this.getMot().d(1.0D, 0.6D, 1.0D));
        }

        if (this.world.isClientSide) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.world.a(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, SoundEffects.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.world.addParticle(Particles.LARGE_SMOKE, this.locX + (this.random.nextDouble() - 0.5D) * (double) this.getWidth(), this.locY + this.random.nextDouble() * (double) this.getHeight(), this.locZ + (this.random.nextDouble() - 0.5D) * (double) this.getWidth(), 0.0D, 0.0D, 0.0D);
            }
        }

        super.movementTick();
    }

    @Override
    protected void mobTick() {
        if (this.au()) {
            this.damageEntity(DamageSource.DROWN, 1.0F);
        }

        --this.c;
        if (this.c <= 0) {
            this.c = 100;
            this.b = 0.5F + (float) this.random.nextGaussian() * 3.0F;
        }

        EntityLiving entityliving = this.getGoalTarget();

        if (entityliving != null && entityliving.locY + (double) entityliving.getHeadHeight() > this.locY + (double) this.getHeadHeight() + (double) this.b && this.c(entityliving)) {
            Vec3D vec3d = this.getMot();

            this.setMot(this.getMot().add(0.0D, (0.30000001192092896D - vec3d.y) * 0.30000001192092896D, 0.0D));
            this.impulse = true;
        }

        super.mobTick();
    }

    @Override
    public void b(float f, float f1) {}

    @Override
    public boolean isBurning() {
        return this.l();
    }

    private boolean l() {
        return ((Byte) this.datawatcher.get(EntityBlaze.d) & 1) != 0;
    }

    private void r(boolean flag) {
        byte b0 = (Byte) this.datawatcher.get(EntityBlaze.d);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.datawatcher.set(EntityBlaze.d, b0);
    }

    static class PathfinderGoalBlazeFireball extends PathfinderGoal {

        private final EntityBlaze a;
        private int b;
        private int c;
        private int d;

        public PathfinderGoalBlazeFireball(EntityBlaze entityblaze) {
            this.a = entityblaze;
            this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
        }

        @Override
        public boolean a() {
            EntityLiving entityliving = this.a.getGoalTarget();

            return entityliving != null && entityliving.isAlive() && this.a.c(entityliving);
        }

        @Override
        public void c() {
            this.b = 0;
        }

        @Override
        public void d() {
            this.a.r(false);
            this.d = 0;
        }

        @Override
        public void e() {
            --this.c;
            EntityLiving entityliving = this.a.getGoalTarget();

            if (entityliving != null) {
                boolean flag = this.a.getEntitySenses().a(entityliving);

                if (flag) {
                    this.d = 0;
                } else {
                    ++this.d;
                }

                double d0 = this.a.h((Entity) entityliving);

                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.c <= 0) {
                        this.c = 20;
                        this.a.C(entityliving);
                    }

                    this.a.getControllerMove().a(entityliving.locX, entityliving.locY, entityliving.locZ, 1.0D);
                } else if (d0 < this.g() * this.g() && flag) {
                    double d1 = entityliving.locX - this.a.locX;
                    double d2 = entityliving.getBoundingBox().minY + (double) (entityliving.getHeight() / 2.0F) - (this.a.locY + (double) (this.a.getHeight() / 2.0F));
                    double d3 = entityliving.locZ - this.a.locZ;

                    if (this.c <= 0) {
                        ++this.b;
                        if (this.b == 1) {
                            this.c = 60;
                            this.a.r(true);
                        } else if (this.b <= 4) {
                            this.c = 6;
                        } else {
                            this.c = 100;
                            this.b = 0;
                            this.a.r(false);
                        }

                        if (this.b > 1) {
                            float f = MathHelper.c(MathHelper.sqrt(d0)) * 0.5F;

                            this.a.world.a((EntityHuman) null, 1018, new BlockPosition(this.a), 0);

                            for (int i = 0; i < 1; ++i) {
                                EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.a.world, this.a, d1 + this.a.getRandom().nextGaussian() * (double) f, d2, d3 + this.a.getRandom().nextGaussian() * (double) f);

                                entitysmallfireball.locY = this.a.locY + (double) (this.a.getHeight() / 2.0F) + 0.5D;
                                this.a.world.addEntity(entitysmallfireball);
                            }
                        }
                    }

                    this.a.getControllerLook().a(entityliving, 10.0F, 10.0F);
                } else if (this.d < 5) {
                    this.a.getControllerMove().a(entityliving.locX, entityliving.locY, entityliving.locZ, 1.0D);
                }

                super.e();
            }
        }

        private double g() {
            return this.a.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).getValue();
        }
    }
}
