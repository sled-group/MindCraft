package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import javax.annotation.Nullable;

public class LootItemConditionWeatherCheck implements LootItemCondition {

    @Nullable
    private final Boolean a;
    @Nullable
    private final Boolean b;

    private LootItemConditionWeatherCheck(@Nullable Boolean obool, @Nullable Boolean obool1) {
        this.a = obool;
        this.b = obool1;
    }

    public boolean test(LootTableInfo loottableinfo) {
        WorldServer worldserver = loottableinfo.d();

        return this.a != null && this.a != worldserver.isRaining() ? false : this.b == null || this.b == worldserver.U();
    }

    public static class b extends LootItemCondition.b<LootItemConditionWeatherCheck> {

        public b() {
            super(new MinecraftKey("weather_check"), LootItemConditionWeatherCheck.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionWeatherCheck lootitemconditionweathercheck, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("raining", lootitemconditionweathercheck.a);
            jsonobject.addProperty("thundering", lootitemconditionweathercheck.b);
        }

        @Override
        public LootItemConditionWeatherCheck b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            Boolean obool = jsonobject.has("raining") ? ChatDeserializer.j(jsonobject, "raining") : null;
            Boolean obool1 = jsonobject.has("thundering") ? ChatDeserializer.j(jsonobject, "thundering") : null;

            return new LootItemConditionWeatherCheck(obool, obool1);
        }
    }
}
