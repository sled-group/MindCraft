package net.minecraft.server;

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

public class CommandEnchant {

    private static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.enchant.failed.entity", new Object[]{object});
    });
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.enchant.failed.itemless", new Object[]{object});
    });
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.enchant.failed.incompatible", new Object[]{object});
    });
    private static final Dynamic2CommandExceptionType d = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("commands.enchant.failed.level", new Object[]{object, object1});
    });
    private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.enchant.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("enchant").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).then(((RequiredArgumentBuilder) CommandDispatcher.a("enchantment", (ArgumentType) ArgumentEnchantment.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentEnchantment.a(commandcontext, "enchantment"), 1);
        })).then(CommandDispatcher.a("level", (ArgumentType) IntegerArgumentType.integer(0)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentEnchantment.a(commandcontext, "enchantment"), IntegerArgumentType.getInteger(commandcontext, "level"));
        })))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, Enchantment enchantment, int i) throws CommandSyntaxException {
        if (i > enchantment.getMaxLevel()) {
            throw CommandEnchant.d.create(i, enchantment.getMaxLevel());
        } else {
            int j = 0;
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (entity instanceof EntityLiving) {
                    EntityLiving entityliving = (EntityLiving) entity;
                    ItemStack itemstack = entityliving.getItemInMainHand();

                    if (!itemstack.isEmpty()) {
                        if (enchantment.canEnchant(itemstack) && EnchantmentManager.a((Collection) EnchantmentManager.a(itemstack).keySet(), enchantment)) {
                            itemstack.addEnchantment(enchantment, i);
                            ++j;
                        } else if (collection.size() == 1) {
                            throw CommandEnchant.c.create(itemstack.getItem().g(itemstack).getString());
                        }
                    } else if (collection.size() == 1) {
                        throw CommandEnchant.b.create(entityliving.getDisplayName().getString());
                    }
                } else if (collection.size() == 1) {
                    throw CommandEnchant.a.create(entity.getDisplayName().getString());
                }
            }

            if (j == 0) {
                throw CommandEnchant.e.create();
            } else {
                if (collection.size() == 1) {
                    commandlistenerwrapper.sendMessage(new ChatMessage("commands.enchant.success.single", new Object[]{enchantment.d(i), ((Entity) collection.iterator().next()).getScoreboardDisplayName()}), true);
                } else {
                    commandlistenerwrapper.sendMessage(new ChatMessage("commands.enchant.success.multiple", new Object[]{enchantment.d(i), collection.size()}), true);
                }

                return j;
            }
        }
    }
}
