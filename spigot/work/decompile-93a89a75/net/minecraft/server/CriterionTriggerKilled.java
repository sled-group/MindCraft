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

public class CriterionTriggerKilled implements CriterionTrigger<CriterionTriggerKilled.b> {

    private final Map<AdvancementDataPlayer, CriterionTriggerKilled.a> a = Maps.newHashMap();
    private final MinecraftKey b;

    public CriterionTriggerKilled(MinecraftKey minecraftkey) {
        this.b = minecraftkey;
    }

    @Override
    public MinecraftKey a() {
        return this.b;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerKilled.b> criteriontrigger_a) {
        CriterionTriggerKilled.a criteriontriggerkilled_a = (CriterionTriggerKilled.a) this.a.get(advancementdataplayer);

        if (criteriontriggerkilled_a == null) {
            criteriontriggerkilled_a = new CriterionTriggerKilled.a(advancementdataplayer);
            this.a.put(advancementdataplayer, criteriontriggerkilled_a);
        }

        criteriontriggerkilled_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerKilled.b> criteriontrigger_a) {
        CriterionTriggerKilled.a criteriontriggerkilled_a = (CriterionTriggerKilled.a) this.a.get(advancementdataplayer);

        if (criteriontriggerkilled_a != null) {
            criteriontriggerkilled_a.b(criteriontrigger_a);
            if (criteriontriggerkilled_a.a()) {
                this.a.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.a.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerKilled.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        return new CriterionTriggerKilled.b(this.b, CriterionConditionEntity.a(jsonobject.get("entity")), CriterionConditionDamageSource.a(jsonobject.get("killing_blow")));
    }

    public void a(EntityPlayer entityplayer, Entity entity, DamageSource damagesource) {
        CriterionTriggerKilled.a criteriontriggerkilled_a = (CriterionTriggerKilled.a) this.a.get(entityplayer.getAdvancementData());

        if (criteriontriggerkilled_a != null) {
            criteriontriggerkilled_a.a(entityplayer, entity, damagesource);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerKilled.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerKilled.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerKilled.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, Entity entity, DamageSource damagesource) {
            List<CriterionTrigger.a<CriterionTriggerKilled.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerKilled.b) criteriontrigger_a.a()).a(entityplayer, entity, damagesource)) {
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

        private final CriterionConditionEntity a;
        private final CriterionConditionDamageSource b;

        public b(MinecraftKey minecraftkey, CriterionConditionEntity criterionconditionentity, CriterionConditionDamageSource criterionconditiondamagesource) {
            super(minecraftkey);
            this.a = criterionconditionentity;
            this.b = criterionconditiondamagesource;
        }

        public static CriterionTriggerKilled.b a(CriterionConditionEntity.a criterionconditionentity_a) {
            return new CriterionTriggerKilled.b(CriterionTriggers.b.b, criterionconditionentity_a.b(), CriterionConditionDamageSource.a);
        }

        public static CriterionTriggerKilled.b c() {
            return new CriterionTriggerKilled.b(CriterionTriggers.b.b, CriterionConditionEntity.a, CriterionConditionDamageSource.a);
        }

        public static CriterionTriggerKilled.b a(CriterionConditionEntity.a criterionconditionentity_a, CriterionConditionDamageSource.a criterionconditiondamagesource_a) {
            return new CriterionTriggerKilled.b(CriterionTriggers.b.b, criterionconditionentity_a.b(), criterionconditiondamagesource_a.b());
        }

        public static CriterionTriggerKilled.b d() {
            return new CriterionTriggerKilled.b(CriterionTriggers.c.b, CriterionConditionEntity.a, CriterionConditionDamageSource.a);
        }

        public boolean a(EntityPlayer entityplayer, Entity entity, DamageSource damagesource) {
            return !this.b.a(entityplayer, damagesource) ? false : this.a.a(entityplayer, entity);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("entity", this.a.a());
            jsonobject.add("killing_blow", this.b.a());
            return jsonobject;
        }
    }
}
