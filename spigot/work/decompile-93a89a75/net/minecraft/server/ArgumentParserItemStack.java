package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.annotation.Nullable;

public class ArgumentParserItemStack {

    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.item.tag.disallowed", new Object[0]));
    public static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.item.id.invalid", new Object[]{object});
    });
    private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> c = SuggestionsBuilder::buildFuture;
    private final StringReader d;
    private final boolean e;
    private final Map<IBlockState<?>, Comparable<?>> f = Maps.newHashMap();
    private Item g;
    @Nullable
    private NBTTagCompound h;
    private MinecraftKey i = new MinecraftKey("");
    private int j;
    private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> k;

    public ArgumentParserItemStack(StringReader stringreader, boolean flag) {
        this.k = ArgumentParserItemStack.c;
        this.d = stringreader;
        this.e = flag;
    }

    public Item b() {
        return this.g;
    }

    @Nullable
    public NBTTagCompound c() {
        return this.h;
    }

    public MinecraftKey d() {
        return this.i;
    }

    public void e() throws CommandSyntaxException {
        int i = this.d.getCursor();
        MinecraftKey minecraftkey = MinecraftKey.a(this.d);

        this.g = (Item) IRegistry.ITEM.getOptional(minecraftkey).orElseThrow(() -> {
            this.d.setCursor(i);
            return ArgumentParserItemStack.b.createWithContext(this.d, minecraftkey.toString());
        });
    }

    public void f() throws CommandSyntaxException {
        if (!this.e) {
            throw ArgumentParserItemStack.a.create();
        } else {
            this.k = this::c;
            this.d.expect('#');
            this.j = this.d.getCursor();
            this.i = MinecraftKey.a(this.d);
        }
    }

    public void g() throws CommandSyntaxException {
        this.h = (new MojangsonParser(this.d)).f();
    }

    public ArgumentParserItemStack h() throws CommandSyntaxException {
        this.k = this::d;
        if (this.d.canRead() && this.d.peek() == '#') {
            this.f();
        } else {
            this.e();
            this.k = this::b;
        }

        if (this.d.canRead() && this.d.peek() == '{') {
            this.k = ArgumentParserItemStack.c;
            this.g();
        }

        return this;
    }

    private CompletableFuture<Suggestions> b(SuggestionsBuilder suggestionsbuilder) {
        if (suggestionsbuilder.getRemaining().isEmpty()) {
            suggestionsbuilder.suggest(String.valueOf('{'));
        }

        return suggestionsbuilder.buildFuture();
    }

    private CompletableFuture<Suggestions> c(SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.a((Iterable) TagsItem.a().a(), suggestionsbuilder.createOffset(this.j));
    }

    private CompletableFuture<Suggestions> d(SuggestionsBuilder suggestionsbuilder) {
        if (this.e) {
            ICompletionProvider.a((Iterable) TagsItem.a().a(), suggestionsbuilder, String.valueOf('#'));
        }

        return ICompletionProvider.a((Iterable) IRegistry.ITEM.keySet(), suggestionsbuilder);
    }

    public CompletableFuture<Suggestions> a(SuggestionsBuilder suggestionsbuilder) {
        return (CompletableFuture) this.k.apply(suggestionsbuilder.createOffset(this.d.getCursor()));
    }
}
