package net.minecraft.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public class LootContextParameterSets {

    private static final BiMap<MinecraftKey, LootContextParameterSet> i = HashBiMap.create();
    public static final LootContextParameterSet EMPTY = a("empty", (lootcontextparameterset_a) -> {
    });
    public static final LootContextParameterSet CHEST = a("chest", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.POSITION).b(LootContextParameters.THIS_ENTITY);
    });
    public static final LootContextParameterSet FISHING = a("fishing", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.POSITION).a(LootContextParameters.TOOL);
    });
    public static final LootContextParameterSet ENTITY = a("entity", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.THIS_ENTITY).a(LootContextParameters.POSITION).a(LootContextParameters.DAMAGE_SOURCE).b(LootContextParameters.KILLER_ENTITY).b(LootContextParameters.DIRECT_KILLER_ENTITY).b(LootContextParameters.LAST_DAMAGE_PLAYER);
    });
    public static final LootContextParameterSet GIFT = a("gift", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.POSITION).a(LootContextParameters.THIS_ENTITY);
    });
    public static final LootContextParameterSet ADVANCEMENT_REWARD = a("advancement_reward", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.THIS_ENTITY).a(LootContextParameters.POSITION);
    });
    public static final LootContextParameterSet GENERIC = a("generic", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.THIS_ENTITY).a(LootContextParameters.LAST_DAMAGE_PLAYER).a(LootContextParameters.DAMAGE_SOURCE).a(LootContextParameters.KILLER_ENTITY).a(LootContextParameters.DIRECT_KILLER_ENTITY).a(LootContextParameters.POSITION).a(LootContextParameters.BLOCK_STATE).a(LootContextParameters.BLOCK_ENTITY).a(LootContextParameters.TOOL).a(LootContextParameters.EXPLOSION_RADIUS);
    });
    public static final LootContextParameterSet BLOCK = a("block", (lootcontextparameterset_a) -> {
        lootcontextparameterset_a.a(LootContextParameters.BLOCK_STATE).a(LootContextParameters.POSITION).a(LootContextParameters.TOOL).b(LootContextParameters.THIS_ENTITY).b(LootContextParameters.BLOCK_ENTITY).b(LootContextParameters.EXPLOSION_RADIUS);
    });

    private static LootContextParameterSet a(String s, Consumer<LootContextParameterSet.a> consumer) {
        LootContextParameterSet.a lootcontextparameterset_a = new LootContextParameterSet.a();

        consumer.accept(lootcontextparameterset_a);
        LootContextParameterSet lootcontextparameterset = lootcontextparameterset_a.a();
        MinecraftKey minecraftkey = new MinecraftKey(s);
        LootContextParameterSet lootcontextparameterset1 = (LootContextParameterSet) LootContextParameterSets.i.put(minecraftkey, lootcontextparameterset);

        if (lootcontextparameterset1 != null) {
            throw new IllegalStateException("Loot table parameter set " + minecraftkey + " is already registered");
        } else {
            return lootcontextparameterset;
        }
    }

    @Nullable
    public static LootContextParameterSet a(MinecraftKey minecraftkey) {
        return (LootContextParameterSet) LootContextParameterSets.i.get(minecraftkey);
    }

    @Nullable
    public static MinecraftKey a(LootContextParameterSet lootcontextparameterset) {
        return (MinecraftKey) LootContextParameterSets.i.inverse().get(lootcontextparameterset);
    }
}
