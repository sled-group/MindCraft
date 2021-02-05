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

public class CriterionTriggerCuredZombieVillager implements CriterionTrigger<CriterionTriggerCuredZombieVillager.b> {

    private static final MinecraftKey a = new MinecraftKey("cured_zombie_villager");
    private final Map<AdvancementDataPlayer, CriterionTriggerCuredZombieVillager.a> b = Maps.newHashMap();

    public CriterionTriggerCuredZombieVillager() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerCuredZombieVillager.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b> criteriontrigger_a) {
        CriterionTriggerCuredZombieVillager.a criteriontriggercuredzombievillager_a = (CriterionTriggerCuredZombieVillager.a) this.b.get(advancementdataplayer);

        if (criteriontriggercuredzombievillager_a == null) {
            criteriontriggercuredzombievillager_a = new CriterionTriggerCuredZombieVillager.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggercuredzombievillager_a);
        }

        criteriontriggercuredzombievillager_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b> criteriontrigger_a) {
        CriterionTriggerCuredZombieVillager.a criteriontriggercuredzombievillager_a = (CriterionTriggerCuredZombieVillager.a) this.b.get(advancementdataplayer);

        if (criteriontriggercuredzombievillager_a != null) {
            criteriontriggercuredzombievillager_a.b(criteriontrigger_a);
            if (criteriontriggercuredzombievillager_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerCuredZombieVillager.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionEntity criterionconditionentity = CriterionConditionEntity.a(jsonobject.get("zombie"));
        CriterionConditionEntity criterionconditionentity1 = CriterionConditionEntity.a(jsonobject.get("villager"));

        return new CriterionTriggerCuredZombieVillager.b(criterionconditionentity, criterionconditionentity1);
    }

    public void a(EntityPlayer entityplayer, EntityZombie entityzombie, EntityVillager entityvillager) {
        CriterionTriggerCuredZombieVillager.a criteriontriggercuredzombievillager_a = (CriterionTriggerCuredZombieVillager.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggercuredzombievillager_a != null) {
            criteriontriggercuredzombievillager_a.a(entityplayer, entityzombie, entityvillager);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, EntityZombie entityzombie, EntityVillager entityvillager) {
            List<CriterionTrigger.a<CriterionTriggerCuredZombieVillager.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerCuredZombieVillager.b) criteriontrigger_a.a()).a(entityplayer, entityzombie, entityvillager)) {
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
        private final CriterionConditionEntity b;

        public b(CriterionConditionEntity criterionconditionentity, CriterionConditionEntity criterionconditionentity1) {
            super(CriterionTriggerCuredZombieVillager.a);
            this.a = criterionconditionentity;
            this.b = criterionconditionentity1;
        }

        public static CriterionTriggerCuredZombieVillager.b c() {
            return new CriterionTriggerCuredZombieVillager.b(CriterionConditionEntity.a, CriterionConditionEntity.a);
        }

        public boolean a(EntityPlayer entityplayer, EntityZombie entityzombie, EntityVillager entityvillager) {
            return !this.a.a(entityplayer, entityzombie) ? false : this.b.a(entityplayer, entityvillager);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("zombie", this.a.a());
            jsonobject.add("villager", this.b.a());
            return jsonobject;
        }
    }
}
