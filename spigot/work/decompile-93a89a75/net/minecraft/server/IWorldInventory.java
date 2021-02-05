package net.minecraft.server;

import javax.annotation.Nullable;

public interface IWorldInventory extends IInventory {

    int[] getSlotsForFace(EnumDirection enumdirection);

    boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection);

    boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection);
}
