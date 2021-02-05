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

public class CriterionTriggerFishingRodHooked implements CriterionTrigger<CriterionTriggerFishingRodHooked.b> {

    private static final MinecraftKey a = new MinecraftKey("fishing_rod_hooked");
    private final Map<AdvancementDataPlayer, CriterionTriggerFishingRodHooked.a> b = Maps.newHashMap();

    public CriterionTriggerFishingRodHooked() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerFishingRodHooked.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerFishingRodHooked.b> criteriontrigger_a) {
        CriterionTriggerFishingRodHooked.a criteriontriggerfishingrodhooked_a = (CriterionTriggerFishingRodHooked.a) this.b.get(advancementdataplayer);

        if (criteriontriggerfishingrodhooked_a == null) {
            criteriontriggerfishingrodhooked_a = new CriterionTriggerFishingRodHooked.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerfishingrodhooked_a);
        }

        criteriontriggerfishingrodhooked_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerFishingRodHooked.b> criteriontrigger_a) {
        CriterionTriggerFishingRodHooked.a criteriontriggerfishingrodhooked_a = (CriterionTriggerFishingRodHooked.a) this.b.get(advancementdataplayer);

        if (criteriontriggerfishingrodhooked_a != null) {
            criteriontriggerfishingrodhooked_a.b(criteriontrigger_a);
            if (criteriontriggerfishingrodhooked_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerFishingRodHooked.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("rod"));
        CriterionConditionEntity criterionconditionentity = CriterionConditionEntity.a(jsonobject.get("entity"));
        CriterionConditionItem criterionconditionitem1 = CriterionConditionItem.a(jsonobject.get("item"));

        return new CriterionTriggerFishingRodHooked.b(criterionconditionitem, criterionconditionentity, criterionconditionitem1);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack, EntityFishingHook entityfishinghook, Collection<ItemStack> collection) {
        CriterionTriggerFishingRodHooked.a criteriontriggerfishingrodhooked_a = (CriterionTriggerFishingRodHooked.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerfishingrodhooked_a != null) {
            criteriontriggerfishingrodhooked_a.a(entityplayer, itemstack, entityfishinghook, collection);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerFishingRodHooked.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerFishingRodHooked.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerFishingRodHooked.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(EntityPlayer entityplayer, ItemStack itemstack, EntityFishingHook entityfishinghook, Collection<ItemStack> collection) {
            List<CriterionTrigger.a<CriterionTriggerFishingRodHooked.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerFishingRodHooked.b) criteriontrigger_a.a()).a(entityplayer, itemstack, entityfishinghook, collection)) {
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
        private final CriterionConditionEntity b;
        private final CriterionConditionItem c;

        public b(CriterionConditionItem criterionconditionitem, CriterionConditionEntity criterionconditionentity, CriterionConditionItem criterionconditionitem1) {
            super(CriterionTriggerFishingRodHooked.a);
            this.a = criterionconditionitem;
            this.b = criterionconditionentity;
            this.c = criterionconditionitem1;
        }

        public static CriterionTriggerFishingRodHooked.b a(CriterionConditionItem criterionconditionitem, CriterionConditionEntity criterionconditionentity, CriterionConditionItem criterionconditionitem1) {
            return new CriterionTriggerFishingRodHooked.b(criterionconditionitem, criterionconditionentity, criterionconditionitem1);
        }

        public boolean a(EntityPlayer entityplayer, ItemStack itemstack, EntityFishingHook entityfishinghook, Collection<ItemStack> collection) {
            if (!this.a.a(itemstack)) {
                return false;
            } else if (!this.b.a(entityplayer, entityfishinghook.hooked)) {
                return false;
            } else {
                if (this.c != CriterionConditionItem.a) {
                    boolean flag = false;

                    if (entityfishinghook.hooked instanceof EntityItem) {
                        EntityItem entityitem = (EntityItem) entityfishinghook.hooked;

                        if (this.c.a(entityitem.getItemStack())) {
                            flag = true;
                        }
                    }

                    Iterator iterator = collection.iterator();

                    while (iterator.hasNext()) {
                        ItemStack itemstack1 = (ItemStack) iterator.next();

                        if (this.c.a(itemstack1)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }

                return true;
            }
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("rod", this.a.a());
            jsonobject.add("entity", this.b.a());
            jsonobject.add("item", this.c.a());
            return jsonobject;
        }
    }
}
