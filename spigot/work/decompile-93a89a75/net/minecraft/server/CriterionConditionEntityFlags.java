package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class CriterionConditionEntityFlags {

    public static final CriterionConditionEntityFlags a = (new CriterionConditionEntityFlags.a()).b();
    @Nullable
    private final Boolean b;
    @Nullable
    private final Boolean c;
    @Nullable
    private final Boolean d;
    @Nullable
    private final Boolean e;
    @Nullable
    private final Boolean f;

    public CriterionConditionEntityFlags(@Nullable Boolean obool, @Nullable Boolean obool1, @Nullable Boolean obool2, @Nullable Boolean obool3, @Nullable Boolean obool4) {
        this.b = obool;
        this.c = obool1;
        this.d = obool2;
        this.e = obool3;
        this.f = obool4;
    }

    public boolean a(Entity entity) {
        return this.b != null && entity.isBurning() != this.b ? false : (this.c != null && entity.isSneaking() != this.c ? false : (this.d != null && entity.isSprinting() != this.d ? false : (this.e != null && entity.isSwimming() != this.e ? false : this.f == null || !(entity instanceof EntityLiving) || ((EntityLiving) entity).isBaby() == this.f)));
    }

    @Nullable
    private static Boolean a(JsonObject jsonobject, String s) {
        return jsonobject.has(s) ? ChatDeserializer.j(jsonobject, s) : null;
    }

    public static CriterionConditionEntityFlags a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "entity flags");
            Boolean obool = a(jsonobject, "is_on_fire");
            Boolean obool1 = a(jsonobject, "is_sneaking");
            Boolean obool2 = a(jsonobject, "is_sprinting");
            Boolean obool3 = a(jsonobject, "is_swimming");
            Boolean obool4 = a(jsonobject, "is_baby");

            return new CriterionConditionEntityFlags(obool, obool1, obool2, obool3, obool4);
        } else {
            return CriterionConditionEntityFlags.a;
        }
    }

    private void a(JsonObject jsonobject, String s, @Nullable Boolean obool) {
        if (obool != null) {
            jsonobject.addProperty(s, obool);
        }

    }

    public JsonElement a() {
        if (this == CriterionConditionEntityFlags.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            this.a(jsonobject, "is_on_fire", this.b);
            this.a(jsonobject, "is_sneaking", this.c);
            this.a(jsonobject, "is_sprinting", this.d);
            this.a(jsonobject, "is_swimming", this.e);
            this.a(jsonobject, "is_baby", this.f);
            return jsonobject;
        }
    }

    public static class a {

        @Nullable
        private Boolean a;
        @Nullable
        private Boolean b;
        @Nullable
        private Boolean c;
        @Nullable
        private Boolean d;
        @Nullable
        private Boolean e;

        public a() {}

        public static CriterionConditionEntityFlags.a a() {
            return new CriterionConditionEntityFlags.a();
        }

        public CriterionConditionEntityFlags.a a(@Nullable Boolean obool) {
            this.a = obool;
            return this;
        }

        public CriterionConditionEntityFlags b() {
            return new CriterionConditionEntityFlags(this.a, this.b, this.c, this.d, this.e);
        }
    }
}
