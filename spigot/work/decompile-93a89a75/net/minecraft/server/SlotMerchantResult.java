package net.minecraft.server;

public class SlotMerchantResult extends Slot {

    private final InventoryMerchant a;
    private final EntityHuman b;
    private int g;
    private final IMerchant h;

    public SlotMerchantResult(EntityHuman entityhuman, IMerchant imerchant, InventoryMerchant inventorymerchant, int i, int j, int k) {
        super(inventorymerchant, i, j, k);
        this.b = entityhuman;
        this.h = imerchant;
        this.a = inventorymerchant;
    }

    @Override
    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    @Override
    public ItemStack a(int i) {
        if (this.hasItem()) {
            this.g += Math.min(i, this.getItem().getCount());
        }

        return super.a(i);
    }

    @Override
    protected void a(ItemStack itemstack, int i) {
        this.g += i;
        this.c(itemstack);
    }

    @Override
    protected void c(ItemStack itemstack) {
        itemstack.a(this.b.world, this.b, this.g);
        this.g = 0;
    }

    @Override
    public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
        this.c(itemstack);
        MerchantRecipe merchantrecipe = this.a.getRecipe();

        if (merchantrecipe != null) {
            ItemStack itemstack1 = this.a.getItem(0);
            ItemStack itemstack2 = this.a.getItem(1);

            if (merchantrecipe.b(itemstack1, itemstack2) || merchantrecipe.b(itemstack2, itemstack1)) {
                this.h.a(merchantrecipe);
                entityhuman.a(StatisticList.TRADED_WITH_VILLAGER);
                this.a.setItem(0, itemstack1);
                this.a.setItem(1, itemstack2);
            }

            this.h.s(this.h.getExperience() + merchantrecipe.getXp());
        }

        return itemstack;
    }
}
