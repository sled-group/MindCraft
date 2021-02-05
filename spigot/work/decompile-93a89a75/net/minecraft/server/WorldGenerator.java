package net.minecraft.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldGenerator<FC extends WorldGenFeatureConfiguration> {

    public static final StructureGenerator<WorldGenFeaturePillagerOutpostConfiguration> PILLAGER_OUTPOST = (StructureGenerator) a("pillager_outpost", new WorldGenFeaturePillagerOutpost(WorldGenFeaturePillagerOutpostConfiguration::a));
    public static final StructureGenerator<WorldGenMineshaftConfiguration> MINESHAFT = (StructureGenerator) a("mineshaft", new WorldGenMineshaft(WorldGenMineshaftConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> WOODLAND_MANSION = (StructureGenerator) a("woodland_mansion", new WorldGenWoodlandMansion(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> JUNGLE_TEMPLE = (StructureGenerator) a("jungle_temple", new WorldGenFeatureJunglePyramid(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> DESERT_PYRAMID = (StructureGenerator) a("desert_pyramid", new WorldGenFeatureDesertPyramid(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> IGLOO = (StructureGenerator) a("igloo", new WorldGenFeatureIgloo(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureShipwreckConfiguration> SHIPWRECK = (StructureGenerator) a("shipwreck", new WorldGenFeatureShipwreck(WorldGenFeatureShipwreckConfiguration::a));
    public static final WorldGenFeatureSwampHut SWAMP_HUT = (WorldGenFeatureSwampHut) a("swamp_hut", new WorldGenFeatureSwampHut(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> STRONGHOLD = (StructureGenerator) a("stronghold", new WorldGenStronghold(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> OCEAN_MONUMENT = (StructureGenerator) a("ocean_monument", new WorldGenMonument(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureOceanRuinConfiguration> OCEAN_RUIN = (StructureGenerator) a("ocean_ruin", new WorldGenFeatureOceanRuin(WorldGenFeatureOceanRuinConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> NETHER_BRIDGE = (StructureGenerator) a("nether_bridge", new WorldGenNether(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureEmptyConfiguration> END_CITY = (StructureGenerator) a("end_city", new WorldGenEndCity(WorldGenFeatureEmptyConfiguration::a));
    public static final StructureGenerator<WorldGenBuriedTreasureConfiguration> BURIED_TREASURE = (StructureGenerator) a("buried_treasure", new WorldGenBuriedTreasure(WorldGenBuriedTreasureConfiguration::a));
    public static final StructureGenerator<WorldGenFeatureVillageConfiguration> VILLAGE = (StructureGenerator) a("village", new WorldGenVillage(WorldGenFeatureVillageConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> FANCY_TREE = a("fancy_tree", new WorldGenBigTree(WorldGenFeatureEmptyConfiguration::a, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> BIRCH_TREE = a("birch_tree", new WorldGenForest(WorldGenFeatureEmptyConfiguration::a, false, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SUPER_BIRCH_TREE = a("super_birch_tree", new WorldGenForest(WorldGenFeatureEmptyConfiguration::a, false, true));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> JUNGLE_GROUND_BUSH = a("jungle_ground_bush", new WorldGenGroundBush(WorldGenFeatureEmptyConfiguration::a, Blocks.JUNGLE_LOG.getBlockData(), Blocks.OAK_LEAVES.getBlockData()));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> JUNGLE_TREE = a("jungle_tree", new WorldGenFeatureJungleTree(WorldGenFeatureEmptyConfiguration::a, false, 4, Blocks.JUNGLE_LOG.getBlockData(), Blocks.JUNGLE_LEAVES.getBlockData(), true));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> PINE_TREE = a("pine_tree", new WorldGenTaiga1(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> DARK_OAK_TREE = a("dark_oak_tree", new WorldGenForestTree(WorldGenFeatureEmptyConfiguration::a, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SAVANNA_TREE = a("savanna_tree", new WorldGenAcaciaTree(WorldGenFeatureEmptyConfiguration::a, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SPRUCE_TREE = a("spruce_tree", new WorldGenTaiga2(WorldGenFeatureEmptyConfiguration::a, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SWAMP_TREE = a("swamp_tree", new WorldGenSwampTree(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> NORMAL_TREE = a("normal_tree", new WorldGenTrees(WorldGenFeatureEmptyConfiguration::a, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MEGA_JUNGLE_TREE = a("mega_jungle_tree", new WorldGenJungleTree(WorldGenFeatureEmptyConfiguration::a, false, 10, 20, Blocks.JUNGLE_LOG.getBlockData(), Blocks.JUNGLE_LEAVES.getBlockData()));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MEGA_PINE_TREE = a("mega_pine_tree", new WorldGenMegaTree(WorldGenFeatureEmptyConfiguration::a, false, false));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MEGA_SPRUCE_TREE = a("mega_spruce_tree", new WorldGenMegaTree(WorldGenFeatureEmptyConfiguration::a, false, true));
    public static final WorldGenFlowers DEFAULT_FLOWER = (WorldGenFlowers) a("default_flower", new WorldGenFeatureFlower(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenFlowers FOREST_FLOWER = (WorldGenFlowers) a("forest_flower", new WorldGenFeatureFlowerForest(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenFlowers PLAIN_FLOWER = (WorldGenFlowers) a("plain_flower", new WorldGenFeatureFlowerPlain(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenFlowers SWAMP_FLOWER = (WorldGenFlowers) a("swamp_flower", new WorldGenFeatureFlowerSwamp(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenFlowers GENERAL_FOREST_FLOWER = (WorldGenFlowers) a("general_forest_flower", new WorldGenFlowerValley(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> JUNGLE_GRASS = a("jungle_grass", new WorldGenFeatureJungleGrass(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> TAIGA_GRASS = a("taiga_grass", new WorldGenFeatureTaigaGrass(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureTallGrassConfiguration> GRASS = a("grass", new WorldGenGrass(WorldGenFeatureTallGrassConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> VOID_START_PLATFORM = a("void_start_platform", new WorldGenFeatureEndPlatform(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CACTUS = a("cactus", new WorldGenCactus(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> DEAD_BUSH = a("dead_bush", new WorldGenDeadBush(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> DESERT_WELL = a("desert_well", new WorldGenDesertWell(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> FOSSIL = a("fossil", new WorldGenFossils(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> HELL_FIRE = a("hell_fire", new WorldGenFire(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenHugeMushroomConfiguration> HUGE_RED_MUSHROOM = a("huge_red_mushroom", new WorldGenHugeMushroomRed(WorldGenHugeMushroomConfiguration::a));
    public static final WorldGenerator<WorldGenHugeMushroomConfiguration> HUGE_BROWN_MUSHROOM = a("huge_brown_mushroom", new WorldGenHugeMushroomBrown(WorldGenHugeMushroomConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> ICE_SPIKE = a("ice_spike", new WorldGenPackedIce2(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> GLOWSTONE_BLOB = a("glowstone_blob", new WorldGenLightStone1(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MELON = a("melon", new WorldGenMelon(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> PUMPKIN = a("pumpkin", new WorldGenPumpkin(WorldGenFeatureEmptyConfiguration::a, Blocks.PUMPKIN.getBlockData()));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> REED = a("reed", new WorldGenReed(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> FREEZE_TOP_LAYER = a("freeze_top_layer", new WorldGenFeatureIceSnow(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> VINES = a("vines", new WorldGenVines(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> WATERLILY = a("waterlily", new WorldGenWaterLily(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MONSTER_ROOM = a("monster_room", new WorldGenDungeons(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> BLUE_ICE = a("blue_ice", new WorldGenFeatureBlueIce(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureIceburgConfiguration> ICEBERG = a("iceberg", new WorldGenFeatureIceburg(WorldGenFeatureIceburgConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureBlockOffsetConfiguration> FOREST_ROCK = a("forest_rock", new WorldGenTaigaStructure(WorldGenFeatureBlockOffsetConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> HAY_PILE = a("hay_pile", new WorldGenFeatureHayPile(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SNOW_PILE = a("snow_pile", new WorldGenFeatureSnowStack(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> ICE_PILE = a("ice_pile", new WorldGenFeatureIcePile(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> MELON_PILE = a("melon_pile", new WorldGenFeatureMelonPile(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> PUMPKIN_PILE = a("pumpkin_pile", new WorldGenFeaturePumpkinStack(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureMushroomConfiguration> BUSH = a("bush", new WorldGenMushrooms(WorldGenFeatureMushroomConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureCircleConfiguration> DISK = a("disk", new WorldGenFeatureCircle(WorldGenFeatureCircleConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureDoublePlantConfiguration> DOUBLE_PLANT = a("double_plant", new WorldGenTallPlant(WorldGenFeatureDoublePlantConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureHellFlowingLavaConfiguration> NETHER_SPRING = a("nether_spring", new WorldGenHellLava(WorldGenFeatureHellFlowingLavaConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureRadiusConfiguration> ICE_PATCH = a("ice_patch", new WorldGenPackedIce1(WorldGenFeatureRadiusConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureLakeConfiguration> LAKE = a("lake", new WorldGenLakes(WorldGenFeatureLakeConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureOreConfiguration> ORE = a("ore", new WorldGenMinable(WorldGenFeatureOreConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureRandomConfiguration> RANDOM_RANDOM_SELECTOR = a("random_random_selector", new WorldGenFeatureRandom(WorldGenFeatureRandomConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureRandomChoiceConfiguration> RANDOM_SELECTOR = a("random_selector", new WorldGenFeatureRandomChoice(WorldGenFeatureRandomChoiceConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureRandom2> SIMPLE_RANDOM_SELECTOR = a("simple_random_selector", new WorldGenFeatureRandom2Configuration(WorldGenFeatureRandom2::a));
    public static final WorldGenerator<WorldGenFeatureChoiceConfiguration> RANDOM_BOOLEAN_SELECTOR = a("random_boolean_selector", new WorldGenFeatureChoice(WorldGenFeatureChoiceConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureReplaceBlockConfiguration> EMERALD_ORE = a("emerald_ore", new WorldGenFeatureReplaceBlock(WorldGenFeatureReplaceBlockConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureFlowingConfiguration> SPRING_FEATURE = a("spring_feature", new WorldGenLiquids(WorldGenFeatureFlowingConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEndSpikeConfiguration> END_SPIKE = a("end_spike", new WorldGenEnder(WorldGenFeatureEndSpikeConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> END_ISLAND = a("end_island", new WorldGenEndIsland(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CHORUS_PLANT = a("chorus_plant", new WorldGenFeatureChorusPlant(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenEndGatewayConfiguration> END_GATEWAY = a("end_gateway", new WorldGenEndGateway(WorldGenEndGatewayConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureSeaGrassConfiguration> SEAGRASS = a("seagrass", new WorldGenFeatureSeaGrass(WorldGenFeatureSeaGrassConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> KELP = a("kelp", new WorldGenFeatureKelp(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_TREE = a("coral_tree", new WorldGenFeatureCoralTree(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_MUSHROOM = a("coral_mushroom", new WorldGenFeatureCoralMushroom(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> CORAL_CLAW = a("coral_claw", new WorldGenFeatureCoralClaw(WorldGenFeatureEmptyConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureKelpConfiguration> SEA_PICKLE = a("sea_pickle", new WorldGenFeatureSeaPickel(WorldGenFeatureKelpConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureBlockConfiguration> SIMPLE_BLOCK = a("simple_block", new WorldGenFeatureBlock(WorldGenFeatureBlockConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureConfigurationChance> BAMBOO = a("bamboo", new WorldGenFeatureBamboo(WorldGenFeatureConfigurationChance::a));
    public static final WorldGenerator<WorldGenFeatureCompositeConfiguration> DECORATED = a("decorated", new WorldGenFeatureComposite(WorldGenFeatureCompositeConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureCompositeConfiguration> DECORATED_FLOWER = a("decorated_flower", new WorldGenFeatureCompositeFlower(WorldGenFeatureCompositeConfiguration::a));
    public static final WorldGenerator<WorldGenFeatureEmptyConfiguration> SWEET_BERRY_BUSH = a("sweet_berry_bush", new WorldGenPumpkin(WorldGenFeatureEmptyConfiguration::a, (IBlockData) Blocks.SWEET_BERRY_BUSH.getBlockData().set(BlockSweetBerryBush.a, 3)));
    public static final WorldGenerator<WorldGenFeatureFillConfiguration> aN = a("fill_layer", new WorldGenFeatureFill(WorldGenFeatureFillConfiguration::a));
    public static final WorldGenBonusChest BONUS_CHEST = (WorldGenBonusChest) a("bonus_chest", new WorldGenBonusChest(WorldGenFeatureEmptyConfiguration::a));
    public static final BiMap<String, StructureGenerator<?>> aP = (BiMap) SystemUtils.a((Object) HashBiMap.create(), (hashbimap) -> {
        hashbimap.put("Pillager_Outpost".toLowerCase(Locale.ROOT), WorldGenerator.PILLAGER_OUTPOST);
        hashbimap.put("Mineshaft".toLowerCase(Locale.ROOT), WorldGenerator.MINESHAFT);
        hashbimap.put("Mansion".toLowerCase(Locale.ROOT), WorldGenerator.WOODLAND_MANSION);
        hashbimap.put("Jungle_Pyramid".toLowerCase(Locale.ROOT), WorldGenerator.JUNGLE_TEMPLE);
        hashbimap.put("Desert_Pyramid".toLowerCase(Locale.ROOT), WorldGenerator.DESERT_PYRAMID);
        hashbimap.put("Igloo".toLowerCase(Locale.ROOT), WorldGenerator.IGLOO);
        hashbimap.put("Shipwreck".toLowerCase(Locale.ROOT), WorldGenerator.SHIPWRECK);
        hashbimap.put("Swamp_Hut".toLowerCase(Locale.ROOT), WorldGenerator.SWAMP_HUT);
        hashbimap.put("Stronghold".toLowerCase(Locale.ROOT), WorldGenerator.STRONGHOLD);
        hashbimap.put("Monument".toLowerCase(Locale.ROOT), WorldGenerator.OCEAN_MONUMENT);
        hashbimap.put("Ocean_Ruin".toLowerCase(Locale.ROOT), WorldGenerator.OCEAN_RUIN);
        hashbimap.put("Fortress".toLowerCase(Locale.ROOT), WorldGenerator.NETHER_BRIDGE);
        hashbimap.put("EndCity".toLowerCase(Locale.ROOT), WorldGenerator.END_CITY);
        hashbimap.put("Buried_Treasure".toLowerCase(Locale.ROOT), WorldGenerator.BURIED_TREASURE);
        hashbimap.put("Village".toLowerCase(Locale.ROOT), WorldGenerator.VILLAGE);
    });
    public static final List<StructureGenerator<?>> aQ = ImmutableList.of(WorldGenerator.PILLAGER_OUTPOST, WorldGenerator.VILLAGE);
    private final Function<Dynamic<?>, ? extends FC> a;
    protected final boolean aR;

    private static <C extends WorldGenFeatureConfiguration, F extends WorldGenerator<C>> F a(String s, F f0) {
        return (WorldGenerator) IRegistry.a(IRegistry.FEATURE, s, (Object) f0);
    }

    public WorldGenerator(Function<Dynamic<?>, ? extends FC> function) {
        this.a = function;
        this.aR = false;
    }

    public WorldGenerator(Function<Dynamic<?>, ? extends FC> function, boolean flag) {
        this.a = function;
        this.aR = flag;
    }

    public FC a(Dynamic<?> dynamic) {
        return (WorldGenFeatureConfiguration) this.a.apply(dynamic);
    }

    protected void a(IWorldWriter iworldwriter, BlockPosition blockposition, IBlockData iblockdata) {
        if (this.aR) {
            iworldwriter.setTypeAndData(blockposition, iblockdata, 3);
        } else {
            iworldwriter.setTypeAndData(blockposition, iblockdata, 2);
        }

    }

    public abstract boolean generate(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, FC fc);

    public List<BiomeBase.BiomeMeta> e() {
        return Collections.emptyList();
    }

    public List<BiomeBase.BiomeMeta> f() {
        return Collections.emptyList();
    }
}
