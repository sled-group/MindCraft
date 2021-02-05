package net.minecraft.server;

import javax.annotation.Nullable;

public interface INamableTileEntity {

    IChatBaseComponent getDisplayName();

    default boolean hasCustomName() {
        return this.getCustomName() != null;
    }

    default IChatBaseComponent getScoreboardDisplayName() {
        return this.getDisplayName();
    }

    @Nullable
    default IChatBaseComponent getCustomName() {
        return null;
    }
}
