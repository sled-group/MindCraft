package net.minecraft.server;

import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

public class CommandExceptionProvider implements BuiltInExceptionProvider {

    private static final Dynamic2CommandExceptionType a = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.double.low", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType b = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.double.big", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType c = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.float.low", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType d = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.float.big", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType e = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.integer.low", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType f = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.integer.big", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType g = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.long.low", new Object[]{object1, object});
    });
    private static final Dynamic2CommandExceptionType h = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.long.big", new Object[]{object1, object});
    });
    private static final DynamicCommandExceptionType i = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.literal.incorrect", new Object[]{object});
    });
    private static final SimpleCommandExceptionType j = new SimpleCommandExceptionType(new ChatMessage("parsing.quote.expected.start", new Object[0]));
    private static final SimpleCommandExceptionType k = new SimpleCommandExceptionType(new ChatMessage("parsing.quote.expected.end", new Object[0]));
    private static final DynamicCommandExceptionType l = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.quote.escape", new Object[]{object});
    });
    private static final DynamicCommandExceptionType m = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.bool.invalid", new Object[]{object});
    });
    private static final DynamicCommandExceptionType n = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.int.invalid", new Object[]{object});
    });
    private static final SimpleCommandExceptionType o = new SimpleCommandExceptionType(new ChatMessage("parsing.int.expected", new Object[0]));
    private static final DynamicCommandExceptionType p = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.long.invalid", new Object[]{object});
    });
    private static final SimpleCommandExceptionType q = new SimpleCommandExceptionType(new ChatMessage("parsing.long.expected", new Object[0]));
    private static final DynamicCommandExceptionType r = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.double.invalid", new Object[]{object});
    });
    private static final SimpleCommandExceptionType s = new SimpleCommandExceptionType(new ChatMessage("parsing.double.expected", new Object[0]));
    private static final DynamicCommandExceptionType t = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.float.invalid", new Object[]{object});
    });
    private static final SimpleCommandExceptionType u = new SimpleCommandExceptionType(new ChatMessage("parsing.float.expected", new Object[0]));
    private static final SimpleCommandExceptionType v = new SimpleCommandExceptionType(new ChatMessage("parsing.bool.expected", new Object[0]));
    private static final DynamicCommandExceptionType w = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("parsing.expected", new Object[]{object});
    });
    private static final SimpleCommandExceptionType x = new SimpleCommandExceptionType(new ChatMessage("command.unknown.command", new Object[0]));
    private static final SimpleCommandExceptionType y = new SimpleCommandExceptionType(new ChatMessage("command.unknown.argument", new Object[0]));
    private static final SimpleCommandExceptionType z = new SimpleCommandExceptionType(new ChatMessage("command.expected.separator", new Object[0]));
    private static final DynamicCommandExceptionType A = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("command.exception", new Object[]{object});
    });

    public CommandExceptionProvider() {}

    public Dynamic2CommandExceptionType doubleTooLow() {
        return CommandExceptionProvider.a;
    }

    public Dynamic2CommandExceptionType doubleTooHigh() {
        return CommandExceptionProvider.b;
    }

    public Dynamic2CommandExceptionType floatTooLow() {
        return CommandExceptionProvider.c;
    }

    public Dynamic2CommandExceptionType floatTooHigh() {
        return CommandExceptionProvider.d;
    }

    public Dynamic2CommandExceptionType integerTooLow() {
        return CommandExceptionProvider.e;
    }

    public Dynamic2CommandExceptionType integerTooHigh() {
        return CommandExceptionProvider.f;
    }

    public Dynamic2CommandExceptionType longTooLow() {
        return CommandExceptionProvider.g;
    }

    public Dynamic2CommandExceptionType longTooHigh() {
        return CommandExceptionProvider.h;
    }

    public DynamicCommandExceptionType literalIncorrect() {
        return CommandExceptionProvider.i;
    }

    public SimpleCommandExceptionType readerExpectedStartOfQuote() {
        return CommandExceptionProvider.j;
    }

    public SimpleCommandExceptionType readerExpectedEndOfQuote() {
        return CommandExceptionProvider.k;
    }

    public DynamicCommandExceptionType readerInvalidEscape() {
        return CommandExceptionProvider.l;
    }

    public DynamicCommandExceptionType readerInvalidBool() {
        return CommandExceptionProvider.m;
    }

    public DynamicCommandExceptionType readerInvalidInt() {
        return CommandExceptionProvider.n;
    }

    public SimpleCommandExceptionType readerExpectedInt() {
        return CommandExceptionProvider.o;
    }

    public DynamicCommandExceptionType readerInvalidLong() {
        return CommandExceptionProvider.p;
    }

    public SimpleCommandExceptionType readerExpectedLong() {
        return CommandExceptionProvider.q;
    }

    public DynamicCommandExceptionType readerInvalidDouble() {
        return CommandExceptionProvider.r;
    }

    public SimpleCommandExceptionType readerExpectedDouble() {
        return CommandExceptionProvider.s;
    }

    public DynamicCommandExceptionType readerInvalidFloat() {
        return CommandExceptionProvider.t;
    }

    public SimpleCommandExceptionType readerExpectedFloat() {
        return CommandExceptionProvider.u;
    }

    public SimpleCommandExceptionType readerExpectedBool() {
        return CommandExceptionProvider.v;
    }

    public DynamicCommandExceptionType readerExpectedSymbol() {
        return CommandExceptionProvider.w;
    }

    public SimpleCommandExceptionType dispatcherUnknownCommand() {
        return CommandExceptionProvider.x;
    }

    public SimpleCommandExceptionType dispatcherUnknownArgument() {
        return CommandExceptionProvider.y;
    }

    public SimpleCommandExceptionType dispatcherExpectedArgumentSeparator() {
        return CommandExceptionProvider.z;
    }

    public DynamicCommandExceptionType dispatcherParseException() {
        return CommandExceptionProvider.A;
    }
}
