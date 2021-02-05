package net.minecraft.server;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.Iterator;

public class AutoRecipeFurnace<C extends IInventory> extends AutoRecipe<C> {

    private boolean e;

    public AutoRecipeFurnace(ContainerRecipeBook<C> containerrecipebook) {
        super(containerrecipebook);
    }

    @Override
    protected void a(IRecipe<C> irecipe, boolean flag) {
        this.e = this.d.a(irecipe);
        int i = this.b.b(irecipe, (IntList) null);

        if (this.e) {
            ItemStack itemstack = this.d.getSlot(0).getItem();

            if (itemstack.isEmpty() || i <= itemstack.getCount()) {
                return;
            }
        }

        int j = this.a(flag, i, this.e);
        IntArrayList intarraylist = new IntArrayList();

        if (this.b.a(irecipe, intarraylist, j)) {
            if (!this.e) {
                this.a(this.d.f());
                this.a(0);
            }

            this.a(j, intarraylist);
        }
    }

    @Override
    protected void a() {
        this.a(this.d.f());
        super.a();
    }

    protected void a(int i, IntList intlist) {
        Iterator<Integer> iterator = intlist.iterator();
        Slot slot = this.d.getSlot(0);
        ItemStack itemstack = AutoRecipeStackManager.a((Integer) iterator.next());

        if (!itemstack.isEmpty()) {
            int j = Math.min(itemstack.getMaxStackSize(), i);

            if (this.e) {
                j -= slot.getItem().getCount();
            }

            for (int k = 0; k < j; ++k) {
                this.a(slot, itemstack);
            }

        }
    }
}
