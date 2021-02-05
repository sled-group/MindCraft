package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Either;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArgumentTag implements ArgumentType<ArgumentTag.a> {

    private static final Collection<String> a = Arrays.asList("foo", "foo:bar", "#foo");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.function.tag.unknown", new Object[]{object});
    });
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.function.unknown", new Object[]{object});
    });

    public ArgumentTag() {}

    public static ArgumentTag a() {
        return new ArgumentTag();
    }

    public ArgumentTag.a parse(StringReader stringreader) throws CommandSyntaxException {
        final MinecraftKey minecraftkey;

        if (stringreader.canRead() && stringreader.peek() == '#') {
            stringreader.skip();
            minecraftkey = MinecraftKey.a(stringreader);
            return new ArgumentTag.a() {
                @Override
                public Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                    Tag<CustomFunction> tag = ArgumentTag.d(commandcontext, minecraftkey);

                    return tag.a();
                }

                @Override
                public Either<CustomFunction, Tag<CustomFunction>> b(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                    return Either.right(ArgumentTag.d(commandcontext, minecraftkey));
                }
            };
        } else {
            minecraftkey = MinecraftKey.a(stringreader);
            return new ArgumentTag.a() {
                @Override
                public Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                    return Collections.singleton(ArgumentTag.c(commandcontext, minecraftkey));
                }

                @Override
                public Either<CustomFunction, Tag<CustomFunction>> b(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                    return Either.left(ArgumentTag.c(commandcontext, minecraftkey));
                }
            };
        }
    }

    private static CustomFunction c(CommandContext<CommandListenerWrapper> commandcontext, MinecraftKey minecraftkey) throws CommandSyntaxException {
        return (CustomFunction) ((CommandListenerWrapper) commandcontext.getSource()).getServer().getFunctionData().a(minecraftkey).orElseThrow(() -> {
            return ArgumentTag.c.create(minecraftkey.toString());
        });
    }

    private static Tag<CustomFunction> d(CommandContext<CommandListenerWrapper> commandcontext, MinecraftKey minecraftkey) throws CommandSyntaxException {
        Tag<CustomFunction> tag = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getFunctionData().h().a(minecraftkey);

        if (tag == null) {
            throw ArgumentTag.b.create(minecraftkey.toString());
        } else {
            return tag;
        }
    }

    public static Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentTag.a) commandcontext.getArgument(s, ArgumentTag.a.class)).a(commandcontext);
    }

    public static Either<CustomFunction, Tag<CustomFunction>> b(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentTag.a) commandcontext.getArgument(s, ArgumentTag.a.class)).b(commandcontext);
    }

    public Collection<String> getExamples() {
        return ArgumentTag.a;
    }

    public interface a {

        Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;

        Either<CustomFunction, Tag<CustomFunction>> b(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;
    }
}
