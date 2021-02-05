package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class MemoryModuleType<U> {

    public static final MemoryModuleType<Void> DUMMY = a("dummy");
    public static final MemoryModuleType<GlobalPos> HOME = a("home", Optional.of(GlobalPos::a));
    public static final MemoryModuleType<GlobalPos> JOB_SITE = a("job_site", Optional.of(GlobalPos::a));
    public static final MemoryModuleType<GlobalPos> MEETING_POINT = a("meeting_point", Optional.of(GlobalPos::a));
    public static final MemoryModuleType<List<GlobalPos>> SECONDARY_JOB_SITE = a("secondary_job_site");
    public static final MemoryModuleType<List<EntityLiving>> MOBS = a("mobs");
    public static final MemoryModuleType<List<EntityLiving>> VISIBLE_MOBS = a("visible_mobs");
    public static final MemoryModuleType<List<EntityLiving>> VISIBLE_VILLAGER_BABIES = a("visible_villager_babies");
    public static final MemoryModuleType<List<EntityHuman>> NEAREST_PLAYERS = a("nearest_players");
    public static final MemoryModuleType<EntityHuman> NEAREST_VISIBLE_PLAYER = a("nearest_visible_player");
    public static final MemoryModuleType<MemoryTarget> WALK_TARGET = a("walk_target");
    public static final MemoryModuleType<BehaviorPosition> LOOK_TARGET = a("look_target");
    public static final MemoryModuleType<EntityLiving> INTERACTION_TARGET = a("interaction_target");
    public static final MemoryModuleType<EntityVillager> BREED_TARGET = a("breed_target");
    public static final MemoryModuleType<PathEntity> PATH = a("path");
    public static final MemoryModuleType<List<GlobalPos>> INTERACTABLE_DOORS = a("interactable_doors");
    public static final MemoryModuleType<Set<GlobalPos>> OPENED_DOORS = a("opened_doors");
    public static final MemoryModuleType<BlockPosition> NEAREST_BED = a("nearest_bed");
    public static final MemoryModuleType<DamageSource> HURT_BY = a("hurt_by");
    public static final MemoryModuleType<EntityLiving> HURT_BY_ENTITY = a("hurt_by_entity");
    public static final MemoryModuleType<EntityLiving> NEAREST_HOSTILE = a("nearest_hostile");
    public static final MemoryModuleType<GlobalPos> HIDING_PLACE = a("hiding_place");
    public static final MemoryModuleType<Long> HEARD_BELL_TIME = a("heard_bell_time");
    public static final MemoryModuleType<Long> CANT_REACH_WALK_TARGET_SINCE = a("cant_reach_walk_target_since");
    public static final MemoryModuleType<Long> GOLEM_LAST_SEEN_TIME = a("golem_last_seen_time");
    public static final MemoryModuleType<MinecraftSerializableLong> LAST_SLEPT = a("last_slept", Optional.of(MinecraftSerializableLong::a));
    public static final MemoryModuleType<MinecraftSerializableLong> LAST_WORKED_AT_POI = a("last_worked_at_poi", Optional.of(MinecraftSerializableLong::a));
    private final Optional<Function<Dynamic<?>, U>> B;

    private MemoryModuleType(Optional<Function<Dynamic<?>, U>> optional) {
        this.B = optional;
    }

    public String toString() {
        return IRegistry.MEMORY_MODULE_TYPE.getKey(this).toString();
    }

    public Optional<Function<Dynamic<?>, U>> getSerializer() {
        return this.B;
    }

    private static <U extends MinecraftSerializable> MemoryModuleType<U> a(String s, Optional<Function<Dynamic<?>, U>> optional) {
        return (MemoryModuleType) IRegistry.a((IRegistry) IRegistry.MEMORY_MODULE_TYPE, new MinecraftKey(s), (Object) (new MemoryModuleType<>(optional)));
    }

    private static <U> MemoryModuleType<U> a(String s) {
        return (MemoryModuleType) IRegistry.a((IRegistry) IRegistry.MEMORY_MODULE_TYPE, new MinecraftKey(s), (Object) (new MemoryModuleType<>(Optional.empty())));
    }
}
