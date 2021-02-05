package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureDefinedStructureJigsawJunction {

    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final WorldGenFeatureDefinedStructurePoolTemplate.Matching e;

    public WorldGenFeatureDefinedStructureJigsawJunction(int i, int j, int k, int l, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = worldgenfeaturedefinedstructurepooltemplate_matching;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        Builder<T, T> builder = ImmutableMap.builder();

        builder.put(dynamicops.createString("source_x"), dynamicops.createInt(this.a)).put(dynamicops.createString("source_ground_y"), dynamicops.createInt(this.b)).put(dynamicops.createString("source_z"), dynamicops.createInt(this.c)).put(dynamicops.createString("delta_y"), dynamicops.createInt(this.d)).put(dynamicops.createString("dest_proj"), dynamicops.createString(this.e.a()));
        return new Dynamic(dynamicops, dynamicops.createMap(builder.build()));
    }

    public static <T> WorldGenFeatureDefinedStructureJigsawJunction a(Dynamic<T> dynamic) {
        return new WorldGenFeatureDefinedStructureJigsawJunction(dynamic.get("source_x").asInt(0), dynamic.get("source_ground_y").asInt(0), dynamic.get("source_z").asInt(0), dynamic.get("delta_y").asInt(0), WorldGenFeatureDefinedStructurePoolTemplate.Matching.a(dynamic.get("dest_proj").asString("")));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            WorldGenFeatureDefinedStructureJigsawJunction worldgenfeaturedefinedstructurejigsawjunction = (WorldGenFeatureDefinedStructureJigsawJunction) object;

            return this.a != worldgenfeaturedefinedstructurejigsawjunction.a ? false : (this.c != worldgenfeaturedefinedstructurejigsawjunction.c ? false : (this.d != worldgenfeaturedefinedstructurejigsawjunction.d ? false : this.e == worldgenfeaturedefinedstructurejigsawjunction.e));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i = this.a;

        i = 31 * i + this.b;
        i = 31 * i + this.c;
        i = 31 * i + this.d;
        i = 31 * i + this.e.hashCode();
        return i;
    }

    public String toString() {
        return "JigsawJunction{sourceX=" + this.a + ", sourceGroundY=" + this.b + ", sourceZ=" + this.c + ", deltaY=" + this.d + ", destProjection=" + this.e + '}';
    }
}
