package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Iterator;

public class CommandFunction {

    public static final SuggestionProvider<CommandListenerWrapper> a = (commandcontext, suggestionsbuilder) -> {
        CustomFunctionData customfunctiondata = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getFunctionData();

        ICompletionProvider.a((Iterable) customfunctiondata.h().a(), suggestionsbuilder, "#");
        return ICompletionProvider.a((Iterable) customfunctiondata.c().keySet(), suggestionsbuilder);
    };

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("function").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("name", (ArgumentType) ArgumentTag.a()).suggests(CommandFunction.a).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentTag.a(commandcontext, "name"));
        })));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<CustomFunction> collection) {
        int i = 0;

        CustomFunction customfunction;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += commandlistenerwrapper.getServer().getFunctionData().a(customfunction, commandlistenerwrapper.a().b(2))) {
            customfunction = (CustomFunction) iterator.next();
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.function.success.single", new Object[]{i, ((CustomFunction) collection.iterator().next()).a()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.function.success.multiple", new Object[]{i, collection.size()}), true);
        }

        return i;
    }
}
