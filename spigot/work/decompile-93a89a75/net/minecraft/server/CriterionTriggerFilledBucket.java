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

public class CriterionTriggerFilledBucket implements CriterionTrigger<CriterionTriggerFilledBucket.b> {

    private static final MinecraftKey a = new MinecraftKey("filled_bucket");
    private final Map<AdvancementDataPlayer, CriterionTriggerFilledBucket.a> b = Maps.newHashMap();

    public CriterionTriggerFilledBucket() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerFilledBucket.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerFilledBucket.b> criteriontrigger_a) {
        CriterionTriggerFilledBucket.a criteriontriggerfilledbucket_a = (CriterionTriggerFilledBucket.a) this.b.get(advancementdataplayer);

        if (criteriontriggerfilledbucket_a == null) {
            criteriontriggerfilledbucket_a = new CriterionTriggerFilledBucket.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerfilledbucket_a);
        }

        criteriontriggerfilledbucket_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerFilledBucket.b> criteriontrigger_a) {
        CriterionTriggerFilledBucket.a criteriontriggerfilledbucket_a = (CriterionTriggerFilledBucket.a) this.b.get(advancementdataplayer);

        if (criteriontriggerfilledbucket_a != null) {
            criteriontriggerfilledbucket_a.b(criteriontrigger_a);
            if (criteriontriggerfilledbucket_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerFilledBucket.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("item"));

        return new CriterionTriggerFilledBucket.b(criterionconditionitem);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        CriterionTriggerFilledBucket.a criteriontriggerfilledbucket_a = (CriterionTriggerFilledBucket.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerfilledbucket_a != null) {
            criteriontriggerfilledbucket_a.a(itemstack);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerFilledBucket.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerFilledBucket.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerFilledBucket.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(ItemStack itemstack) {
            List<CriterionTrigger.a<CriterionTriggerFilledBucket.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerFilledBucket.b) criteriontrigger_a.a()).a(itemstack)) {
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

        private final CriterionConditionItem a;

        public b(CriterionConditionItem criterionconditionitem) {
            super(CriterionTriggerFilledBucket.a);
            this.a = criterionconditionitem;
        }

        public static CriterionTriggerFilledBucket.b a(CriterionConditionItem criterionconditionitem) {
            return new CriterionTriggerFilledBucket.b(criterionconditionitem);
        }

        public boolean a(ItemStack itemstack) {
            return this.a.a(itemstack);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("item", this.a.a());
            return jsonobject;
        }
    }
}
