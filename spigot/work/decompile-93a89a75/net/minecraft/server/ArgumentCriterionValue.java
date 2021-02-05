package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.Collection;

public interface ArgumentCriterionValue<T extends CriterionConditionValue<?>> extends ArgumentType<T> {

    static ArgumentCriterionValue.b a() {
        return new ArgumentCriterionValue.b();
    }

    public abstract static class c<T extends ArgumentCriterionValue<?>> implements ArgumentSerializer<T> {

        public c() {}

        public void a(T t0, PacketDataSerializer packetdataserializer) {}

        public void a(T t0, JsonObject jsonobject) {}
    }

    public static class a implements ArgumentCriterionValue<CriterionConditionValue.FloatRange> {

        private static final Collection<String> a = Arrays.asList("0..5.2", "0", "-5.4", "-100.76..", "..100");

        public a() {}

        public CriterionConditionValue.FloatRange parse(StringReader stringreader) throws CommandSyntaxException {
            return CriterionConditionValue.FloatRange.a(stringreader);
        }

        public Collection<String> getExamples() {
            return ArgumentCriterionValue.a.a;
        }

        public static class a extends ArgumentCriterionValue.c<ArgumentCriterionValue.a> {

            public a() {}

            @Override
            public ArgumentCriterionValue.a b(PacketDataSerializer packetdataserializer) {
                return new ArgumentCriterionValue.a();
            }
        }
    }

    public static class b implements ArgumentCriterionValue<CriterionConditionValue.IntegerRange> {

        private static final Collection<String> a = Arrays.asList("0..5", "0", "-5", "-100..", "..100");

        public b() {}

        public static CriterionConditionValue.IntegerRange a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
            return (CriterionConditionValue.IntegerRange) commandcontext.getArgument(s, CriterionConditionValue.IntegerRange.class);
        }

        public CriterionConditionValue.IntegerRange parse(StringReader stringreader) throws CommandSyntaxException {
            return CriterionConditionValue.IntegerRange.a(stringreader);
        }

        public Collection<String> getExamples() {
            return ArgumentCriterionValue.b.a;
        }

        public static class a extends ArgumentCriterionValue.c<ArgumentCriterionValue.b> {

            public a() {}

            @Override
            public ArgumentCriterionValue.b b(PacketDataSerializer packetdataserializer) {
                return new ArgumentCriterionValue.b();
            }
        }
    }
}
