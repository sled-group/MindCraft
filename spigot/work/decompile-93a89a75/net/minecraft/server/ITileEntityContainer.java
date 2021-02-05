package net.minecraft.server;

import javax.annotation.Nullable;

@FunctionalInterface
public interface ITileEntityContainer {

    @Nullable
    Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman);
}
