package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

public interface VillagerType {

    VillagerType DESERT = a("desert");
    VillagerType JUNGLE = a("jungle");
    VillagerType PLAINS = a("plains");
    VillagerType SAVANNA = a("savanna");
    VillagerType SNOW = a("snow");
    VillagerType SWAMP = a("swamp");
    VillagerType TAIGA = a("taiga");
    Map<BiomeBase, VillagerType> h = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put(Biomes.BADLANDS, VillagerType.DESERT);
        hashmap.put(Biomes.BADLANDS_PLATEAU, VillagerType.DESERT);
        hashmap.put(Biomes.DESERT, VillagerType.DESERT);
        hashmap.put(Biomes.DESERT_HILLS, VillagerType.DESERT);
        hashmap.put(Biomes.DESERT_LAKES, VillagerType.DESERT);
        hashmap.put(Biomes.ERODED_BADLANDS, VillagerType.DESERT);
        hashmap.put(Biomes.MODIFIED_BADLANDS_PLATEAU, VillagerType.DESERT);
        hashmap.put(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, VillagerType.DESERT);
        hashmap.put(Biomes.WOODED_BADLANDS_PLATEAU, VillagerType.DESERT);
        hashmap.put(Biomes.BAMBOO_JUNGLE, VillagerType.JUNGLE);
        hashmap.put(Biomes.BAMBOO_JUNGLE_HILLS, VillagerType.JUNGLE);
        hashmap.put(Biomes.JUNGLE, VillagerType.JUNGLE);
        hashmap.put(Biomes.JUNGLE_EDGE, VillagerType.JUNGLE);
        hashmap.put(Biomes.JUNGLE_HILLS, VillagerType.JUNGLE);
        hashmap.put(Biomes.MODIFIED_JUNGLE, VillagerType.JUNGLE);
        hashmap.put(Biomes.MODIFIED_JUNGLE_EDGE, VillagerType.JUNGLE);
        hashmap.put(Biomes.SAVANNA_PLATEAU, VillagerType.SAVANNA);
        hashmap.put(Biomes.SAVANNA, VillagerType.SAVANNA);
        hashmap.put(Biomes.SHATTERED_SAVANNA, VillagerType.SAVANNA);
        hashmap.put(Biomes.SHATTERED_SAVANNA_PLATEAU, VillagerType.SAVANNA);
        hashmap.put(Biomes.DEEP_FROZEN_OCEAN, VillagerType.SNOW);
        hashmap.put(Biomes.FROZEN_OCEAN, VillagerType.SNOW);
        hashmap.put(Biomes.FROZEN_RIVER, VillagerType.SNOW);
        hashmap.put(Biomes.ICE_SPIKES, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_BEACH, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_MOUNTAINS, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_TAIGA, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_TAIGA_HILLS, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_TAIGA_MOUNTAINS, VillagerType.SNOW);
        hashmap.put(Biomes.SNOWY_TUNDRA, VillagerType.SNOW);
        hashmap.put(Biomes.SWAMP, VillagerType.SWAMP);
        hashmap.put(Biomes.SWAMP_HILLS, VillagerType.SWAMP);
        hashmap.put(Biomes.GIANT_SPRUCE_TAIGA, VillagerType.TAIGA);
        hashmap.put(Biomes.GIANT_SPRUCE_TAIGA_HILLS, VillagerType.TAIGA);
        hashmap.put(Biomes.GIANT_TREE_TAIGA, VillagerType.TAIGA);
        hashmap.put(Biomes.GIANT_TREE_TAIGA_HILLS, VillagerType.TAIGA);
        hashmap.put(Biomes.GRAVELLY_MOUNTAINS, VillagerType.TAIGA);
        hashmap.put(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, VillagerType.TAIGA);
        hashmap.put(Biomes.MOUNTAIN_EDGE, VillagerType.TAIGA);
        hashmap.put(Biomes.MOUNTAINS, VillagerType.TAIGA);
        hashmap.put(Biomes.TAIGA, VillagerType.TAIGA);
        hashmap.put(Biomes.TAIGA_HILLS, VillagerType.TAIGA);
        hashmap.put(Biomes.TAIGA_MOUNTAINS, VillagerType.TAIGA);
        hashmap.put(Biomes.WOODED_MOUNTAINS, VillagerType.TAIGA);
    });

    static VillagerType a(final String s) {
        return (VillagerType) IRegistry.a((IRegistry) IRegistry.VILLAGER_TYPE, new MinecraftKey(s), (Object) (new VillagerType() {
            public String toString() {
                return s;
            }
        }));
    }

    static VillagerType a(BiomeBase biomebase) {
        return (VillagerType) VillagerType.h.getOrDefault(biomebase, VillagerType.PLAINS);
    }
}
