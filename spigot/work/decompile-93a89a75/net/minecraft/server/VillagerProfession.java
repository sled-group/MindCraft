package net.minecraft.server;

import com.google.common.collect.ImmutableSet;

public class VillagerProfession {

    public static final VillagerProfession NONE = a("none", VillagePlaceType.b);
    public static final VillagerProfession ARMORER = a("armorer", VillagePlaceType.c);
    public static final VillagerProfession BUTCHER = a("butcher", VillagePlaceType.d);
    public static final VillagerProfession CARTOGRAPHER = a("cartographer", VillagePlaceType.e);
    public static final VillagerProfession CLERIC = a("cleric", VillagePlaceType.f);
    public static final VillagerProfession FARMER = a("farmer", VillagePlaceType.g, ImmutableSet.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS), ImmutableSet.of(Blocks.FARMLAND));
    public static final VillagerProfession FISHERMAN = a("fisherman", VillagePlaceType.h);
    public static final VillagerProfession FLETCHER = a("fletcher", VillagePlaceType.i);
    public static final VillagerProfession LEATHERWORKER = a("leatherworker", VillagePlaceType.j);
    public static final VillagerProfession LIBRARIAN = a("librarian", VillagePlaceType.k);
    public static final VillagerProfession MASON = a("mason", VillagePlaceType.l);
    public static final VillagerProfession NITWIT = a("nitwit", VillagePlaceType.m);
    public static final VillagerProfession SHEPHERD = a("shepherd", VillagePlaceType.n);
    public static final VillagerProfession TOOLSMITH = a("toolsmith", VillagePlaceType.o);
    public static final VillagerProfession WEAPONSMITH = a("weaponsmith", VillagePlaceType.p);
    private final String p;
    private final VillagePlaceType q;
    private final ImmutableSet<Item> r;
    private final ImmutableSet<Block> s;

    private VillagerProfession(String s, VillagePlaceType villageplacetype, ImmutableSet<Item> immutableset, ImmutableSet<Block> immutableset1) {
        this.p = s;
        this.q = villageplacetype;
        this.r = immutableset;
        this.s = immutableset1;
    }

    public VillagePlaceType b() {
        return this.q;
    }

    public ImmutableSet<Item> c() {
        return this.r;
    }

    public ImmutableSet<Block> d() {
        return this.s;
    }

    public String toString() {
        return this.p;
    }

    static VillagerProfession a(String s, VillagePlaceType villageplacetype) {
        return a(s, villageplacetype, ImmutableSet.of(), ImmutableSet.of());
    }

    static VillagerProfession a(String s, VillagePlaceType villageplacetype, ImmutableSet<Item> immutableset, ImmutableSet<Block> immutableset1) {
        return (VillagerProfession) IRegistry.a((IRegistry) IRegistry.VILLAGER_PROFESSION, new MinecraftKey(s), (Object) (new VillagerProfession(s, villageplacetype, immutableset, immutableset1)));
    }
}
