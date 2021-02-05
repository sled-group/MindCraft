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

public class ArgumentInventorySlot implements ArgumentType<Integer> {

    private static final Collection<String> a = Arrays.asList("container.5", "12", "weapon");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("slot.unknown", new Object[]{object});
    });
    private static final Map<String, Integer> c = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        int i;

        for (i = 0; i < 54; ++i) {
            hashmap.put("container." + i, i);
        }

        for (i = 0; i < 9; ++i) {
            hashmap.put("hotbar." + i, i);
        }

        for (i = 0; i < 27; ++i) {
            hashmap.put("inventory." + i, 9 + i);
        }

        for (i = 0; i < 27; ++i) {
            hashmap.put("enderchest." + i, 200 + i);
        }

        for (i = 0; i < 8; ++i) {
            hashmap.put("villager." + i, 300 + i);
        }

        for (i = 0; i < 15; ++i) {
            hashmap.put("horse." + i, 500 + i);
        }

        hashmap.put("weapon", 98);
        hashmap.put("weapon.mainhand", 98);
        hashmap.put("weapon.offhand", 99);
        hashmap.put("armor.head", 100 + EnumItemSlot.HEAD.b());
        hashmap.put("armor.chest", 100 + EnumItemSlot.CHEST.b());
        hashmap.put("armor.legs", 100 + EnumItemSlot.LEGS.b());
        hashmap.put("armor.feet", 100 + EnumItemSlot.FEET.b());
        hashmap.put("horse.saddle", 400);
        hashmap.put("horse.armor", 401);
        hashmap.put("horse.chest", 499);
    });

    public ArgumentInventorySlot() {}

    public static ArgumentInventorySlot a() {
        return new ArgumentInventorySlot();
    }

    public static int a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (Integer) commandcontext.getArgument(s, Integer.class);
    }

    public Integer parse(StringReader stringreader) throws CommandSyntaxException {
        String s = stringreader.readUnquotedString();

        if (!ArgumentInventorySlot.c.containsKey(s)) {
            throw ArgumentInventorySlot.b.create(s);
        } else {
            return (Integer) ArgumentInventorySlot.c.get(s);
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.b((Iterable) ArgumentInventorySlot.c.keySet(), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentInventorySlot.a;
    }
}
