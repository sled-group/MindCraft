package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CommandLoot {

    public static final SuggestionProvider<CommandListenerWrapper> a = (commandcontext, suggestionsbuilder) -> {
        LootTableRegistry loottableregistry = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getLootTableRegistry();

        return ICompletionProvider.a((Iterable) loottableregistry.a(), suggestionsbuilder);
    };
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.drop.no_held_items", new Object[]{object});
    });
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.drop.no_loot_table", new Object[]{object});
    });

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) a(CommandDispatcher.a("loot").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        }), (argumentbuilder, commandloot_b) -> {
            return argumentbuilder.then(CommandDispatcher.a("fish").then(CommandDispatcher.a("loot_table", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CommandLoot.a).then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).executes((commandcontext) -> {
                return a(commandcontext, ArgumentMinecraftKeyRegistered.c(commandcontext, "loot_table"), ArgumentPosition.a(commandcontext, "pos"), ItemStack.a, commandloot_b);
            })).then(CommandDispatcher.a("tool", (ArgumentType) ArgumentItemStack.a()).executes((commandcontext) -> {
                return a(commandcontext, ArgumentMinecraftKeyRegistered.c(commandcontext, "loot_table"), ArgumentPosition.a(commandcontext, "pos"), ArgumentItemStack.a(commandcontext, "tool").a(1, false), commandloot_b);
            }))).then(CommandDispatcher.a("mainhand").executes((commandcontext) -> {
                return a(commandcontext, ArgumentMinecraftKeyRegistered.c(commandcontext, "loot_table"), ArgumentPosition.a(commandcontext, "pos"), a((CommandListenerWrapper) commandcontext.getSource(), EnumItemSlot.MAINHAND), commandloot_b);
            }))).then(CommandDispatcher.a("offhand").executes((commandcontext) -> {
                return a(commandcontext, ArgumentMinecraftKeyRegistered.c(commandcontext, "loot_table"), ArgumentPosition.a(commandcontext, "pos"), a((CommandListenerWrapper) commandcontext.getSource(), EnumItemSlot.OFFHAND), commandloot_b);
            }))))).then(CommandDispatcher.a("loot").then(CommandDispatcher.a("loot_table", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CommandLoot.a).executes((commandcontext) -> {
                return a(commandcontext, ArgumentMinecraftKeyRegistered.c(commandcontext, "loot_table"), commandloot_b);
            }))).then(CommandDispatcher.a("kill").then(CommandDispatcher.a("target", (ArgumentType) ArgumentEntity.a()).executes((commandcontext) -> {
                return a(commandcontext, ArgumentEntity.a(commandcontext, "target"), commandloot_b);
            }))).then(CommandDispatcher.a("mine").then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).executes((commandcontext) -> {
                return a(commandcontext, ArgumentPosition.a(commandcontext, "pos"), ItemStack.a, commandloot_b);
            })).then(CommandDispatcher.a("tool", (ArgumentType) ArgumentItemStack.a()).executes((commandcontext) -> {
                return a(commandcontext, ArgumentPosition.a(commandcontext, "pos"), ArgumentItemStack.a(commandcontext, "tool").a(1, false), commandloot_b);
            }))).then(CommandDispatcher.a("mainhand").executes((commandcontext) -> {
                return a(commandcontext, ArgumentPosition.a(commandcontext, "pos"), a((CommandListenerWrapper) commandcontext.getSource(), EnumItemSlot.MAINHAND), commandloot_b);
            }))).then(CommandDispatcher.a("offhand").executes((commandcontext) -> {
                return a(commandcontext, ArgumentPosition.a(commandcontext, "pos"), a((CommandListenerWrapper) commandcontext.getSource(), EnumItemSlot.OFFHAND), commandloot_b);
            }))));
        }));
    }

    private static <T extends ArgumentBuilder<CommandListenerWrapper, T>> T a(T t0, CommandLoot.c commandloot_c) {
        return t0.then(((LiteralArgumentBuilder) CommandDispatcher.a("replace").then(CommandDispatcher.a("entity").then(CommandDispatcher.a("entities", (ArgumentType) ArgumentEntity.multipleEntities()).then(commandloot_c.construct(CommandDispatcher.a("slot", (ArgumentType) ArgumentInventorySlot.a()), (commandcontext, list, commandloot_a) -> {
            return a(ArgumentEntity.b(commandcontext, "entities"), ArgumentInventorySlot.a(commandcontext, "slot"), list.size(), list, commandloot_a);
        }).then(commandloot_c.construct(CommandDispatcher.a("count", (ArgumentType) IntegerArgumentType.integer(0)), (commandcontext, list, commandloot_a) -> {
            return a(ArgumentEntity.b(commandcontext, "entities"), ArgumentInventorySlot.a(commandcontext, "slot"), IntegerArgumentType.getInteger(commandcontext, "count"), list, commandloot_a);
        })))))).then(CommandDispatcher.a("block").then(CommandDispatcher.a("targetPos", (ArgumentType) ArgumentPosition.a()).then(commandloot_c.construct(CommandDispatcher.a("slot", (ArgumentType) ArgumentInventorySlot.a()), (commandcontext, list, commandloot_a) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "targetPos"), ArgumentInventorySlot.a(commandcontext, "slot"), list.size(), list, commandloot_a);
        }).then(commandloot_c.construct(CommandDispatcher.a("count", (ArgumentType) IntegerArgumentType.integer(0)), (commandcontext, list, commandloot_a) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "targetPos"), IntegerArgumentType.getInteger(commandcontext, "slot"), IntegerArgumentType.getInteger(commandcontext, "count"), list, commandloot_a);
        })))))).then(CommandDispatcher.a("insert").then(commandloot_c.construct(CommandDispatcher.a("targetPos", (ArgumentType) ArgumentPosition.a()), (commandcontext, list, commandloot_a) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "targetPos"), list, commandloot_a);
        }))).then(CommandDispatcher.a("give").then(commandloot_c.construct(CommandDispatcher.a("players", (ArgumentType) ArgumentEntity.d()), (commandcontext, list, commandloot_a) -> {
            return a(ArgumentEntity.f(commandcontext, "players"), list, commandloot_a);
        }))).then(CommandDispatcher.a("spawn").then(commandloot_c.construct(CommandDispatcher.a("targetPos", (ArgumentType) ArgumentVec3.a()), (commandcontext, list, commandloot_a) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentVec3.a(commandcontext, "targetPos"), list, commandloot_a);
        })));
    }

    private static IInventory a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition) throws CommandSyntaxException {
        TileEntity tileentity = commandlistenerwrapper.getWorld().getTileEntity(blockposition);

        if (!(tileentity instanceof IInventory)) {
            throw CommandReplaceItem.a.create();
        } else {
            return (IInventory) tileentity;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException {
        IInventory iinventory = a(commandlistenerwrapper, blockposition);
        List<ItemStack> list1 = Lists.newArrayListWithCapacity(list.size());
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack = (ItemStack) iterator.next();

            if (a(iinventory, itemstack.cloneItemStack())) {
                iinventory.update();
                list1.add(itemstack);
            }
        }

        commandloot_a.accept(list1);
        return list1.size();
    }

    private static boolean a(IInventory iinventory, ItemStack itemstack) {
        boolean flag = false;

        for (int i = 0; i < iinventory.getSize() && !itemstack.isEmpty(); ++i) {
            ItemStack itemstack1 = iinventory.getItem(i);

            if (iinventory.b(i, itemstack)) {
                if (itemstack1.isEmpty()) {
                    iinventory.setItem(i, itemstack);
                    flag = true;
                    break;
                }

                if (a(itemstack1, itemstack)) {
                    int j = itemstack.getMaxStackSize() - itemstack1.getCount();
                    int k = Math.min(itemstack.getCount(), j);

                    itemstack.subtract(k);
                    itemstack1.add(k);
                    flag = true;
                }
            }
        }

        return flag;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition, int i, int j, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException {
        IInventory iinventory = a(commandlistenerwrapper, blockposition);
        int k = iinventory.getSize();

        if (i >= 0 && i < k) {
            List<ItemStack> list1 = Lists.newArrayListWithCapacity(list.size());

            for (int l = 0; l < j; ++l) {
                int i1 = i + l;
                ItemStack itemstack = l < list.size() ? (ItemStack) list.get(l) : ItemStack.a;

                if (iinventory.b(i1, itemstack)) {
                    iinventory.setItem(i1, itemstack);
                    list1.add(itemstack);
                }
            }

            commandloot_a.accept(list1);
            return list1.size();
        } else {
            throw CommandReplaceItem.b.create(i);
        }
    }

    private static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack.getItem() == itemstack1.getItem() && itemstack.getDamage() == itemstack1.getDamage() && itemstack.getCount() <= itemstack.getMaxStackSize() && Objects.equals(itemstack.getTag(), itemstack1.getTag());
    }

    private static int a(Collection<EntityPlayer> collection, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException {
        List<ItemStack> list1 = Lists.newArrayListWithCapacity(list.size());
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack = (ItemStack) iterator.next();
            Iterator iterator1 = collection.iterator();

            while (iterator1.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer) iterator1.next();

                if (entityplayer.inventory.pickup(itemstack.cloneItemStack())) {
                    list1.add(itemstack);
                }
            }
        }

        commandloot_a.accept(list1);
        return list1.size();
    }

    private static void a(Entity entity, List<ItemStack> list, int i, int j, List<ItemStack> list1) {
        for (int k = 0; k < j; ++k) {
            ItemStack itemstack = k < list.size() ? (ItemStack) list.get(k) : ItemStack.a;

            if (entity.a_(i + k, itemstack.cloneItemStack())) {
                list1.add(itemstack);
            }
        }

    }

    private static int a(Collection<? extends Entity> collection, int i, int j, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException {
        List<ItemStack> list1 = Lists.newArrayListWithCapacity(list.size());
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entity;

                entityplayer.defaultContainer.c();
                a(entity, list, i, j, list1);
                entityplayer.defaultContainer.c();
            } else {
                a(entity, list, i, j, list1);
            }
        }

        commandloot_a.accept(list1);
        return list1.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Vec3D vec3d, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException {
        WorldServer worldserver = commandlistenerwrapper.getWorld();

        list.forEach((itemstack) -> {
            EntityItem entityitem = new EntityItem(worldserver, vec3d.x, vec3d.y, vec3d.z, itemstack.cloneItemStack());

            entityitem.defaultPickupDelay();
            worldserver.addEntity(entityitem);
        });
        commandloot_a.accept(list);
        return list.size();
    }

    private static void a(CommandListenerWrapper commandlistenerwrapper, List<ItemStack> list) {
        if (list.size() == 1) {
            ItemStack itemstack = (ItemStack) list.get(0);

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.drop.success.single", new Object[]{itemstack.getCount(), itemstack.B()}), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.drop.success.multiple", new Object[]{list.size()}), false);
        }

    }

    private static void a(CommandListenerWrapper commandlistenerwrapper, List<ItemStack> list, MinecraftKey minecraftkey) {
        if (list.size() == 1) {
            ItemStack itemstack = (ItemStack) list.get(0);

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.drop.success.single_with_table", new Object[]{itemstack.getCount(), itemstack.B(), minecraftkey}), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.drop.success.multiple_with_table", new Object[]{list.size(), minecraftkey}), false);
        }

    }

    private static ItemStack a(CommandListenerWrapper commandlistenerwrapper, EnumItemSlot enumitemslot) throws CommandSyntaxException {
        Entity entity = commandlistenerwrapper.g();

        if (entity instanceof EntityLiving) {
            return ((EntityLiving) entity).getEquipment(enumitemslot);
        } else {
            throw CommandLoot.b.create(entity.getScoreboardDisplayName());
        }
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, BlockPosition blockposition, ItemStack itemstack, CommandLoot.b commandloot_b) throws CommandSyntaxException {
        CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper) commandcontext.getSource();
        WorldServer worldserver = commandlistenerwrapper.getWorld();
        IBlockData iblockdata = worldserver.getType(blockposition);
        TileEntity tileentity = worldserver.getTileEntity(blockposition);
        LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder(worldserver)).set(LootContextParameters.POSITION, blockposition).set(LootContextParameters.BLOCK_STATE, iblockdata).setOptional(LootContextParameters.BLOCK_ENTITY, tileentity).setOptional(LootContextParameters.THIS_ENTITY, commandlistenerwrapper.getEntity()).set(LootContextParameters.TOOL, itemstack);
        List<ItemStack> list = iblockdata.a(loottableinfo_builder);

        return commandloot_b.accept(commandcontext, list, (list1) -> {
            a(commandlistenerwrapper, list1, iblockdata.getBlock().i());
        });
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, Entity entity, CommandLoot.b commandloot_b) throws CommandSyntaxException {
        if (!(entity instanceof EntityLiving)) {
            throw CommandLoot.c.create(entity.getScoreboardDisplayName());
        } else {
            MinecraftKey minecraftkey = ((EntityLiving) entity).cG();
            CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper) commandcontext.getSource();
            LootTableInfo.Builder loottableinfo_builder = new LootTableInfo.Builder(commandlistenerwrapper.getWorld());
            Entity entity1 = commandlistenerwrapper.getEntity();

            if (entity1 instanceof EntityHuman) {
                loottableinfo_builder.set(LootContextParameters.LAST_DAMAGE_PLAYER, (EntityHuman) entity1);
            }

            loottableinfo_builder.set(LootContextParameters.DAMAGE_SOURCE, DamageSource.MAGIC);
            loottableinfo_builder.setOptional(LootContextParameters.DIRECT_KILLER_ENTITY, entity1);
            loottableinfo_builder.setOptional(LootContextParameters.KILLER_ENTITY, entity1);
            loottableinfo_builder.set(LootContextParameters.THIS_ENTITY, entity);
            loottableinfo_builder.set(LootContextParameters.POSITION, new BlockPosition(commandlistenerwrapper.getPosition()));
            LootTable loottable = commandlistenerwrapper.getServer().getLootTableRegistry().getLootTable(minecraftkey);
            List<ItemStack> list = loottable.populateLoot(loottableinfo_builder.build(LootContextParameterSets.ENTITY));

            return commandloot_b.accept(commandcontext, list, (list1) -> {
                a(commandlistenerwrapper, list1, minecraftkey);
            });
        }
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, MinecraftKey minecraftkey, CommandLoot.b commandloot_b) throws CommandSyntaxException {
        CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper) commandcontext.getSource();
        LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder(commandlistenerwrapper.getWorld())).setOptional(LootContextParameters.THIS_ENTITY, commandlistenerwrapper.getEntity()).set(LootContextParameters.POSITION, new BlockPosition(commandlistenerwrapper.getPosition()));

        return a(commandcontext, minecraftkey, loottableinfo_builder.build(LootContextParameterSets.CHEST), commandloot_b);
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, MinecraftKey minecraftkey, BlockPosition blockposition, ItemStack itemstack, CommandLoot.b commandloot_b) throws CommandSyntaxException {
        CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper) commandcontext.getSource();
        LootTableInfo loottableinfo = (new LootTableInfo.Builder(commandlistenerwrapper.getWorld())).set(LootContextParameters.POSITION, blockposition).set(LootContextParameters.TOOL, itemstack).build(LootContextParameterSets.FISHING);

        return a(commandcontext, minecraftkey, loottableinfo, commandloot_b);
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, MinecraftKey minecraftkey, LootTableInfo loottableinfo, CommandLoot.b commandloot_b) throws CommandSyntaxException {
        CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper) commandcontext.getSource();
        LootTable loottable = commandlistenerwrapper.getServer().getLootTableRegistry().getLootTable(minecraftkey);
        List<ItemStack> list = loottable.populateLoot(loottableinfo);

        return commandloot_b.accept(commandcontext, list, (list1) -> {
            a(commandlistenerwrapper, list1);
        });
    }

    @FunctionalInterface
    interface c {

        ArgumentBuilder<CommandListenerWrapper, ?> construct(ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, CommandLoot.b commandloot_b);
    }

    @FunctionalInterface
    interface b {

        int accept(CommandContext<CommandListenerWrapper> commandcontext, List<ItemStack> list, CommandLoot.a commandloot_a) throws CommandSyntaxException;
    }

    @FunctionalInterface
    interface a {

        void accept(List<ItemStack> list) throws CommandSyntaxException;
    }
}
