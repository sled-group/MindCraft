package net.minecraft.server;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WorldGenStage {
    public static enum Features {

        AIR("air"), LIQUID("liquid");

        private static final Map<String, WorldGenStage.Features> c = (Map) Arrays.stream(values()).collect(Collectors.toMap(WorldGenStage.Features::a, (worldgenstage_features) -> {
            return worldgenstage_features;
        }));
        private final String d;

        private Features(String s) {
            this.d = s;
        }

        public String a() {
            return this.d;
        }
    }

    public static enum Decoration {

        RAW_GENERATION("raw_generation"), LOCAL_MODIFICATIONS("local_modifications"), UNDERGROUND_STRUCTURES("underground_structures"), SURFACE_STRUCTURES("surface_structures"), UNDERGROUND_ORES("underground_ores"), UNDERGROUND_DECORATION("underground_decoration"), VEGETAL_DECORATION("vegetal_decoration"), TOP_LAYER_MODIFICATION("top_layer_modification");

        private static final Map<String, WorldGenStage.Decoration> i = (Map) Arrays.stream(values()).collect(Collectors.toMap(WorldGenStage.Decoration::a, (worldgenstage_decoration) -> {
            return worldgenstage_decoration;
        }));
        private final String j;

        private Decoration(String s) {
            this.j = s;
        }

        public String a() {
            return this.j;
        }
    }
}
