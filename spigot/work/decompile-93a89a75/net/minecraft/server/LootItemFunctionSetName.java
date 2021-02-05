package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Set;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootItemFunctionSetName extends LootItemFunctionConditional {

    private static final Logger LOGGER = LogManager.getLogger();
    private final IChatBaseComponent c;
    @Nullable
    private final LootTableInfo.EntityTarget d;

    private LootItemFunctionSetName(LootItemCondition[] alootitemcondition, @Nullable IChatBaseComponent ichatbasecomponent, @Nullable LootTableInfo.EntityTarget loottableinfo_entitytarget) {
        super(alootitemcondition);
        this.c = ichatbasecomponent;
        this.d = loottableinfo_entitytarget;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return this.d != null ? ImmutableSet.of(this.d.a()) : ImmutableSet.of();
    }

    public static UnaryOperator<IChatBaseComponent> a(LootTableInfo loottableinfo, @Nullable LootTableInfo.EntityTarget loottableinfo_entitytarget) {
        if (loottableinfo_entitytarget != null) {
            Entity entity = (Entity) loottableinfo.getContextParameter(loottableinfo_entitytarget.a());

            if (entity != null) {
                CommandListenerWrapper commandlistenerwrapper = entity.getCommandListener().a(2);

                return (ichatbasecomponent) -> {
                    try {
                        return ChatComponentUtils.filterForDisplay(commandlistenerwrapper, ichatbasecomponent, entity, 0);
                    } catch (CommandSyntaxException commandsyntaxexception) {
                        LootItemFunctionSetName.LOGGER.warn("Failed to resolve text component", commandsyntaxexception);
                        return ichatbasecomponent;
                    }
                };
            }
        }

        return (ichatbasecomponent) -> {
            return ichatbasecomponent;
        };
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (this.c != null) {
            itemstack.a((IChatBaseComponent) a(loottableinfo, this.d).apply(this.c));
        }

        return itemstack;
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionSetName> {

        public a() {
            super(new MinecraftKey("set_name"), LootItemFunctionSetName.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetName lootitemfunctionsetname, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetname, jsonserializationcontext);
            if (lootitemfunctionsetname.c != null) {
                jsonobject.add("name", IChatBaseComponent.ChatSerializer.b(lootitemfunctionsetname.c));
            }

            if (lootitemfunctionsetname.d != null) {
                jsonobject.add("entity", jsonserializationcontext.serialize(lootitemfunctionsetname.d));
            }

        }

        @Override
        public LootItemFunctionSetName b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            IChatBaseComponent ichatbasecomponent = IChatBaseComponent.ChatSerializer.a(jsonobject.get("name"));
            LootTableInfo.EntityTarget loottableinfo_entitytarget = (LootTableInfo.EntityTarget) ChatDeserializer.a(jsonobject, "entity", (Object) null, jsondeserializationcontext, LootTableInfo.EntityTarget.class);

            return new LootItemFunctionSetName(alootitemcondition, ichatbasecomponent, loottableinfo_entitytarget);
        }
    }
}
