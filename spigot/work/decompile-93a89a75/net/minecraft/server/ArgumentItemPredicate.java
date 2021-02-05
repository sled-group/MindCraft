package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class ArgumentItemPredicate implements ArgumentType<ArgumentItemPredicate.b> {

    private static final Collection<String> a = Arrays.asList("stick", "minecraft:stick", "#stick", "#stick{foo=bar}");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.item.tag.unknown", new Object[]{object});
    });

    public ArgumentItemPredicate() {}

    public static ArgumentItemPredicate a() {
        return new ArgumentItemPredicate();
    }

    public ArgumentItemPredicate.b parse(StringReader stringreader) throws CommandSyntaxException {
        ArgumentParserItemStack argumentparseritemstack = (new ArgumentParserItemStack(stringreader, true)).h();

        if (argumentparseritemstack.b() != null) {
            ArgumentItemPredicate.a argumentitempredicate_a = new ArgumentItemPredicate.a(argumentparseritemstack.b(), argumentparseritemstack.c());

            return (commandcontext) -> {
                return argumentitempredicate_a;
            };
        } else {
            MinecraftKey minecraftkey = argumentparseritemstack.d();

            return (commandcontext) -> {
                Tag<Item> tag = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getTagRegistry().getItemTags().a(minecraftkey);

                if (tag == null) {
                    throw ArgumentItemPredicate.b.create(minecraftkey.toString());
                } else {
                    return new ArgumentItemPredicate.c(tag, argumentparseritemstack.c());
                }
            };
        }
    }

    public static Predicate<ItemStack> a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentItemPredicate.b) commandcontext.getArgument(s, ArgumentItemPredicate.b.class)).create(commandcontext);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        StringReader stringreader = new StringReader(suggestionsbuilder.getInput());

        stringreader.setCursor(suggestionsbuilder.getStart());
        ArgumentParserItemStack argumentparseritemstack = new ArgumentParserItemStack(stringreader, true);

        try {
            argumentparseritemstack.h();
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        return argumentparseritemstack.a(suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentItemPredicate.a;
    }

    static class c implements Predicate<ItemStack> {

        private final Tag<Item> a;
        @Nullable
        private final NBTTagCompound b;

        public c(Tag<Item> tag, @Nullable NBTTagCompound nbttagcompound) {
            this.a = tag;
            this.b = nbttagcompound;
        }

        public boolean test(ItemStack itemstack) {
            return this.a.isTagged(itemstack.getItem()) && GameProfileSerializer.a(this.b, itemstack.getTag(), true);
        }
    }

    static class a implements Predicate<ItemStack> {

        private final Item a;
        @Nullable
        private final NBTTagCompound b;

        public a(Item item, @Nullable NBTTagCompound nbttagcompound) {
            this.a = item;
            this.b = nbttagcompound;
        }

        public boolean test(ItemStack itemstack) {
            return itemstack.getItem() == this.a && GameProfileSerializer.a(this.b, itemstack.getTag(), true);
        }
    }

    public interface b {

        Predicate<ItemStack> create(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;
    }
}
