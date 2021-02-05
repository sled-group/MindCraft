package net.minecraft.server;

public abstract class EntityProjectileThrowable extends EntityProjectile {

    private static final DataWatcherObject<ItemStack> e = DataWatcher.a(EntityProjectileThrowable.class, DataWatcherRegistry.g);

    public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, double d0, double d1, double d2, World world) {
        super(entitytypes, d0, d1, d2, world);
    }

    public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, EntityLiving entityliving, World world) {
        super(entitytypes, entityliving, world);
    }

    public void setItem(ItemStack itemstack) {
        if (itemstack.getItem() != this.i() || itemstack.hasTag()) {
            this.getDataWatcher().set(EntityProjectileThrowable.e, SystemUtils.a((Object) itemstack.cloneItemStack(), (itemstack1) -> {
                itemstack1.setCount(1);
            }));
        }

    }

    protected abstract Item i();

    protected ItemStack getItem() {
        return (ItemStack) this.getDataWatcher().get(EntityProjectileThrowable.e);
    }

    @Override
    protected void initDatawatcher() {
        this.getDataWatcher().register(EntityProjectileThrowable.e, ItemStack.a);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        ItemStack itemstack = this.getItem();

        if (!itemstack.isEmpty()) {
            nbttagcompound.set("Item", itemstack.save(new NBTTagCompound()));
        }

    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("Item"));

        this.setItem(itemstack);
    }
}
