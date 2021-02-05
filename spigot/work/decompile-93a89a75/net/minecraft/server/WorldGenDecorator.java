package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class WorldGenDecorator<DC extends WorldGenFeatureDecoratorConfiguration> {

    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> a = a("count_heightmap", new WorldGenDecoratorHeight(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> b = a("count_top_solid", new WorldGenDecoratorSkyVisible(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> c = a("count_heightmap_32", new WorldGenDecoratorHeight32(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> d = a("count_heightmap_double", new WorldGenDecoratorHeightDouble(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> e = a("count_height_64", new WorldGenDecoratorHeight64(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorNoiseConfiguration> f = a("noise_heightmap_32", new WorldGenDecoratorNoiseHeight32(WorldGenFeatureDecoratorNoiseConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorNoiseConfiguration> g = a("noise_heightmap_double", new WorldGenDecoratorNoiseHeightDouble(WorldGenFeatureDecoratorNoiseConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> h = a("nope", new WorldGenDecoratorEmpty(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorChanceConfiguration> i = a("chance_heightmap", new WorldGenDecoratorChance(WorldGenDecoratorChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorChanceConfiguration> j = a("chance_heightmap_double", new WorldGenDecoratorChanceHeight(WorldGenDecoratorChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorChanceConfiguration> k = a("chance_passthrough", new WorldGenDecoratorChancePass(WorldGenDecoratorChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorChanceConfiguration> l = a("chance_top_solid_heightmap", new WorldGenDecoratorSkyVisibleBiased(WorldGenDecoratorChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyExtraChanceConfiguration> m = a("count_extra_heightmap", new WorldGenDecoratorHeightExtraChance(WorldGenDecoratorFrequencyExtraChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorCountConfiguration> n = a("count_range", new WorldGenDecoratorNetherHeight(WorldGenFeatureChanceDecoratorCountConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorCountConfiguration> o = a("count_biased_range", new WorldGenDecoratorHeightBiased(WorldGenFeatureChanceDecoratorCountConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorCountConfiguration> p = a("count_very_biased_range", new WorldGenDecoratorHeightBiased2(WorldGenFeatureChanceDecoratorCountConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorCountConfiguration> q = a("random_count_range", new WorldGenDecoratorNetherRandomCount(WorldGenFeatureChanceDecoratorCountConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorRangeConfiguration> r = a("chance_range", new WorldGenDecoratorNetherChance(WorldGenFeatureChanceDecoratorRangeConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyChanceConfiguration> s = a("count_chance_heightmap", new WorldGenFeatureChanceDecorator(WorldGenDecoratorFrequencyChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyChanceConfiguration> t = a("count_chance_heightmap_double", new WorldGenFeatureChanceDecoratorHeight(WorldGenDecoratorFrequencyChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorHeightAverageConfiguration> u = a("count_depth_average", new WorldGenDecoratorHeightAverage(WorldGenDecoratorHeightAverageConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> v = a("top_solid_heightmap", new WorldGenDecoratorSolidTop(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorRangeConfiguration> w = a("top_solid_heightmap_range", new WorldGenDecoratorSolidTopHeight(WorldGenDecoratorRangeConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorNoiseConfiguration> x = a("top_solid_heightmap_noise_biased", new WorldGenDecoratorSolidTopNoise(WorldGenDecoratorNoiseConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorCarveMaskConfiguration> y = a("carving_mask", new WorldGenDecoratorCarveMask(WorldGenDecoratorCarveMaskConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> z = a("forest_rock", new WorldGenDecoratorForestRock(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> A = a("hell_fire", new WorldGenDecoratorNetherFire(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> B = a("magma", new WorldGenDecoratorNetherMagma(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> C = a("emerald_ore", new WorldGenDecoratorEmerald(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorLakeChanceConfiguration> D = a("lava_lake", new WorldGenDecoratorLakeLava(WorldGenDecoratorLakeChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorLakeChanceConfiguration> E = a("water_lake", new WorldGenDecoratorLakeWater(WorldGenDecoratorLakeChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> F = a("dungeons", new WorldGenDecoratorDungeon(WorldGenDecoratorDungeonConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> G = a("dark_oak_tree", new WorldGenDecoratorRoofedTree(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorChanceConfiguration> H = a("iceberg", new WorldGenDecoratorIceburg(WorldGenDecoratorChanceConfiguration::a));
    public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> I = a("light_gem_chance", new WorldGenDecoratorNetherGlowstone(WorldGenDecoratorFrequencyConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> J = a("end_island", new WorldGenDecoratorEndIsland(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> K = a("chorus_plant", new WorldGenDecoratorChorusPlant(WorldGenFeatureDecoratorEmptyConfiguration::a));
    public static final WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> L = a("end_gateway", new WorldGenDecoratorEndGateway(WorldGenFeatureDecoratorEmptyConfiguration::a));
    private final Function<Dynamic<?>, ? extends DC> M;

    private static <T extends WorldGenFeatureDecoratorConfiguration, G extends WorldGenDecorator<T>> G a(String s, G g0) {
        return (WorldGenDecorator) IRegistry.a(IRegistry.DECORATOR, s, (Object) g0);
    }

    public WorldGenDecorator(Function<Dynamic<?>, ? extends DC> function) {
        this.M = function;
    }

    public DC a(Dynamic<?> dynamic) {
        return (WorldGenFeatureDecoratorConfiguration) this.M.apply(dynamic);
    }

    protected <FC extends WorldGenFeatureConfiguration> boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, DC dc, WorldGenFeatureConfigured<FC> worldgenfeatureconfigured) {
        AtomicBoolean atomicboolean = new AtomicBoolean(false);

        this.a(generatoraccess, chunkgenerator, random, dc, blockposition).forEach((blockposition1) -> {
            boolean flag = worldgenfeatureconfigured.a(generatoraccess, chunkgenerator, random, blockposition1);

            atomicboolean.set(atomicboolean.get() || flag);
        });
        return atomicboolean.get();
    }

    public abstract Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, DC dc, BlockPosition blockposition);

    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode());
    }
}
