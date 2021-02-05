package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class CommandClone {

    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.clone.overlap", new Object[0]));
    private static final Dynamic2CommandExceptionType c = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("commands.clone.toobig", new Object[]{object, object1});
    });
    private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.clone.failed", new Object[0]));
    public static final Predicate<ShapeDetectorBlock> a = (shapedetectorblock) -> {
        return !shapedetectorblock.a().isAir();
    };

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("clone").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("begin", (ArgumentType) ArgumentPosition.a()).then(CommandDispatcher.a("end", (ArgumentType) ArgumentPosition.a()).then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("destination", (ArgumentType) ArgumentPosition.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), (shapedetectorblock) -> {
                return true;
            }, CommandClone.Mode.NORMAL);
        })).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("replace").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), (shapedetectorblock) -> {
                return true;
            }, CommandClone.Mode.NORMAL);
        })).then(CommandDispatcher.a("force").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), (shapedetectorblock) -> {
                return true;
            }, CommandClone.Mode.FORCE);
        }))).then(CommandDispatcher.a("move").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), (shapedetectorblock) -> {
                return true;
            }, CommandClone.Mode.MOVE);
        }))).then(CommandDispatcher.a("normal").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), (shapedetectorblock) -> {
                return true;
            }, CommandClone.Mode.NORMAL);
        })))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("masked").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), CommandClone.a, CommandClone.Mode.NORMAL);
        })).then(CommandDispatcher.a("force").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), CommandClone.a, CommandClone.Mode.FORCE);
        }))).then(CommandDispatcher.a("move").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), CommandClone.a, CommandClone.Mode.MOVE);
        }))).then(CommandDispatcher.a("normal").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), CommandClone.a, CommandClone.Mode.NORMAL);
        })))).then(CommandDispatcher.a("filtered").then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("filter", (ArgumentType) ArgumentBlockPredicate.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), ArgumentBlockPredicate.a(commandcontext, "filter"), CommandClone.Mode.NORMAL);
        })).then(CommandDispatcher.a("force").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), ArgumentBlockPredicate.a(commandcontext, "filter"), CommandClone.Mode.FORCE);
        }))).then(CommandDispatcher.a("move").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), ArgumentBlockPredicate.a(commandcontext, "filter"), CommandClone.Mode.MOVE);
        }))).then(CommandDispatcher.a("normal").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.a(commandcontext, "begin"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), ArgumentBlockPredicate.a(commandcontext, "filter"), CommandClone.Mode.NORMAL);
        }))))))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition, BlockPosition blockposition1, BlockPosition blockposition2, Predicate<ShapeDetectorBlock> predicate, CommandClone.Mode commandclone_mode) throws CommandSyntaxException {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(blockposition, blockposition1);
        BlockPosition blockposition3 = blockposition2.a(structureboundingbox.b());
        StructureBoundingBox structureboundingbox1 = new StructureBoundingBox(blockposition2, blockposition3);

        if (!commandclone_mode.a() && structureboundingbox1.b(structureboundingbox)) {
            throw CommandClone.b.create();
        } else {
            int i = structureboundingbox.c() * structureboundingbox.d() * structureboundingbox.e();

            if (i > 32768) {
                throw CommandClone.c.create(32768, i);
            } else {
                WorldServer worldserver = commandlistenerwrapper.getWorld();

                if (worldserver.areChunksLoadedBetween(blockposition, blockposition1) && worldserver.areChunksLoadedBetween(blockposition2, blockposition3)) {
                    List<CommandClone.CommandCloneStoredTileEntity> list = Lists.newArrayList();
                    List<CommandClone.CommandCloneStoredTileEntity> list1 = Lists.newArrayList();
                    List<CommandClone.CommandCloneStoredTileEntity> list2 = Lists.newArrayList();
                    Deque<BlockPosition> deque = Lists.newLinkedList();
                    BlockPosition blockposition4 = new BlockPosition(structureboundingbox1.a - structureboundingbox.a, structureboundingbox1.b - structureboundingbox.b, structureboundingbox1.c - structureboundingbox.c);

                    int j;

                    for (int k = structureboundingbox.c; k <= structureboundingbox.f; ++k) {
                        for (int l = structureboundingbox.b; l <= structureboundingbox.e; ++l) {
                            for (j = structureboundingbox.a; j <= structureboundingbox.d; ++j) {
                                BlockPosition blockposition5 = new BlockPosition(j, l, k);
                                BlockPosition blockposition6 = blockposition5.a((BaseBlockPosition) blockposition4);
                                ShapeDetectorBlock shapedetectorblock = new ShapeDetectorBlock(worldserver, blockposition5, false);
                                IBlockData iblockdata = shapedetectorblock.a();

                                if (predicate.test(shapedetectorblock)) {
                                    TileEntity tileentity = worldserver.getTileEntity(blockposition5);

                                    if (tileentity != null) {
                                        NBTTagCompound nbttagcompound = tileentity.save(new NBTTagCompound());

                                        list1.add(new CommandClone.CommandCloneStoredTileEntity(blockposition6, iblockdata, nbttagcompound));
                                        deque.addLast(blockposition5);
                                    } else if (!iblockdata.g(worldserver, blockposition5) && !iblockdata.o(worldserver, blockposition5)) {
                                        list2.add(new CommandClone.CommandCloneStoredTileEntity(blockposition6, iblockdata, (NBTTagCompound) null));
                                        deque.addFirst(blockposition5);
                                    } else {
                                        list.add(new CommandClone.CommandCloneStoredTileEntity(blockposition6, iblockdata, (NBTTagCompound) null));
                                        deque.addLast(blockposition5);
                                    }
                                }
                            }
                        }
                    }

                    if (commandclone_mode == CommandClone.Mode.MOVE) {
                        Iterator iterator = deque.iterator();

                        BlockPosition blockposition7;

                        while (iterator.hasNext()) {
                            blockposition7 = (BlockPosition) iterator.next();
                            TileEntity tileentity1 = worldserver.getTileEntity(blockposition7);

                            Clearable.a(tileentity1);
                            worldserver.setTypeAndData(blockposition7, Blocks.BARRIER.getBlockData(), 2);
                        }

                        iterator = deque.iterator();

                        while (iterator.hasNext()) {
                            blockposition7 = (BlockPosition) iterator.next();
                            worldserver.setTypeAndData(blockposition7, Blocks.AIR.getBlockData(), 3);
                        }
                    }

                    List<CommandClone.CommandCloneStoredTileEntity> list3 = Lists.newArrayList();

                    list3.addAll(list);
                    list3.addAll(list1);
                    list3.addAll(list2);
                    List<CommandClone.CommandCloneStoredTileEntity> list4 = Lists.reverse(list3);
                    Iterator iterator1 = list4.iterator();

                    while (iterator1.hasNext()) {
                        CommandClone.CommandCloneStoredTileEntity commandclone_commandclonestoredtileentity = (CommandClone.CommandCloneStoredTileEntity) iterator1.next();
                        TileEntity tileentity2 = worldserver.getTileEntity(commandclone_commandclonestoredtileentity.a);

                        Clearable.a(tileentity2);
                        worldserver.setTypeAndData(commandclone_commandclonestoredtileentity.a, Blocks.BARRIER.getBlockData(), 2);
                    }

                    j = 0;
                    Iterator iterator2 = list3.iterator();

                    CommandClone.CommandCloneStoredTileEntity commandclone_commandclonestoredtileentity1;

                    while (iterator2.hasNext()) {
                        commandclone_commandclonestoredtileentity1 = (CommandClone.CommandCloneStoredTileEntity) iterator2.next();
                        if (worldserver.setTypeAndData(commandclone_commandclonestoredtileentity1.a, commandclone_commandclonestoredtileentity1.b, 2)) {
                            ++j;
                        }
                    }

                    for (iterator2 = list1.iterator(); iterator2.hasNext(); worldserver.setTypeAndData(commandclone_commandclonestoredtileentity1.a, commandclone_commandclonestoredtileentity1.b, 2)) {
                        commandclone_commandclonestoredtileentity1 = (CommandClone.CommandCloneStoredTileEntity) iterator2.next();
                        TileEntity tileentity3 = worldserver.getTileEntity(commandclone_commandclonestoredtileentity1.a);

                        if (commandclone_commandclonestoredtileentity1.c != null && tileentity3 != null) {
                            commandclone_commandclonestoredtileentity1.c.setInt("x", commandclone_commandclonestoredtileentity1.a.getX());
                            commandclone_commandclonestoredtileentity1.c.setInt("y", commandclone_commandclonestoredtileentity1.a.getY());
                            commandclone_commandclonestoredtileentity1.c.setInt("z", commandclone_commandclonestoredtileentity1.a.getZ());
                            tileentity3.load(commandclone_commandclonestoredtileentity1.c);
                            tileentity3.update();
                        }
                    }

                    iterator2 = list4.iterator();

                    while (iterator2.hasNext()) {
                        commandclone_commandclonestoredtileentity1 = (CommandClone.CommandCloneStoredTileEntity) iterator2.next();
                        worldserver.update(commandclone_commandclonestoredtileentity1.a, commandclone_commandclonestoredtileentity1.b.getBlock());
                    }

                    worldserver.getBlockTickList().a(structureboundingbox, blockposition4);
                    if (j == 0) {
                        throw CommandClone.d.create();
                    } else {
                        commandlistenerwrapper.sendMessage(new ChatMessage("commands.clone.success", new Object[]{j}), true);
                        return j;
                    }
                } else {
                    throw ArgumentPosition.a.create();
                }
            }
        }
    }

    static class CommandCloneStoredTileEntity {

        public final BlockPosition a;
        public final IBlockData b;
        @Nullable
        public final NBTTagCompound c;

        public CommandCloneStoredTileEntity(BlockPosition blockposition, IBlockData iblockdata, @Nullable NBTTagCompound nbttagcompound) {
            this.a = blockposition;
            this.b = iblockdata;
            this.c = nbttagcompound;
        }
    }

    static enum Mode {

        FORCE(true), MOVE(true), NORMAL(false);

        private final boolean d;

        private Mode(boolean flag) {
            this.d = flag;
        }

        public boolean a() {
            return this.d;
        }
    }
}
