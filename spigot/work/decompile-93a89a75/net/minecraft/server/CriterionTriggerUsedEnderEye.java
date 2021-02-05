package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriterionTriggerUsedEnderEye implements CriterionTrigger<CriterionTriggerUsedEnderEye.b> {

    private static final MinecraftKey a = new MinecraftKey("used_ender_eye");
    private final Map<AdvancementDataPlayer, CriterionTriggerUsedEnderEye.a> b = Maps.newHashMap();

    public CriterionTriggerUsedEnderEye() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerUsedEnderEye.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> criteriontrigger_a) {
        CriterionTriggerUsedEnderEye.a criteriontriggerusedendereye_a = (CriterionTriggerUsedEnderEye.a) this.b.get(advancementdataplayer);

        if (criteriontriggerusedendereye_a == null) {
            criteriontriggerusedendereye_a = new CriterionTriggerUsedEnderEye.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerusedendereye_a);
        }

        criteriontriggerusedendereye_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> criteriontrigger_a) {
        CriterionTriggerUsedEnderEye.a criteriontriggerusedendereye_a = (CriterionTriggerUsedEnderEye.a) this.b.get(advancementdataplayer);

        if (criteriontriggerusedendereye_a != null) {
            criteriontriggerusedendereye_a.b(criteriontrigger_a);
            if (criteriontriggerusedendereye_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerUsedEnderEye.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionValue.FloatRange criterionconditionvalue_floatrange = CriterionConditionValue.FloatRange.a(jsonobject.get("distance"));

        return new CriterionTriggerUsedEnderEye.b(criterionconditionvalue_floatrange);
    }

    public void a(EntityPlayer entityplayer, BlockPosition blockposition) {
        CriterionTriggerUsedEnderEye.a criteriontriggerusedendereye_a = (CriterionTriggerUsedEnderEye.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerusedendereye_a != null) {
            double d0 = entityplayer.locX - (double) blockposition.getX();
            double d1 = entityplayer.locZ - (double) blockposition.getZ();

            criteriontriggerusedendereye_a.a(d0 * d0 + d1 * d1);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerUsedEnderEye.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerUsedEnderEye.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(double d0) {
            List<CriterionTrigger.a<CriterionTriggerUsedEnderEye.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerUsedEnderEye.b) criteriontrigger_a.a()).a(d0)) {
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

        private final CriterionConditionValue.FloatRange a;

        public b(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange) {
            super(CriterionTriggerUsedEnderEye.a);
            this.a = criterionconditionvalue_floatrange;
        }

        public boolean a(double d0) {
            return this.a.a(d0);
        }
    }
}
