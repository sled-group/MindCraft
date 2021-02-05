package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriterionTriggerKilledByCrossbow implements CriterionTrigger<CriterionTriggerKilledByCrossbow.b> {

    private static final MinecraftKey a = new MinecraftKey("killed_by_crossbow");
    private final Map<AdvancementDataPlayer, CriterionTriggerKilledByCrossbow.a> b = Maps.newHashMap();

    public CriterionTriggerKilledByCrossbow() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerKilledByCrossbow.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b> criteriontrigger_a) {
        CriterionTriggerKilledByCrossbow.a criteriontriggerkilledbycrossbow_a = (CriterionTriggerKilledByCrossbow.a) this.b.get(advancementdataplayer);

        if (criteriontriggerkilledbycrossbow_a == null) {
            criteriontriggerkilledbycrossbow_a = new CriterionTriggerKilledByCrossbow.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerkilledbycrossbow_a);
        }

        criteriontriggerkilledbycrossbow_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b> criteriontrigger_a) {
        CriterionTriggerKilledByCrossbow.a criteriontriggerkilledbycrossbow_a = (CriterionTriggerKilledByCrossbow.a) this.b.get(advancementdataplayer);

        if (criteriontriggerkilledbycrossbow_a != null) {
            criteriontriggerkilledbycrossbow_a.b(criteriontrigger_a);
            if (criteriontriggerkilledbycrossbow_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerKilledByCrossbow.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionEntity[] acriterionconditionentity = CriterionConditionEntity.b(jsonobject.get("victims"));
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange = CriterionConditionValue.IntegerRange.a(jsonobject.get("unique_entity_types"));

        return new CriterionTriggerKilledByCrossbow.b(acriterionconditionentity, criterionconditionvalue_integerrange);
    }

    public void a(EntityPlayer entityplayer, Collection<Entity> collection, int i) {
        CriterionTriggerKilledByCrossbow.a criteriontriggerkilledbycrossbow_a = (CriterionTriggerKilledByCrossbow.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerkilledbycrossbow_a != null) {
            criteriontriggerkilledbycrossbow_a.a(entityplayer, collection, i);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, Collection<Entity> collection, int i) {
            List<CriterionTrigger.a<CriterionTriggerKilledByCrossbow.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerKilledByCrossbow.b) criteriontrigger_a.a()).a(entityplayer, collection, i)) {
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

        private final CriterionConditionEntity[] a;
        private final CriterionConditionValue.IntegerRange b;

        public b(CriterionConditionEntity[] acriterionconditionentity, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
            super(CriterionTriggerKilledByCrossbow.a);
            this.a = acriterionconditionentity;
            this.b = criterionconditionvalue_integerrange;
        }

        public static CriterionTriggerKilledByCrossbow.b a(CriterionConditionEntity.a... acriterionconditionentity_a) {
            CriterionConditionEntity[] acriterionconditionentity = new CriterionConditionEntity[acriterionconditionentity_a.length];

            for (int i = 0; i < acriterionconditionentity_a.length; ++i) {
                CriterionConditionEntity.a criterionconditionentity_a = acriterionconditionentity_a[i];

                acriterionconditionentity[i] = criterionconditionentity_a.b();
            }

            return new CriterionTriggerKilledByCrossbow.b(acriterionconditionentity, CriterionConditionValue.IntegerRange.e);
        }

        public static CriterionTriggerKilledByCrossbow.b a(CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
            CriterionConditionEntity[] acriterionconditionentity = new CriterionConditionEntity[0];

            return new CriterionTriggerKilledByCrossbow.b(acriterionconditionentity, criterionconditionvalue_integerrange);
        }

        public boolean a(EntityPlayer entityplayer, Collection<Entity> collection, int i) {
            if (this.a.length > 0) {
                List<Entity> list = Lists.newArrayList(collection);
                CriterionConditionEntity[] acriterionconditionentity = this.a;
                int j = acriterionconditionentity.length;

                for (int k = 0; k < j; ++k) {
                    CriterionConditionEntity criterionconditionentity = acriterionconditionentity[k];
                    boolean flag = false;
                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        Entity entity = (Entity) iterator.next();

                        if (criterionconditionentity.a(entityplayer, entity)) {
                            iterator.remove();
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            }

            if (this.b == CriterionConditionValue.IntegerRange.e) {
                return true;
            } else {
                Set<EntityTypes<?>> set = Sets.newHashSet();
                Iterator iterator1 = collection.iterator();

                while (iterator1.hasNext()) {
                    Entity entity1 = (Entity) iterator1.next();

                    set.add(entity1.getEntityType());
                }

                return this.b.d(set.size()) && this.b.d(i);
            }
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("victims", CriterionConditionEntity.a(this.a));
            jsonobject.add("unique_entity_types", this.b.d());
            return jsonobject;
        }
    }
}
