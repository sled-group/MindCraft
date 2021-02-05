package net.minecraft.server;

public abstract class EntityFireballFireball extends EntityFireball {

    private static final DataWatcherObject<ItemStack> f = DataWatcher.a(EntityFireballFireball.class, DataWatcherRegistry.g);

    public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, World world) {
        super(entitytypes, world);
    }

    public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, double d0, double d1, double d2, double d3, double d4, double d5, World world) {
        super(entitytypes, d0, d1, d2, d3, d4, d5, world);
    }

    public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, EntityLiving entityliving, double d0, double d1, double d2, World world) {
        super(entitytypes, entityliving, d0, d1, d2, world);
    }

    public void b(ItemStack itemstack) {
        if (itemstack.getItem() != Items.FIRE_CHARGE || itemstack.hasTag()) {
            this.getDataWatcher().set(EntityFireballFireball.f, SystemUtils.a((Object) itemstack.cloneItemStack(), (itemstack1) -> {
                itemstack1.setCount(1);
            }));
        }

    }

    protected ItemStack l() {
        return (ItemStack) this.getDataWatcher().get(EntityFireballFireball.f);
    }

    @Override
    protected void initDatawatcher() {
        this.getDataWatcher().register(EntityFireballFireball.f, ItemStack.a);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        ItemStack itemstack = this.l();

        if (!itemstack.isEmpty()) {
            nbttagcompound.set("Item", itemstack.save(new NBTTagCompound()));
        }

    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("Item"));

        this.b(itemstack);
    }
}
