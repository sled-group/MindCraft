package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.Iterator;

public class CommandGive {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("give").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).then(((RequiredArgumentBuilder) CommandDispatcher.a("item", (ArgumentType) ArgumentItemStack.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentItemStack.a(commandcontext, "item"), ArgumentEntity.f(commandcontext, "targets"), 1);
        })).then(CommandDispatcher.a("count", (ArgumentType) IntegerArgumentType.integer(1)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentItemStack.a(commandcontext, "item"), ArgumentEntity.f(commandcontext, "targets"), IntegerArgumentType.getInteger(commandcontext, "count"));
        })))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ArgumentPredicateItemStack argumentpredicateitemstack, Collection<EntityPlayer> collection, int i) throws CommandSyntaxException {
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();
            int j = i;

            while (j > 0) {
                int k = Math.min(argumentpredicateitemstack.a().getMaxStackSize(), j);

                j -= k;
                ItemStack itemstack = argumentpredicateitemstack.a(k, false);
                boolean flag = entityplayer.inventory.pickup(itemstack);
                EntityItem entityitem;

                if (flag && itemstack.isEmpty()) {
                    itemstack.setCount(1);
                    entityitem = entityplayer.drop(itemstack, false);
                    if (entityitem != null) {
                        entityitem.u();
                    }

                    entityplayer.world.playSound((EntityHuman) null, entityplayer.locX, entityplayer.locY, entityplayer.locZ, SoundEffects.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((entityplayer.getRandom().nextFloat() - entityplayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    entityplayer.defaultContainer.c();
                } else {
                    entityitem = entityplayer.drop(itemstack, false);
                    if (entityitem != null) {
                        entityitem.o();
                        entityitem.setOwner(entityplayer.getUniqueID());
                    }
                }
            }
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.give.success.single", new Object[]{i, argumentpredicateitemstack.a(i, false).B(), ((EntityPlayer) collection.iterator().next()).getScoreboardDisplayName()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.give.success.single", new Object[]{i, argumentpredicateitemstack.a(i, false).B(), collection.size()}), true);
        }

        return collection.size();
    }
}
