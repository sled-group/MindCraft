package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import javax.annotation.Nullable;

public class ArgumentAnchor implements ArgumentType<ArgumentAnchor.Anchor> {

    private static final Collection<String> a = Arrays.asList("eyes", "feet");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.anchor.invalid", new Object[]{object});
    });

    public ArgumentAnchor() {}

    public static ArgumentAnchor.Anchor a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (ArgumentAnchor.Anchor) commandcontext.getArgument(s, ArgumentAnchor.Anchor.class);
    }

    public static ArgumentAnchor a() {
        return new ArgumentAnchor();
    }

    public ArgumentAnchor.Anchor parse(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();
        String s = stringreader.readUnquotedString();
        ArgumentAnchor.Anchor argumentanchor_anchor = ArgumentAnchor.Anchor.a(s);

        if (argumentanchor_anchor == null) {
            stringreader.setCursor(i);
            throw ArgumentAnchor.b.createWithContext(stringreader, s);
        } else {
            return argumentanchor_anchor;
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.b((Iterable) ArgumentAnchor.Anchor.c.keySet(), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentAnchor.a;
    }

    public static enum Anchor {

        FEET("feet", (vec3d, entity) -> {
            return vec3d;
        }), EYES("eyes", (vec3d, entity) -> {
            return new Vec3D(vec3d.x, vec3d.y + (double) entity.getHeadHeight(), vec3d.z);
        });

        private static final Map<String, ArgumentAnchor.Anchor> c = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
            ArgumentAnchor.Anchor[] aargumentanchor_anchor = values();
            int i = aargumentanchor_anchor.length;

            for (int j = 0; j < i; ++j) {
                ArgumentAnchor.Anchor argumentanchor_anchor = aargumentanchor_anchor[j];

                hashmap.put(argumentanchor_anchor.d, argumentanchor_anchor);
            }

        });
        private final String d;
        private final BiFunction<Vec3D, Entity, Vec3D> e;

        private Anchor(String s, BiFunction bifunction) {
            this.d = s;
            this.e = bifunction;
        }

        @Nullable
        public static ArgumentAnchor.Anchor a(String s) {
            return (ArgumentAnchor.Anchor) ArgumentAnchor.Anchor.c.get(s);
        }

        public Vec3D a(Entity entity) {
            return (Vec3D) this.e.apply(new Vec3D(entity.locX, entity.locY, entity.locZ), entity);
        }

        public Vec3D a(CommandListenerWrapper commandlistenerwrapper) {
            Entity entity = commandlistenerwrapper.getEntity();

            return entity == null ? commandlistenerwrapper.getPosition() : (Vec3D) this.e.apply(commandlistenerwrapper.getPosition(), entity);
        }
    }
}
