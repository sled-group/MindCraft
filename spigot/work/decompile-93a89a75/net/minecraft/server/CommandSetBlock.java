package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class CommandSetBlock {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.setblock.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("setblock").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("block", (ArgumentType) ArgumentTile.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentTile.a(commandcontext, "block"), CommandSetBlock.Mode.REPLACE, (Predicate) null);
        })).then(CommandDispatcher.a("destroy").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentTile.a(commandcontext, "block"), CommandSetBlock.Mode.DESTROY, (Predicate) null);
        }))).then(CommandDispatcher.a("keep").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentTile.a(commandcontext, "block"), CommandSetBlock.Mode.REPLACE, (shapedetectorblock) -> {
                return shapedetectorblock.c().isEmpty(shapedetectorblock.getPosition());
            });
        }))).then(CommandDispatcher.a("replace").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "pos"), ArgumentTile.a(commandcontext, "block"), CommandSetBlock.Mode.REPLACE, (Predicate) null);
        })))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition, ArgumentTileLocation argumenttilelocation, CommandSetBlock.Mode commandsetblock_mode, @Nullable Predicate<ShapeDetectorBlock> predicate) throws CommandSyntaxException {
        WorldServer worldserver = commandlistenerwrapper.getWorld();

        if (predicate != null && !predicate.test(new ShapeDetectorBlock(worldserver, blockposition, true))) {
            throw CommandSetBlock.a.create();
        } else {
            boolean flag;

            if (commandsetblock_mode == CommandSetBlock.Mode.DESTROY) {
                worldserver.b(blockposition, true);
                flag = !argumenttilelocation.a().isAir();
            } else {
                TileEntity tileentity = worldserver.getTileEntity(blockposition);

                Clearable.a(tileentity);
                flag = true;
            }

            if (flag && !argumenttilelocation.a(worldserver, blockposition, 2)) {
                throw CommandSetBlock.a.create();
            } else {
                worldserver.update(blockposition, argumenttilelocation.a().getBlock());
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.setblock.success", new Object[]{blockposition.getX(), blockposition.getY(), blockposition.getZ()}), true);
                return 1;
            }
        }
    }

    public interface Filter {

        @Nullable
        ArgumentTileLocation filter(StructureBoundingBox structureboundingbox, BlockPosition blockposition, ArgumentTileLocation argumenttilelocation, WorldServer worldserver);
    }

    public static enum Mode {

        REPLACE, DESTROY;

        private Mode() {}
    }
}
