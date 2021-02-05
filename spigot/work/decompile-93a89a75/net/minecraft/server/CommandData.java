package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CommandData {

    private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.data.merge.failed", new Object[0]));
    private static final DynamicCommandExceptionType e = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.data.get.invalid", new Object[]{object});
    });
    private static final DynamicCommandExceptionType f = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.data.get.unknown", new Object[]{object});
    });
    private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.data.get.multiple", new Object[0]));
    private static final DynamicCommandExceptionType h = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.data.modify.expected_list", new Object[]{object});
    });
    private static final DynamicCommandExceptionType i = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.data.modify.expected_object", new Object[]{object});
    });
    private static final DynamicCommandExceptionType j = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.data.modify.invalid_index", new Object[]{object});
    });
    public static final List<Function<String, CommandData.c>> a = ImmutableList.of(CommandDataAccessorEntity.a, CommandDataAccessorTile.a);
    public static final List<CommandData.c> b = (List) CommandData.a.stream().map((function) -> {
        return (CommandData.c) function.apply("target");
    }).collect(ImmutableList.toImmutableList());
    public static final List<CommandData.c> c = (List) CommandData.a.stream().map((function) -> {
        return (CommandData.c) function.apply("source");
    }).collect(ImmutableList.toImmutableList());

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = (LiteralArgumentBuilder) CommandDispatcher.a("data").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        });
        Iterator iterator = CommandData.b.iterator();

        while (iterator.hasNext()) {
            CommandData.c commanddata_c = (CommandData.c) iterator.next();

            ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) literalargumentbuilder.then(commanddata_c.a(CommandDispatcher.a("merge"), (argumentbuilder) -> {
                return argumentbuilder.then(CommandDispatcher.a("nbt", (ArgumentType) ArgumentNBTTag.a()).executes((commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTTag.a(commandcontext, "nbt"));
                }));
            }))).then(commanddata_c.a(CommandDispatcher.a("get"), (argumentbuilder) -> {
                return argumentbuilder.executes((commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext));
                }).then(((RequiredArgumentBuilder) CommandDispatcher.a("path", (ArgumentType) ArgumentNBTKey.a()).executes((commandcontext) -> {
                    return b((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"));
                })).then(CommandDispatcher.a("scale", (ArgumentType) DoubleArgumentType.doubleArg()).executes((commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"), DoubleArgumentType.getDouble(commandcontext, "scale"));
                })));
            }))).then(commanddata_c.a(CommandDispatcher.a("remove"), (argumentbuilder) -> {
                return argumentbuilder.then(CommandDispatcher.a("path", (ArgumentType) ArgumentNBTKey.a()).executes((commandcontext) -> {
                    return a((CommandListenerWrapper) commandcontext.getSource(), commanddata_c.a(commandcontext), ArgumentNBTKey.a(commandcontext, "path"));
                }));
            }))).then(a((argumentbuilder, commanddata_b) -> {
                argumentbuilder.then(CommandDispatcher.a("insert").then(CommandDispatcher.a("index", (ArgumentType) IntegerArgumentType.integer()).then(commanddata_b.create((commandcontext, nbttagcompound, argumentnbtkey_h, list) -> {
                    int i = IntegerArgumentType.getInteger(commandcontext, "index");

                    return a(i, nbttagcompound, argumentnbtkey_h, list);
                })))).then(CommandDispatcher.a("prepend").then(commanddata_b.create((commandcontext, nbttagcompound, argumentnbtkey_h, list) -> {
                    return a(0, nbttagcompound, argumentnbtkey_h, list);
                }))).then(CommandDispatcher.a("append").then(commanddata_b.create((commandcontext, nbttagcompound, argumentnbtkey_h, list) -> {
                    return a(-1, nbttagcompound, argumentnbtkey_h, list);
                }))).then(CommandDispatcher.a("set").then(commanddata_b.create((commandcontext, nbttagcompound, argumentnbtkey_h, list) -> {
                    NBTBase nbtbase = (NBTBase) Iterables.getLast(list);

                    nbtbase.getClass();
                    return argumentnbtkey_h.b(nbttagcompound, nbtbase::clone);
                }))).then(CommandDispatcher.a("merge").then(commanddata_b.create((commandcontext, nbttagcompound, argumentnbtkey_h, list) -> {
                    Collection<NBTBase> collection = argumentnbtkey_h.a((NBTBase) nbttagcompound, NBTTagCompound::new);
                    int i = 0;

                    NBTTagCompound nbttagcompound1;
                    NBTTagCompound nbttagcompound2;

                    for (Iterator iterator1 = collection.iterator(); iterator1.hasNext(); i += nbttagcompound2.equals(nbttagcompound1) ? 0 : 1) {
                        NBTBase nbtbase = (NBTBase) iterator1.next();

                        if (!(nbtbase instanceof NBTTagCompound)) {
                            throw CommandData.i.create(nbtbase);
                        }

                        nbttagcompound1 = (NBTTagCompound) nbtbase;
                        nbttagcompound2 = nbttagcompound1.clone();
                        Iterator iterator2 = list.iterator();

                        while (iterator2.hasNext()) {
                            NBTBase nbtbase1 = (NBTBase) iterator2.next();

                            if (!(nbtbase1 instanceof NBTTagCompound)) {
                                throw CommandData.i.create(nbtbase1);
                            }

                            nbttagcompound1.a((NBTTagCompound) nbtbase1);
                        }
                    }

                    return i;
                })));
            }));
        }

        com_mojang_brigadier_commanddispatcher.register(literalargumentbuilder);
    }

    private static int a(int i, NBTTagCompound nbttagcompound, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException {
        Collection<NBTBase> collection = argumentnbtkey_h.a((NBTBase) nbttagcompound, NBTTagList::new);
        int j = 0;

        boolean flag;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); j += flag ? 1 : 0) {
            NBTBase nbtbase = (NBTBase) iterator.next();

            if (!(nbtbase instanceof NBTList)) {
                throw CommandData.h.create(nbtbase);
            }

            flag = false;
            NBTList<?> nbtlist = (NBTList) nbtbase;
            int k = i < 0 ? nbtlist.size() + i + 1 : i;
            Iterator iterator1 = list.iterator();

            while (iterator1.hasNext()) {
                NBTBase nbtbase1 = (NBTBase) iterator1.next();

                try {
                    if (nbtlist.b(k, nbtbase1.clone())) {
                        ++k;
                        flag = true;
                    }
                } catch (IndexOutOfBoundsException indexoutofboundsexception) {
                    throw CommandData.j.create(k);
                }
            }
        }

        return j;
    }

    private static ArgumentBuilder<CommandListenerWrapper, ?> a(BiConsumer<ArgumentBuilder<CommandListenerWrapper, ?>, CommandData.b> biconsumer) {
        LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = CommandDispatcher.a("modify");
        Iterator iterator = CommandData.b.iterator();

        while (iterator.hasNext()) {
            CommandData.c commanddata_c = (CommandData.c) iterator.next();

            commanddata_c.a(literalargumentbuilder, (argumentbuilder) -> {
                ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder1 = CommandDispatcher.a("targetPath", (ArgumentType) ArgumentNBTKey.a());
                Iterator iterator1 = CommandData.c.iterator();

                while (iterator1.hasNext()) {
                    CommandData.c commanddata_c1 = (CommandData.c) iterator1.next();

                    biconsumer.accept(argumentbuilder1, (commanddata_a) -> {
                        return commanddata_c1.a(CommandDispatcher.a("from"), (argumentbuilder2) -> {
                            return argumentbuilder2.executes((commandcontext) -> {
                                List<NBTBase> list = Collections.singletonList(commanddata_c1.a(commandcontext).a());

                                return a(commandcontext, commanddata_c, commanddata_a, list);
                            }).then(CommandDispatcher.a("sourcePath", (ArgumentType) ArgumentNBTKey.a()).executes((commandcontext) -> {
                                CommandDataAccessor commanddataaccessor = commanddata_c1.a(commandcontext);
                                ArgumentNBTKey.h argumentnbtkey_h = ArgumentNBTKey.a(commandcontext, "sourcePath");
                                List<NBTBase> list = argumentnbtkey_h.a((NBTBase) commanddataaccessor.a());

                                return a(commandcontext, commanddata_c, commanddata_a, list);
                            }));
                        });
                    });
                }

                biconsumer.accept(argumentbuilder1, (commanddata_a) -> {
                    return (LiteralArgumentBuilder) CommandDispatcher.a("value").then(CommandDispatcher.a("value", (ArgumentType) ArgumentNBTBase.a()).executes((commandcontext) -> {
                        List<NBTBase> list = Collections.singletonList(ArgumentNBTBase.a(commandcontext, "value"));

                        return a(commandcontext, commanddata_c, commanddata_a, list);
                    }));
                });
                return argumentbuilder.then(argumentbuilder1);
            });
        }

        return literalargumentbuilder;
    }

    private static int a(CommandContext<CommandListenerWrapper> commandcontext, CommandData.c commanddata_c, CommandData.a commanddata_a, List<NBTBase> list) throws CommandSyntaxException {
        CommandDataAccessor commanddataaccessor = commanddata_c.a(commandcontext);
        ArgumentNBTKey.h argumentnbtkey_h = ArgumentNBTKey.a(commandcontext, "targetPath");
        NBTTagCompound nbttagcompound = commanddataaccessor.a();
        int i = commanddata_a.modify(commandcontext, nbttagcompound, argumentnbtkey_h, list);

        if (i == 0) {
            throw CommandData.d.create();
        } else {
            commanddataaccessor.a(nbttagcompound);
            ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(commanddataaccessor.b(), true);
            return i;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor, ArgumentNBTKey.h argumentnbtkey_h) throws CommandSyntaxException {
        NBTTagCompound nbttagcompound = commanddataaccessor.a();
        int i = argumentnbtkey_h.c(nbttagcompound);

        if (i == 0) {
            throw CommandData.d.create();
        } else {
            commanddataaccessor.a(nbttagcompound);
            commandlistenerwrapper.sendMessage(commanddataaccessor.b(), true);
            return i;
        }
    }

    private static NBTBase a(ArgumentNBTKey.h argumentnbtkey_h, CommandDataAccessor commanddataaccessor) throws CommandSyntaxException {
        Collection<NBTBase> collection = argumentnbtkey_h.a((NBTBase) commanddataaccessor.a());
        Iterator<NBTBase> iterator = collection.iterator();
        NBTBase nbtbase = (NBTBase) iterator.next();

        if (iterator.hasNext()) {
            throw CommandData.g.create();
        } else {
            return nbtbase;
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor, ArgumentNBTKey.h argumentnbtkey_h) throws CommandSyntaxException {
        NBTBase nbtbase = a(argumentnbtkey_h, commanddataaccessor);
        int i;

        if (nbtbase instanceof NBTNumber) {
            i = MathHelper.floor(((NBTNumber) nbtbase).asDouble());
        } else if (nbtbase instanceof NBTList) {
            i = ((NBTList) nbtbase).size();
        } else if (nbtbase instanceof NBTTagCompound) {
            i = ((NBTTagCompound) nbtbase).d();
        } else {
            if (!(nbtbase instanceof NBTTagString)) {
                throw CommandData.f.create(argumentnbtkey_h.toString());
            }

            i = nbtbase.asString().length();
        }

        commandlistenerwrapper.sendMessage(commanddataaccessor.a(nbtbase), false);
        return i;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor, ArgumentNBTKey.h argumentnbtkey_h, double d0) throws CommandSyntaxException {
        NBTBase nbtbase = a(argumentnbtkey_h, commanddataaccessor);

        if (!(nbtbase instanceof NBTNumber)) {
            throw CommandData.e.create(argumentnbtkey_h.toString());
        } else {
            int i = MathHelper.floor(((NBTNumber) nbtbase).asDouble() * d0);

            commandlistenerwrapper.sendMessage(commanddataaccessor.a(argumentnbtkey_h, d0, i), false);
            return i;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor) throws CommandSyntaxException {
        commandlistenerwrapper.sendMessage(commanddataaccessor.a((NBTBase) commanddataaccessor.a()), false);
        return 1;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, CommandDataAccessor commanddataaccessor, NBTTagCompound nbttagcompound) throws CommandSyntaxException {
        NBTTagCompound nbttagcompound1 = commanddataaccessor.a();
        NBTTagCompound nbttagcompound2 = nbttagcompound1.clone().a(nbttagcompound);

        if (nbttagcompound1.equals(nbttagcompound2)) {
            throw CommandData.d.create();
        } else {
            commanddataaccessor.a(nbttagcompound2);
            commandlistenerwrapper.sendMessage(commanddataaccessor.b(), true);
            return 1;
        }
    }

    public interface c {

        CommandDataAccessor a(CommandContext<CommandListenerWrapper> commandcontext) throws CommandSyntaxException;

        ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder<CommandListenerWrapper, ?> argumentbuilder, Function<ArgumentBuilder<CommandListenerWrapper, ?>, ArgumentBuilder<CommandListenerWrapper, ?>> function);
    }

    interface b {

        ArgumentBuilder<CommandListenerWrapper, ?> create(CommandData.a commanddata_a);
    }

    interface a {

        int modify(CommandContext<CommandListenerWrapper> commandcontext, NBTTagCompound nbttagcompound, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException;
    }
}
