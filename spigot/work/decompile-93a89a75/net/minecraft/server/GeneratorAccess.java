package net.minecraft.server;

import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public interface GeneratorAccess extends IEntityAccess, IWorldReader, VirtualLevelWritable {

    long getSeed();

    default float aa() {
        return WorldProvider.a[this.getWorldProvider().a(this.getWorldData().getDayTime())];
    }

    default float j(float f) {
        return this.getWorldProvider().a(this.getWorldData().getDayTime(), f);
    }

    TickList<Block> getBlockTickList();

    TickList<FluidType> getFluidTickList();

    World getMinecraftWorld();

    WorldData getWorldData();

    DifficultyDamageScaler getDamageScaler(BlockPosition blockposition);

    default EnumDifficulty getDifficulty() {
        return this.getWorldData().getDifficulty();
    }

    IChunkProvider getChunkProvider();

    @Override
    default boolean isChunkLoaded(int i, int j) {
        return this.getChunkProvider().b(i, j);
    }

    Random getRandom();

    void update(BlockPosition blockposition, Block block);

    void playSound(@Nullable EntityHuman entityhuman, BlockPosition blockposition, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1);

    void addParticle(ParticleParam particleparam, double d0, double d1, double d2, double d3, double d4, double d5);

    void a(@Nullable EntityHuman entityhuman, int i, BlockPosition blockposition, int j);

    default void triggerEffect(int i, BlockPosition blockposition, int j) {
        this.a((EntityHuman) null, i, blockposition, j);
    }

    @Override
    default Stream<VoxelShape> a(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Set<Entity> set) {
        return IEntityAccess.super.a(entity, axisalignedbb, set);
    }

    @Override
    default boolean a(@Nullable Entity entity, VoxelShape voxelshape) {
        return IEntityAccess.super.a(entity, voxelshape);
    }
}
