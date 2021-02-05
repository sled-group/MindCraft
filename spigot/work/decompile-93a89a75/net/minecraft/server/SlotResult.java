package net.minecraft.server;

public class SlotResult extends Slot {

    private final InventoryCrafting a;
    private final EntityHuman b;
    private int g;

    public SlotResult(EntityHuman entityhuman, InventoryCrafting inventorycrafting, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.b = entityhuman;
        this.a = inventorycrafting;
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
    protected void b(int i) {
        this.g += i;
    }

    @Override
    protected void c(ItemStack itemstack) {
        if (this.g > 0) {
            itemstack.a(this.b.world, this.b, this.g);
        }

        if (this.inventory instanceof RecipeHolder) {
            ((RecipeHolder) this.inventory).b(this.b);
        }

        this.g = 0;
    }

    @Override
    public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
        this.c(itemstack);
        NonNullList<ItemStack> nonnulllist = entityhuman.world.getCraftingManager().c(Recipes.CRAFTING, this.a, entityhuman.world);

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack1 = this.a.getItem(i);
            ItemStack itemstack2 = (ItemStack) nonnulllist.get(i);

            if (!itemstack1.isEmpty()) {
                this.a.splitStack(i, 1);
                itemstack1 = this.a.getItem(i);
            }

            if (!itemstack2.isEmpty()) {
                if (itemstack1.isEmpty()) {
                    this.a.setItem(i, itemstack2);
                } else if (ItemStack.c(itemstack1, itemstack2) && ItemStack.equals(itemstack1, itemstack2)) {
                    itemstack2.add(itemstack1.getCount());
                    this.a.setItem(i, itemstack2);
                } else if (!this.b.inventory.pickup(itemstack2)) {
                    this.b.drop(itemstack2, false);
                }
            }
        }

        return itemstack;
    }
}
