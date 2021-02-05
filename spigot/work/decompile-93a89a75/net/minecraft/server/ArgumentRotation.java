package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;

public class ArgumentRotation implements ArgumentType<IVectorPosition> {

    private static final Collection<String> b = Arrays.asList("0 0", "~ ~", "~-5 ~5");
    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.rotation.incomplete", new Object[0]));

    public ArgumentRotation() {}

    public static ArgumentRotation a() {
        return new ArgumentRotation();
    }

    public static IVectorPosition a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (IVectorPosition) commandcontext.getArgument(s, IVectorPosition.class);
    }

    public IVectorPosition parse(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        if (!stringreader.canRead()) {
            throw ArgumentRotation.a.createWithContext(stringreader);
        } else {
            ArgumentParserPosition argumentparserposition = ArgumentParserPosition.a(stringreader, false);

            if (stringreader.canRead() && stringreader.peek() == ' ') {
                stringreader.skip();
                ArgumentParserPosition argumentparserposition1 = ArgumentParserPosition.a(stringreader, false);

                return new VectorPosition(argumentparserposition1, argumentparserposition, new ArgumentParserPosition(true, 0.0D));
            } else {
                stringreader.setCursor(i);
                throw ArgumentRotation.a.createWithContext(stringreader);
            }
        }
    }

    public Collection<String> getExamples() {
        return ArgumentRotation.b;
    }
}
