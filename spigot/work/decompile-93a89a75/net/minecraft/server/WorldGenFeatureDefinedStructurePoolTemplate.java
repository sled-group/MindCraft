package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class WorldGenFeatureDefinedStructurePoolTemplate {

    public static final WorldGenFeatureDefinedStructurePoolTemplate a = new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("empty"), new MinecraftKey("empty"), ImmutableList.of(), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
    public static final WorldGenFeatureDefinedStructurePoolTemplate b = new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("invalid"), new MinecraftKey("invalid"), ImmutableList.of(), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
    private final MinecraftKey c;
    private final ImmutableList<Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer>> d;
    private final List<WorldGenFeatureDefinedStructurePoolStructure> e;
    private final MinecraftKey f;
    private final WorldGenFeatureDefinedStructurePoolTemplate.Matching g;
    private int h = Integer.MIN_VALUE;

    public WorldGenFeatureDefinedStructurePoolTemplate(MinecraftKey minecraftkey, MinecraftKey minecraftkey1, List<Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer>> list, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.c = minecraftkey;
        this.d = ImmutableList.copyOf(list);
        this.e = Lists.newArrayList();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer> pair = (Pair) iterator.next();

            for (Integer integer = 0; integer < (Integer) pair.getSecond(); integer = integer + 1) {
                this.e.add(((WorldGenFeatureDefinedStructurePoolStructure) pair.getFirst()).a(worldgenfeaturedefinedstructurepooltemplate_matching));
            }
        }

        this.f = minecraftkey1;
        this.g = worldgenfeaturedefinedstructurepooltemplate_matching;
    }

    public int a(DefinedStructureManager definedstructuremanager) {
        if (this.h == Integer.MIN_VALUE) {
            this.h = this.e.stream().mapToInt((worldgenfeaturedefinedstructurepoolstructure) -> {
                return worldgenfeaturedefinedstructurepoolstructure.a(definedstructuremanager, BlockPosition.ZERO, EnumBlockRotation.NONE).d();
            }).max().orElse(0);
        }

        return this.h;
    }

    public MinecraftKey a() {
        return this.f;
    }

    public WorldGenFeatureDefinedStructurePoolStructure a(Random random) {
        return (WorldGenFeatureDefinedStructurePoolStructure) this.e.get(random.nextInt(this.e.size()));
    }

    public List<WorldGenFeatureDefinedStructurePoolStructure> b(Random random) {
        return ImmutableList.copyOf(ObjectArrays.shuffle(this.e.toArray(new WorldGenFeatureDefinedStructurePoolStructure[0]), random));
    }

    public MinecraftKey b() {
        return this.c;
    }

    public int c() {
        return this.e.size();
    }

    public static enum Matching {

        TERRAIN_MATCHING("terrain_matching", ImmutableList.of(new DefinedStructureProcessorGravity(HeightMap.Type.WORLD_SURFACE_WG, -1))), RIGID("rigid", ImmutableList.of());

        private static final Map<String, WorldGenFeatureDefinedStructurePoolTemplate.Matching> c = (Map) Arrays.stream(values()).collect(Collectors.toMap(WorldGenFeatureDefinedStructurePoolTemplate.Matching::a, (worldgenfeaturedefinedstructurepooltemplate_matching) -> {
            return worldgenfeaturedefinedstructurepooltemplate_matching;
        }));
        private final String d;
        private final ImmutableList<DefinedStructureProcessor> e;

        private Matching(String s, ImmutableList immutablelist) {
            this.d = s;
            this.e = immutablelist;
        }

        public String a() {
            return this.d;
        }

        public static WorldGenFeatureDefinedStructurePoolTemplate.Matching a(String s) {
            return (WorldGenFeatureDefinedStructurePoolTemplate.Matching) WorldGenFeatureDefinedStructurePoolTemplate.Matching.c.get(s);
        }

        public ImmutableList<DefinedStructureProcessor> b() {
            return this.e;
        }
    }
}
