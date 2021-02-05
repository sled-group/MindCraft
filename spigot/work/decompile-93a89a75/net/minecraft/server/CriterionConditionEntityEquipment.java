package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class CriterionConditionEntityEquipment {

    public static final CriterionConditionEntityEquipment a = new CriterionConditionEntityEquipment(CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a);
    public static final CriterionConditionEntityEquipment b = new CriterionConditionEntityEquipment(CriterionConditionItem.a.a().a((IMaterial) Items.WHITE_BANNER).a(Raid.s().getTag()).b(), CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a);
    private final CriterionConditionItem c;
    private final CriterionConditionItem d;
    private final CriterionConditionItem e;
    private final CriterionConditionItem f;
    private final CriterionConditionItem g;
    private final CriterionConditionItem h;

    public CriterionConditionEntityEquipment(CriterionConditionItem criterionconditionitem, CriterionConditionItem criterionconditionitem1, CriterionConditionItem criterionconditionitem2, CriterionConditionItem criterionconditionitem3, CriterionConditionItem criterionconditionitem4, CriterionConditionItem criterionconditionitem5) {
        this.c = criterionconditionitem;
        this.d = criterionconditionitem1;
        this.e = criterionconditionitem2;
        this.f = criterionconditionitem3;
        this.g = criterionconditionitem4;
        this.h = criterionconditionitem5;
    }

    public boolean a(@Nullable Entity entity) {
        if (this == CriterionConditionEntityEquipment.a) {
            return true;
        } else if (!(entity instanceof EntityLiving)) {
            return false;
        } else {
            EntityLiving entityliving = (EntityLiving) entity;

            return !this.c.a(entityliving.getEquipment(EnumItemSlot.HEAD)) ? false : (!this.d.a(entityliving.getEquipment(EnumItemSlot.CHEST)) ? false : (!this.e.a(entityliving.getEquipment(EnumItemSlot.LEGS)) ? false : (!this.f.a(entityliving.getEquipment(EnumItemSlot.FEET)) ? false : (!this.g.a(entityliving.getEquipment(EnumItemSlot.MAINHAND)) ? false : this.h.a(entityliving.getEquipment(EnumItemSlot.OFFHAND))))));
        }
    }

    public static CriterionConditionEntityEquipment a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "equipment");
            CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("head"));
            CriterionConditionItem criterionconditionitem1 = CriterionConditionItem.a(jsonobject.get("chest"));
            CriterionConditionItem criterionconditionitem2 = CriterionConditionItem.a(jsonobject.get("legs"));
            CriterionConditionItem criterionconditionitem3 = CriterionConditionItem.a(jsonobject.get("feet"));
            CriterionConditionItem criterionconditionitem4 = CriterionConditionItem.a(jsonobject.get("mainhand"));
            CriterionConditionItem criterionconditionitem5 = CriterionConditionItem.a(jsonobject.get("offhand"));

            return new CriterionConditionEntityEquipment(criterionconditionitem, criterionconditionitem1, criterionconditionitem2, criterionconditionitem3, criterionconditionitem4, criterionconditionitem5);
        } else {
            return CriterionConditionEntityEquipment.a;
        }
    }

    public JsonElement a() {
        if (this == CriterionConditionEntityEquipment.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("head", this.c.a());
            jsonobject.add("chest", this.d.a());
            jsonobject.add("legs", this.e.a());
            jsonobject.add("feet", this.f.a());
            jsonobject.add("mainhand", this.g.a());
            jsonobject.add("offhand", this.h.a());
            return jsonobject;
        }
    }
}
