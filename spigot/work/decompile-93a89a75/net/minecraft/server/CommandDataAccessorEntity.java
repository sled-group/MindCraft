package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Function;

public class CommandDataAccessorEntity implements CommandDataAccessor {

    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.data.entity.invalid", new Object[0]));
    public static final Function<String, CommandData.c> a = (s) -> {
        return new CommandData.c() {
            @Override
            public CommandDataAccessor a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                return new CommandDataAccessorEntity(ArgumentEntity.a(commandcontext, s));
            }

            @Override
            public ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, Function<ArgumentBuilder<CommandListenerWrapper, ?>, ArgumentBuilder<CommandListenerWrapper, ?>> function) {
                return argumentbuilder.then(CommandDispatcher.a("entity").then((ArgumentBuilder) function.apply(CommandDispatcher.a(s, (ArgumentType) ArgumentEntity.a()))));
            }
        };
    };
    private final Entity c;

    public CommandDataAccessorEntity(Entity entity) {
        this.c = entity;
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) throws CommandSyntaxException {
        if (this.c instanceof EntityHuman) {
            throw CommandDataAccessorEntity.b.create();
        } else {
            UUID uuid = this.c.getUniqueID();

            this.c.f(nbttagcompound);
            this.c.a(uuid);
        }
    }

    @Override
    public NBTTagCompound a() {
        return CriterionConditionNBT.b(this.c);
    }

    @Override
    public IChatBaseComponent b() {
        return new ChatMessage("commands.data.entity.modified", new Object[]{this.c.getScoreboardDisplayName()});
    }

    @Override
    public IChatBaseComponent a(NBTBase nbtbase) {
        return new ChatMessage("commands.data.entity.query", new Object[]{this.c.getScoreboardDisplayName(), nbtbase.k()});
    }

    @Override
    public IChatBaseComponent a(ArgumentNBTKey.h argumentnbtkey_h, double d0, int i) {
        return new ChatMessage("commands.data.entity.get", new Object[]{argumentnbtkey_h, this.c.getScoreboardDisplayName(), String.format(Locale.ROOT, "%.2f", d0), i});
    }
}
