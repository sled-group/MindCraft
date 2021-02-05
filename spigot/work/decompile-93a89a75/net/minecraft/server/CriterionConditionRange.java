package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.Function;
import javax.annotation.Nullable;

public class CriterionConditionRange {

    public static final CriterionConditionRange a = new CriterionConditionRange((Float) null, (Float) null);
    public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.range.ints", new Object[0]));
    private final Float c;
    private final Float d;

    public CriterionConditionRange(@Nullable Float ofloat, @Nullable Float ofloat1) {
        this.c = ofloat;
        this.d = ofloat1;
    }

    @Nullable
    public Float a() {
        return this.c;
    }

    @Nullable
    public Float b() {
        return this.d;
    }

    public static CriterionConditionRange a(StringReader stringreader, boolean flag, Function<Float, Float> function) throws CommandSyntaxException {
        if (!stringreader.canRead()) {
            throw CriterionConditionValue.a.createWithContext(stringreader);
        } else {
            int i = stringreader.getCursor();
            Float ofloat = a(b(stringreader, flag), function);
            Float ofloat1;

            if (stringreader.canRead(2) && stringreader.peek() == '.' && stringreader.peek(1) == '.') {
                stringreader.skip();
                stringreader.skip();
                ofloat1 = a(b(stringreader, flag), function);
                if (ofloat == null && ofloat1 == null) {
                    stringreader.setCursor(i);
                    throw CriterionConditionValue.a.createWithContext(stringreader);
                }
            } else {
                if (!flag && stringreader.canRead() && stringreader.peek() == '.') {
                    stringreader.setCursor(i);
                    throw CriterionConditionRange.b.createWithContext(stringreader);
                }

                ofloat1 = ofloat;
            }

            if (ofloat == null && ofloat1 == null) {
                stringreader.setCursor(i);
                throw CriterionConditionValue.a.createWithContext(stringreader);
            } else {
                return new CriterionConditionRange(ofloat, ofloat1);
            }
        }
    }

    @Nullable
    private static Float b(StringReader stringreader, boolean flag) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        while (stringreader.canRead() && c(stringreader, flag)) {
            stringreader.skip();
        }

        String s = stringreader.getString().substring(i, stringreader.getCursor());

        if (s.isEmpty()) {
            return null;
        } else {
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException numberformatexception) {
                if (flag) {
                    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidDouble().createWithContext(stringreader, s);
                } else {
                    throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidInt().createWithContext(stringreader, s);
                }
            }
        }
    }

    private static boolean c(StringReader stringreader, boolean flag) {
        char c0 = stringreader.peek();

        return (c0 < '0' || c0 > '9') && c0 != '-' ? (flag && c0 == '.' ? !stringreader.canRead(2) || stringreader.peek(1) != '.' : false) : true;
    }

    @Nullable
    private static Float a(@Nullable Float ofloat, Function<Float, Float> function) {
        return ofloat == null ? null : (Float) function.apply(ofloat);
    }
}
