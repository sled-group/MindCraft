package net.minecraft.server;

import java.util.List;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {

    private boolean d = true;
    private int e = -1;
    private final BlockPosition f;

    public EntityMinecartHopper(EntityTypes<? extends EntityMinecartHopper> entitytypes, World world) {
        super(entitytypes, world);
        this.f = BlockPosition.ZERO;
    }

    public EntityMinecartHopper(World world, double d0, double d1, double d2) {
        super(EntityTypes.HOPPER_MINECART, d0, d1, d2, world);
        this.f = BlockPosition.ZERO;
    }

    @Override
    public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
        return EntityMinecartAbstract.EnumMinecartType.HOPPER;
    }

    @Override
    public IBlockData q() {
        return Blocks.HOPPER.getBlockData();
    }

    @Override
    public int s() {
        return 1;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public void a(int i, int j, int k, boolean flag) {
        boolean flag1 = !flag;

        if (flag1 != this.isEnabled()) {
            this.setEnabled(flag1);
        }

    }

    public boolean isEnabled() {
        return this.d;
    }

    public void setEnabled(boolean flag) {
        this.d = flag;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public double z() {
        return this.locX;
    }

    @Override
    public double A() {
        return this.locY + 0.5D;
    }

    @Override
    public double B() {
        return this.locZ;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClientSide && this.isAlive() && this.isEnabled()) {
            BlockPosition blockposition = new BlockPosition(this);

            if (blockposition.equals(this.f)) {
                --this.e;
            } else {
                this.setCooldown(0);
            }

            if (!this.D()) {
                this.setCooldown(0);
                if (this.C()) {
                    this.setCooldown(4);
                    this.update();
                }
            }
        }

    }

    public boolean C() {
        if (TileEntityHopper.a((IHopper) this)) {
            return true;
        } else {
            List<EntityItem> list = this.world.a(EntityItem.class, this.getBoundingBox().grow(0.25D, 0.0D, 0.25D), IEntitySelector.a);

            if (!list.isEmpty()) {
                TileEntityHopper.a((IInventory) this, (EntityItem) list.get(0));
            }

            return false;
        }
    }

    @Override
    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            this.a((IMaterial) Blocks.HOPPER);
        }

    }

    @Override
    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("TransferCooldown", this.e);
        nbttagcompound.setBoolean("Enabled", this.d);
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.e = nbttagcompound.getInt("TransferCooldown");
        this.d = nbttagcompound.hasKey("Enabled") ? nbttagcompound.getBoolean("Enabled") : true;
    }

    public void setCooldown(int i) {
        this.e = i;
    }

    public boolean D() {
        return this.e > 0;
    }

    @Override
    public Container a(int i, PlayerInventory playerinventory) {
        return new ContainerHopper(i, playerinventory, this);
    }
}
