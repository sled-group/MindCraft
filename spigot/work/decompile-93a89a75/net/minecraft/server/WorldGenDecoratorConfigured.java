package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class WorldGenDecoratorConfigured<DC extends WorldGenFeatureDecoratorConfiguration> {

    public final WorldGenDecorator<DC> a;
    public final DC b;

    public WorldGenDecoratorConfigured(WorldGenDecorator<DC> worldgendecorator, Dynamic<?> dynamic) {
        this(worldgendecorator, worldgendecorator.a(dynamic));
    }

    public WorldGenDecoratorConfigured(WorldGenDecorator<DC> worldgendecorator, DC dc) {
        this.a = worldgendecorator;
        this.b = dc;
    }

    public <FC extends WorldGenFeatureConfiguration> boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureConfigured<FC> worldgenfeatureconfigured) {
        return this.a.a(generatoraccess, chunkgenerator, random, blockposition, this.b, worldgenfeatureconfigured);
    }

    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("name"), dynamicops.createString(IRegistry.DECORATOR.getKey(this.a).toString()), dynamicops.createString("config"), this.b.a(dynamicops).getValue())));
    }

    public static <T> WorldGenDecoratorConfigured<?> a(Dynamic<T> dynamic) {
        WorldGenDecorator<? extends WorldGenFeatureDecoratorConfiguration> worldgendecorator = (WorldGenDecorator) IRegistry.DECORATOR.get(new MinecraftKey(dynamic.get("name").asString("")));

        return new WorldGenDecoratorConfigured<>(worldgendecorator, dynamic.get("config").orElseEmptyMap());
    }
}
