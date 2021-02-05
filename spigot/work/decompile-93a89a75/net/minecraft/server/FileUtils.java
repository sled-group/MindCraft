package net.minecraft.server;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

public class FileUtils {

    private static final Pattern a = Pattern.compile("(<name>.*) \\((<count>\\d*)\\)", 66);
    private static final Pattern b = Pattern.compile(".*\\.|(?:COM|CLOCK\\$|CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(?:\\..*)?", 2);

    public static boolean a(java.nio.file.Path java_nio_file_path) {
        java.nio.file.Path java_nio_file_path1 = java_nio_file_path.normalize();

        return java_nio_file_path1.equals(java_nio_file_path);
    }

    public static boolean b(java.nio.file.Path java_nio_file_path) {
        Iterator iterator = java_nio_file_path.iterator();

        java.nio.file.Path java_nio_file_path1;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            java_nio_file_path1 = (java.nio.file.Path) iterator.next();
        } while (!FileUtils.b.matcher(java_nio_file_path1.toString()).matches());

        return false;
    }

    public static java.nio.file.Path b(java.nio.file.Path java_nio_file_path, String s, String s1) {
        String s2 = s + s1;
        java.nio.file.Path java_nio_file_path1 = Paths.get(s2);

        if (java_nio_file_path1.endsWith(s1)) {
            throw new InvalidPathException(s2, "empty resource name");
        } else {
            return java_nio_file_path.resolve(java_nio_file_path1);
        }
    }
}
