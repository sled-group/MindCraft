package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class CriterionConditionDamage {

    public static final CriterionConditionDamage a = CriterionConditionDamage.a.a().b();
    private final CriterionConditionValue.FloatRange b;
    private final CriterionConditionValue.FloatRange c;
    private final CriterionConditionEntity d;
    private final Boolean e;
    private final CriterionConditionDamageSource f;

    public CriterionConditionDamage() {
        this.b = CriterionConditionValue.FloatRange.e;
        this.c = CriterionConditionValue.FloatRange.e;
        this.d = CriterionConditionEntity.a;
        this.e = null;
        this.f = CriterionConditionDamageSource.a;
    }

    public CriterionConditionDamage(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange1, CriterionConditionEntity criterionconditionentity, @Nullable Boolean obool, CriterionConditionDamageSource criterionconditiondamagesource) {
        this.b = criterionconditionvalue_floatrange;
        this.c = criterionconditionvalue_floatrange1;
        this.d = criterionconditionentity;
        this.e = obool;
        this.f = criterionconditiondamagesource;
    }

    public boolean a(EntityPlayer entityplayer, DamageSource damagesource, float f, float f1, boolean flag) {
        return this == CriterionConditionDamage.a ? true : (!this.b.d(f) ? false : (!this.c.d(f1) ? false : (!this.d.a(entityplayer, damagesource.getEntity()) ? false : (this.e != null && this.e != flag ? false : this.f.a(entityplayer, damagesource)))));
    }

    public static CriterionConditionDamage a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "damage");
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange = CriterionConditionValue.FloatRange.a(jsonobject.get("dealt"));
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange1 = CriterionConditionValue.FloatRange.a(jsonobject.get("taken"));
            Boolean obool = jsonobject.has("blocked") ? ChatDeserializer.j(jsonobject, "blocked") : null;
            CriterionConditionEntity criterionconditionentity = CriterionConditionEntity.a(jsonobject.get("source_entity"));
            CriterionConditionDamageSource criterionconditiondamagesource = CriterionConditionDamageSource.a(jsonobject.get("type"));

            return new CriterionConditionDamage(criterionconditionvalue_floatrange, criterionconditionvalue_floatrange1, criterionconditionentity, obool, criterionconditiondamagesource);
        } else {
            return CriterionConditionDamage.a;
        }
    }

    public JsonElement a() {
        if (this == CriterionConditionDamage.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("dealt", this.b.d());
            jsonobject.add("taken", this.c.d());
            jsonobject.add("source_entity", this.d.a());
            jsonobject.add("type", this.f.a());
            if (this.e != null) {
                jsonobject.addProperty("blocked", this.e);
            }

            return jsonobject;
        }
    }

    public static class a {

        private CriterionConditionValue.FloatRange a;
        private CriterionConditionValue.FloatRange b;
        private CriterionConditionEntity c;
        private Boolean d;
        private CriterionConditionDamageSource e;

        public a() {
            this.a = CriterionConditionValue.FloatRange.e;
            this.b = CriterionConditionValue.FloatRange.e;
            this.c = CriterionConditionEntity.a;
            this.e = CriterionConditionDamageSource.a;
        }

        public static CriterionConditionDamage.a a() {
            return new CriterionConditionDamage.a();
        }

        public CriterionConditionDamage.a a(Boolean obool) {
            this.d = obool;
            return this;
        }

        public CriterionConditionDamage.a a(CriterionConditionDamageSource.a criterionconditiondamagesource_a) {
            this.e = criterionconditiondamagesource_a.b();
            return this;
        }

        public CriterionConditionDamage b() {
            return new CriterionConditionDamage(this.a, this.b, this.c, this.d, this.e);
        }
    }
}
