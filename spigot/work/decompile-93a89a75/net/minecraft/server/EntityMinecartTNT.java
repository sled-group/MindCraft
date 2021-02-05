package net.minecraft.server;

public class EntityMinecartTNT extends EntityMinecartAbstract {

    private int b = -1;

    public EntityMinecartTNT(EntityTypes<? extends EntityMinecartTNT> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntityMinecartTNT(World world, double d0, double d1, double d2) {
        super(EntityTypes.TNT_MINECART, world, d0, d1, d2);
    }

    @Override
    public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
        return EntityMinecartAbstract.EnumMinecartType.TNT;
    }

    @Override
    public IBlockData q() {
        return Blocks.TNT.getBlockData();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.b > 0) {
            --this.b;
            this.world.addParticle(Particles.SMOKE, this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D);
        } else if (this.b == 0) {
            this.c(b(this.getMot()));
        }

        if (this.positionChanged) {
            double d0 = b(this.getMot());

            if (d0 >= 0.009999999776482582D) {
                this.c(d0);
            }
        }

    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        Entity entity = damagesource.j();

        if (entity instanceof EntityArrow) {
            EntityArrow entityarrow = (EntityArrow) entity;

            if (entityarrow.isBurning()) {
                this.c(entityarrow.getMot().g());
            }
        }

        return super.damageEntity(damagesource, f);
    }

    @Override
    public void a(DamageSource damagesource) {
        double d0 = b(this.getMot());

        if (!damagesource.p() && !damagesource.isExplosion() && d0 < 0.009999999776482582D) {
            super.a(damagesource);
            if (!damagesource.isExplosion() && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                this.a((IMaterial) Blocks.TNT);
            }

        } else {
            if (this.b < 0) {
                this.v();
                this.b = this.random.nextInt(20) + this.random.nextInt(20);
            }

        }
    }

    protected void c(double d0) {
        if (!this.world.isClientSide) {
            double d1 = Math.sqrt(d0);

            if (d1 > 5.0D) {
                d1 = 5.0D;
            }

            this.world.explode(this, this.locX, this.locY, this.locZ, (float) (4.0D + this.random.nextDouble() * 1.5D * d1), Explosion.Effect.BREAK);
            this.die();
        }

    }

    @Override
    public void b(float f, float f1) {
        if (f >= 3.0F) {
            float f2 = f / 10.0F;

            this.c((double) (f2 * f2));
        }

        super.b(f, f1);
    }

    @Override
    public void a(int i, int j, int k, boolean flag) {
        if (flag && this.b < 0) {
            this.v();
        }

    }

    public void v() {
        this.b = 80;
        if (!this.world.isClientSide) {
            this.world.broadcastEntityEffect(this, (byte) 10);
            if (!this.isSilent()) {
                this.world.playSound((EntityHuman) null, this.locX, this.locY, this.locZ, SoundEffects.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }

    }

    public boolean z() {
        return this.b > -1;
    }

    @Override
    public float a(Explosion explosion, IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid, float f) {
        return this.z() && (iblockdata.a(TagsBlock.RAILS) || iblockaccess.getType(blockposition.up()).a(TagsBlock.RAILS)) ? 0.0F : super.a(explosion, iblockaccess, blockposition, iblockdata, fluid, f);
    }

    @Override
    public boolean a(Explosion explosion, IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, float f) {
        return this.z() && (iblockdata.a(TagsBlock.RAILS) || iblockaccess.getType(blockposition.up()).a(TagsBlock.RAILS)) ? false : super.a(explosion, iblockaccess, blockposition, iblockdata, f);
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("TNTFuse", 99)) {
            this.b = nbttagcompound.getInt("TNTFuse");
        }

    }

    @Override
    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("TNTFuse", this.b);
    }
}
