package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Locale;
import java.util.function.Function;

public class CommandDataAccessorTile implements CommandDataAccessor {

    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.data.block.invalid", new Object[0]));
    public static final Function<String, CommandData.c> a = (s) -> {
        return new CommandData.c() {
            @Override
            public CommandDataAccessor a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException {
                BlockPosition blockposition = ArgumentPosition.a(commandcontext, s + "Pos");
                TileEntity tileentity = ((CommandListenerWrapper) commandcontext.getSource()).getWorld().getTileEntity(blockposition);

                if (tileentity == null) {
                    throw CommandDataAccessorTile.b.create();
                } else {
                    return new CommandDataAccessorTile(tileentity, blockposition);
                }
            }

            @Override
            public ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, Function<ArgumentBuilder<CommandListenerWrapper, ?>, ArgumentBuilder<CommandListenerWrapper, ?>> function) {
                return argumentbuilder.then(CommandDispatcher.a("block").then((ArgumentBuilder) function.apply(CommandDispatcher.a(s + "Pos", (ArgumentType) ArgumentPosition.a()))));
            }
        };
    };
    private final TileEntity c;
    private final BlockPosition d;

    public CommandDataAccessorTile(TileEntity tileentity, BlockPosition blockposition) {
        this.c = tileentity;
        this.d = blockposition;
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("x", this.d.getX());
        nbttagcompound.setInt("y", this.d.getY());
        nbttagcompound.setInt("z", this.d.getZ());
        this.c.load(nbttagcompound);
        this.c.update();
        IBlockData iblockdata = this.c.getWorld().getType(this.d);

        this.c.getWorld().notify(this.d, iblockdata, iblockdata, 3);
    }

    @Override
    public NBTTagCompound a() {
        return this.c.save(new NBTTagCompound());
    }

    @Override
    public IChatBaseComponent b() {
        return new ChatMessage("commands.data.block.modified", new Object[]{this.d.getX(), this.d.getY(), this.d.getZ()});
    }

    @Override
    public IChatBaseComponent a(NBTBase nbtbase) {
        return new ChatMessage("commands.data.block.query", new Object[]{this.d.getX(), this.d.getY(), this.d.getZ(), nbtbase.k()});
    }

    @Override
    public IChatBaseComponent a(ArgumentNBTKey.h argumentnbtkey_h, double d0, int i) {
        return new ChatMessage("commands.data.block.get", new Object[]{argumentnbtkey_h, this.d.getX(), this.d.getY(), this.d.getZ(), String.format(Locale.ROOT, "%.2f", d0), i});
    }
}
