package net.minecraft.server;

import javax.annotation.Nullable;

public interface ILightAccess {

    @Nullable
    IBlockAccess c(int i, int j);

    default void a(EnumSkyBlock enumskyblock, SectionPosition sectionposition) {}

    IBlockAccess getWorld();
}
