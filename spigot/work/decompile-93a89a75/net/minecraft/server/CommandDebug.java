package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.spi.FileSystemProvider;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDebug {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.debug.notRunning", new Object[0]));
    private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.debug.alreadyRunning", new Object[0]));
    @Nullable
    private static final FileSystemProvider d = (FileSystemProvider) FileSystemProvider.installedProviders().stream().filter((filesystemprovider) -> {
        return filesystemprovider.getScheme().equalsIgnoreCase("jar");
    }).findFirst().orElse((Object) null);

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("debug").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(3);
        })).then(CommandDispatcher.a("start").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource());
        }))).then(CommandDispatcher.a("stop").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource());
        }))).then(CommandDispatcher.a("report").executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource());
        })));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
        MinecraftServer minecraftserver = commandlistenerwrapper.getServer();
        GameProfiler gameprofiler = minecraftserver.getMethodProfiler();

        if (gameprofiler.d().a()) {
            throw CommandDebug.c.create();
        } else {
            minecraftserver.ak();
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.debug.started", new Object[]{"Started the debug profiler. Type '/debug stop' to stop it."}), true);
            return 0;
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
        MinecraftServer minecraftserver = commandlistenerwrapper.getServer();
        GameProfiler gameprofiler = minecraftserver.getMethodProfiler();

        if (!gameprofiler.d().a()) {
            throw CommandDebug.b.create();
        } else {
            MethodProfilerResults methodprofilerresults = gameprofiler.d().b();
            File file = new File(minecraftserver.d("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");

            methodprofilerresults.a(file);
            float f = (float) methodprofilerresults.g() / 1.0E9F;
            float f1 = (float) methodprofilerresults.f() / f;

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.debug.stopped", new Object[]{String.format(Locale.ROOT, "%.2f", f), methodprofilerresults.f(), String.format("%.2f", f1)}), true);
            return MathHelper.d(f1);
        }
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper) {
        MinecraftServer minecraftserver = commandlistenerwrapper.getServer();
        String s = "debug-report-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date());

        try {
            java.nio.file.Path java_nio_file_path = minecraftserver.d("debug").toPath();

            Files.createDirectories(java_nio_file_path);
            java.nio.file.Path java_nio_file_path1;

            if (!SharedConstants.b && CommandDebug.d != null) {
                java_nio_file_path1 = java_nio_file_path.resolve(s + ".zip");
                FileSystem filesystem = CommandDebug.d.newFileSystem(java_nio_file_path1, ImmutableMap.of("create", "true"));
                Throwable throwable = null;

                try {
                    minecraftserver.a(filesystem.getPath("/"));
                } catch (Throwable throwable1) {
                    throwable = throwable1;
                    throw throwable1;
                } finally {
                    if (filesystem != null) {
                        if (throwable != null) {
                            try {
                                filesystem.close();
                            } catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        } else {
                            filesystem.close();
                        }
                    }

                }
            } else {
                java_nio_file_path1 = java_nio_file_path.resolve(s);
                minecraftserver.a(java_nio_file_path1);
            }

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.debug.reportSaved", new Object[]{s}), false);
            return 1;
        } catch (IOException ioexception) {
            CommandDebug.LOGGER.error("Failed to save debug dump", ioexception);
            commandlistenerwrapper.sendFailureMessage(new ChatMessage("commands.debug.reportFailed", new Object[0]));
            return 0;
        }
    }
}
