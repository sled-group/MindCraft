package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class WorldGenFeatureEndSpikeConfiguration implements WorldGenFeatureConfiguration {

    private final boolean a;
    private final List<WorldGenEnder.Spike> b;
    @Nullable
    private final BlockPosition c;

    public WorldGenFeatureEndSpikeConfiguration(boolean flag, List<WorldGenEnder.Spike> list, @Nullable BlockPosition blockposition) {
        this.a = flag;
        this.b = list;
        this.c = blockposition;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        Dynamic dynamic = new Dynamic;
        Object object = dynamicops.createString("crystalInvulnerable");
        Object object1 = dynamicops.createBoolean(this.a);
        Object object2 = dynamicops.createString("spikes");
        Object object3 = dynamicops.createList(this.b.stream().map((worldgenender_spike) -> {
            return worldgenender_spike.a(dynamicops).getValue();
        }));
        Object object4 = dynamicops.createString("crystalBeamTarget");
        Object object5;

        if (this.c == null) {
            object5 = dynamicops.createList(Stream.empty());
        } else {
            IntStream intstream = IntStream.of(new int[]{this.c.getX(), this.c.getY(), this.c.getZ()});

            dynamicops.getClass();
            object5 = dynamicops.createList(intstream.mapToObj(dynamicops::createInt));
        }

        dynamic.<init>(dynamicops, dynamicops.createMap(ImmutableMap.of(object, object1, object2, object3, object4, object5)));
        return dynamic;
    }

    public static <T> WorldGenFeatureEndSpikeConfiguration a(Dynamic<T> dynamic) {
        List<WorldGenEnder.Spike> list = dynamic.get("spikes").asList(WorldGenEnder.Spike::a);
        List<Integer> list1 = dynamic.get("crystalBeamTarget").asList((dynamic1) -> {
            return dynamic1.asInt(0);
        });
        BlockPosition blockposition;

        if (list1.size() == 3) {
            blockposition = new BlockPosition((Integer) list1.get(0), (Integer) list1.get(1), (Integer) list1.get(2));
        } else {
            blockposition = null;
        }

        return new WorldGenFeatureEndSpikeConfiguration(dynamic.get("crystalInvulnerable").asBoolean(false), list, blockposition);
    }

    public boolean a() {
        return this.a;
    }

    public List<WorldGenEnder.Spike> b() {
        return this.b;
    }

    @Nullable
    public BlockPosition c() {
        return this.c;
    }
}
