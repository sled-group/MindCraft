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

public class CriterionTriggerLocation implements CriterionTrigger<CriterionTriggerLocation.b> {

    private final MinecraftKey a;
    private final Map<AdvancementDataPlayer, CriterionTriggerLocation.a> b = Maps.newHashMap();

    public CriterionTriggerLocation(MinecraftKey minecraftkey) {
        this.a = minecraftkey;
    }

    @Override
    public MinecraftKey a() {
        return this.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerLocation.b> criteriontrigger_a) {
        CriterionTriggerLocation.a criteriontriggerlocation_a = (CriterionTriggerLocation.a) this.b.get(advancementdataplayer);

        if (criteriontriggerlocation_a == null) {
            criteriontriggerlocation_a = new CriterionTriggerLocation.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerlocation_a);
        }

        criteriontriggerlocation_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerLocation.b> criteriontrigger_a) {
        CriterionTriggerLocation.a criteriontriggerlocation_a = (CriterionTriggerLocation.a) this.b.get(advancementdataplayer);

        if (criteriontriggerlocation_a != null) {
            criteriontriggerlocation_a.b(criteriontrigger_a);
            if (criteriontriggerlocation_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerLocation.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionLocation criterionconditionlocation = CriterionConditionLocation.a((JsonElement) jsonobject);

        return new CriterionTriggerLocation.b(this.a, criterionconditionlocation);
    }

    public void a(EntityPlayer entityplayer) {
        CriterionTriggerLocation.a criteriontriggerlocation_a = (CriterionTriggerLocation.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerlocation_a != null) {
            criteriontriggerlocation_a.a(entityplayer.getWorldServer(), entityplayer.locX, entityplayer.locY, entityplayer.locZ);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerLocation.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerLocation.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerLocation.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(WorldServer worldserver, double d0, double d1, double d2) {
            List<CriterionTrigger.a<CriterionTriggerLocation.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerLocation.b) criteriontrigger_a.a()).a(worldserver, d0, d1, d2)) {
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

        private final CriterionConditionLocation a;

        public b(MinecraftKey minecraftkey, CriterionConditionLocation criterionconditionlocation) {
            super(minecraftkey);
            this.a = criterionconditionlocation;
        }

        public static CriterionTriggerLocation.b a(CriterionConditionLocation criterionconditionlocation) {
            return new CriterionTriggerLocation.b(CriterionTriggers.p.a, criterionconditionlocation);
        }

        public static CriterionTriggerLocation.b c() {
            return new CriterionTriggerLocation.b(CriterionTriggers.q.a, CriterionConditionLocation.a);
        }

        public static CriterionTriggerLocation.b d() {
            return new CriterionTriggerLocation.b(CriterionTriggers.H.a, CriterionConditionLocation.a);
        }

        public boolean a(WorldServer worldserver, double d0, double d1, double d2) {
            return this.a.a(worldserver, d0, d1, d2);
        }

        @Override
        public JsonElement b() {
            return this.a.a();
        }
    }
}
