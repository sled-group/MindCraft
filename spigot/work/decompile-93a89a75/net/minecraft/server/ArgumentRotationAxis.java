package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

public class ArgumentRotationAxis implements ArgumentType<EnumSet<EnumDirection.EnumAxis>> {

    private static final Collection<String> a = Arrays.asList("xyz", "x");
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("arguments.swizzle.invalid", new Object[0]));

    public ArgumentRotationAxis() {}

    public static ArgumentRotationAxis a() {
        return new ArgumentRotationAxis();
    }

    public static EnumSet<EnumDirection.EnumAxis> a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (EnumSet) commandcontext.getArgument(s, EnumSet.class);
    }

    public EnumSet<EnumDirection.EnumAxis> parse(StringReader stringreader) throws CommandSyntaxException {
        EnumSet enumset = EnumSet.noneOf(EnumDirection.EnumAxis.class);

        while (stringreader.canRead() && stringreader.peek() != ' ') {
            char c0 = stringreader.read();
            EnumDirection.EnumAxis enumdirection_enumaxis;

            switch (c0) {
                case 'x':
                    enumdirection_enumaxis = EnumDirection.EnumAxis.X;
                    break;
                case 'y':
                    enumdirection_enumaxis = EnumDirection.EnumAxis.Y;
                    break;
                case 'z':
                    enumdirection_enumaxis = EnumDirection.EnumAxis.Z;
                    break;
                default:
                    throw ArgumentRotationAxis.b.create();
            }

            if (enumset.contains(enumdirection_enumaxis)) {
                throw ArgumentRotationAxis.b.create();
            }

            enumset.add(enumdirection_enumaxis);
        }

        return enumset;
    }

    public Collection<String> getExamples() {
        return ArgumentRotationAxis.a;
    }
}
