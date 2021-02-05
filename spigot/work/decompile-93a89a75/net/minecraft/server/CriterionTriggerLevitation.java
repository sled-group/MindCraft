package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriterionTriggerLevitation implements CriterionTrigger<CriterionTriggerLevitation.b> {

    private static final MinecraftKey a = new MinecraftKey("levitation");
    private final Map<AdvancementDataPlayer, CriterionTriggerLevitation.a> b = Maps.newHashMap();

    public CriterionTriggerLevitation() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerLevitation.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerLevitation.b> criteriontrigger_a) {
        CriterionTriggerLevitation.a criteriontriggerlevitation_a = (CriterionTriggerLevitation.a) this.b.get(advancementdataplayer);

        if (criteriontriggerlevitation_a == null) {
            criteriontriggerlevitation_a = new CriterionTriggerLevitation.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerlevitation_a);
        }

        criteriontriggerlevitation_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerLevitation.b> criteriontrigger_a) {
        CriterionTriggerLevitation.a criteriontriggerlevitation_a = (CriterionTriggerLevitation.a) this.b.get(advancementdataplayer);

        if (criteriontriggerlevitation_a != null) {
            criteriontriggerlevitation_a.b(criteriontrigger_a);
            if (criteriontriggerlevitation_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerLevitation.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionDistance criterionconditiondistance = CriterionConditionDistance.a(jsonobject.get("distance"));
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange = CriterionConditionValue.IntegerRange.a(jsonobject.get("duration"));

        return new CriterionTriggerLevitation.b(criterionconditiondistance, criterionconditionvalue_integerrange);
    }

    public void a(EntityPlayer entityplayer, Vec3D vec3d, int i) {
        CriterionTriggerLevitation.a criteriontriggerlevitation_a = (CriterionTriggerLevitation.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerlevitation_a != null) {
            criteriontriggerlevitation_a.a(entityplayer, vec3d, i);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerLevitation.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerLevitation.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerLevitation.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, Vec3D vec3d, int i) {
            List<CriterionTrigger.a<CriterionTriggerLevitation.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerLevitation.b) criteriontrigger_a.a()).a(entityplayer, vec3d, i)) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(criteriontrigger_a);
                }
            }

            if (list != null) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                    criteriontrigger_a.a(this.a);
                }
            }

        }
    }

    public static class b extends CriterionInstanceAbstract {

        private final CriterionConditionDistance a;
        private final CriterionConditionValue.IntegerRange b;

        public b(CriterionConditionDistance criterionconditiondistance, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
            super(CriterionTriggerLevitation.a);
            this.a = criterionconditiondistance;
            this.b = criterionconditionvalue_integerrange;
        }

        public static CriterionTriggerLevitation.b a(CriterionConditionDistance criterionconditiondistance) {
            return new CriterionTriggerLevitation.b(criterionconditiondistance, CriterionConditionValue.IntegerRange.e);
        }

        public boolean a(EntityPlayer entityplayer, Vec3D vec3d, int i) {
            return !this.a.a(vec3d.x, vec3d.y, vec3d.z, entityplayer.locX, entityplayer.locY, entityplayer.locZ) ? false : this.b.d(i);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("distance", this.a.a());
            jsonobject.add("duration", this.b.d());
            return jsonobject;
        }
    }
}
