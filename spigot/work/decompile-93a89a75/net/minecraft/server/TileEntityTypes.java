package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TileEntityTypes<T extends TileEntity> {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final TileEntityTypes<TileEntityFurnaceFurnace> FURNACE = a("furnace", TileEntityTypes.a.a(TileEntityFurnaceFurnace::new, Blocks.FURNACE));
    public static final TileEntityTypes<TileEntityChest> CHEST = a("chest", TileEntityTypes.a.a(TileEntityChest::new, Blocks.CHEST));
    public static final TileEntityTypes<TileEntityChestTrapped> TRAPPED_CHEST = a("trapped_chest", TileEntityTypes.a.a(TileEntityChestTrapped::new, Blocks.TRAPPED_CHEST));
    public static final TileEntityTypes<TileEntityEnderChest> ENDER_CHEST = a("ender_chest", TileEntityTypes.a.a(TileEntityEnderChest::new, Blocks.ENDER_CHEST));
    public static final TileEntityTypes<TileEntityJukeBox> JUKEBOX = a("jukebox", TileEntityTypes.a.a(TileEntityJukeBox::new, Blocks.JUKEBOX));
    public static final TileEntityTypes<TileEntityDispenser> DISPENSER = a("dispenser", TileEntityTypes.a.a(TileEntityDispenser::new, Blocks.DISPENSER));
    public static final TileEntityTypes<TileEntityDropper> DROPPER = a("dropper", TileEntityTypes.a.a(TileEntityDropper::new, Blocks.DROPPER));
    public static final TileEntityTypes<TileEntitySign> SIGN = a("sign", TileEntityTypes.a.a(TileEntitySign::new, Blocks.OAK_SIGN, Blocks.SPRUCE_SIGN, Blocks.BIRCH_SIGN, Blocks.ACACIA_SIGN, Blocks.JUNGLE_SIGN, Blocks.DARK_OAK_SIGN, Blocks.OAK_WALL_SIGN, Blocks.SPRUCE_WALL_SIGN, Blocks.BIRCH_WALL_SIGN, Blocks.ACACIA_WALL_SIGN, Blocks.JUNGLE_WALL_SIGN, Blocks.DARK_OAK_WALL_SIGN));
    public static final TileEntityTypes<TileEntityMobSpawner> MOB_SPAWNER = a("mob_spawner", TileEntityTypes.a.a(TileEntityMobSpawner::new, Blocks.SPAWNER));
    public static final TileEntityTypes<TileEntityPiston> PISTON = a("piston", TileEntityTypes.a.a(TileEntityPiston::new, Blocks.MOVING_PISTON));
    public static final TileEntityTypes<TileEntityBrewingStand> BREWING_STAND = a("brewing_stand", TileEntityTypes.a.a(TileEntityBrewingStand::new, Blocks.BREWING_STAND));
    public static final TileEntityTypes<TileEntityEnchantTable> ENCHANTING_TABLE = a("enchanting_table", TileEntityTypes.a.a(TileEntityEnchantTable::new, Blocks.ENCHANTING_TABLE));
    public static final TileEntityTypes<TileEntityEnderPortal> END_PORTAL = a("end_portal", TileEntityTypes.a.a(TileEntityEnderPortal::new, Blocks.END_PORTAL));
    public static final TileEntityTypes<TileEntityBeacon> BEACON = a("beacon", TileEntityTypes.a.a(TileEntityBeacon::new, Blocks.BEACON));
    public static final TileEntityTypes<TileEntitySkull> SKULL = a("skull", TileEntityTypes.a.a(TileEntitySkull::new, Blocks.SKELETON_SKULL, Blocks.SKELETON_WALL_SKULL, Blocks.CREEPER_HEAD, Blocks.CREEPER_WALL_HEAD, Blocks.DRAGON_HEAD, Blocks.DRAGON_WALL_HEAD, Blocks.ZOMBIE_HEAD, Blocks.ZOMBIE_WALL_HEAD, Blocks.WITHER_SKELETON_SKULL, Blocks.WITHER_SKELETON_WALL_SKULL, Blocks.PLAYER_HEAD, Blocks.PLAYER_WALL_HEAD));
    public static final TileEntityTypes<TileEntityLightDetector> DAYLIGHT_DETECTOR = a("daylight_detector", TileEntityTypes.a.a(TileEntityLightDetector::new, Blocks.DAYLIGHT_DETECTOR));
    public static final TileEntityTypes<TileEntityHopper> HOPPER = a("hopper", TileEntityTypes.a.a(TileEntityHopper::new, Blocks.HOPPER));
    public static final TileEntityTypes<TileEntityComparator> COMPARATOR = a("comparator", TileEntityTypes.a.a(TileEntityComparator::new, Blocks.COMPARATOR));
    public static final TileEntityTypes<TileEntityBanner> BANNER = a("banner", TileEntityTypes.a.a(TileEntityBanner::new, Blocks.WHITE_BANNER, Blocks.ORANGE_BANNER, Blocks.MAGENTA_BANNER, Blocks.LIGHT_BLUE_BANNER, Blocks.YELLOW_BANNER, Blocks.LIME_BANNER, Blocks.PINK_BANNER, Blocks.GRAY_BANNER, Blocks.LIGHT_GRAY_BANNER, Blocks.CYAN_BANNER, Blocks.PURPLE_BANNER, Blocks.BLUE_BANNER, Blocks.BROWN_BANNER, Blocks.GREEN_BANNER, Blocks.RED_BANNER, Blocks.BLACK_BANNER, Blocks.WHITE_WALL_BANNER, Blocks.ORANGE_WALL_BANNER, Blocks.MAGENTA_WALL_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, Blocks.YELLOW_WALL_BANNER, Blocks.LIME_WALL_BANNER, Blocks.PINK_WALL_BANNER, Blocks.GRAY_WALL_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, Blocks.CYAN_WALL_BANNER, Blocks.PURPLE_WALL_BANNER, Blocks.BLUE_WALL_BANNER, Blocks.BROWN_WALL_BANNER, Blocks.GREEN_WALL_BANNER, Blocks.RED_WALL_BANNER, Blocks.BLACK_WALL_BANNER));
    public static final TileEntityTypes<TileEntityStructure> STRUCTURE_BLOCK = a("structure_block", TileEntityTypes.a.a(TileEntityStructure::new, Blocks.STRUCTURE_BLOCK));
    public static final TileEntityTypes<TileEntityEndGateway> END_GATEWAY = a("end_gateway", TileEntityTypes.a.a(TileEntityEndGateway::new, Blocks.END_GATEWAY));
    public static final TileEntityTypes<TileEntityCommand> COMMAND_BLOCK = a("command_block", TileEntityTypes.a.a(TileEntityCommand::new, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK));
    public static final TileEntityTypes<TileEntityShulkerBox> SHULKER_BOX = a("shulker_box", TileEntityTypes.a.a(TileEntityShulkerBox::new, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX));
    public static final TileEntityTypes<TileEntityBed> BED = a("bed", TileEntityTypes.a.a(TileEntityBed::new, Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED));
    public static final TileEntityTypes<TileEntityConduit> CONDUIT = a("conduit", TileEntityTypes.a.a(TileEntityConduit::new, Blocks.CONDUIT));
    public static final TileEntityTypes<TileEntityBarrel> BARREL = a("barrel", TileEntityTypes.a.a(TileEntityBarrel::new, Blocks.BARREL));
    public static final TileEntityTypes<TileEntitySmoker> SMOKER = a("smoker", TileEntityTypes.a.a(TileEntitySmoker::new, Blocks.SMOKER));
    public static final TileEntityTypes<TileEntityBlastFurnace> BLAST_FURNACE = a("blast_furnace", TileEntityTypes.a.a(TileEntityBlastFurnace::new, Blocks.BLAST_FURNACE));
    public static final TileEntityTypes<TileEntityLectern> LECTERN = a("lectern", TileEntityTypes.a.a(TileEntityLectern::new, Blocks.LECTERN));
    public static final TileEntityTypes<TileEntityBell> BELL = a("bell", TileEntityTypes.a.a(TileEntityBell::new, Blocks.BELL));
    public static final TileEntityTypes<TileEntityJigsaw> JIGSAW = a("jigsaw", TileEntityTypes.a.a(TileEntityJigsaw::new, Blocks.JIGSAW));
    public static final TileEntityTypes<TileEntityCampfire> CAMPFIRE = a("campfire", TileEntityTypes.a.a(TileEntityCampfire::new, Blocks.CAMPFIRE));
    private final Supplier<? extends T> H;
    private final Set<Block> I;
    private final Type<?> J;

    @Nullable
    public static MinecraftKey a(TileEntityTypes<?> tileentitytypes) {
        return IRegistry.BLOCK_ENTITY_TYPE.getKey(tileentitytypes);
    }

    private static <T extends TileEntity> TileEntityTypes<T> a(String s, TileEntityTypes.a<T> tileentitytypes_a) {
        Type type = null;

        try {
            type = DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(SharedConstants.a().getWorldVersion())).getChoiceType(DataConverterTypes.k, s);
        } catch (IllegalStateException illegalstateexception) {
            if (SharedConstants.b) {
                throw illegalstateexception;
            }

            TileEntityTypes.LOGGER.warn("No data fixer registered for block entity {}", s);
        }

        if (tileentitytypes_a.b.isEmpty()) {
            TileEntityTypes.LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", s);
        }

        return (TileEntityTypes) IRegistry.a(IRegistry.BLOCK_ENTITY_TYPE, s, (Object) tileentitytypes_a.a(type));
    }

    public TileEntityTypes(Supplier<? extends T> supplier, Set<Block> set, Type<?> type) {
        this.H = supplier;
        this.I = set;
        this.J = type;
    }

    @Nullable
    public T a() {
        return (TileEntity) this.H.get();
    }

    public boolean a(Block block) {
        return this.I.contains(block);
    }

    public static final class a<T extends TileEntity> {

        private final Supplier<? extends T> a;
        private final Set<Block> b;

        private a(Supplier<? extends T> supplier, Set<Block> set) {
            this.a = supplier;
            this.b = set;
        }

        public static <T extends TileEntity> TileEntityTypes.a<T> a(Supplier<? extends T> supplier, Block... ablock) {
            return new TileEntityTypes.a<>(supplier, ImmutableSet.copyOf(ablock));
        }

        public TileEntityTypes<T> a(Type<?> type) {
            return new TileEntityTypes<>(this.a, this.b, type);
        }
    }
}
