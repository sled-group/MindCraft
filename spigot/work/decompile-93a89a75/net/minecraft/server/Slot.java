package net.minecraft.server;

public class Slot {

    public final int index;
    public final IInventory inventory;
    public int rawSlotIndex;
    public int e;
    public int f;

    public Slot(IInventory iinventory, int i, int j, int k) {
        this.inventory = iinventory;
        this.index = i;
        this.e = j;
        this.f = k;
    }

    public void a(ItemStack itemstack, ItemStack itemstack1) {
        int i = itemstack1.getCount() - itemstack.getCount();

        if (i > 0) {
            this.a(itemstack1, i);
        }

    }

    protected void a(ItemStack itemstack, int i) {}

    protected void b(int i) {}

    protected void c(ItemStack itemstack) {}

    public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
        this.d();
        return itemstack;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return true;
    }

    public ItemStack getItem() {
        return this.inventory.getItem(this.index);
    }

    public boolean hasItem() {
        return !this.getItem().isEmpty();
    }

    public void set(ItemStack itemstack) {
        this.inventory.setItem(this.index, itemstack);
        this.d();
    }

    public void d() {
        this.inventory.update();
    }

    public int getMaxStackSize() {
        return this.inventory.getMaxStackSize();
    }

    public int getMaxStackSize(ItemStack itemstack) {
        return this.getMaxStackSize();
    }

    public ItemStack a(int i) {
        return this.inventory.splitStack(this.index, i);
    }

    public boolean isAllowed(EntityHuman entityhuman) {
        return true;
    }
}
