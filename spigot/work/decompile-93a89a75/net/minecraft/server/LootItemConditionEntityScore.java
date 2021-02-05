package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LootItemConditionEntityScore implements LootItemCondition {

    private final Map<String, LootValueBounds> a;
    private final LootTableInfo.EntityTarget b;

    private LootItemConditionEntityScore(Map<String, LootValueBounds> map, LootTableInfo.EntityTarget loottableinfo_entitytarget) {
        this.a = ImmutableMap.copyOf(map);
        this.b = loottableinfo_entitytarget;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(this.b.a());
    }

    public boolean test(LootTableInfo loottableinfo) {
        Entity entity = (Entity) loottableinfo.getContextParameter(this.b.a());

        if (entity == null) {
            return false;
        } else {
            Scoreboard scoreboard = entity.world.getScoreboard();
            Iterator iterator = this.a.entrySet().iterator();

            Entry entry;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entry = (Entry) iterator.next();
            } while (this.a(entity, scoreboard, (String) entry.getKey(), (LootValueBounds) entry.getValue()));

            return false;
        }
    }

    protected boolean a(Entity entity, Scoreboard scoreboard, String s, LootValueBounds lootvaluebounds) {
        ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s);

        if (scoreboardobjective == null) {
            return false;
        } else {
            String s1 = entity.getName();

            return !scoreboard.b(s1, scoreboardobjective) ? false : lootvaluebounds.a(scoreboard.getPlayerScoreForObjective(s1, scoreboardobjective).getScore());
        }
    }

    public static class b extends LootItemCondition.b<LootItemConditionEntityScore> {

        protected b() {
            super(new MinecraftKey("entity_scores"), LootItemConditionEntityScore.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionEntityScore lootitemconditionentityscore, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject1 = new JsonObject();
            Iterator iterator = lootitemconditionentityscore.a.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, LootValueBounds> entry = (Entry) iterator.next();

                jsonobject1.add((String) entry.getKey(), jsonserializationcontext.serialize(entry.getValue()));
            }

            jsonobject.add("scores", jsonobject1);
            jsonobject.add("entity", jsonserializationcontext.serialize(lootitemconditionentityscore.b));
        }

        @Override
        public LootItemConditionEntityScore b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            Set<Entry<String, JsonElement>> set = ChatDeserializer.t(jsonobject, "scores").entrySet();
            Map<String, LootValueBounds> map = Maps.newLinkedHashMap();
            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
                Entry<String, JsonElement> entry = (Entry) iterator.next();

                map.put(entry.getKey(), ChatDeserializer.a((JsonElement) entry.getValue(), "score", jsondeserializationcontext, LootValueBounds.class));
            }

            return new LootItemConditionEntityScore(map, (LootTableInfo.EntityTarget) ChatDeserializer.a(jsonobject, "entity", jsondeserializationcontext, LootTableInfo.EntityTarget.class));
        }
    }
}
