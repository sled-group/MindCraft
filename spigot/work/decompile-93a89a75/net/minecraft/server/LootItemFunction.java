package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public interface LootItemFunction extends LootItemUser, BiFunction<ItemStack, LootTableInfo, ItemStack> {

    static Consumer<ItemStack> a(BiFunction<ItemStack, LootTableInfo, ItemStack> bifunction, Consumer<ItemStack> consumer, LootTableInfo loottableinfo) {
        return (itemstack) -> {
            consumer.accept(bifunction.apply(itemstack, loottableinfo));
        };
    }

    public abstract static class b<T extends LootItemFunction> {

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

    public interface a {

        LootItemFunction b();
    }
}
