package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ArgumentTime implements ArgumentType<Integer> {

    private static final Collection<String> a = Arrays.asList("0d", "0s", "0t", "0");
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.time.invalid_unit", new Object[0]));
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.time.invalid_tick_count", new Object[]{object});
    });
    private static final Object2IntMap<String> d = new Object2IntOpenHashMap();

    public ArgumentTime() {}

    public static ArgumentTime a() {
        return new ArgumentTime();
    }

    public Integer parse(StringReader stringreader) throws CommandSyntaxException {
        float f = stringreader.readFloat();
        String s = stringreader.readUnquotedString();
        int i = ArgumentTime.d.getOrDefault(s, 0);

        if (i == 0) {
            throw ArgumentTime.b.create();
        } else {
            int j = Math.round(f * (float) i);

            if (j < 0) {
                throw ArgumentTime.c.create(j);
            } else {
                return j;
            }
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        StringReader stringreader = new StringReader(suggestionsbuilder.getRemaining());

        try {
            stringreader.readFloat();
        } catch (CommandSyntaxException commandsyntaxexception) {
            return suggestionsbuilder.buildFuture();
        }

        return ICompletionProvider.b((Iterable) ArgumentTime.d.keySet(), suggestionsbuilder.createOffset(suggestionsbuilder.getStart() + stringreader.getCursor()));
    }

    public Collection<String> getExamples() {
        return ArgumentTime.a;
    }

    static {
        ArgumentTime.d.put("d", 24000);
        ArgumentTime.d.put("s", 20);
        ArgumentTime.d.put("t", 1);
        ArgumentTime.d.put("", 1);
    }
}
