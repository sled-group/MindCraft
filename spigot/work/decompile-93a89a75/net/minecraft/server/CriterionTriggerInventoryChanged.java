package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriterionTriggerInventoryChanged implements CriterionTrigger<CriterionTriggerInventoryChanged.b> {

    private static final MinecraftKey a = new MinecraftKey("inventory_changed");
    private final Map<AdvancementDataPlayer, CriterionTriggerInventoryChanged.a> b = Maps.newHashMap();

    public CriterionTriggerInventoryChanged() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerInventoryChanged.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerInventoryChanged.b> criteriontrigger_a) {
        CriterionTriggerInventoryChanged.a criteriontriggerinventorychanged_a = (CriterionTriggerInventoryChanged.a) this.b.get(advancementdataplayer);

        if (criteriontriggerinventorychanged_a == null) {
            criteriontriggerinventorychanged_a = new CriterionTriggerInventoryChanged.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerinventorychanged_a);
        }

        criteriontriggerinventorychanged_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerInventoryChanged.b> criteriontrigger_a) {
        CriterionTriggerInventoryChanged.a criteriontriggerinventorychanged_a = (CriterionTriggerInventoryChanged.a) this.b.get(advancementdataplayer);

        if (criteriontriggerinventorychanged_a != null) {
            criteriontriggerinventorychanged_a.b(criteriontrigger_a);
            if (criteriontriggerinventorychanged_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerInventoryChanged.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        JsonObject jsonobject1 = ChatDeserializer.a(jsonobject, "slots", new JsonObject());
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange = CriterionConditionValue.IntegerRange.a(jsonobject1.get("occupied"));
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange1 = CriterionConditionValue.IntegerRange.a(jsonobject1.get("full"));
        CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange2 = CriterionConditionValue.IntegerRange.a(jsonobject1.get("empty"));
        CriterionConditionItem[] acriterionconditionitem = CriterionConditionItem.b(jsonobject.get("items"));

        return new CriterionTriggerInventoryChanged.b(criterionconditionvalue_integerrange, criterionconditionvalue_integerrange1, criterionconditionvalue_integerrange2, acriterionconditionitem);
    }

    public void a(EntityPlayer entityplayer, PlayerInventory playerinventory) {
        CriterionTriggerInventoryChanged.a criteriontriggerinventorychanged_a = (CriterionTriggerInventoryChanged.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerinventorychanged_a != null) {
            criteriontriggerinventorychanged_a.a(playerinventory);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerInventoryChanged.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerInventoryChanged.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerInventoryChanged.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(PlayerInventory playerinventory) {
            List<CriterionTrigger.a<CriterionTriggerInventoryChanged.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerInventoryChanged.b) criteriontrigger_a.a()).a(playerinventory)) {
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

        private final CriterionConditionValue.IntegerRange a;
        private final CriterionConditionValue.IntegerRange b;
        private final CriterionConditionValue.IntegerRange c;
        private final CriterionConditionItem[] d;

        public b(CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange1, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange2, CriterionConditionItem[] acriterionconditionitem) {
            super(CriterionTriggerInventoryChanged.a);
            this.a = criterionconditionvalue_integerrange;
            this.b = criterionconditionvalue_integerrange1;
            this.c = criterionconditionvalue_integerrange2;
            this.d = acriterionconditionitem;
        }

        public static CriterionTriggerInventoryChanged.b a(CriterionConditionItem... acriterionconditionitem) {
            return new CriterionTriggerInventoryChanged.b(CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, acriterionconditionitem);
        }

        public static CriterionTriggerInventoryChanged.b a(IMaterial... aimaterial) {
            CriterionConditionItem[] acriterionconditionitem = new CriterionConditionItem[aimaterial.length];

            for (int i = 0; i < aimaterial.length; ++i) {
                acriterionconditionitem[i] = new CriterionConditionItem((Tag) null, aimaterial[i].getItem(), CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, new CriterionConditionEnchantments[0], (PotionRegistry) null, CriterionConditionNBT.a);
            }

            return a(acriterionconditionitem);
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            if (!this.a.c() || !this.b.c() || !this.c.c()) {
                JsonObject jsonobject1 = new JsonObject();

                jsonobject1.add("occupied", this.a.d());
                jsonobject1.add("full", this.b.d());
                jsonobject1.add("empty", this.c.d());
                jsonobject.add("slots", jsonobject1);
            }

            if (this.d.length > 0) {
                JsonArray jsonarray = new JsonArray();
                CriterionConditionItem[] acriterionconditionitem = this.d;
                int i = acriterionconditionitem.length;

                for (int j = 0; j < i; ++j) {
                    CriterionConditionItem criterionconditionitem = acriterionconditionitem[j];

                    jsonarray.add(criterionconditionitem.a());
                }

                jsonobject.add("items", jsonarray);
            }

            return jsonobject;
        }

        public boolean a(PlayerInventory playerinventory) {
            int i = 0;
            int j = 0;
            int k = 0;
            List<CriterionConditionItem> list = Lists.newArrayList(this.d);

            for (int l = 0; l < playerinventory.getSize(); ++l) {
                ItemStack itemstack = playerinventory.getItem(l);

                if (itemstack.isEmpty()) {
                    ++j;
                } else {
                    ++k;
                    if (itemstack.getCount() >= itemstack.getMaxStackSize()) {
                        ++i;
                    }

                    Iterator iterator = list.iterator();

                    while (iterator.hasNext()) {
                        CriterionConditionItem criterionconditionitem = (CriterionConditionItem) iterator.next();

                        if (criterionconditionitem.a(itemstack)) {
                            iterator.remove();
                        }
                    }
                }
            }

            if (!this.b.d(i)) {
                return false;
            } else if (!this.c.d(j)) {
                return false;
            } else if (!this.a.d(k)) {
                return false;
            } else if (!list.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
