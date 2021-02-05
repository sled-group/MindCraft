package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;

public abstract class CriterionConditionValue<T extends Number> {

    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.range.empty", new Object[0]));
    public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.range.swapped", new Object[0]));
    protected final T c;
    protected final T d;

    protected CriterionConditionValue(@Nullable T t0, @Nullable T t1) {
        this.c = t0;
        this.d = t1;
    }

    @Nullable
    public T a() {
        return this.c;
    }

    @Nullable
    public T b() {
        return this.d;
    }

    public boolean c() {
        return this.c == null && this.d == null;
    }

    public JsonElement d() {
        if (this.c()) {
            return JsonNull.INSTANCE;
        } else if (this.c != null && this.c.equals(this.d)) {
            return new JsonPrimitive(this.c);
        } else {
            JsonObject jsonobject = new JsonObject();

            if (this.c != null) {
                jsonobject.addProperty("min", this.c);
            }

            if (this.d != null) {
                jsonobject.addProperty("max", this.d);
            }

            return jsonobject;
        }
    }

    protected static <T extends Number, R extends CriterionConditionValue<T>> R a(@Nullable JsonElement jsonelement, R r0, BiFunction<JsonElement, String, T> bifunction, CriterionConditionValue.a<T, R> criterionconditionvalue_a) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            if (ChatDeserializer.b(jsonelement)) {
                T t0 = (Number) bifunction.apply(jsonelement, "value");

                return criterionconditionvalue_a.create(t0, t0);
            } else {
                JsonObject jsonobject = ChatDeserializer.m(jsonelement, "value");
                T t1 = jsonobject.has("min") ? (Number) bifunction.apply(jsonobject.get("min"), "min") : null;
                T t2 = jsonobject.has("max") ? (Number) bifunction.apply(jsonobject.get("max"), "max") : null;

                return criterionconditionvalue_a.create(t1, t2);
            }
        } else {
            return r0;
        }
    }

    protected static <T extends Number, R extends CriterionConditionValue<T>> R a(StringReader stringreader, CriterionConditionValue.b<T, R> criterionconditionvalue_b, Function<String, T> function, Supplier<DynamicCommandExceptionType> supplier, Function<T, T> function1) throws CommandSyntaxException {
        if (!stringreader.canRead()) {
            throw CriterionConditionValue.a.createWithContext(stringreader);
        } else {
            int i = stringreader.getCursor();

            try {
                T t0 = (Number) a(a(stringreader, function, supplier), function1);
                Number number;

                if (stringreader.canRead(2) && stringreader.peek() == '.' && stringreader.peek(1) == '.') {
                    stringreader.skip();
                    stringreader.skip();
                    number = (Number) a(a(stringreader, function, supplier), function1);
                    if (t0 == null && number == null) {
                        throw CriterionConditionValue.a.createWithContext(stringreader);
                    }
                } else {
                    number = t0;
                }

                if (t0 == null && number == null) {
                    throw CriterionConditionValue.a.createWithContext(stringreader);
                } else {
                    return criterionconditionvalue_b.create(stringreader, t0, number);
                }
            } catch (CommandSyntaxException commandsyntaxexception) {
                stringreader.setCursor(i);
                throw new CommandSyntaxException(commandsyntaxexception.getType(), commandsyntaxexception.getRawMessage(), commandsyntaxexception.getInput(), i);
            }
        }
    }

    @Nullable
    private static <T extends Number> T a(StringReader stringreader, Function<String, T> function, Supplier<DynamicCommandExceptionType> supplier) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        while (stringreader.canRead() && a(stringreader)) {
            stringreader.skip();
        }

        String s = stringreader.getString().substring(i, stringreader.getCursor());

        if (s.isEmpty()) {
            return null;
        } else {
            try {
                return (Number) function.apply(s);
            } catch (NumberFormatException numberformatexception) {
                throw ((DynamicCommandExceptionType) supplier.get()).createWithContext(stringreader, s);
            }
        }
    }

    private static boolean a(StringReader stringreader) {
        char c0 = stringreader.peek();

        return (c0 < '0' || c0 > '9') && c0 != '-' ? (c0 != '.' ? false : !stringreader.canRead(2) || stringreader.peek(1) != '.') : true;
    }

    @Nullable
    private static <T> T a(@Nullable T t0, Function<T, T> function) {
        return t0 == null ? null : function.apply(t0);
    }

    @FunctionalInterface
    public interface b<T extends Number, R extends CriterionConditionValue<T>> {

        R create(StringReader stringreader, @Nullable T t0, @Nullable T t1) throws CommandSyntaxException;
    }

    @FunctionalInterface
    public interface a<T extends Number, R extends CriterionConditionValue<T>> {

        R create(@Nullable T t0, @Nullable T t1);
    }

    public static class FloatRange extends CriterionConditionValue<Float> {

        public static final CriterionConditionValue.FloatRange e = new CriterionConditionValue.FloatRange((Float) null, (Float) null);
        private final Double f;
        private final Double g;

        private static CriterionConditionValue.FloatRange a(StringReader stringreader, @Nullable Float ofloat, @Nullable Float ofloat1) throws CommandSyntaxException {
            if (ofloat != null && ofloat1 != null && ofloat > ofloat1) {
                throw CriterionConditionValue.FloatRange.b.createWithContext(stringreader);
            } else {
                return new CriterionConditionValue.FloatRange(ofloat, ofloat1);
            }
        }

        @Nullable
        private static Double a(@Nullable Float ofloat) {
            return ofloat == null ? null : ofloat.doubleValue() * ofloat.doubleValue();
        }

        private FloatRange(@Nullable Float ofloat, @Nullable Float ofloat1) {
            super(ofloat, ofloat1);
            this.f = a(ofloat);
            this.g = a(ofloat1);
        }

        public static CriterionConditionValue.FloatRange b(float f) {
            return new CriterionConditionValue.FloatRange(f, (Float) null);
        }

        public boolean d(float f) {
            return this.c != null && (Float) this.c > f ? false : this.d == null || (Float) this.d >= f;
        }

        public boolean a(double d0) {
            return this.f != null && this.f > d0 ? false : this.g == null || this.g >= d0;
        }

        public static CriterionConditionValue.FloatRange a(@Nullable JsonElement jsonelement) {
            return (CriterionConditionValue.FloatRange) a(jsonelement, CriterionConditionValue.FloatRange.e, ChatDeserializer::e, CriterionConditionValue.FloatRange::new);
        }

        public static CriterionConditionValue.FloatRange a(StringReader stringreader) throws CommandSyntaxException {
            return a(stringreader, (ofloat) -> {
                return ofloat;
            });
        }

        public static CriterionConditionValue.FloatRange a(StringReader stringreader, Function<Float, Float> function) throws CommandSyntaxException {
            return (CriterionConditionValue.FloatRange) a(stringreader, CriterionConditionValue.FloatRange::a, Float::parseFloat, CommandSyntaxException.BUILT_IN_EXCEPTIONS::readerInvalidFloat, function);
        }
    }

    public static class IntegerRange extends CriterionConditionValue<Integer> {

        public static final CriterionConditionValue.IntegerRange e = new CriterionConditionValue.IntegerRange((Integer) null, (Integer) null);
        private final Long f;
        private final Long g;

        private static CriterionConditionValue.IntegerRange a(StringReader stringreader, @Nullable Integer integer, @Nullable Integer integer1) throws CommandSyntaxException {
            if (integer != null && integer1 != null && integer > integer1) {
                throw CriterionConditionValue.IntegerRange.b.createWithContext(stringreader);
            } else {
                return new CriterionConditionValue.IntegerRange(integer, integer1);
            }
        }

        @Nullable
        private static Long a(@Nullable Integer integer) {
            return integer == null ? null : integer.longValue() * integer.longValue();
        }

        private IntegerRange(@Nullable Integer integer, @Nullable Integer integer1) {
            super(integer, integer1);
            this.f = a(integer);
            this.g = a(integer1);
        }

        public static CriterionConditionValue.IntegerRange a(int i) {
            return new CriterionConditionValue.IntegerRange(i, i);
        }

        public static CriterionConditionValue.IntegerRange b(int i) {
            return new CriterionConditionValue.IntegerRange(i, (Integer) null);
        }

        public boolean d(int i) {
            return this.c != null && (Integer) this.c > i ? false : this.d == null || (Integer) this.d >= i;
        }

        public static CriterionConditionValue.IntegerRange a(@Nullable JsonElement jsonelement) {
            return (CriterionConditionValue.IntegerRange) a(jsonelement, CriterionConditionValue.IntegerRange.e, ChatDeserializer::g, CriterionConditionValue.IntegerRange::new);
        }

        public static CriterionConditionValue.IntegerRange a(StringReader stringreader) throws CommandSyntaxException {
            return a(stringreader, (integer) -> {
                return integer;
            });
        }

        public static CriterionConditionValue.IntegerRange a(StringReader stringreader, Function<Integer, Integer> function) throws CommandSyntaxException {
            return (CriterionConditionValue.IntegerRange) a(stringreader, CriterionConditionValue.IntegerRange::a, Integer::parseInt, CommandSyntaxException.BUILT_IN_EXCEPTIONS::readerInvalidInt, function);
        }
    }
}
