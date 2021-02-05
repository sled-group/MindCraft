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

public class CriterionTriggerChanneledLightning implements CriterionTrigger<CriterionTriggerChanneledLightning.b> {

    private static final MinecraftKey a = new MinecraftKey("channeled_lightning");
    private final Map<AdvancementDataPlayer, CriterionTriggerChanneledLightning.a> b = Maps.newHashMap();

    public CriterionTriggerChanneledLightning() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerChanneledLightning.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerChanneledLightning.b> criteriontrigger_a) {
        CriterionTriggerChanneledLightning.a criteriontriggerchanneledlightning_a = (CriterionTriggerChanneledLightning.a) this.b.get(advancementdataplayer);

        if (criteriontriggerchanneledlightning_a == null) {
            criteriontriggerchanneledlightning_a = new CriterionTriggerChanneledLightning.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerchanneledlightning_a);
        }

        criteriontriggerchanneledlightning_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerChanneledLightning.b> criteriontrigger_a) {
        CriterionTriggerChanneledLightning.a criteriontriggerchanneledlightning_a = (CriterionTriggerChanneledLightning.a) this.b.get(advancementdataplayer);

        if (criteriontriggerchanneledlightning_a != null) {
            criteriontriggerchanneledlightning_a.b(criteriontrigger_a);
            if (criteriontriggerchanneledlightning_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerChanneledLightning.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionEntity[] acriterionconditionentity = CriterionConditionEntity.b(jsonobject.get("victims"));

        return new CriterionTriggerChanneledLightning.b(acriterionconditionentity);
    }

    public void a(EntityPlayer entityplayer, Collection<? extends Entity> collection) {
        CriterionTriggerChanneledLightning.a criteriontriggerchanneledlightning_a = (CriterionTriggerChanneledLightning.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerchanneledlightning_a != null) {
            criteriontriggerchanneledlightning_a.a(entityplayer, collection);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerChanneledLightning.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerChanneledLightning.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerChanneledLightning.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, Collection<? extends Entity> collection) {
            List<CriterionTrigger.a<CriterionTriggerChanneledLightning.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerChanneledLightning.b) criteriontrigger_a.a()).a(entityplayer, collection)) {
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

        public b(CriterionConditionEntity[] acriterionconditionentity) {
            super(CriterionTriggerChanneledLightning.a);
            this.a = acriterionconditionentity;
        }

        public static CriterionTriggerChanneledLightning.b a(CriterionConditionEntity... acriterionconditionentity) {
            return new CriterionTriggerChanneledLightning.b(acriterionconditionentity);
        }

        public boolean a(EntityPlayer entityplayer, Collection<? extends Entity> collection) {
            CriterionConditionEntity[] acriterionconditionentity = this.a;
            int i = acriterionconditionentity.length;
            int j = 0;

            while (j < i) {
                CriterionConditionEntity criterionconditionentity = acriterionconditionentity[j];
                boolean flag = false;
                Iterator iterator = collection.iterator();

                while (true) {
                    if (iterator.hasNext()) {
                        Entity entity = (Entity) iterator.next();

                        if (!criterionconditionentity.a(entityplayer, entity)) {
                            continue;
                        }

                        flag = true;
                    }

                    if (!flag) {
                        return false;
                    }

                    ++j;
                    break;
                }
            }

            return true;
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("victims", CriterionConditionEntity.a(this.a));
            return jsonobject;
        }
    }
}
