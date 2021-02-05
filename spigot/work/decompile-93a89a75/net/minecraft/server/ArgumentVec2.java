package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ArgumentVec2 implements ArgumentType<IVectorPosition> {

    private static final Collection<String> b = Arrays.asList("0 0", "~ ~", "0.1 -0.5", "~1 ~-2");
    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos2d.incomplete", new Object[0]));
    private final boolean c;

    public ArgumentVec2(boolean flag) {
        this.c = flag;
    }

    public static ArgumentVec2 a() {
        return new ArgumentVec2(true);
    }

    public static Vec2F a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        Vec3D vec3d = ((IVectorPosition) commandcontext.getArgument(s, IVectorPosition.class)).a((CommandListenerWrapper) commandcontext.getSource());

        return new Vec2F((float) vec3d.x, (float) vec3d.z);
    }

    public IVectorPosition parse(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        if (!stringreader.canRead()) {
            throw ArgumentVec2.a.createWithContext(stringreader);
        } else {
            ArgumentParserPosition argumentparserposition = ArgumentParserPosition.a(stringreader, this.c);

            if (stringreader.canRead() && stringreader.peek() == ' ') {
                stringreader.skip();
                ArgumentParserPosition argumentparserposition1 = ArgumentParserPosition.a(stringreader, this.c);

                return new VectorPosition(argumentparserposition, new ArgumentParserPosition(true, 0.0D), argumentparserposition1);
            } else {
                stringreader.setCursor(i);
                throw ArgumentVec2.a.createWithContext(stringreader);
            }
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        if (!(commandcontext.getSource() instanceof ICompletionProvider)) {
            return Suggestions.empty();
        } else {
            String s = suggestionsbuilder.getRemaining();
            Object object;

            if (!s.isEmpty() && s.charAt(0) == '^') {
                object = Collections.singleton(ICompletionProvider.a.a);
            } else {
                object = ((ICompletionProvider) commandcontext.getSource()).r();
            }

            return ICompletionProvider.b(s, (Collection) object, suggestionsbuilder, CommandDispatcher.a(this::parse));
        }
    }

    public Collection<String> getExamples() {
        return ArgumentVec2.b;
    }
}
