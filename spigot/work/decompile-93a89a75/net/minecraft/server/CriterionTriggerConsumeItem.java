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

public class CriterionTriggerConsumeItem implements CriterionTrigger<CriterionTriggerConsumeItem.b> {

    private static final MinecraftKey a = new MinecraftKey("consume_item");
    private final Map<AdvancementDataPlayer, CriterionTriggerConsumeItem.a> b = Maps.newHashMap();

    public CriterionTriggerConsumeItem() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerConsumeItem.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerConsumeItem.b> criteriontrigger_a) {
        CriterionTriggerConsumeItem.a criteriontriggerconsumeitem_a = (CriterionTriggerConsumeItem.a) this.b.get(advancementdataplayer);

        if (criteriontriggerconsumeitem_a == null) {
            criteriontriggerconsumeitem_a = new CriterionTriggerConsumeItem.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerconsumeitem_a);
        }

        criteriontriggerconsumeitem_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerConsumeItem.b> criteriontrigger_a) {
        CriterionTriggerConsumeItem.a criteriontriggerconsumeitem_a = (CriterionTriggerConsumeItem.a) this.b.get(advancementdataplayer);

        if (criteriontriggerconsumeitem_a != null) {
            criteriontriggerconsumeitem_a.b(criteriontrigger_a);
            if (criteriontriggerconsumeitem_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerConsumeItem.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        return new CriterionTriggerConsumeItem.b(CriterionConditionItem.a(jsonobject.get("item")));
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        CriterionTriggerConsumeItem.a criteriontriggerconsumeitem_a = (CriterionTriggerConsumeItem.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerconsumeitem_a != null) {
            criteriontriggerconsumeitem_a.a(itemstack);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerConsumeItem.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerConsumeItem.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerConsumeItem.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(ItemStack itemstack) {
            List<CriterionTrigger.a<CriterionTriggerConsumeItem.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerConsumeItem.b) criteriontrigger_a.a()).a(itemstack)) {
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
            super(CriterionTriggerConsumeItem.a);
            this.a = criterionconditionitem;
        }

        public static CriterionTriggerConsumeItem.b c() {
            return new CriterionTriggerConsumeItem.b(CriterionConditionItem.a);
        }

        public static CriterionTriggerConsumeItem.b a(IMaterial imaterial) {
            return new CriterionTriggerConsumeItem.b(new CriterionConditionItem((Tag) null, imaterial.getItem(), CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, new CriterionConditionEnchantments[0], (PotionRegistry) null, CriterionConditionNBT.a));
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
