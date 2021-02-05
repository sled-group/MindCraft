package net.minecraft.server;

import com.google.common.collect.Iterables;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import java.util.Iterator;
import java.util.Map;

public class CommandHelp {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.help.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("help").executes((commandcontext) -> {
            Map<CommandNode<CommandListenerWrapper>, String> map = com_mojang_brigadier_commanddispatcher.getSmartUsage(com_mojang_brigadier_commanddispatcher.getRoot(), commandcontext.getSource());
            Iterator iterator = map.values().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatComponentText("/" + s), false);
            }

            return map.size();
        })).then(CommandDispatcher.a("command", (ArgumentType) StringArgumentType.greedyString()).executes((commandcontext) -> {
            ParseResults<CommandListenerWrapper> parseresults = com_mojang_brigadier_commanddispatcher.parse(StringArgumentType.getString(commandcontext, "command"), commandcontext.getSource());

            if (parseresults.getContext().getNodes().isEmpty()) {
                throw CommandHelp.a.create();
            } else {
                Map<CommandNode<CommandListenerWrapper>, String> map = com_mojang_brigadier_commanddispatcher.getSmartUsage(((ParsedCommandNode) Iterables.getLast(parseresults.getContext().getNodes())).getNode(), commandcontext.getSource());
                Iterator iterator = map.values().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();

                    ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatComponentText("/" + parseresults.getReader().getString() + " " + s), false);
                }

                return map.size();
            }
        })));
    }
}
