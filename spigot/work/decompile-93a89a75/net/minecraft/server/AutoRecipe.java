package net.minecraft.server;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutoRecipe<C extends IInventory> implements AutoRecipeAbstract<Integer> {

    protected static final Logger LOGGER = LogManager.getLogger();
    protected final AutoRecipeStackManager b = new AutoRecipeStackManager();
    protected PlayerInventory c;
    protected ContainerRecipeBook<C> d;

    public AutoRecipe(ContainerRecipeBook<C> containerrecipebook) {
        this.d = containerrecipebook;
    }

    public void a(EntityPlayer entityplayer, @Nullable IRecipe<C> irecipe, boolean flag) {
        if (irecipe != null && entityplayer.B().b(irecipe)) {
            this.c = entityplayer.inventory;
            if (this.b() || entityplayer.isCreative()) {
                this.b.a();
                entityplayer.inventory.a(this.b);
                this.d.a(this.b);
                if (this.b.a(irecipe, (IntList) null)) {
                    this.a(irecipe, flag);
                } else {
                    this.a();
                    entityplayer.playerConnection.sendPacket(new PacketPlayOutAutoRecipe(entityplayer.activeContainer.windowId, irecipe));
                }

                entityplayer.inventory.update();
            }
        }
    }

    protected void a() {
        for (int i = 0; i < this.d.g() * this.d.h() + 1; ++i) {
            if (i != this.d.f() || !(this.d instanceof ContainerWorkbench) && !(this.d instanceof ContainerPlayer)) {
                this.a(i);
            }
        }

        this.d.e();
    }

    protected void a(int i) {
        ItemStack itemstack = this.d.getSlot(i).getItem();

        if (!itemstack.isEmpty()) {
            for (; itemstack.getCount() > 0; this.d.getSlot(i).a(1)) {
                int j = this.c.firstPartial(itemstack);

                if (j == -1) {
                    j = this.c.getFirstEmptySlotIndex();
                }

                ItemStack itemstack1 = itemstack.cloneItemStack();

                itemstack1.setCount(1);
                if (!this.c.c(j, itemstack1)) {
                    AutoRecipe.LOGGER.error("Can't find any space for item in the inventory");
                }
            }

        }
    }

    protected void a(IRecipe<C> irecipe, boolean flag) {
        boolean flag1 = this.d.a(irecipe);
        int i = this.b.b(irecipe, (IntList) null);
        int j;

        if (flag1) {
            for (j = 0; j < this.d.h() * this.d.g() + 1; ++j) {
                if (j != this.d.f()) {
                    ItemStack itemstack = this.d.getSlot(j).getItem();

                    if (!itemstack.isEmpty() && Math.min(i, itemstack.getMaxStackSize()) < itemstack.getCount() + 1) {
                        return;
                    }
                }
            }
        }

        j = this.a(flag, i, flag1);
        IntArrayList intarraylist = new IntArrayList();

        if (this.b.a(irecipe, intarraylist, j)) {
            int k = j;
            IntListIterator intlistiterator = intarraylist.iterator();

            while (intlistiterator.hasNext()) {
                int l = (Integer) intlistiterator.next();
                int i1 = AutoRecipeStackManager.a(l).getMaxStackSize();

                if (i1 < k) {
                    k = i1;
                }
            }

            if (this.b.a(irecipe, intarraylist, k)) {
                this.a();
                this.a(this.d.g(), this.d.h(), this.d.f(), irecipe, intarraylist.iterator(), k);
            }
        }

    }

    @Override
    public void a(Iterator<Integer> iterator, int i, int j, int k, int l) {
        Slot slot = this.d.getSlot(i);
        ItemStack itemstack = AutoRecipeStackManager.a((Integer) iterator.next());

        if (!itemstack.isEmpty()) {
            for (int i1 = 0; i1 < j; ++i1) {
                this.a(slot, itemstack);
            }
        }

    }

    protected int a(boolean flag, int i, boolean flag1) {
        int j = 1;

        if (flag) {
            j = i;
        } else if (flag1) {
            j = 64;

            for (int k = 0; k < this.d.g() * this.d.h() + 1; ++k) {
                if (k != this.d.f()) {
                    ItemStack itemstack = this.d.getSlot(k).getItem();

                    if (!itemstack.isEmpty() && j > itemstack.getCount()) {
                        j = itemstack.getCount();
                    }
                }
            }

            if (j < 64) {
                ++j;
            }
        }

        return j;
    }

    protected void a(Slot slot, ItemStack itemstack) {
        int i = this.c.c(itemstack);

        if (i != -1) {
            ItemStack itemstack1 = this.c.getItem(i).cloneItemStack();

            if (!itemstack1.isEmpty()) {
                if (itemstack1.getCount() > 1) {
                    this.c.splitStack(i, 1);
                } else {
                    this.c.splitWithoutUpdate(i);
                }

                itemstack1.setCount(1);
                if (slot.getItem().isEmpty()) {
                    slot.set(itemstack1);
                } else {
                    slot.getItem().add(1);
                }

            }
        }
    }

    private boolean b() {
        List<ItemStack> list = Lists.newArrayList();
        int i = this.c();

        for (int j = 0; j < this.d.g() * this.d.h() + 1; ++j) {
            if (j != this.d.f()) {
                ItemStack itemstack = this.d.getSlot(j).getItem().cloneItemStack();

                if (!itemstack.isEmpty()) {
                    int k = this.c.firstPartial(itemstack);

                    if (k == -1 && list.size() <= i) {
                        Iterator iterator = list.iterator();

                        while (iterator.hasNext()) {
                            ItemStack itemstack1 = (ItemStack) iterator.next();

                            if (itemstack1.doMaterialsMatch(itemstack) && itemstack1.getCount() != itemstack1.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
                                itemstack1.add(itemstack.getCount());
                                itemstack.setCount(0);
                                break;
                            }
                        }

                        if (!itemstack.isEmpty()) {
                            if (list.size() >= i) {
                                return false;
                            }

                            list.add(itemstack);
                        }
                    } else if (k == -1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private int c() {
        int i = 0;
        Iterator iterator = this.c.items.iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack = (ItemStack) iterator.next();

            if (itemstack.isEmpty()) {
                ++i;
            }
        }

        return i;
    }
}
