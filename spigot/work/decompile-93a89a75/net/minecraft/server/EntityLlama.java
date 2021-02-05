package net.minecraft.server;

import java.util.Iterator;
import javax.annotation.Nullable;

public class EntityLlama extends EntityHorseChestedAbstract implements IRangedEntity {

    private static final DataWatcherObject<Integer> bI = DataWatcher.a(EntityLlama.class, DataWatcherRegistry.b);
    private static final DataWatcherObject<Integer> bJ = DataWatcher.a(EntityLlama.class, DataWatcherRegistry.b);
    private static final DataWatcherObject<Integer> bK = DataWatcher.a(EntityLlama.class, DataWatcherRegistry.b);
    private boolean bL;
    @Nullable
    private EntityLlama bM;
    @Nullable
    private EntityLlama bN;

    public EntityLlama(EntityTypes<? extends EntityLlama> entitytypes, World world) {
        super(entitytypes, world);
    }

    public void setStrength(int i) {
        this.datawatcher.set(EntityLlama.bI, Math.max(1, Math.min(5, i)));
    }

    private void eK() {
        int i = this.random.nextFloat() < 0.04F ? 5 : 3;

        this.setStrength(1 + this.random.nextInt(i));
    }

    public int getStrength() {
        return (Integer) this.datawatcher.get(EntityLlama.bI);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Variant", this.getVariant());
        nbttagcompound.setInt("Strength", this.getStrength());
        if (!this.inventoryChest.getItem(1).isEmpty()) {
            nbttagcompound.set("DecorItem", this.inventoryChest.getItem(1).save(new NBTTagCompound()));
        }

    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.setStrength(nbttagcompound.getInt("Strength"));
        super.a(nbttagcompound);
        this.setVariant(nbttagcompound.getInt("Variant"));
        if (nbttagcompound.hasKeyOfType("DecorItem", 10)) {
            this.inventoryChest.setItem(1, ItemStack.a(nbttagcompound.getCompound("DecorItem")));
        }

        this.en();
    }

    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTame(this, 1.2D));
        this.goalSelector.a(2, new PathfinderGoalLlamaFollow(this, 2.0999999046325684D));
        this.goalSelector.a(3, new PathfinderGoalArrowAttack(this, 1.25D, 40, 20.0F));
        this.goalSelector.a(3, new PathfinderGoalPanic(this, 1.2D));
        this.goalSelector.a(4, new PathfinderGoalBreed(this, 1.0D));
        this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.7D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new EntityLlama.c(this));
        this.targetSelector.a(2, new EntityLlama.a(this));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(40.0D);
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.register(EntityLlama.bI, 0);
        this.datawatcher.register(EntityLlama.bJ, -1);
        this.datawatcher.register(EntityLlama.bK, 0);
    }

    public int getVariant() {
        return MathHelper.clamp((Integer) this.datawatcher.get(EntityLlama.bK), 0, 3);
    }

    public void setVariant(int i) {
        this.datawatcher.set(EntityLlama.bK, i);
    }

    @Override
    protected int getChestSlots() {
        return this.isCarryingChest() ? 2 + 3 * this.dZ() : super.getChestSlots();
    }

    @Override
    public void k(Entity entity) {
        if (this.w(entity)) {
            float f = MathHelper.cos(this.aK * 0.017453292F);
            float f1 = MathHelper.sin(this.aK * 0.017453292F);
            float f2 = 0.3F;

            entity.setPosition(this.locX + (double) (0.3F * f1), this.locY + this.aP() + entity.aO(), this.locZ - (double) (0.3F * f));
        }
    }

    @Override
    public double aP() {
        return (double) this.getHeight() * 0.67D;
    }

    @Override
    public boolean dD() {
        return false;
    }

    @Override
    protected boolean b(EntityHuman entityhuman, ItemStack itemstack) {
        byte b0 = 0;
        byte b1 = 0;
        float f = 0.0F;
        boolean flag = false;
        Item item = itemstack.getItem();

        if (item == Items.WHEAT) {
            b0 = 10;
            b1 = 3;
            f = 2.0F;
        } else if (item == Blocks.HAY_BLOCK.getItem()) {
            b0 = 90;
            b1 = 6;
            f = 10.0F;
            if (this.isTamed() && this.getAge() == 0 && this.ea()) {
                flag = true;
                this.f(entityhuman);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (this.isBaby() && b0 > 0) {
            this.world.addParticle(Particles.HAPPY_VILLAGER, this.locX + (double) (this.random.nextFloat() * this.getWidth() * 2.0F) - (double) this.getWidth(), this.locY + 0.5D + (double) (this.random.nextFloat() * this.getHeight()), this.locZ + (double) (this.random.nextFloat() * this.getWidth() * 2.0F) - (double) this.getWidth(), 0.0D, 0.0D, 0.0D);
            if (!this.world.isClientSide) {
                this.setAge(b0);
            }

            flag = true;
        }

        if (b1 > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxDomestication()) {
            flag = true;
            if (!this.world.isClientSide) {
                this.u(b1);
            }
        }

        if (flag && !this.isSilent()) {
            this.world.playSound((EntityHuman) null, this.locX, this.locY, this.locZ, SoundEffects.ENTITY_LLAMA_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        }

        return flag;
    }

    @Override
    protected boolean isFrozen() {
        return this.getHealth() <= 0.0F || this.ei();
    }

    @Nullable
    @Override
    public GroupDataEntity prepare(GeneratorAccess generatoraccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
        Object object = super.prepare(generatoraccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);

        this.eK();
        int i;

        if (object instanceof EntityLlama.b) {
            i = ((EntityLlama.b) object).a;
        } else {
            i = this.random.nextInt(4);
            object = new EntityLlama.b(i);
        }

        this.setVariant(i);
        return (GroupDataEntity) object;
    }

    @Override
    protected SoundEffect getSoundAngry() {
        return SoundEffects.ENTITY_LLAMA_ANGRY;
    }

    @Override
    protected SoundEffect getSoundAmbient() {
        return SoundEffects.ENTITY_LLAMA_AMBIENT;
    }

    @Override
    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return SoundEffects.ENTITY_LLAMA_HURT;
    }

    @Override
    protected SoundEffect getSoundDeath() {
        return SoundEffects.ENTITY_LLAMA_DEATH;
    }

    @Override
    protected void a(BlockPosition blockposition, IBlockData iblockdata) {
        this.a(SoundEffects.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void dY() {
        this.a(SoundEffects.ENTITY_LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public void eu() {
        SoundEffect soundeffect = this.getSoundAngry();

        if (soundeffect != null) {
            this.a(soundeffect, this.getSoundVolume(), this.cV());
        }

    }

    @Override
    public int dZ() {
        return this.getStrength();
    }

    @Override
    public boolean eA() {
        return true;
    }

    @Override
    public boolean j(ItemStack itemstack) {
        Item item = itemstack.getItem();

        return TagsItem.CARPETS.isTagged(item);
    }

    @Override
    public boolean ep() {
        return false;
    }

    @Override
    public void a(IInventory iinventory) {
        EnumColor enumcolor = this.eE();

        super.a(iinventory);
        EnumColor enumcolor1 = this.eE();

        if (this.ticksLived > 20 && enumcolor1 != null && enumcolor1 != enumcolor) {
            this.a(SoundEffects.ENTITY_LLAMA_SWAG, 0.5F, 1.0F);
        }

    }

    @Override
    protected void en() {
        if (!this.world.isClientSide) {
            super.en();
            this.a(k(this.inventoryChest.getItem(1)));
        }
    }

    private void a(@Nullable EnumColor enumcolor) {
        this.datawatcher.set(EntityLlama.bJ, enumcolor == null ? -1 : enumcolor.getColorIndex());
    }

    @Nullable
    private static EnumColor k(ItemStack itemstack) {
        Block block = Block.asBlock(itemstack.getItem());

        return block instanceof BlockCarpet ? ((BlockCarpet) block).d() : null;
    }

    @Nullable
    public EnumColor eE() {
        int i = (Integer) this.datawatcher.get(EntityLlama.bJ);

        return i == -1 ? null : EnumColor.fromColorIndex(i);
    }

    @Override
    public int getMaxDomestication() {
        return 30;
    }

    @Override
    public boolean mate(EntityAnimal entityanimal) {
        return entityanimal != this && entityanimal instanceof EntityLlama && this.ew() && ((EntityLlama) entityanimal).ew();
    }

    @Override
    public EntityLlama createChild(EntityAgeable entityageable) {
        EntityLlama entityllama = this.eF();

        this.a(entityageable, (EntityHorseAbstract) entityllama);
        EntityLlama entityllama1 = (EntityLlama) entityageable;
        int i = this.random.nextInt(Math.max(this.getStrength(), entityllama1.getStrength())) + 1;

        if (this.random.nextFloat() < 0.03F) {
            ++i;
        }

        entityllama.setStrength(i);
        entityllama.setVariant(this.random.nextBoolean() ? this.getVariant() : entityllama1.getVariant());
        return entityllama;
    }

    protected EntityLlama eF() {
        return (EntityLlama) EntityTypes.LLAMA.a(this.world);
    }

    private void h(EntityLiving entityliving) {
        EntityLlamaSpit entityllamaspit = new EntityLlamaSpit(this.world, this);
        double d0 = entityliving.locX - this.locX;
        double d1 = entityliving.getBoundingBox().minY + (double) (entityliving.getHeight() / 3.0F) - entityllamaspit.locY;
        double d2 = entityliving.locZ - this.locZ;
        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;

        entityllamaspit.shoot(d0, d1 + (double) f, d2, 1.5F, 10.0F);
        this.world.playSound((EntityHuman) null, this.locX, this.locY, this.locZ, SoundEffects.ENTITY_LLAMA_SPIT, this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        this.world.addEntity(entityllamaspit);
        this.bL = true;
    }

    private void z(boolean flag) {
        this.bL = flag;
    }

    @Override
    public void b(float f, float f1) {
        int i = MathHelper.f((f * 0.5F - 3.0F) * f1);

        if (i > 0) {
            if (f >= 6.0F) {
                this.damageEntity(DamageSource.FALL, (float) i);
                if (this.isVehicle()) {
                    Iterator iterator = this.getAllPassengers().iterator();

                    while (iterator.hasNext()) {
                        Entity entity = (Entity) iterator.next();

                        entity.damageEntity(DamageSource.FALL, (float) i);
                    }
                }
            }

            IBlockData iblockdata = this.world.getType(new BlockPosition(this.locX, this.locY - 0.2D - (double) this.lastYaw, this.locZ));

            if (!iblockdata.isAir() && !this.isSilent()) {
                SoundEffectType soundeffecttype = iblockdata.r();

                this.world.playSound((EntityHuman) null, this.locX, this.locY, this.locZ, soundeffecttype.d(), this.getSoundCategory(), soundeffecttype.a() * 0.5F, soundeffecttype.b() * 0.75F);
            }

        }
    }

    public void eG() {
        if (this.bM != null) {
            this.bM.bN = null;
        }

        this.bM = null;
    }

    public void a(EntityLlama entityllama) {
        this.bM = entityllama;
        this.bM.bN = this;
    }

    public boolean eH() {
        return this.bN != null;
    }

    public boolean eI() {
        return this.bM != null;
    }

    @Nullable
    public EntityLlama eJ() {
        return this.bM;
    }

    @Override
    protected double dU() {
        return 2.0D;
    }

    @Override
    protected void es() {
        if (!this.eI() && this.isBaby()) {
            super.es();
        }

    }

    @Override
    public boolean et() {
        return false;
    }

    @Override
    public void a(EntityLiving entityliving, float f) {
        this.h(entityliving);
    }

    static class a extends PathfinderGoalNearestAttackableTarget<EntityWolf> {

        public a(EntityLlama entityllama) {
            super(entityllama, EntityWolf.class, 16, false, true, (entityliving) -> {
                return !((EntityWolf) entityliving).isTamed();
            });
        }

        @Override
        protected double k() {
            return super.k() * 0.25D;
        }
    }

    static class c extends PathfinderGoalHurtByTarget {

        public c(EntityLlama entityllama) {
            super(entityllama);
        }

        @Override
        public boolean b() {
            if (this.e instanceof EntityLlama) {
                EntityLlama entityllama = (EntityLlama) this.e;

                if (entityllama.bL) {
                    entityllama.z(false);
                    return false;
                }
            }

            return super.b();
        }
    }

    static class b implements GroupDataEntity {

        public final int a;

        private b(int i) {
            this.a = i;
        }
    }
}
