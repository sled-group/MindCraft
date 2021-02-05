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

public class CriterionTriggerShotCrossbow implements CriterionTrigger<CriterionTriggerShotCrossbow.b> {

    private static final MinecraftKey a = new MinecraftKey("shot_crossbow");
    private final Map<AdvancementDataPlayer, CriterionTriggerShotCrossbow.a> b = Maps.newHashMap();

    public CriterionTriggerShotCrossbow() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerShotCrossbow.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerShotCrossbow.b> criteriontrigger_a) {
        CriterionTriggerShotCrossbow.a criteriontriggershotcrossbow_a = (CriterionTriggerShotCrossbow.a) this.b.get(advancementdataplayer);

        if (criteriontriggershotcrossbow_a == null) {
            criteriontriggershotcrossbow_a = new CriterionTriggerShotCrossbow.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggershotcrossbow_a);
        }

        criteriontriggershotcrossbow_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerShotCrossbow.b> criteriontrigger_a) {
        CriterionTriggerShotCrossbow.a criteriontriggershotcrossbow_a = (CriterionTriggerShotCrossbow.a) this.b.get(advancementdataplayer);

        if (criteriontriggershotcrossbow_a != null) {
            criteriontriggershotcrossbow_a.b(criteriontrigger_a);
            if (criteriontriggershotcrossbow_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerShotCrossbow.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("item"));

        return new CriterionTriggerShotCrossbow.b(criterionconditionitem);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        CriterionTriggerShotCrossbow.a criteriontriggershotcrossbow_a = (CriterionTriggerShotCrossbow.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggershotcrossbow_a != null) {
            criteriontriggershotcrossbow_a.a(itemstack);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerShotCrossbow.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerShotCrossbow.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerShotCrossbow.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(ItemStack itemstack) {
            List<CriterionTrigger.a<CriterionTriggerShotCrossbow.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerShotCrossbow.b) criteriontrigger_a.a()).a(itemstack)) {
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
            super(CriterionTriggerShotCrossbow.a);
            this.a = criterionconditionitem;
        }

        public static CriterionTriggerShotCrossbow.b a(IMaterial imaterial) {
            return new CriterionTriggerShotCrossbow.b(CriterionConditionItem.a.a().a(imaterial).b());
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
