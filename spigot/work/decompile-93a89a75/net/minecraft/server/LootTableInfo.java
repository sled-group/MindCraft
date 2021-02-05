package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public class LootTableInfo {

    private final Random a;
    private final float b;
    private final WorldServer c;
    private final LootTableRegistry d;
    private final Set<LootTable> e;
    private final Map<LootContextParameter<?>, Object> f;
    private final Map<MinecraftKey, LootTableInfo.b> g;

    private LootTableInfo(Random random, float f, WorldServer worldserver, LootTableRegistry loottableregistry, Map<LootContextParameter<?>, Object> map, Map<MinecraftKey, LootTableInfo.b> map1) {
        this.e = Sets.newLinkedHashSet();
        this.a = random;
        this.b = f;
        this.c = worldserver;
        this.d = loottableregistry;
        this.f = ImmutableMap.copyOf(map);
        this.g = ImmutableMap.copyOf(map1);
    }

    public boolean hasContextParameter(LootContextParameter<?> lootcontextparameter) {
        return this.f.containsKey(lootcontextparameter);
    }

    public void a(MinecraftKey minecraftkey, Consumer<ItemStack> consumer) {
        LootTableInfo.b loottableinfo_b = (LootTableInfo.b) this.g.get(minecraftkey);

        if (loottableinfo_b != null) {
            loottableinfo_b.add(this, consumer);
        }

    }

    @Nullable
    public <T> T getContextParameter(LootContextParameter<T> lootcontextparameter) {
        return this.f.get(lootcontextparameter);
    }

    public boolean a(LootTable loottable) {
        return this.e.add(loottable);
    }

    public void b(LootTable loottable) {
        this.e.remove(loottable);
    }

    public LootTableRegistry a() {
        return this.d;
    }

    public Random b() {
        return this.a;
    }

    public float c() {
        return this.b;
    }

    public WorldServer d() {
        return this.c;
    }

    public static enum EntityTarget {

        THIS("this", LootContextParameters.THIS_ENTITY), KILLER("killer", LootContextParameters.KILLER_ENTITY), DIRECT_KILLER("direct_killer", LootContextParameters.DIRECT_KILLER_ENTITY), KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER);

        private final String e;
        private final LootContextParameter<? extends Entity> f;

        private EntityTarget(String s, LootContextParameter lootcontextparameter) {
            this.e = s;
            this.f = lootcontextparameter;
        }

        public LootContextParameter<? extends Entity> a() {
            return this.f;
        }

        public static LootTableInfo.EntityTarget a(String s) {
            LootTableInfo.EntityTarget[] aloottableinfo_entitytarget = values();
            int i = aloottableinfo_entitytarget.length;

            for (int j = 0; j < i; ++j) {
                LootTableInfo.EntityTarget loottableinfo_entitytarget = aloottableinfo_entitytarget[j];

                if (loottableinfo_entitytarget.e.equals(s)) {
                    return loottableinfo_entitytarget;
                }
            }

            throw new IllegalArgumentException("Invalid entity target " + s);
        }

        public static class a extends TypeAdapter<LootTableInfo.EntityTarget> {

            public a() {}

            public void write(JsonWriter jsonwriter, LootTableInfo.EntityTarget loottableinfo_entitytarget) throws IOException {
                jsonwriter.value(loottableinfo_entitytarget.e);
            }

            public LootTableInfo.EntityTarget read(JsonReader jsonreader) throws IOException {
                return LootTableInfo.EntityTarget.a(jsonreader.nextString());
            }
        }
    }

    public static class Builder {

        private final WorldServer a;
        private final Map<LootContextParameter<?>, Object> b = Maps.newIdentityHashMap();
        private final Map<MinecraftKey, LootTableInfo.b> c = Maps.newHashMap();
        private Random d;
        private float e;

        public Builder(WorldServer worldserver) {
            this.a = worldserver;
        }

        public LootTableInfo.Builder a(Random random) {
            this.d = random;
            return this;
        }

        public LootTableInfo.Builder a(long i) {
            if (i != 0L) {
                this.d = new Random(i);
            }

            return this;
        }

        public LootTableInfo.Builder a(long i, Random random) {
            if (i == 0L) {
                this.d = random;
            } else {
                this.d = new Random(i);
            }

            return this;
        }

        public LootTableInfo.Builder a(float f) {
            this.e = f;
            return this;
        }

        public <T> LootTableInfo.Builder set(LootContextParameter<T> lootcontextparameter, T t0) {
            this.b.put(lootcontextparameter, t0);
            return this;
        }

        public <T> LootTableInfo.Builder setOptional(LootContextParameter<T> lootcontextparameter, @Nullable T t0) {
            if (t0 == null) {
                this.b.remove(lootcontextparameter);
            } else {
                this.b.put(lootcontextparameter, t0);
            }

            return this;
        }

        public LootTableInfo.Builder a(MinecraftKey minecraftkey, LootTableInfo.b loottableinfo_b) {
            LootTableInfo.b loottableinfo_b1 = (LootTableInfo.b) this.c.put(minecraftkey, loottableinfo_b);

            if (loottableinfo_b1 != null) {
                throw new IllegalStateException("Duplicated dynamic drop '" + this.c + "'");
            } else {
                return this;
            }
        }

        public WorldServer a() {
            return this.a;
        }

        public <T> T a(LootContextParameter<T> lootcontextparameter) {
            T t0 = this.b.get(lootcontextparameter);

            if (t0 == null) {
                throw new IllegalArgumentException("No parameter " + lootcontextparameter);
            } else {
                return t0;
            }
        }

        @Nullable
        public <T> T b(LootContextParameter<T> lootcontextparameter) {
            return this.b.get(lootcontextparameter);
        }

        public LootTableInfo build(LootContextParameterSet lootcontextparameterset) {
            Set<LootContextParameter<?>> set = Sets.difference(this.b.keySet(), lootcontextparameterset.b());

            if (!set.isEmpty()) {
                throw new IllegalArgumentException("Parameters not allowed in this parameter set: " + set);
            } else {
                Set<LootContextParameter<?>> set1 = Sets.difference(lootcontextparameterset.a(), this.b.keySet());

                if (!set1.isEmpty()) {
                    throw new IllegalArgumentException("Missing required parameters: " + set1);
                } else {
                    Random random = this.d;

                    if (random == null) {
                        random = new Random();
                    }

                    return new LootTableInfo(random, this.e, this.a, this.a.getMinecraftServer().getLootTableRegistry(), this.b, this.c);
                }
            }
        }
    }

    @FunctionalInterface
    public interface b {

        void add(LootTableInfo loottableinfo, Consumer<ItemStack> consumer);
    }
}
