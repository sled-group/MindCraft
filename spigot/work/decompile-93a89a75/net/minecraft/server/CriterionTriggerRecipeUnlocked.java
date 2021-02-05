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

public class CriterionTriggerRecipeUnlocked implements CriterionTrigger<CriterionTriggerRecipeUnlocked.b> {

    private static final MinecraftKey a = new MinecraftKey("recipe_unlocked");
    private final Map<AdvancementDataPlayer, CriterionTriggerRecipeUnlocked.a> b = Maps.newHashMap();

    public CriterionTriggerRecipeUnlocked() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerRecipeUnlocked.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> criteriontrigger_a) {
        CriterionTriggerRecipeUnlocked.a criteriontriggerrecipeunlocked_a = (CriterionTriggerRecipeUnlocked.a) this.b.get(advancementdataplayer);

        if (criteriontriggerrecipeunlocked_a == null) {
            criteriontriggerrecipeunlocked_a = new CriterionTriggerRecipeUnlocked.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerrecipeunlocked_a);
        }

        criteriontriggerrecipeunlocked_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> criteriontrigger_a) {
        CriterionTriggerRecipeUnlocked.a criteriontriggerrecipeunlocked_a = (CriterionTriggerRecipeUnlocked.a) this.b.get(advancementdataplayer);

        if (criteriontriggerrecipeunlocked_a != null) {
            criteriontriggerrecipeunlocked_a.b(criteriontrigger_a);
            if (criteriontriggerrecipeunlocked_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerRecipeUnlocked.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "recipe"));

        return new CriterionTriggerRecipeUnlocked.b(minecraftkey);
    }

    public void a(EntityPlayer entityplayer, IRecipe<?> irecipe) {
        CriterionTriggerRecipeUnlocked.a criteriontriggerrecipeunlocked_a = (CriterionTriggerRecipeUnlocked.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerrecipeunlocked_a != null) {
            criteriontriggerrecipeunlocked_a.a(irecipe);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(IRecipe<?> irecipe) {
            List<CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerRecipeUnlocked.b) criteriontrigger_a.a()).a(irecipe)) {
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

        private final MinecraftKey a;

        public b(MinecraftKey minecraftkey) {
            super(CriterionTriggerRecipeUnlocked.a);
            this.a = minecraftkey;
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("recipe", this.a.toString());
            return jsonobject;
        }

        public boolean a(IRecipe<?> irecipe) {
            return this.a.equals(irecipe.getKey());
        }
    }
}
