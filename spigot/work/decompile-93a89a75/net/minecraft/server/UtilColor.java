package net.minecraft.server;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class UtilColor {

    private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static boolean b(@Nullable String s) {
        return StringUtils.isEmpty(s);
    }
}
