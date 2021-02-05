package net.minecraft.server;

interface ChunkGeneratorFactory<C extends GeneratorSettingsDefault, T extends ChunkGenerator<C>> {

    T create(World world, WorldChunkManager worldchunkmanager, C c0);
}
