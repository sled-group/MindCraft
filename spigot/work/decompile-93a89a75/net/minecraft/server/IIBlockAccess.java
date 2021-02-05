package net.minecraft.server;

public interface IIBlockAccess extends IBlockAccess {

    BiomeBase getBiome(BlockPosition blockposition);

    int getBrightness(EnumSkyBlock enumskyblock, BlockPosition blockposition);

    default boolean f(BlockPosition blockposition) {
        return this.getBrightness(EnumSkyBlock.SKY, blockposition) >= this.H();
    }
}
