package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CriterionTriggerTick implements CriterionTrigger<CriterionTriggerTick.b> {

    public static final MinecraftKey a = new MinecraftKey("tick");
    private final Map<AdvancementDataPlayer, CriterionTriggerTick.a> b = Maps.newHashMap();

    public CriterionTriggerTick() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerTick.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerTick.b> criteriontrigger_a) {
        CriterionTriggerTick.a criteriontriggertick_a = (CriterionTriggerTick.a) this.b.get(advancementdataplayer);

        if (criteriontriggertick_a == null) {
            criteriontriggertick_a = new CriterionTriggerTick.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggertick_a);
        }

        criteriontriggertick_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerTick.b> criteriontrigger_a) {
        CriterionTriggerTick.a criteriontriggertick_a = (CriterionTriggerTick.a) this.b.get(advancementdataplayer);

        if (criteriontriggertick_a != null) {
            criteriontriggertick_a.b(criteriontrigger_a);
            if (criteriontriggertick_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerTick.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        return new CriterionTriggerTick.b();
    }

    public void a(EntityPlayer entityplayer) {
        CriterionTriggerTick.a criteriontriggertick_a = (CriterionTriggerTick.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggertick_a != null) {
            criteriontriggertick_a.b();
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerTick.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerTick.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerTick.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void b() {
            Iterator iterator = Lists.newArrayList(this.b).iterator();

            while (iterator.hasNext()) {
                CriterionTrigger.a<CriterionTriggerTick.b> criteriontrigger_a = (CriterionTrigger.a) iterator.next();

                criteriontrigger_a.a(this.a);
            }

        }
    }

    public static class b extends CriterionInstanceAbstract {

        public b() {
            super(CriterionTriggerTick.a);
        }
    }
}
