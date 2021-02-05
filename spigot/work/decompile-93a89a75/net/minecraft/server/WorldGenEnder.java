package net.minecraft.server;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorldGenEnder extends WorldGenerator<WorldGenFeatureEndSpikeConfiguration> {

    private static final LoadingCache<Long, List<WorldGenEnder.Spike>> a = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build(new WorldGenEnder.b());

    public WorldGenEnder(Function<Dynamic<?>, ? extends WorldGenFeatureEndSpikeConfiguration> function) {
        super(function);
    }

    public static List<WorldGenEnder.Spike> a(GeneratorAccess generatoraccess) {
        Random random = new Random(generatoraccess.getSeed());
        long i = random.nextLong() & 65535L;

        return (List) WorldGenEnder.a.getUnchecked(i);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEndSpikeConfiguration worldgenfeatureendspikeconfiguration) {
        List<WorldGenEnder.Spike> list = worldgenfeatureendspikeconfiguration.b();

        if (list.isEmpty()) {
            list = a(generatoraccess);
        }

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            WorldGenEnder.Spike worldgenender_spike = (WorldGenEnder.Spike) iterator.next();

            if (worldgenender_spike.a(blockposition)) {
                this.a(generatoraccess, random, worldgenfeatureendspikeconfiguration, worldgenender_spike);
            }
        }

        return true;
    }

    private void a(GeneratorAccess generatoraccess, Random random, WorldGenFeatureEndSpikeConfiguration worldgenfeatureendspikeconfiguration, WorldGenEnder.Spike worldgenender_spike) {
        int i = worldgenender_spike.c();
        Iterator iterator = BlockPosition.a(new BlockPosition(worldgenender_spike.a() - i, 0, worldgenender_spike.b() - i), new BlockPosition(worldgenender_spike.a() + i, worldgenender_spike.d() + 10, worldgenender_spike.b() + i)).iterator();

        while (iterator.hasNext()) {
            BlockPosition blockposition = (BlockPosition) iterator.next();

            if (blockposition.a((BaseBlockPosition) (new BlockPosition(worldgenender_spike.a(), blockposition.getY(), worldgenender_spike.b())), (double) i) && blockposition.getY() < worldgenender_spike.d()) {
                this.a(generatoraccess, blockposition, Blocks.OBSIDIAN.getBlockData());
            } else if (blockposition.getY() > 65) {
                this.a(generatoraccess, blockposition, Blocks.AIR.getBlockData());
            }
        }

        if (worldgenender_spike.e()) {
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

            for (int j = -2; j <= 2; ++j) {
                for (int k = -2; k <= 2; ++k) {
                    for (int l = 0; l <= 3; ++l) {
                        boolean flag3 = MathHelper.a(j) == 2;
                        boolean flag4 = MathHelper.a(k) == 2;
                        boolean flag5 = l == 3;

                        if (flag3 || flag4 || flag5) {
                            boolean flag6 = j == -2 || j == 2 || flag5;
                            boolean flag7 = k == -2 || k == 2 || flag5;
                            IBlockData iblockdata = (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, flag6 && k != -2)).set(BlockIronBars.SOUTH, flag6 && k != 2)).set(BlockIronBars.WEST, flag7 && j != -2)).set(BlockIronBars.EAST, flag7 && j != 2);

                            this.a(generatoraccess, blockposition_mutableblockposition.d(worldgenender_spike.a() + j, worldgenender_spike.d() + l, worldgenender_spike.b() + k), iblockdata);
                        }
                    }
                }
            }
        }

        EntityEnderCrystal entityendercrystal = (EntityEnderCrystal) EntityTypes.END_CRYSTAL.a(generatoraccess.getMinecraftWorld());

        entityendercrystal.setBeamTarget(worldgenfeatureendspikeconfiguration.c());
        entityendercrystal.setInvulnerable(worldgenfeatureendspikeconfiguration.a());
        entityendercrystal.setPositionRotation((double) ((float) worldgenender_spike.a() + 0.5F), (double) (worldgenender_spike.d() + 1), (double) ((float) worldgenender_spike.b() + 0.5F), random.nextFloat() * 360.0F, 0.0F);
        generatoraccess.addEntity(entityendercrystal);
        this.a(generatoraccess, new BlockPosition(worldgenender_spike.a(), worldgenender_spike.d(), worldgenender_spike.b()), Blocks.BEDROCK.getBlockData());
    }

    static class b extends CacheLoader<Long, List<WorldGenEnder.Spike>> {

        private b() {}

        public List<WorldGenEnder.Spike> load(Long olong) {
            List<Integer> list = (List) IntStream.range(0, 10).boxed().collect(Collectors.toList());

            Collections.shuffle(list, new Random(olong));
            List<WorldGenEnder.Spike> list1 = Lists.newArrayList();

            for (int i = 0; i < 10; ++i) {
                int j = MathHelper.floor(42.0D * Math.cos(2.0D * (-3.141592653589793D + 0.3141592653589793D * (double) i)));
                int k = MathHelper.floor(42.0D * Math.sin(2.0D * (-3.141592653589793D + 0.3141592653589793D * (double) i)));
                int l = (Integer) list.get(i);
                int i1 = 2 + l / 3;
                int j1 = 76 + l * 3;
                boolean flag = l == 1 || l == 2;

                list1.add(new WorldGenEnder.Spike(j, k, i1, j1, flag));
            }

            return list1;
        }
    }

    public static class Spike {

        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private final boolean e;
        private final AxisAlignedBB f;

        public Spike(int i, int j, int k, int l, boolean flag) {
            this.a = i;
            this.b = j;
            this.c = k;
            this.d = l;
            this.e = flag;
            this.f = new AxisAlignedBB((double) (i - k), 0.0D, (double) (j - k), (double) (i + k), 256.0D, (double) (j + k));
        }

        public boolean a(BlockPosition blockposition) {
            return blockposition.getX() >> 4 == this.a >> 4 && blockposition.getZ() >> 4 == this.b >> 4;
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

        public int d() {
            return this.d;
        }

        public boolean e() {
            return this.e;
        }

        public AxisAlignedBB f() {
            return this.f;
        }

        <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
            Builder<T, T> builder = ImmutableMap.builder();

            builder.put(dynamicops.createString("centerX"), dynamicops.createInt(this.a));
            builder.put(dynamicops.createString("centerZ"), dynamicops.createInt(this.b));
            builder.put(dynamicops.createString("radius"), dynamicops.createInt(this.c));
            builder.put(dynamicops.createString("height"), dynamicops.createInt(this.d));
            builder.put(dynamicops.createString("guarded"), dynamicops.createBoolean(this.e));
            return new Dynamic(dynamicops, dynamicops.createMap(builder.build()));
        }

        public static <T> WorldGenEnder.Spike a(Dynamic<T> dynamic) {
            return new WorldGenEnder.Spike(dynamic.get("centerX").asInt(0), dynamic.get("centerZ").asInt(0), dynamic.get("radius").asInt(0), dynamic.get("height").asInt(0), dynamic.get("guarded").asBoolean(false));
        }
    }
}
