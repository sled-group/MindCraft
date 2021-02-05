package net.minecraft.server;

import java.util.List;

public class ContainerUtil {

    public static ItemStack a(List<ItemStack> list, int i, int j) {
        return i >= 0 && i < list.size() && !((ItemStack) list.get(i)).isEmpty() && j > 0 ? ((ItemStack) list.get(i)).cloneAndSubtract(j) : ItemStack.a;
    }

    public static ItemStack a(List<ItemStack> list, int i) {
        return i >= 0 && i < list.size() ? (ItemStack) list.set(i, ItemStack.a) : ItemStack.a;
    }

    public static NBTTagCompound a(NBTTagCompound nbttagcompound, NonNullList<ItemStack> nonnulllist) {
        return a(nbttagcompound, nonnulllist, true);
    }

    public static NBTTagCompound a(NBTTagCompound nbttagcompound, NonNullList<ItemStack> nonnulllist, boolean flag) {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = (ItemStack) nonnulllist.get(i);

            if (!itemstack.isEmpty()) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.setByte("Slot", (byte) i);
                itemstack.save(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        if (!nbttaglist.isEmpty() || flag) {
            nbttagcompound.set("Items", nbttaglist);
        }

        return nbttagcompound;
    }

    public static void b(NBTTagCompound nbttagcompound, NonNullList<ItemStack> nonnulllist) {
        NBTTagList nbttaglist = nbttagcompound.getList("Items", 10);

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < nonnulllist.size()) {
                nonnulllist.set(j, ItemStack.a(nbttagcompound1));
            }
        }

    }
}
