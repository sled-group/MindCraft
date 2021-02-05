package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class CriterionConditionDistance {

    public static final CriterionConditionDistance a = new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e);
    private final CriterionConditionValue.FloatRange b;
    private final CriterionConditionValue.FloatRange c;
    private final CriterionConditionValue.FloatRange d;
    private final CriterionConditionValue.FloatRange e;
    private final CriterionConditionValue.FloatRange f;

    public CriterionConditionDistance(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange1, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange2, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange3, CriterionConditionValue.FloatRange criterionconditionvalue_floatrange4) {
        this.b = criterionconditionvalue_floatrange;
        this.c = criterionconditionvalue_floatrange1;
        this.d = criterionconditionvalue_floatrange2;
        this.e = criterionconditionvalue_floatrange3;
        this.f = criterionconditionvalue_floatrange4;
    }

    public static CriterionConditionDistance a(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange) {
        return new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, criterionconditionvalue_floatrange, CriterionConditionValue.FloatRange.e);
    }

    public static CriterionConditionDistance b(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange) {
        return new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, criterionconditionvalue_floatrange, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e);
    }

    public boolean a(double d0, double d1, double d2, double d3, double d4, double d5) {
        float f = (float) (d0 - d3);
        float f1 = (float) (d1 - d4);
        float f2 = (float) (d2 - d5);

        return this.b.d(MathHelper.e(f)) && this.c.d(MathHelper.e(f1)) && this.d.d(MathHelper.e(f2)) ? (!this.e.a((double) (f * f + f2 * f2)) ? false : this.f.a((double) (f * f + f1 * f1 + f2 * f2))) : false;
    }

    public static CriterionConditionDistance a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "distance");
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange = CriterionConditionValue.FloatRange.a(jsonobject.get("x"));
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange1 = CriterionConditionValue.FloatRange.a(jsonobject.get("y"));
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange2 = CriterionConditionValue.FloatRange.a(jsonobject.get("z"));
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange3 = CriterionConditionValue.FloatRange.a(jsonobject.get("horizontal"));
            CriterionConditionValue.FloatRange criterionconditionvalue_floatrange4 = CriterionConditionValue.FloatRange.a(jsonobject.get("absolute"));

            return new CriterionConditionDistance(criterionconditionvalue_floatrange, criterionconditionvalue_floatrange1, criterionconditionvalue_floatrange2, criterionconditionvalue_floatrange3, criterionconditionvalue_floatrange4);
        } else {
            return CriterionConditionDistance.a;
        }
    }

    public JsonElement a() {
        if (this == CriterionConditionDistance.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("x", this.b.d());
            jsonobject.add("y", this.c.d());
            jsonobject.add("z", this.d.d());
            jsonobject.add("horizontal", this.e.d());
            jsonobject.add("absolute", this.f.d());
            return jsonobject;
        }
    }
}
