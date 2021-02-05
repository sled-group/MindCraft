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

public class CriterionTriggerEnchantedItem implements CriterionTrigger<CriterionTriggerEnchantedItem.b> {

    private static final MinecraftKey a = new MinecraftKey("enchanted_item");
    private final Map<AdvancementDataPlayer, CriterionTriggerEnchantedItem.a> b = Maps.newHashMap();

    public CriterionTriggerEnchantedItem() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerEnchantedItem.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerEnchantedItem.b> criteriontrigger_a) {
        CriterionTriggerEnchantedItem.a criteriontriggerenchanteditem_a = (CriterionTriggerEnchantedItem.a) this.b.get(advancementdataplayer);

        if (criteriontriggerenchanteditem_a == null) {
            criteriontriggerenchanteditem_a = new CriterionTriggerEnchantedItem.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerenchanteditem_a);
        }

        criteriontriggerenchanteditem_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerEnchantedItem.b> criteriontrigger_a) {
        CriterionTriggerEnchantedItem.a criteriontriggerenchanteditem_a = (CriterionTriggerEnchantedItem.a) this.b.get(advancementdataplayer);

        if (criteriontriggerenchanteditem_a != null) {
            criteriontriggerenchanteditem_a.b(criteriontrigger_a);
            if (criteriontriggerenchanteditem_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerEnchantedItem.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("item"));
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange = CriterionConditionValue.IntegerRange.a(jsonobject.get("levels"));

        return new CriterionTriggerEnchantedItem.b(criterionconditionitem, criterionconditionvalue_integerrange);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack, int i) {
        CriterionTriggerEnchantedItem.a criteriontriggerenchanteditem_a = (CriterionTriggerEnchantedItem.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerenchanteditem_a != null) {
            criteriontriggerenchanteditem_a.a(itemstack, i);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerEnchantedItem.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerEnchantedItem.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerEnchantedItem.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(ItemStack itemstack, int i) {
            List<CriterionTrigger.a<CriterionTriggerEnchantedItem.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerEnchantedItem.b) criteriontrigger_a.a()).a(itemstack, i)) {
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
        private final CriterionConditionValue.IntegerRange b;

        public b(CriterionConditionItem criterionconditionitem, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
            super(CriterionTriggerEnchantedItem.a);
            this.a = criterionconditionitem;
            this.b = criterionconditionvalue_integerrange;
        }

        public static CriterionTriggerEnchantedItem.b c() {
            return new CriterionTriggerEnchantedItem.b(CriterionConditionItem.a, CriterionConditionValue.IntegerRange.e);
        }

        public boolean a(ItemStack itemstack, int i) {
            return !this.a.a(itemstack) ? false : this.b.d(i);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("item", this.a.a());
            jsonobject.add("levels", this.b.d());
            return jsonobject;
        }
    }
}
