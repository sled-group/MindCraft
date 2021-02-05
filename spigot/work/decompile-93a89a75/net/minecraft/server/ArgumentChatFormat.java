package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ArgumentChatFormat implements ArgumentType<EnumChatFormat> {

    private static final Collection<String> b = Arrays.asList("red", "green");
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.color.invalid", new Object[]{object});
    });

    private ArgumentChatFormat() {}

    public static ArgumentChatFormat a() {
        return new ArgumentChatFormat();
    }

    public static EnumChatFormat a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (EnumChatFormat) commandcontext.getArgument(s, EnumChatFormat.class);
    }

    public EnumChatFormat parse(StringReader stringreader) throws CommandSyntaxException {
        String s = stringreader.readUnquotedString();
        EnumChatFormat enumchatformat = EnumChatFormat.c(s);

        if (enumchatformat != null && !enumchatformat.isFormat()) {
            return enumchatformat;
        } else {
            throw ArgumentChatFormat.a.create(s);
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.b((Iterable) EnumChatFormat.a(true, false), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentChatFormat.b;
    }
}
