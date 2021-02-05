package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class IRegistry<T> implements Registry<T> {

    protected static final Logger LOGGER = LogManager.getLogger();
    private static final Map<MinecraftKey, Supplier<?>> a = Maps.newLinkedHashMap();
    public static final IRegistryWritable<IRegistryWritable<?>> f = new RegistryMaterials<>();
    public static final IRegistry<SoundEffect> SOUND_EVENT = a("sound_event", () -> {
        return SoundEffects.ENTITY_ITEM_PICKUP;
    });
    public static final RegistryBlocks<FluidType> FLUID = a("fluid", "empty", () -> {
        return FluidTypes.EMPTY;
    });
    public static final IRegistry<MobEffectList> MOB_EFFECT = a("mob_effect", () -> {
        return MobEffects.LUCK;
    });
    public static final RegistryBlocks<Block> BLOCK = a("block", "air", () -> {
        return Blocks.AIR;
    });
    public static final IRegistry<Enchantment> ENCHANTMENT = a("enchantment", () -> {
        return Enchantments.LOOT_BONUS_BLOCKS;
    });
    public static final RegistryBlocks<EntityTypes<?>> ENTITY_TYPE = a("entity_type", "pig", () -> {
        return EntityTypes.PIG;
    });
    public static final RegistryBlocks<Item> ITEM = a("item", "air", () -> {
        return Items.AIR;
    });
    public static final RegistryBlocks<PotionRegistry> POTION = a("potion", "empty", () -> {
        return Potions.EMPTY;
    });
    public static final IRegistry<WorldGenCarverAbstract<?>> CARVER = a("carver", () -> {
        return WorldGenCarverAbstract.a;
    });
    public static final IRegistry<WorldGenSurface<?>> SURFACE_BUILDER = a("surface_builder", () -> {
        return WorldGenSurface.G;
    });
    public static final IRegistry<WorldGenerator<?>> FEATURE = a("feature", () -> {
        return WorldGenerator.ORE;
    });
    public static final IRegistry<WorldGenDecorator<?>> DECORATOR = a("decorator", () -> {
        return WorldGenDecorator.h;
    });
    public static final IRegistry<BiomeBase> BIOME = a("biome", () -> {
        return Biomes.b;
    });
    public static final IRegistry<Particle<? extends ParticleParam>> PARTICLE_TYPE = a("particle_type", () -> {
        return Particles.BLOCK;
    });
    public static final IRegistry<BiomeLayout<?, ?>> BIOME_SOURCE_TYPE = a("biome_source_type", () -> {
        return BiomeLayout.c;
    });
    public static final IRegistry<TileEntityTypes<?>> BLOCK_ENTITY_TYPE = a("block_entity_type", () -> {
        return TileEntityTypes.FURNACE;
    });
    public static final IRegistry<ChunkGeneratorType<?, ?>> CHUNK_GENERATOR_TYPE = a("chunk_generator_type", () -> {
        return ChunkGeneratorType.e;
    });
    public static final IRegistry<DimensionManager> DIMENSION_TYPE = a("dimension_type", () -> {
        return DimensionManager.OVERWORLD;
    });
    public static final RegistryBlocks<Paintings> MOTIVE = a("motive", "kebab", () -> {
        return Paintings.a;
    });
    public static final IRegistry<MinecraftKey> CUSTOM_STAT = a("custom_stat", () -> {
        return StatisticList.JUMP;
    });
    public static final RegistryBlocks<ChunkStatus> CHUNK_STATUS = a("chunk_status", "empty", () -> {
        return ChunkStatus.EMPTY;
    });
    public static final IRegistry<StructureGenerator<?>> STRUCTURE_FEATURE = a("structure_feature", () -> {
        return WorldGenFactory.a;
    });
    public static final IRegistry<WorldGenFeatureStructurePieceType> STRUCTURE_PIECE = a("structure_piece", () -> {
        return WorldGenFeatureStructurePieceType.c;
    });
    public static final IRegistry<DefinedStructureRuleTestType> RULE_TEST = a("rule_test", () -> {
        return DefinedStructureRuleTestType.b;
    });
    public static final IRegistry<DefinedStructureStructureProcessorType> STRUCTURE_PROCESSOR = a("structure_processor", () -> {
        return DefinedStructureStructureProcessorType.b;
    });
    public static final IRegistry<WorldGenFeatureDefinedStructurePools> STRUCTURE_POOL_ELEMENT = a("structure_pool_element", () -> {
        return WorldGenFeatureDefinedStructurePools.e;
    });
    public static final IRegistry<Containers<?>> MENU = a("menu", () -> {
        return Containers.ANVIL;
    });
    public static final IRegistry<Recipes<?>> RECIPE_TYPE = a("recipe_type", () -> {
        return Recipes.CRAFTING;
    });
    public static final IRegistry<RecipeSerializer<?>> RECIPE_SERIALIZER = a("recipe_serializer", () -> {
        return RecipeSerializer.b;
    });
    public static final IRegistry<StatisticWrapper<?>> STATS = a("stat_type", () -> {
        return StatisticList.ITEM_USED;
    });
    public static final RegistryBlocks<VillagerType> VILLAGER_TYPE = a("villager_type", "plains", () -> {
        return VillagerType.PLAINS;
    });
    public static final RegistryBlocks<VillagerProfession> VILLAGER_PROFESSION = a("villager_profession", "none", () -> {
        return VillagerProfession.NONE;
    });
    public static final RegistryBlocks<VillagePlaceType> POINT_OF_INTEREST_TYPE = a("point_of_interest_type", "unemployed", () -> {
        return VillagePlaceType.b;
    });
    public static final RegistryBlocks<MemoryModuleType<?>> MEMORY_MODULE_TYPE = a("memory_module_type", "dummy", () -> {
        return MemoryModuleType.DUMMY;
    });
    public static final RegistryBlocks<SensorType<?>> SENSOR_TYPE = a("sensor_type", "dummy", () -> {
        return SensorType.a;
    });
    public static final IRegistry<Schedule> SCHEDULE = a("schedule", () -> {
        return Schedule.EMPTY;
    });
    public static final IRegistry<Activity> ACTIVITY = a("activity", () -> {
        return Activity.IDLE;
    });

    public IRegistry() {}

    private static <T> IRegistry<T> a(String s, Supplier<T> supplier) {
        return a(s, (IRegistryWritable) (new RegistryMaterials<>()), supplier);
    }

    private static <T> RegistryBlocks<T> a(String s, String s1, Supplier<T> supplier) {
        return (RegistryBlocks) a(s, (IRegistryWritable) (new RegistryBlocks<>(s1)), supplier);
    }

    private static <T, R extends IRegistryWritable<T>> R a(String s, R r0, Supplier<T> supplier) {
        MinecraftKey minecraftkey = new MinecraftKey(s);

        IRegistry.a.put(minecraftkey, supplier);
        return (IRegistryWritable) IRegistry.f.a(minecraftkey, (Object) r0);
    }

    @Nullable
    public abstract MinecraftKey getKey(T t0);

    public abstract int a(@Nullable T t0);

    @Nullable
    public abstract T get(@Nullable MinecraftKey minecraftkey);

    public abstract Optional<T> getOptional(@Nullable MinecraftKey minecraftkey);

    public abstract Set<MinecraftKey> keySet();

    @Nullable
    public abstract T a(Random random);

    public Stream<T> d() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public static <T> T a(IRegistry<? super T> iregistry, String s, T t0) {
        return a(iregistry, new MinecraftKey(s), t0);
    }

    public static <T> T a(IRegistry<? super T> iregistry, MinecraftKey minecraftkey, T t0) {
        return ((IRegistryWritable) iregistry).a(minecraftkey, t0);
    }

    public static <T> T a(IRegistry<? super T> iregistry, int i, String s, T t0) {
        return ((IRegistryWritable) iregistry).a(i, new MinecraftKey(s), t0);
    }

    static {
        IRegistry.a.entrySet().forEach((entry) -> {
            if (((Supplier) entry.getValue()).get() == null) {
                IRegistry.LOGGER.error("Unable to bootstrap registry '{}'", entry.getKey());
            }

        });
        IRegistry.f.forEach((iregistrywritable) -> {
            if (iregistrywritable.c()) {
                IRegistry.LOGGER.error("Registry '{}' was empty after loading", IRegistry.f.getKey(iregistrywritable));
                if (SharedConstants.b) {
                    throw new IllegalStateException("Registry: '" + IRegistry.f.getKey(iregistrywritable) + "' is empty, not allowed, fix me!");
                }
            }

            if (iregistrywritable instanceof RegistryBlocks) {
                MinecraftKey minecraftkey = ((RegistryBlocks) iregistrywritable).a();

                Validate.notNull(iregistrywritable.get(minecraftkey), "Missing default of DefaultedMappedRegistry: " + minecraftkey, new Object[0]);
            }

        });
    }
}
