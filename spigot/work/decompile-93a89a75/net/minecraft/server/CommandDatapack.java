package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;

public class CommandDatapack {

    private static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.datapack.unknown", new Object[]{object});
    });
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.datapack.enable.failed", new Object[]{object});
    });
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.datapack.disable.failed", new Object[]{object});
    });
    private static final SuggestionProvider<CommandListenerWrapper> d = (commandcontext, suggestionsbuilder) -> {
        return ICompletionProvider.b(((CommandListenerWrapper) commandcontext.getSource()).getServer().getResourcePackRepository().d().stream().map(ResourcePackLoader::e).map(StringArgumentType::escapeIfRequired), suggestionsbuilder);
    };
    private static final SuggestionProvider<CommandListenerWrapper> e = (commandcontext, suggestionsbuilder) -> {
        return ICompletionProvider.b(((CommandListenerWrapper) commandcontext.getSource()).getServer().getResourcePackRepository().c().stream().map(ResourcePackLoader::e).map(StringArgumentType::escapeIfRequired), suggestionsbuilder);
    };

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("datapack").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("enable").then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("name", (ArgumentType) StringArgumentType.string()).suggests(CommandDatapack.e).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", true), (list, resourcepackloader) -> {
                resourcepackloader.h().a(list, resourcepackloader, (resourcepackloader1) -> {
                    return resourcepackloader1;
                }, false);
            });
        })).then(CommandDispatcher.a("after").then(CommandDispatcher.a("existing", (ArgumentType) StringArgumentType.string()).suggests(CommandDatapack.d).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", true), (list, resourcepackloader) -> {
                list.add(list.indexOf(a(commandcontext, "existing", false)) + 1, resourcepackloader);
            });
        })))).then(CommandDispatcher.a("before").then(CommandDispatcher.a("existing", (ArgumentType) StringArgumentType.string()).suggests(CommandDatapack.d).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", true), (list, resourcepackloader) -> {
                list.add(list.indexOf(a(commandcontext, "existing", false)), resourcepackloader);
            });
        })))).then(CommandDispatcher.a("last").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", true), List::add);
        }))).then(CommandDispatcher.a("first").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", true), (list, resourcepackloader) -> {
                list.add(0, resourcepackloader);
            });
        }))))).then(CommandDispatcher.a("disable").then(CommandDispatcher.a("name", (ArgumentType) StringArgumentType.string()).suggests(CommandDatapack.d).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(commandcontext, "name", false));
        })))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("list").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource());
        })).then(CommandDispatcher.a("available").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource());
        }))).then(CommandDispatcher.a("enabled").executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource());
        }))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ResourcePackLoader resourcepackloader, CommandDatapack.a commanddatapack_a) throws CommandSyntaxException {
        ResourcePackRepository<ResourcePackLoader> resourcepackrepository = commandlistenerwrapper.getServer().getResourcePackRepository();
        List<ResourcePackLoader> list = Lists.newArrayList(resourcepackrepository.d());

        commanddatapack_a.apply(list, resourcepackloader);
        resourcepackrepository.a((Collection) list);
        WorldData worlddata = commandlistenerwrapper.getServer().getWorldServer(DimensionManager.OVERWORLD).getWorldData();

        worlddata.O().clear();
        resourcepackrepository.d().forEach((resourcepackloader1) -> {
            worlddata.O().add(resourcepackloader1.e());
        });
        worlddata.N().remove(resourcepackloader.e());
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.enable.success", new Object[]{resourcepackloader.a(true)}), true);
        commandlistenerwrapper.getServer().reload();
        return resourcepackrepository.d().size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ResourcePackLoader resourcepackloader) {
        ResourcePackRepository<ResourcePackLoader> resourcepackrepository = commandlistenerwrapper.getServer().getResourcePackRepository();
        List<ResourcePackLoader> list = Lists.newArrayList(resourcepackrepository.d());

        list.remove(resourcepackloader);
        resourcepackrepository.a((Collection) list);
        WorldData worlddata = commandlistenerwrapper.getServer().getWorldServer(DimensionManager.OVERWORLD).getWorldData();

        worlddata.O().clear();
        resourcepackrepository.d().forEach((resourcepackloader1) -> {
            worlddata.O().add(resourcepackloader1.e());
        });
        worlddata.N().add(resourcepackloader.e());
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.disable.success", new Object[]{resourcepackloader.a(true)}), true);
        commandlistenerwrapper.getServer().reload();
        return resourcepackrepository.d().size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper) {
        return c(commandlistenerwrapper) + b(commandlistenerwrapper);
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper) {
        ResourcePackRepository<ResourcePackLoader> resourcepackrepository = commandlistenerwrapper.getServer().getResourcePackRepository();

        if (resourcepackrepository.c().isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.list.available.none", new Object[0]), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.list.available.success", new Object[]{resourcepackrepository.c().size(), ChatComponentUtils.b(resourcepackrepository.c(), (resourcepackloader) -> {
                        return resourcepackloader.a(false);
                    })}), false);
        }

        return resourcepackrepository.c().size();
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper) {
        ResourcePackRepository<ResourcePackLoader> resourcepackrepository = commandlistenerwrapper.getServer().getResourcePackRepository();

        if (resourcepackrepository.d().isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.list.enabled.none", new Object[0]), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.datapack.list.enabled.success", new Object[]{resourcepackrepository.d().size(), ChatComponentUtils.b(resourcepackrepository.d(), (resourcepackloader) -> {
                        return resourcepackloader.a(true);
                    })}), false);
        }

        return resourcepackrepository.d().size();
    }

    private static ResourcePackLoader a(CommandContext<CommandListenerWrapper> commandcontext, String s, boolean flag) throws CommandSyntaxException {
        String s1 = StringArgumentType.getString(commandcontext, s);
        ResourcePackRepository<ResourcePackLoader> resourcepackrepository = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getResourcePackRepository();
        ResourcePackLoader resourcepackloader = resourcepackrepository.a(s1);

        if (resourcepackloader == null) {
            throw CommandDatapack.a.create(s1);
        } else {
            boolean flag1 = resourcepackrepository.d().contains(resourcepackloader);

            if (flag && flag1) {
                throw CommandDatapack.b.create(s1);
            } else if (!flag && !flag1) {
                throw CommandDatapack.c.create(s1);
            } else {
                return resourcepackloader;
            }
        }
    }

    interface a {

        void apply(List<ResourcePackLoader> list, ResourcePackLoader resourcepackloader) throws CommandSyntaxException;
    }
}
