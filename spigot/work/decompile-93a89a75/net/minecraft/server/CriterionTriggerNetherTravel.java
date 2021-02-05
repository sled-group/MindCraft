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

public class CriterionTriggerNetherTravel implements CriterionTrigger<CriterionTriggerNetherTravel.b> {

    private static final MinecraftKey a = new MinecraftKey("nether_travel");
    private final Map<AdvancementDataPlayer, CriterionTriggerNetherTravel.a> b = Maps.newHashMap();

    public CriterionTriggerNetherTravel() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerNetherTravel.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerNetherTravel.b> criteriontrigger_a) {
        CriterionTriggerNetherTravel.a criteriontriggernethertravel_a = (CriterionTriggerNetherTravel.a) this.b.get(advancementdataplayer);

        if (criteriontriggernethertravel_a == null) {
            criteriontriggernethertravel_a = new CriterionTriggerNetherTravel.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggernethertravel_a);
        }

        criteriontriggernethertravel_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerNetherTravel.b> criteriontrigger_a) {
        CriterionTriggerNetherTravel.a criteriontriggernethertravel_a = (CriterionTriggerNetherTravel.a) this.b.get(advancementdataplayer);

        if (criteriontriggernethertravel_a != null) {
            criteriontriggernethertravel_a.b(criteriontrigger_a);
            if (criteriontriggernethertravel_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerNetherTravel.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        CriterionConditionLocation criterionconditionlocation = CriterionConditionLocation.a(jsonobject.get("entered"));
        CriterionConditionLocation criterionconditionlocation1 = CriterionConditionLocation.a(jsonobject.get("exited"));
        CriterionConditionDistance criterionconditiondistance = CriterionConditionDistance.a(jsonobject.get("distance"));

        return new CriterionTriggerNetherTravel.b(criterionconditionlocation, criterionconditionlocation1, criterionconditiondistance);
    }

    public void a(EntityPlayer entityplayer, Vec3D vec3d) {
        CriterionTriggerNetherTravel.a criteriontriggernethertravel_a = (CriterionTriggerNetherTravel.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggernethertravel_a != null) {
            criteriontriggernethertravel_a.a(entityplayer.getWorldServer(), vec3d, entityplayer.locX, entityplayer.locY, entityplayer.locZ);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerNetherTravel.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerNetherTravel.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerNetherTravel.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(WorldServer worldserver, Vec3D vec3d, double d0, double d1, double d2) {
            List<CriterionTrigger.a<CriterionTriggerNetherTravel.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerNetherTravel.b) criteriontrigger_a.a()).a(worldserver, vec3d, d0, d1, d2)) {
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
        private final CriterionConditionLocation b;
        private final CriterionConditionDistance c;

        public b(CriterionConditionLocation criterionconditionlocation, CriterionConditionLocation criterionconditionlocation1, CriterionConditionDistance criterionconditiondistance) {
            super(CriterionTriggerNetherTravel.a);
            this.a = criterionconditionlocation;
            this.b = criterionconditionlocation1;
            this.c = criterionconditiondistance;
        }

        public static CriterionTriggerNetherTravel.b a(CriterionConditionDistance criterionconditiondistance) {
            return new CriterionTriggerNetherTravel.b(CriterionConditionLocation.a, CriterionConditionLocation.a, criterionconditiondistance);
        }

        public boolean a(WorldServer worldserver, Vec3D vec3d, double d0, double d1, double d2) {
            return !this.a.a(worldserver, vec3d.x, vec3d.y, vec3d.z) ? false : (!this.b.a(worldserver, d0, d1, d2) ? false : this.c.a(vec3d.x, vec3d.y, vec3d.z, d0, d1, d2));
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.add("entered", this.a.a());
            jsonobject.add("exited", this.b.a());
            jsonobject.add("distance", this.c.a());
            return jsonobject;
        }
    }
}
