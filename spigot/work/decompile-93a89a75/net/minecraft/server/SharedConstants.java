package net.minecraft.server;

import com.mojang.bridge.game.GameVersion;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.types.constant.NamespacedStringType;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;

public class SharedConstants {

    public static final Level a = Level.DISABLED;
    public static boolean b;
    public static final char[] allowedCharacters = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'};
    private static GameVersion d;

    public static boolean isAllowedChatCharacter(char c0) {
        return c0 != 167 && c0 >= ' ' && c0 != 127;
    }

    public static String a(String s) {
        StringBuilder stringbuilder = new StringBuilder();
        char[] achar = s.toCharArray();
        int i = achar.length;

        for (int j = 0; j < i; ++j) {
            char c0 = achar[j];

            if (isAllowedChatCharacter(c0)) {
                stringbuilder.append(c0);
            }
        }

        return stringbuilder.toString();
    }

    public static GameVersion a() {
        if (SharedConstants.d == null) {
            SharedConstants.d = MinecraftVersion.a();
        }

        return SharedConstants.d;
    }

    static {
        ResourceLeakDetector.setLevel(SharedConstants.a);
        CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES = false;
        CommandSyntaxException.BUILT_IN_EXCEPTIONS = new CommandExceptionProvider();
        NamespacedStringType.ENSURE_NAMESPACE = DataConverterSchemaNamed::a;
    }
}
