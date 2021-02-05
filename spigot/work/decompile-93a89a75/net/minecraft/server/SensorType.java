package net.minecraft.server;

import java.util.function.Supplier;

public class SensorType<U extends Sensor<?>> {

    public static final SensorType<SensorDummy> a = a("dummy", SensorDummy::new);
    public static final SensorType<SensorNearestLivingEntities> b = a("nearest_living_entities", SensorNearestLivingEntities::new);
    public static final SensorType<SensorNearestPlayers> c = a("nearest_players", SensorNearestPlayers::new);
    public static final SensorType<SensorInteractableDoors> d = a("interactable_doors", SensorInteractableDoors::new);
    public static final SensorType<SensorNearestBed> e = a("nearest_bed", SensorNearestBed::new);
    public static final SensorType<SensorHurtBy> f = a("hurt_by", SensorHurtBy::new);
    public static final SensorType<SensorVillagerHostiles> g = a("villager_hostiles", SensorVillagerHostiles::new);
    public static final SensorType<SensorVillagerBabies> h = a("villager_babies", SensorVillagerBabies::new);
    public static final SensorType<SensorSecondaryPlaces> i = a("secondary_pois", SensorSecondaryPlaces::new);
    public static final SensorType<SensorGolemLastSeen> j = a("golem_last_seen", SensorGolemLastSeen::new);
    private final Supplier<U> k;

    private SensorType(Supplier<U> supplier) {
        this.k = supplier;
    }

    public U a() {
        return (Sensor) this.k.get();
    }

    private static <U extends Sensor<?>> SensorType<U> a(String s, Supplier<U> supplier) {
        return (SensorType) IRegistry.a((IRegistry) IRegistry.SENSOR_TYPE, new MinecraftKey(s), (Object) (new SensorType<>(supplier)));
    }
}
