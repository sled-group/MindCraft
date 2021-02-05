package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

public class ArgumentParserPosition {

    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos.missing.double", new Object[0]));
    public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.pos.missing.int", new Object[0]));
    private final boolean c;
    private final double d;

    public ArgumentParserPosition(boolean flag, double d0) {
        this.c = flag;
        this.d = d0;
    }

    public double a(double d0) {
        return this.c ? this.d + d0 : this.d;
    }

    public static ArgumentParserPosition a(StringReader stringreader, boolean flag) throws CommandSyntaxException {
        if (stringreader.canRead() && stringreader.peek() == '^') {
            throw ArgumentVec3.b.createWithContext(stringreader);
        } else if (!stringreader.canRead()) {
            throw ArgumentParserPosition.a.createWithContext(stringreader);
        } else {
            boolean flag1 = b(stringreader);
            int i = stringreader.getCursor();
            double d0 = stringreader.canRead() && stringreader.peek() != ' ' ? stringreader.readDouble() : 0.0D;
            String s = stringreader.getString().substring(i, stringreader.getCursor());

            if (flag1 && s.isEmpty()) {
                return new ArgumentParserPosition(true, 0.0D);
            } else {
                if (!s.contains(".") && !flag1 && flag) {
                    d0 += 0.5D;
                }

                return new ArgumentParserPosition(flag1, d0);
            }
        }
    }

    public static ArgumentParserPosition a(StringReader stringreader) throws CommandSyntaxException {
        if (stringreader.canRead() && stringreader.peek() == '^') {
            throw ArgumentVec3.b.createWithContext(stringreader);
        } else if (!stringreader.canRead()) {
            throw ArgumentParserPosition.b.createWithContext(stringreader);
        } else {
            boolean flag = b(stringreader);
            double d0;

            if (stringreader.canRead() && stringreader.peek() != ' ') {
                d0 = flag ? stringreader.readDouble() : (double) stringreader.readInt();
            } else {
                d0 = 0.0D;
            }

            return new ArgumentParserPosition(flag, d0);
        }
    }

    private static boolean b(StringReader stringreader) {
        boolean flag;

        if (stringreader.peek() == '~') {
            flag = true;
            stringreader.skip();
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ArgumentParserPosition)) {
            return false;
        } else {
            ArgumentParserPosition argumentparserposition = (ArgumentParserPosition) object;

            return this.c != argumentparserposition.c ? false : Double.compare(argumentparserposition.d, this.d) == 0;
        }
    }

    public int hashCode() {
        int i = this.c ? 1 : 0;
        long j = Double.doubleToLongBits(this.d);

        i = 31 * i + (int) (j ^ j >>> 32);
        return i;
    }

    public boolean a() {
        return this.c;
    }
}
