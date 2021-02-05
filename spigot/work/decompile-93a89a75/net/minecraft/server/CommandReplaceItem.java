package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandReplaceItem {

    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.replaceitem.block.failed", new Object[0]));
    public static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.replaceitem.slot.inapplicable", new Object[]{object});
    });
    public static final Dynamic2CommandExceptionType c = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("commands.replaceitem.entity.failed", new Object[]{object, object1});
    });

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("replaceitem").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("block").then(CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).then(CommandDispatcher.a("slot", (ArgumentType) ArgumentInventorySlot.a()).then(((RequiredArgumentBuilder) CommandDispatcher.a("item", (ArgumentType) ArgumentItemStack.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentInventorySlot.a(commandcontext, "slot"), ArgumentItemStack.a(commandcontext, "item").a(1, false));
        })).then(CommandDispatcher.a("count", (ArgumentType) IntegerArgumentType.integer(1, 64)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentInventorySlot.a(commandcontext, "slot"), ArgumentItemStack.a(commandcontext, "item").a(IntegerArgumentType.getInteger(commandcontext, "count"), true));
        }))))))).then(CommandDispatcher.a("entity").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).then(CommandDispatcher.a("slot", (ArgumentType) ArgumentInventorySlot.a()).then(((RequiredArgumentBuilder) CommandDispatcher.a("item", (ArgumentType) ArgumentItemStack.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentInventorySlot.a(commandcontext, "slot"), ArgumentItemStack.a(commandcontext, "item").a(1, false));
        })).then(CommandDispatcher.a("count", (ArgumentType) IntegerArgumentType.integer(1, 64)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentInventorySlot.a(commandcontext, "slot"), ArgumentItemStack.a(commandcontext, "item").a(IntegerArgumentType.getInteger(commandcontext, "count"), true));
        })))))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition, int i, ItemStack itemstack) throws CommandSyntaxException {
        TileEntity tileentity = commandlistenerwrapper.getWorld().getTileEntity(blockposition);

        if (!(tileentity instanceof IInventory)) {
            throw CommandReplaceItem.a.create();
        } else {
            IInventory iinventory = (IInventory) tileentity;

            if (i >= 0 && i < iinventory.getSize()) {
                iinventory.setItem(i, itemstack);
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.replaceitem.block.success", new Object[]{blockposition.getX(), blockposition.getY(), blockposition.getZ(), itemstack.B()}), true);
                return 1;
            } else {
                throw CommandReplaceItem.b.create(i);
            }
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, int i, ItemStack itemstack) throws CommandSyntaxException {
        List<Entity> list = Lists.newArrayListWithCapacity(collection.size());
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).defaultContainer.c();
            }

            if (entity.a_(i, itemstack.cloneItemStack())) {
                list.add(entity);
                if (entity instanceof EntityPlayer) {
                    ((EntityPlayer) entity).defaultContainer.c();
                }
            }
        }

        if (list.isEmpty()) {
            throw CommandReplaceItem.c.create(itemstack.B(), i);
        } else {
            if (list.size() == 1) {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.replaceitem.entity.success.single", new Object[]{((Entity) list.iterator().next()).getScoreboardDisplayName(), itemstack.B()}), true);
            } else {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.replaceitem.entity.success.multiple", new Object[]{list.size(), itemstack.B()}), true);
            }

            return list.size();
        }
    }
}
