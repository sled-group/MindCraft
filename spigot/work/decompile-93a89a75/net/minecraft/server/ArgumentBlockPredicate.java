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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class ArgumentBlockPredicate implements ArgumentType<ArgumentBlockPredicate.b> {

    private static final Collection<String> a = Arrays.asList("stone", "minecraft:stone", "stone[foo=bar]", "#stone", "#stone[foo=bar]{baz=nbt}");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.block.tag.unknown", new Object[]{object});
    });

    public ArgumentBlockPredicate() {}

    public static ArgumentBlockPredicate a() {
        return new ArgumentBlockPredicate();
    }

    public ArgumentBlockPredicate.b parse(StringReader stringreader) throws CommandSyntaxException {
        ArgumentBlock argumentblock = (new ArgumentBlock(stringreader, true)).a(true);

        if (argumentblock.getBlockData() != null) {
            ArgumentBlockPredicate.a argumentblockpredicate_a = new ArgumentBlockPredicate.a(argumentblock.getBlockData(), argumentblock.getStateMap().keySet(), argumentblock.c());

            return (tagregistry) -> {
                return argumentblockpredicate_a;
            };
        } else {
            MinecraftKey minecraftkey = argumentblock.d();

            return (tagregistry) -> {
                Tag<Block> tag = tagregistry.getBlockTags().a(minecraftkey);

                if (tag == null) {
                    throw ArgumentBlockPredicate.b.create(minecraftkey.toString());
                } else {
                    return new ArgumentBlockPredicate.c(tag, argumentblock.j(), argumentblock.c());
                }
            };
        }
    }

    public static Predicate<ShapeDetectorBlock> a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentBlockPredicate.b) commandcontext.getArgument(s, ArgumentBlockPredicate.b.class)).create(((CommandListenerWrapper) commandcontext.getSource()).getServer().getTagRegistry());
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        StringReader stringreader = new StringReader(suggestionsbuilder.getInput());

        stringreader.setCursor(suggestionsbuilder.getStart());
        ArgumentBlock argumentblock = new ArgumentBlock(stringreader, true);

        try {
            argumentblock.a(true);
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        return argumentblock.a(suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentBlockPredicate.a;
    }

    static class c implements Predicate<ShapeDetectorBlock> {

        private final Tag<Block> a;
        @Nullable
        private final NBTTagCompound b;
        private final Map<String, String> c;

        private c(Tag<Block> tag, Map<String, String> map, @Nullable NBTTagCompound nbttagcompound) {
            this.a = tag;
            this.c = map;
            this.b = nbttagcompound;
        }

        public boolean test(ShapeDetectorBlock shapedetectorblock) {
            IBlockData iblockdata = shapedetectorblock.a();

            if (!iblockdata.a(this.a)) {
                return false;
            } else {
                Iterator iterator = this.c.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<String, String> entry = (Entry) iterator.next();
                    IBlockState<?> iblockstate = iblockdata.getBlock().getStates().a((String) entry.getKey());

                    if (iblockstate == null) {
                        return false;
                    }

                    Comparable<?> comparable = (Comparable) iblockstate.b((String) entry.getValue()).orElse((Object) null);

                    if (comparable == null) {
                        return false;
                    }

                    if (iblockdata.get(iblockstate) != comparable) {
                        return false;
                    }
                }

                if (this.b == null) {
                    return true;
                } else {
                    TileEntity tileentity = shapedetectorblock.b();

                    return tileentity != null && GameProfileSerializer.a(this.b, tileentity.save(new NBTTagCompound()), true);
                }
            }
        }
    }

    static class a implements Predicate<ShapeDetectorBlock> {

        private final IBlockData a;
        private final Set<IBlockState<?>> b;
        @Nullable
        private final NBTTagCompound c;

        public a(IBlockData iblockdata, Set<IBlockState<?>> set, @Nullable NBTTagCompound nbttagcompound) {
            this.a = iblockdata;
            this.b = set;
            this.c = nbttagcompound;
        }

        public boolean test(ShapeDetectorBlock shapedetectorblock) {
            IBlockData iblockdata = shapedetectorblock.a();

            if (iblockdata.getBlock() != this.a.getBlock()) {
                return false;
            } else {
                Iterator iterator = this.b.iterator();

                while (iterator.hasNext()) {
                    IBlockState<?> iblockstate = (IBlockState) iterator.next();

                    if (iblockdata.get(iblockstate) != this.a.get(iblockstate)) {
                        return false;
                    }
                }

                if (this.c == null) {
                    return true;
                } else {
                    TileEntity tileentity = shapedetectorblock.b();

                    return tileentity != null && GameProfileSerializer.a(this.c, tileentity.save(new NBTTagCompound()), true);
                }
            }
        }
    }

    public interface b {

        Predicate<ShapeDetectorBlock> create(TagRegistry tagregistry) throws CommandSyntaxException;
    }
}
