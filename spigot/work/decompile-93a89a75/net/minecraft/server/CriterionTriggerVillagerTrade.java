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

public class CriterionTriggerVillagerTrade implements CriterionTrigger<CriterionTriggerVillagerTrade.b> {

    private static final MinecraftKey a = new MinecraftKey("villager_trade");
    private final Map<AdvancementDataPlayer, CriterionTriggerVillagerTrade.a> b = Maps.newHashMap();

    public CriterionTriggerVillagerTrade() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerVillagerTrade.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerVillagerTrade.b> criteriontrigger_a) {
        CriterionTriggerVillagerTrade.a criteriontriggervillagertrade_a = (CriterionTriggerVillagerTrade.a) this.b.get(advancementdataplayer);

        if (criteriontriggervillagertrade_a == null) {
            criteriontriggervillagertrade_a = new CriterionTriggerVillagerTrade.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggervillagertrade_a);
        }

        criteriontriggervillagertrade_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerVillagerTrade.b> criteriontrigger_a) {
        CriterionTriggerVillagerTrade.a criteriontriggervillagertrade_a = (CriterionTriggerVillagerTrade.a) this.b.get(advancementdataplayer);

        if (criteriontriggervillagertrade_a != null) {
            criteriontriggervillagertrade_a.b(criteriontrigger_a);
            if (criteriontriggervillagertrade_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerVillagerTrade.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionEntity criterionconditionentity = CriterionConditionEntity.a(jsonobject.get("villager"));
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("item"));

        return new CriterionTriggerVillagerTrade.b(criterionconditionentity, criterionconditionitem);
    }

    public void a(EntityPlayer entityplayer, EntityVillagerAbstract entityvillagerabstract, ItemStack itemstack) {
        CriterionTriggerVillagerTrade.a criteriontriggervillagertrade_a = (CriterionTriggerVillagerTrade.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggervillagertrade_a != null) {
            criteriontriggervillagertrade_a.a(entityplayer, entityvillagerabstract, itemstack);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerVillagerTrade.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerVillagerTrade.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerVillagerTrade.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, EntityVillagerAbstract entityvillagerabstract, ItemStack itemstack) {
            List<CriterionTrigger.a<CriterionTriggerVillagerTrade.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerVillagerTrade.b) criteriontrigger_a.a()).a(entityplayer, entityvillagerabstract, itemstack)) {
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
        private final CriterionConditionItem b;

        public b(CriterionConditionEntity criterionconditionentity, CriterionConditionItem criterionconditionitem) {
            super(CriterionTriggerVillagerTrade.a);
            this.a = criterionconditionentity;
            this.b = criterionconditionitem;
        }

        public static CriterionTriggerVillagerTrade.b c() {
            return new CriterionTriggerVillagerTrade.b(CriterionConditionEntity.a, CriterionConditionItem.a);
        }

        public boolean a(EntityPlayer entityplayer, EntityVillagerAbstract entityvillagerabstract, ItemStack itemstack) {
            return !this.a.a(entityplayer, entityvillagerabstract) ? false : this.b.a(itemstack);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("item", this.b.a());
            jsonobject.add("villager", this.a.a());
            return jsonobject;
        }
    }
}
