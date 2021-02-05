package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.function.Predicate;

@FunctionalInterface
public interface LootItemCondition extends LootItemUser, Predicate<LootTableInfo> {
    public abstract static class b<T extends LootItemCondition> {

        private final MinecraftKey a;
        private final Class<T> b;

        protected b(MinecraftKey minecraftkey, Class<T> oclass) {
            this.a = minecraftkey;
            this.b = oclass;
        }

        public MinecraftKey a() {
            return this.a;
        }

        public Class<T> b() {
            return this.b;
        }

        public abstract void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext);

        public abstract T b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext);
    }

    @FunctionalInterface
    public interface a {

        LootItemCondition build();

        default LootItemCondition.a a() {
            return LootItemConditionInverted.a(this);
        }

        default LootItemConditionAlternative.a a(LootItemCondition.a lootitemcondition_a) {
            return LootItemConditionAlternative.a(this, lootitemcondition_a);
        }
    }
}
