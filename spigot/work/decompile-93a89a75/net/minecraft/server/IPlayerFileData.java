package net.minecraft.server;

import javax.annotation.Nullable;

public interface IPlayerFileData {

    void save(EntityHuman entityhuman);

    @Nullable
    NBTTagCompound load(EntityHuman entityhuman);
}
