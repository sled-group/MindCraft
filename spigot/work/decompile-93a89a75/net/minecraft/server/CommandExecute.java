package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.ResultConsumer;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

public class CommandExecute {

    private static final Dynamic2CommandExceptionType a = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("commands.execute.blocks.toobig", new Object[]{object, object1});
    });
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.execute.conditional.fail", new Object[0]));
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.execute.conditional.fail_count", new Object[]{object});
    });
    private static final BinaryOperator<ResultConsumer<CommandListenerWrapper>> d = (resultconsumer, resultconsumer1) -> {
        return (commandcontext, flag, i) -> {
            resultconsumer.onCommandComplete(commandcontext, flag, i);
            resultconsumer1.onCommandComplete(commandcontext, flag, i);
        };
    };

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        LiteralCommandNode<CommandListenerWrapper> literalcommandnode = com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) CommandDispatcher.a("execute").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        }));

        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("execute").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("run").redirect(com_mojang_brigadier_commanddispatcher.getRoot()))).then(a((CommandNode) literalcommandnode, CommandDispatcher.a("if"), true))).then(a((CommandNode) literalcommandnode, CommandDispatcher.a("unless"), false))).then(CommandDispatcher.a("as").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).fork(literalcommandnode, (commandcontext) -> {
            List<CommandListenerWrapper> list = Lists.newArrayList();
            Iterator iterator = ArgumentEntity.c(commandcontext, "targets").iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                list.add(((CommandListenerWrapper) commandcontext.getSource()).a(entity));
            }

            return list;
        })))).then(CommandDispatcher.a("at").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).fork(literalcommandnode, (commandcontext) -> {
            List<CommandListenerWrapper> list = Lists.newArrayList();
            Iterator iterator = ArgumentEntity.c(commandcontext, "targets").iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                list.add(((CommandListenerWrapper) commandcontext.getSource()).a((WorldServer) entity.world).a(entity.bP()).a(entity.aU()));
            }

            return list;
        })))).then(((LiteralArgumentBuilder) CommandDispatcher.a("store").then(a(literalcommandnode, CommandDispatcher.a("result"), true))).then(a(literalcommandnode, CommandDispatcher.a("success"), false)))).then(((LiteralArgumentBuilder) CommandDispatcher.a("positioned").then(CommandDispatcher.a("pos", (ArgumentType) ArgumentVec3.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).a(ArgumentVec3.a(commandcontext, "pos"));
        }))).then(CommandDispatcher.a("as").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).fork(literalcommandnode, (commandcontext) -> {
            List<CommandListenerWrapper> list = Lists.newArrayList();
            Iterator iterator = ArgumentEntity.c(commandcontext, "targets").iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                list.add(((CommandListenerWrapper) commandcontext.getSource()).a(entity.bP()));
            }

            return list;
        }))))).then(((LiteralArgumentBuilder) CommandDispatcher.a("rotated").then(CommandDispatcher.a("rot", (ArgumentType) ArgumentRotation.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).a(ArgumentRotation.a(commandcontext, "rot").b((CommandListenerWrapper) commandcontext.getSource()));
        }))).then(CommandDispatcher.a("as").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).fork(literalcommandnode, (commandcontext) -> {
            List<CommandListenerWrapper> list = Lists.newArrayList();
            Iterator iterator = ArgumentEntity.c(commandcontext, "targets").iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                list.add(((CommandListenerWrapper) commandcontext.getSource()).a(entity.aU()));
            }

            return list;
        }))))).then(((LiteralArgumentBuilder) CommandDispatcher.a("facing").then(CommandDispatcher.a("entity").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.multipleEntities()).then(CommandDispatcher.a("anchor", (ArgumentType) ArgumentAnchor.a()).fork(literalcommandnode, (commandcontext) -> {
            List<CommandListenerWrapper> list = Lists.newArrayList();
            ArgumentAnchor.Anchor argumentanchor_anchor = ArgumentAnchor.a(commandcontext, "anchor");
            Iterator iterator = ArgumentEntity.c(commandcontext, "targets").iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                list.add(((CommandListenerWrapper) commandcontext.getSource()).a(entity, argumentanchor_anchor));
            }

            return list;
        }))))).then(CommandDispatcher.a("pos", (ArgumentType) ArgumentVec3.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).b(ArgumentVec3.a(commandcontext, "pos"));
        })))).then(CommandDispatcher.a("align").then(CommandDispatcher.a("axes", (ArgumentType) ArgumentRotationAxis.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).a(((CommandListenerWrapper) commandcontext.getSource()).getPosition().a(ArgumentRotationAxis.a(commandcontext, "axes")));
        })))).then(CommandDispatcher.a("anchored").then(CommandDispatcher.a("anchor", (ArgumentType) ArgumentAnchor.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).a(ArgumentAnchor.a(commandcontext, "anchor"));
        })))).then(CommandDispatcher.a("in").then(CommandDispatcher.a("dimension", (ArgumentType) ArgumentDimension.a()).redirect(literalcommandnode, (commandcontext) -> {
            return ((CommandListenerWrapper) commandcontext.getSource()).a(((CommandListenerWrapper) commandcontext.getSource()).getServer().getWorldServer(ArgumentDimension.a(commandcontext, "dimension")));
        }))));
    }

    private static ArgumentBuilder<CommandListenerWrapper, ?> a(LiteralCommandNode<CommandListenerWrapper> literalcommandnode, LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder, boolean flag) {
        literalargumentbuilder.then(CommandDispatcher.a("score").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).redirect(literalcommandnode, (commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.a(commandcontext, "objective"), flag);
        }))));
        literalargumentbuilder.then(CommandDispatcher.a("bossbar").then(((RequiredArgumentBuilder) CommandDispatcher.a("id", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CommandBossBar.a).then(CommandDispatcher.a("value").redirect(literalcommandnode, (commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), CommandBossBar.a(commandcontext), true, flag);
        }))).then(CommandDispatcher.a("max").redirect(literalcommandnode, (commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), CommandBossBar.a(commandcontext), false, flag);
        }))));
        Iterator iterator = CommandData.b.iterator();

        while (iterator.hasNext()) {
            CommandData.c commanddata_c = (CommandData.c) iterator.next();

            commanddata_c.a(literalargumentbuilder, (argumentbuilder) -> {
                return argumentbuilder.then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("path", (ArgumentType) ArgumentNBTKey.a()).then(CommandDispatcher.a("int").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagInt((int) ((double) i * DoubleArgumentType.getDouble(commandcontext, "scale")));
                    }, flag);
                })))).then(CommandDispatcher.a("float").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagFloat((float) ((double) i * DoubleArgumentType.getDouble(commandcontext, "scale")));
                    }, flag);
                })))).then(CommandDispatcher.a("short").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagShort((short) ((int) ((double) i * DoubleArgumentType.getDouble(commandcontext, "scale"))));
                    }, flag);
                })))).then(CommandDispatcher.a("long").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagLong((long) ((double) i * DoubleArgumentType.getDouble(commandcontext, "scale")));
                    }, flag);
                })))).then(CommandDispatcher.a("double").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagDouble((double) i * DoubleArgumentType.getDouble(commandcontext, "scale"));
                    }, flag);
                })))).then(CommandDispatcher.a("byte").then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).redirect(literalcommandnode, (commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), (i) -> {
                        return new NBTTagByte((byte) ((int) ((double) i * DoubleArgumentType.getDouble(commandcontext, "scale"))));
                    }, flag);
                }))));
            });
        }

        return literalargumentbuilder;
    }

    private static CommandListenerWrapper a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective, boolean flag) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        return commandlistenerwrapper.a((commandcontext, flag1, i) -> {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);
                int j = flag ? i : (flag1 ? 1 : 0);

                scoreboardscore.setScore(j);
            }

        }, CommandExecute.d);
    }

    private static CommandListenerWrapper a(CommandListenerWrapper commandlistenerwrapper, BossBattleCustom bossbattlecustom, boolean flag, boolean flag1) {
        return commandlistenerwrapper.a((commandcontext, flag2, i) -> {
            int j = flag1 ? i : (flag2 ? 1 : 0);

            if (flag) {
                bossbattlecustom.a(j);
            } else {
                bossbattlecustom.b(j);
            }

        }, CommandExecute.d);
    }

    private static CommandListenerWrapper a(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor, ArgumentNBTKey.h argumentnbtkey_h, IntFunction<NBTBase> intfunction, boolean flag) {
        return commandlistenerwrapper.a((commandcontext, flag1, i) -> {
            try {
                NBTTagCompound nbttagcompound = commanddataaccessor.a();
                int j = flag ? i : (flag1 ? 1 : 0);

                argumentnbtkey_h.b(nbttagcompound, () -> {
                    return (NBTBase) intfunction.apply(j);
                });
                commanddataaccessor.a(nbttagcompound);
            } catch (CommandSyntaxException commandsyntaxexception) {
                ;
            }

        }, CommandExecute.d);
    }

    private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> commandnode, LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder, boolean flag) {
        ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) literalargumentbuilder.then(CommandDispatcher.a("block").then(CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("block", (ArgumentType) ArgumentBlockPredicate.a()), flag, (commandcontext) -> {
            return ArgumentBlockPredicate.a(commandcontext, "block").test(new ShapeDetectorBlock(((CommandListenerWrapper) commandcontext.getSource()).getWorld(), ArgumentPosition.a(commandcontext, "pos"), true));
        }))))).then(CommandDispatcher.a("score").then(CommandDispatcher.a("target", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("targetObjective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("=").then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()), flag, (commandcontext) -> {
            return a(commandcontext, Integer::equals);
        }))))).then(CommandDispatcher.a("<").then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()), flag, (commandcontext) -> {
            return a(commandcontext, (integer, integer1) -> {
                return integer < integer1;
            });
        }))))).then(CommandDispatcher.a("<=").then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()), flag, (commandcontext) -> {
            return a(commandcontext, (integer, integer1) -> {
                return integer <= integer1;
            });
        }))))).then(CommandDispatcher.a(">").then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()), flag, (commandcontext) -> {
            return a(commandcontext, (integer, integer1) -> {
                return integer > integer1;
            });
        }))))).then(CommandDispatcher.a(">=").then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()), flag, (commandcontext) -> {
            return a(commandcontext, (integer, integer1) -> {
                return integer >= integer1;
            });
        }))))).then(CommandDispatcher.a("matches").then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("range", (ArgumentType) ArgumentCriterionValue.a()), flag, (commandcontext) -> {
            return a(commandcontext, ArgumentCriterionValue.b.a(commandcontext, "range"));
        }))))))).then(CommandDispatcher.a("blocks").then(CommandDispatcher.a("start", (ArgumentType) ArgumentPosition.a()).then(CommandDispatcher.a("end", (ArgumentType) ArgumentPosition.a()).then(((RequiredArgumentBuilder) CommandDispatcher.a("destination", (ArgumentType) ArgumentPosition.a()).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("all"), flag, false))).then(a(commandnode, (ArgumentBuilder) CommandDispatcher.a("masked"), flag, true))))))).then(CommandDispatcher.a("entity").then(((RequiredArgumentBuilder) CommandDispatcher.a("entities", (ArgumentType) ArgumentEntity.multipleEntities()).fork(commandnode, (commandcontext) -> {
            return a(commandcontext, flag, !ArgumentEntity.c(commandcontext, "entities").isEmpty());
        })).executes(a(flag, (commandcontext) -> {
            return ArgumentEntity.c(commandcontext, "entities").size();
        }))));
        Iterator iterator = CommandData.c.iterator();

        while (iterator.hasNext()) {
            CommandData.c commanddata_c = (CommandData.c) iterator.next();

            literalargumentbuilder.then(commanddata_c.a(CommandDispatcher.a("data"), (argumentbuilder) -> {
                return argumentbuilder.then(((RequiredArgumentBuilder) CommandDispatcher.a("path", (ArgumentType) ArgumentNBTKey.a()).fork(commandnode, (commandcontext) -> {
                    return a(commandcontext, flag, a(commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path")) > 0);
                })).executes(a(flag, (commandcontext) -> {
                    return a(commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"));
                })));
            }));
        }

        return literalargumentbuilder;
    }

    private static Command<CommandListenerWrapper> a(boolean flag, CommandExecute.a commandexecute_a) {
        return flag ? (commandcontext) -> {
            int i = commandexecute_a.test(commandcontext);

            if (i > 0) {
                ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass_count", new Object[]{i}), false);
                return i;
            } else {
                throw CommandExecute.b.create();
            }
        } : (commandcontext) -> {
            int i = commandexecute_a.test(commandcontext);

            if (i == 0) {
                ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass", new Object[0]), false);
                return 1;
            } else {
                throw CommandExecute.c.create(i);
            }
        };
    }

    private static int a(CommandDataAccessor commanddataaccessor, ArgumentNBTKey.h argumentnbtkey_h) throws CommandSyntaxException {
        return argumentnbtkey_h.b(commanddataaccessor.a());
    }

    private static boolean a(CommandContext<CommandListenerWrapper> commandcontext, BiPredicate<Integer, Integer> bipredicate) throws CommandSyntaxException {
        String s = ArgumentScoreholder.a(commandcontext, "target");
        ScoreboardObjective scoreboardobjective = ArgumentScoreboardObjective.a(commandcontext, "targetObjective");
        String s1 = ArgumentScoreholder.a(commandcontext, "source");
        ScoreboardObjective scoreboardobjective1 = ArgumentScoreboardObjective.a(commandcontext, "sourceObjective");
        ScoreboardServer scoreboardserver = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard();

        if (scoreboardserver.b(s, scoreboardobjective) && scoreboardserver.b(s1, scoreboardobjective1)) {
            ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);
            ScoreboardScore scoreboardscore1 = scoreboardserver.getPlayerScoreForObjective(s1, scoreboardobjective1);

            return bipredicate.test(scoreboardscore.getScore(), scoreboardscore1.getScore());
        } else {
            return false;
        }
    }

    private static boolean a(CommandContext<CommandListenerWrapper> commandcontext, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) throws CommandSyntaxException {
        String s = ArgumentScoreholder.a(commandcontext, "target");
        ScoreboardObjective scoreboardobjective = ArgumentScoreboardObjective.a(commandcontext, "targetObjective");
        ScoreboardServer scoreboardserver = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard();

        return !scoreboardserver.b(s, scoreboardobjective) ? false : criterionconditionvalue_integerrange.d(scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective).getScore());
    }

    private static Collection<CommandListenerWrapper> a(CommandContext<CommandListenerWrapper> commandcontext, boolean flag, boolean flag1) {
        return (Collection) (flag1 == flag ? Collections.singleton(commandcontext.getSource()) : Collections.emptyList());
    }

    private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> commandnode, ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, boolean flag, CommandExecute.b commandexecute_b) {
        return argumentbuilder.fork(commandnode, (commandcontext) -> {
            return a(commandcontext, flag, commandexecute_b.test(commandcontext));
        }).executes((commandcontext) -> {
            if (flag == commandexecute_b.test(commandcontext)) {
                ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass", new Object[0]), false);
                return 1;
            } else {
                throw CommandExecute.b.create();
            }
        });
    }

    private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> commandnode, ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, boolean flag, boolean flag1) {
        return argumentbuilder.fork(commandnode, (commandcontext) -> {
            return a(commandcontext, flag, c(commandcontext, flag1).isPresent());
        }).executes(flag ? (commandcontext) -> {
            return a(commandcontext, flag1);
        } : (commandcontext) -> {
            return b(commandcontext, flag1);
        });
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, boolean flag) throws CommandSyntaxException {
        OptionalInt optionalint = c(commandcontext, flag);

        if (optionalint.isPresent()) {
            ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass_count", new Object[]{optionalint.getAsInt()}), false);
            return optionalint.getAsInt();
        } else {
            throw CommandExecute.b.create();
        }
    }

    private static int b(CommandContext<CommandListenerWrapper> commandcontext, boolean flag) throws CommandSyntaxException {
        OptionalInt optionalint = c(commandcontext, flag);

        if (optionalint.isPresent()) {
            throw CommandExecute.c.create(optionalint.getAsInt());
        } else {
            ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass", new Object[0]), false);
            return 1;
        }
    }

    private static OptionalInt c(CommandContext<CommandListenerWrapper> commandcontext, boolean flag) throws CommandSyntaxException {
        return a(((CommandListenerWrapper) commandcontext.getSource()).getWorld(), ArgumentPosition.a(commandcontext, "start"), ArgumentPosition.a(commandcontext, "end"), ArgumentPosition.a(commandcontext, "destination"), flag);
    }

    private static OptionalInt a(WorldServer worldserver, BlockPosition blockposition, BlockPosition blockposition1, BlockPosition blockposition2, boolean flag) throws CommandSyntaxException {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(blockposition, blockposition1);
        StructureBoundingBox structureboundingbox1 = new StructureBoundingBox(blockposition2, blockposition2.a(structureboundingbox.b()));
        BlockPosition blockposition3 = new BlockPosition(structureboundingbox1.a - structureboundingbox.a, structureboundingbox1.b - structureboundingbox.b, structureboundingbox1.c - structureboundingbox.c);
        int i = structureboundingbox.c() * structureboundingbox.d() * structureboundingbox.e();

        if (i > 32768) {
            throw CommandExecute.a.create(32768, i);
        } else {
            int j = 0;

            for (int k = structureboundingbox.c; k <= structureboundingbox.f; ++k) {
                for (int l = structureboundingbox.b; l <= structureboundingbox.e; ++l) {
                    for (int i1 = structureboundingbox.a; i1 <= structureboundingbox.d; ++i1) {
                        BlockPosition blockposition4 = new BlockPosition(i1, l, k);
                        BlockPosition blockposition5 = blockposition4.a((BaseBlockPosition) blockposition3);
                        IBlockData iblockdata = worldserver.getType(blockposition4);

                        if (!flag || iblockdata.getBlock() != Blocks.AIR) {
                            if (iblockdata != worldserver.getType(blockposition5)) {
                                return OptionalInt.empty();
                            }

                            TileEntity tileentity = worldserver.getTileEntity(blockposition4);
                            TileEntity tileentity1 = worldserver.getTileEntity(blockposition5);

                            if (tileentity != null) {
                                if (tileentity1 == null) {
                                    return OptionalInt.empty();
                                }

                                NBTTagCompound nbttagcompound = tileentity.save(new NBTTagCompound());

                                nbttagcompound.remove("x");
                                nbttagcompound.remove("y");
                                nbttagcompound.remove("z");
                                NBTTagCompound nbttagcompound1 = tileentity1.save(new NBTTagCompound());

                                nbttagcompound1.remove("x");
                                nbttagcompound1.remove("y");
                                nbttagcompound1.remove("z");
                                if (!nbttagcompound.equals(nbttagcompound1)) {
                                    return OptionalInt.empty();
                                }
                            }

                            ++j;
                        }
                    }
                }
            }

            return OptionalInt.of(j);
        }
    }

    @FunctionalInterface
    interface a {

        int test(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;
    }

    @FunctionalInterface
    interface b {

        boolean test(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;
    }
}
