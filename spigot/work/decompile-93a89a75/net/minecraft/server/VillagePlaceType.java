package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class VillagePlaceType {

    private static final Predicate<VillagePlaceType> s = (villageplacetype) -> {
        return ((Set) IRegistry.VILLAGER_PROFESSION.d().map(VillagerProfession::b).collect(Collectors.toSet())).contains(villageplacetype);
    };
    public static final Predicate<VillagePlaceType> a = (villageplacetype) -> {
        return true;
    };
    private static final Set<IBlockData> t = (Set) ImmutableList.of(Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, new Block[]{Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED}).stream().flatMap((block) -> {
        return block.getStates().a().stream();
    }).filter((iblockdata) -> {
        return iblockdata.get(BlockBed.PART) == BlockPropertyBedPart.HEAD;
    }).collect(ImmutableSet.toImmutableSet());
    private static final Map<IBlockData, VillagePlaceType> u = Maps.newHashMap();
    public static final VillagePlaceType b = a("unemployed", ImmutableSet.of(), 1, (SoundEffect) null, VillagePlaceType.s, 1);
    public static final VillagePlaceType c = a("armorer", a(Blocks.BLAST_FURNACE), 1, SoundEffects.ENTITY_VILLAGER_WORK_ARMORER, 1);
    public static final VillagePlaceType d = a("butcher", a(Blocks.SMOKER), 1, SoundEffects.ENTITY_VILLAGER_WORK_BUTCHER, 1);
    public static final VillagePlaceType e = a("cartographer", a(Blocks.CARTOGRAPHY_TABLE), 1, SoundEffects.ENTITY_VILLAGER_WORK_CARTOGRAPHER, 1);
    public static final VillagePlaceType f = a("cleric", a(Blocks.BREWING_STAND), 1, SoundEffects.ENTITY_VILLAGER_WORK_CLERIC, 1);
    public static final VillagePlaceType g = a("farmer", a(Blocks.COMPOSTER), 1, SoundEffects.ENTITY_VILLAGER_WORK_FARMER, 1);
    public static final VillagePlaceType h = a("fisherman", a(Blocks.BARREL), 1, SoundEffects.ENTITY_VILLAGER_WORK_FISHERMAN, 1);
    public static final VillagePlaceType i = a("fletcher", a(Blocks.FLETCHING_TABLE), 1, SoundEffects.ENTITY_VILLAGER_WORK_FLETCHER, 1);
    public static final VillagePlaceType j = a("leatherworker", a(Blocks.CAULDRON), 1, SoundEffects.ENTITY_VILLAGER_WORK_LEATHERWORKER, 1);
    public static final VillagePlaceType k = a("librarian", a(Blocks.LECTERN), 1, SoundEffects.ENTITY_VILLAGER_WORK_LIBRARIAN, 1);
    public static final VillagePlaceType l = a("mason", a(Blocks.STONECUTTER), 1, SoundEffects.ENTITY_VILLAGER_WORK_MASON, 1);
    public static final VillagePlaceType m = a("nitwit", ImmutableSet.of(), 1, (SoundEffect) null, 1);
    public static final VillagePlaceType n = a("shepherd", a(Blocks.LOOM), 1, SoundEffects.ENTITY_VILLAGER_WORK_SHEPHERD, 1);
    public static final VillagePlaceType o = a("toolsmith", a(Blocks.SMITHING_TABLE), 1, SoundEffects.ENTITY_VILLAGER_WORK_TOOLSMITH, 1);
    public static final VillagePlaceType p = a("weaponsmith", a(Blocks.GRINDSTONE), 1, SoundEffects.ENTITY_VILLAGER_WORK_WEAPONSMITH, 1);
    public static final VillagePlaceType q = a("home", VillagePlaceType.t, 1, (SoundEffect) null, 1);
    public static final VillagePlaceType r = a("meeting", a(Blocks.BELL), 32, (SoundEffect) null, 6);
    private final String v;
    private final Set<IBlockData> w;
    private final int x;
    @Nullable
    private final SoundEffect y;
    private final Predicate<VillagePlaceType> z;
    private final int A;

    private static Set<IBlockData> a(Block block) {
        return ImmutableSet.copyOf(block.getStates().a());
    }

    private VillagePlaceType(String s, Set<IBlockData> set, int i, @Nullable SoundEffect soundeffect, Predicate<VillagePlaceType> predicate, int j) {
        this.v = s;
        this.w = ImmutableSet.copyOf(set);
        this.x = i;
        this.y = soundeffect;
        this.z = predicate;
        this.A = j;
    }

    private VillagePlaceType(String s, Set<IBlockData> set, int i, @Nullable SoundEffect soundeffect, int j) {
        this.v = s;
        this.w = ImmutableSet.copyOf(set);
        this.x = i;
        this.y = soundeffect;
        this.z = (villageplacetype) -> {
            return villageplacetype == this;
        };
        this.A = j;
    }

    public int b() {
        return this.x;
    }

    public Predicate<VillagePlaceType> c() {
        return this.z;
    }

    public int d() {
        return this.A;
    }

    public String toString() {
        return this.v;
    }

    @Nullable
    public SoundEffect e() {
        return this.y;
    }

    private static VillagePlaceType a(String s, Set<IBlockData> set, int i, @Nullable SoundEffect soundeffect, int j) {
        return a((VillagePlaceType) IRegistry.POINT_OF_INTEREST_TYPE.a(new MinecraftKey(s), (Object) (new VillagePlaceType(s, set, i, soundeffect, j))));
    }

    private static VillagePlaceType a(String s, Set<IBlockData> set, int i, @Nullable SoundEffect soundeffect, Predicate<VillagePlaceType> predicate, int j) {
        return a((VillagePlaceType) IRegistry.POINT_OF_INTEREST_TYPE.a(new MinecraftKey(s), (Object) (new VillagePlaceType(s, set, i, soundeffect, predicate, j))));
    }

    private static VillagePlaceType a(VillagePlaceType villageplacetype) {
        villageplacetype.w.forEach((iblockdata) -> {
            VillagePlaceType villageplacetype1 = (VillagePlaceType) VillagePlaceType.u.put(iblockdata, villageplacetype);

            if (villageplacetype1 != null) {
                throw new IllegalStateException(String.format("%s is defined in too many tags", iblockdata));
            }
        });
        return villageplacetype;
    }

    public static Optional<VillagePlaceType> b(IBlockData iblockdata) {
        return Optional.ofNullable(VillagePlaceType.u.get(iblockdata));
    }

    public static Stream<IBlockData> f() {
        return VillagePlaceType.u.keySet().stream();
    }
}
