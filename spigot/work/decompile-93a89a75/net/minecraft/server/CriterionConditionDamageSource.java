package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class CriterionConditionDamageSource {

    public static final CriterionConditionDamageSource a = CriterionConditionDamageSource.a.a().b();
    private final Boolean b;
    private final Boolean c;
    private final Boolean d;
    private final Boolean e;
    private final Boolean f;
    private final Boolean g;
    private final Boolean h;
    private final Boolean i;
    private final CriterionConditionEntity j;
    private final CriterionConditionEntity k;

    public CriterionConditionDamageSource(@Nullable Boolean obool, @Nullable Boolean obool1, @Nullable Boolean obool2, @Nullable Boolean obool3, @Nullable Boolean obool4, @Nullable Boolean obool5, @Nullable Boolean obool6, @Nullable Boolean obool7, CriterionConditionEntity criterionconditionentity, CriterionConditionEntity criterionconditionentity1) {
        this.b = obool;
        this.c = obool1;
        this.d = obool2;
        this.e = obool3;
        this.f = obool4;
        this.g = obool5;
        this.h = obool6;
        this.i = obool7;
        this.j = criterionconditionentity;
        this.k = criterionconditionentity1;
    }

    public boolean a(EntityPlayer entityplayer, DamageSource damagesource) {
        return this.a(entityplayer.getWorldServer(), new Vec3D(entityplayer.locX, entityplayer.locY, entityplayer.locZ), damagesource);
    }

    public boolean a(WorldServer worldserver, Vec3D vec3d, DamageSource damagesource) {
        return this == CriterionConditionDamageSource.a ? true : (this.b != null && this.b != damagesource.b() ? false : (this.c != null && this.c != damagesource.isExplosion() ? false : (this.d != null && this.d != damagesource.ignoresArmor() ? false : (this.e != null && this.e != damagesource.ignoresInvulnerability() ? false : (this.f != null && this.f != damagesource.isStarvation() ? false : (this.g != null && this.g != damagesource.p() ? false : (this.h != null && this.h != damagesource.isMagic() ? false : (this.i != null && this.i != (damagesource == DamageSource.LIGHTNING) ? false : (!this.j.a(worldserver, vec3d, damagesource.j()) ? false : this.k.a(worldserver, vec3d, damagesource.getEntity()))))))))));
    }

    public static CriterionConditionDamageSource a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "damage type");
            Boolean obool = a(jsonobject, "is_projectile");
            Boolean obool1 = a(jsonobject, "is_explosion");
            Boolean obool2 = a(jsonobject, "bypasses_armor");
            Boolean obool3 = a(jsonobject, "bypasses_invulnerability");
            Boolean obool4 = a(jsonobject, "bypasses_magic");
            Boolean obool5 = a(jsonobject, "is_fire");
            Boolean obool6 = a(jsonobject, "is_magic");
            Boolean obool7 = a(jsonobject, "is_lightning");
            CriterionConditionEntity criterionconditionentity = CriterionConditionEntity.a(jsonobject.get("direct_entity"));
            CriterionConditionEntity criterionconditionentity1 = CriterionConditionEntity.a(jsonobject.get("source_entity"));

            return new CriterionConditionDamageSource(obool, obool1, obool2, obool3, obool4, obool5, obool6, obool7, criterionconditionentity, criterionconditionentity1);
        } else {
            return CriterionConditionDamageSource.a;
        }
    }

    @Nullable
    private static Boolean a(JsonObject jsonobject, String s) {
        return jsonobject.has(s) ? ChatDeserializer.j(jsonobject, s) : null;
    }

    public JsonElement a() {
        if (this == CriterionConditionDamageSource.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            this.a(jsonobject, "is_projectile", this.b);
            this.a(jsonobject, "is_explosion", this.c);
            this.a(jsonobject, "bypasses_armor", this.d);
            this.a(jsonobject, "bypasses_invulnerability", this.e);
            this.a(jsonobject, "bypasses_magic", this.f);
            this.a(jsonobject, "is_fire", this.g);
            this.a(jsonobject, "is_magic", this.h);
            this.a(jsonobject, "is_lightning", this.i);
            jsonobject.add("direct_entity", this.j.a());
            jsonobject.add("source_entity", this.k.a());
            return jsonobject;
        }
    }

    private void a(JsonObject jsonobject, String s, @Nullable Boolean obool) {
        if (obool != null) {
            jsonobject.addProperty(s, obool);
        }

    }

    public static class a {

        private Boolean a;
        private Boolean b;
        private Boolean c;
        private Boolean d;
        private Boolean e;
        private Boolean f;
        private Boolean g;
        private Boolean h;
        private CriterionConditionEntity i;
        private CriterionConditionEntity j;

        public a() {
            this.i = CriterionConditionEntity.a;
            this.j = CriterionConditionEntity.a;
        }

        public static CriterionConditionDamageSource.a a() {
            return new CriterionConditionDamageSource.a();
        }

        public CriterionConditionDamageSource.a a(Boolean obool) {
            this.a = obool;
            return this;
        }

        public CriterionConditionDamageSource.a h(Boolean obool) {
            this.h = obool;
            return this;
        }

        public CriterionConditionDamageSource.a a(CriterionConditionEntity.a criterionconditionentity_a) {
            this.i = criterionconditionentity_a.b();
            return this;
        }

        public CriterionConditionDamageSource b() {
            return new CriterionConditionDamageSource(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }
}
