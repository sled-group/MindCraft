package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;

public class LootItemConditionKilledByPlayer implements LootItemCondition {

    private static final LootItemConditionKilledByPlayer a = new LootItemConditionKilledByPlayer();

    private LootItemConditionKilledByPlayer() {}

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.LAST_DAMAGE_PLAYER);
    }

    public boolean test(LootTableInfo loottableinfo) {
        return loottableinfo.hasContextParameter(LootContextParameters.LAST_DAMAGE_PLAYER);
    }

    public static LootItemCondition.a b() {
        return () -> {
            return LootItemConditionKilledByPlayer.a;
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionKilledByPlayer> {

        protected a() {
            super(new MinecraftKey("killed_by_player"), LootItemConditionKilledByPlayer.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionKilledByPlayer lootitemconditionkilledbyplayer, JsonSerializationContext jsonserializationcontext) {}

        @Override
        public LootItemConditionKilledByPlayer b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            return LootItemConditionKilledByPlayer.a;
        }
    }
}
