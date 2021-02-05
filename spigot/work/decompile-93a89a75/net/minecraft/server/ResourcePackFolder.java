package net.minecraft.server;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackFolder extends ResourcePackAbstract {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final boolean c = SystemUtils.g() == SystemUtils.OS.WINDOWS;
    private static final CharMatcher d = CharMatcher.is('\\');

    public ResourcePackFolder(File file) {
        super(file);
    }

    public static boolean a(File file, String s) throws IOException {
        String s1 = file.getCanonicalPath();

        if (ResourcePackFolder.c) {
            s1 = ResourcePackFolder.d.replaceFrom(s1, '/');
        }

        return s1.endsWith(s);
    }

    @Override
    protected InputStream a(String s) throws IOException {
        File file = this.e(s);

        if (file == null) {
            throw new ResourceNotFoundException(this.a, s);
        } else {
            return new FileInputStream(file);
        }
    }

    @Override
    protected boolean c(String s) {
        return this.e(s) != null;
    }

    @Nullable
    private File e(String s) {
        try {
            File file = new File(this.a, s);

            if (file.isFile() && a(file, s)) {
                return file;
            }
        } catch (IOException ioexception) {
            ;
        }

        return null;
    }

    @Override
    public Set<String> a(EnumResourcePackType enumresourcepacktype) {
        Set<String> set = Sets.newHashSet();
        File file = new File(this.a, enumresourcepacktype.a());
        File[] afile = file.listFiles(DirectoryFileFilter.DIRECTORY);

        if (afile != null) {
            File[] afile1 = afile;
            int i = afile.length;

            for (int j = 0; j < i; ++j) {
                File file1 = afile1[j];
                String s = a(file, file1);

                if (s.equals(s.toLowerCase(Locale.ROOT))) {
                    set.add(s.substring(0, s.length() - 1));
                } else {
                    this.d(s);
                }
            }
        }

        return set;
    }

    public void close() throws IOException {}

    @Override
    public Collection<MinecraftKey> a(EnumResourcePackType enumresourcepacktype, String s, int i, Predicate<String> predicate) {
        File file = new File(this.a, enumresourcepacktype.a());
        List<MinecraftKey> list = Lists.newArrayList();
        Iterator iterator = this.a(enumresourcepacktype).iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            this.a(new File(new File(file, s1), s), i, s1, list, s + "/", predicate);
        }

        return list;
    }

    private void a(File file, int i, String s, List<MinecraftKey> list, String s1, Predicate<String> predicate) {
        File[] afile = file.listFiles();

        if (afile != null) {
            File[] afile1 = afile;
            int j = afile.length;

            for (int k = 0; k < j; ++k) {
                File file1 = afile1[k];

                if (file1.isDirectory()) {
                    if (i > 0) {
                        this.a(file1, i - 1, s, list, s1 + file1.getName() + "/", predicate);
                    }
                } else if (!file1.getName().endsWith(".mcmeta") && predicate.test(file1.getName())) {
                    try {
                        list.add(new MinecraftKey(s, s1 + file1.getName()));
                    } catch (ResourceKeyInvalidException resourcekeyinvalidexception) {
                        ResourcePackFolder.LOGGER.error(resourcekeyinvalidexception.getMessage());
                    }
                }
            }
        }

    }
}
