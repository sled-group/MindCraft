package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class CriterionTriggerPlacedBlock implements CriterionTrigger<CriterionTriggerPlacedBlock.b> {

    private static final MinecraftKey a = new MinecraftKey("placed_block");
    private final Map<AdvancementDataPlayer, CriterionTriggerPlacedBlock.a> b = Maps.newHashMap();

    public CriterionTriggerPlacedBlock() {}

    @Override
    public MinecraftKey a() {
        return CriterionTriggerPlacedBlock.a;
    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerPlacedBlock.b> criteriontrigger_a) {
        CriterionTriggerPlacedBlock.a criteriontriggerplacedblock_a = (CriterionTriggerPlacedBlock.a) this.b.get(advancementdataplayer);

        if (criteriontriggerplacedblock_a == null) {
            criteriontriggerplacedblock_a = new CriterionTriggerPlacedBlock.a(advancementdataplayer);
            this.b.put(advancementdataplayer, criteriontriggerplacedblock_a);
        }

        criteriontriggerplacedblock_a.a(criteriontrigger_a);
    }

    @Override
    public void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<CriterionTriggerPlacedBlock.b> criteriontrigger_a) {
        CriterionTriggerPlacedBlock.a criteriontriggerplacedblock_a = (CriterionTriggerPlacedBlock.a) this.b.get(advancementdataplayer);

        if (criteriontriggerplacedblock_a != null) {
            criteriontriggerplacedblock_a.b(criteriontrigger_a);
            if (criteriontriggerplacedblock_a.a()) {
                this.b.remove(advancementdataplayer);
            }
        }

    }

    @Override
    public void a(AdvancementDataPlayer advancementdataplayer) {
        this.b.remove(advancementdataplayer);
    }

    @Override
    public CriterionTriggerPlacedBlock.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        Block block = null;

        if (jsonobject.has("block")) {
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "block"));

            block = (Block) IRegistry.BLOCK.getOptional(minecraftkey).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown block type '" + minecraftkey + "'");
            });
        }

        Map<IBlockState<?>, Object> map = null;

        if (jsonobject.has("state")) {
            if (block == null) {
                throw new JsonSyntaxException("Can't define block state without a specific block type");
            }

            BlockStateList<Block, IBlockData> blockstatelist = block.getStates();

            IBlockState iblockstate;
            Optional optional;

            for (Iterator iterator = ChatDeserializer.t(jsonobject, "state").entrySet().iterator(); iterator.hasNext(); map.put(iblockstate, optional.get())) {
                Entry<String, JsonElement> entry = (Entry) iterator.next();

                iblockstate = blockstatelist.a((String) entry.getKey());
                if (iblockstate == null) {
                    throw new JsonSyntaxException("Unknown block state property '" + (String) entry.getKey() + "' for block '" + IRegistry.BLOCK.getKey(block) + "'");
                }

                String s = ChatDeserializer.a((JsonElement) entry.getValue(), (String) entry.getKey());

                optional = iblockstate.b(s);
                if (!optional.isPresent()) {
                    throw new JsonSyntaxException("Invalid block state value '" + s + "' for property '" + (String) entry.getKey() + "' on block '" + IRegistry.BLOCK.getKey(block) + "'");
                }

                if (map == null) {
                    map = Maps.newHashMap();
                }
            }
        }

        CriterionConditionLocation criterionconditionlocation = CriterionConditionLocation.a(jsonobject.get("location"));
        CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("item"));

        return new CriterionTriggerPlacedBlock.b(block, map, criterionconditionlocation, criterionconditionitem);
    }

    public void a(EntityPlayer entityplayer, BlockPosition blockposition, ItemStack itemstack) {
        IBlockData iblockdata = entityplayer.world.getType(blockposition);
        CriterionTriggerPlacedBlock.a criteriontriggerplacedblock_a = (CriterionTriggerPlacedBlock.a) this.b.get(entityplayer.getAdvancementData());

        if (criteriontriggerplacedblock_a != null) {
            criteriontriggerplacedblock_a.a(iblockdata, blockposition, entityplayer.getWorldServer(), itemstack);
        }

    }

    static class a {

        private final AdvancementDataPlayer a;
        private final Set<CriterionTrigger.a<CriterionTriggerPlacedBlock.b>> b = Sets.newHashSet();

        public a(AdvancementDataPlayer advancementdataplayer) {
            this.a = advancementdataplayer;
        }

        public boolean a() {
            return this.b.isEmpty();
        }

        public void a(CriterionTrigger.a<CriterionTriggerPlacedBlock.b> criteriontrigger_a) {
            this.b.add(criteriontrigger_a);
        }

        public void b(CriterionTrigger.a<CriterionTriggerPlacedBlock.b> criteriontrigger_a) {
            this.b.remove(criteriontrigger_a);
        }

        public void a(IBlockData iblockdata, BlockPosition blockposition, WorldServer worldserver, ItemStack itemstack) {
            List<CriterionTrigger.a<CriterionTriggerPlacedBlock.b>> list = null;
            Iterator iterator = this.b.iterator();

            CriterionTrigger.a criteriontrigger_a;

            while (iterator.hasNext()) {
                criteriontrigger_a = (CriterionTrigger.a) iterator.next();
                if (((CriterionTriggerPlacedBlock.b) criteriontrigger_a.a()).a(iblockdata, blockposition, worldserver, itemstack)) {
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

        private final Block a;
        private final Map<IBlockState<?>, Object> b;
        private final CriterionConditionLocation c;
        private final CriterionConditionItem d;

        public b(@Nullable Block block, @Nullable Map<IBlockState<?>, Object> map, CriterionConditionLocation criterionconditionlocation, CriterionConditionItem criterionconditionitem) {
            super(CriterionTriggerPlacedBlock.a);
            this.a = block;
            this.b = map;
            this.c = criterionconditionlocation;
            this.d = criterionconditionitem;
        }

        public static CriterionTriggerPlacedBlock.b a(Block block) {
            return new CriterionTriggerPlacedBlock.b(block, (Map) null, CriterionConditionLocation.a, CriterionConditionItem.a);
        }

        public boolean a(IBlockData iblockdata, BlockPosition blockposition, WorldServer worldserver, ItemStack itemstack) {
            if (this.a != null && iblockdata.getBlock() != this.a) {
                return false;
            } else {
                if (this.b != null) {
                    Iterator iterator = this.b.entrySet().iterator();

                    while (iterator.hasNext()) {
                        Entry<IBlockState<?>, Object> entry = (Entry) iterator.next();

                        if (iblockdata.get((IBlockState) entry.getKey()) != entry.getValue()) {
                            return false;
                        }
                    }
                }

                return !this.c.a(worldserver, (float) blockposition.getX(), (float) blockposition.getY(), (float) blockposition.getZ()) ? false : this.d.a(itemstack);
            }
        }

        @Override
        public JsonElement b() {
            JsonObject jsonobject = new JsonObject();

            if (this.a != null) {
                jsonobject.addProperty("block", IRegistry.BLOCK.getKey(this.a).toString());
            }

            if (this.b != null) {
                JsonObject jsonobject1 = new JsonObject();
                Iterator iterator = this.b.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<IBlockState<?>, Object> entry = (Entry) iterator.next();

                    jsonobject1.addProperty(((IBlockState) entry.getKey()).a(), SystemUtils.a((IBlockState) entry.getKey(), entry.getValue()));
                }

                jsonobject.add("state", jsonobject1);
            }

            jsonobject.add("location", this.c.a());
            jsonobject.add("item", this.d.a());
            return jsonobject;
        }
    }
}
