package net.minecraft.server;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.IOUtils;

public class ResourcePackFile extends ResourcePackAbstract {

    public static final Splitter b = Splitter.on('/').omitEmptyStrings().limit(3);
    private ZipFile c;

    public ResourcePackFile(File file) {
        super(file);
    }

    private ZipFile b() throws IOException {
        if (this.c == null) {
            this.c = new ZipFile(this.a);
        }

        return this.c;
    }

    @Override
    protected InputStream a(String s) throws IOException {
        ZipFile zipfile = this.b();
        ZipEntry zipentry = zipfile.getEntry(s);

        if (zipentry == null) {
            throw new ResourceNotFoundException(this.a, s);
        } else {
            return zipfile.getInputStream(zipentry);
        }
    }

    @Override
    public boolean c(String s) {
        try {
            return this.b().getEntry(s) != null;
        } catch (IOException ioexception) {
            return false;
        }
    }

    @Override
    public Set<String> a(EnumResourcePackType enumresourcepacktype) {
        ZipFile zipfile;

        try {
            zipfile = this.b();
        } catch (IOException ioexception) {
            return Collections.emptySet();
        }

        Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
        HashSet hashset = Sets.newHashSet();

        while (enumeration.hasMoreElements()) {
            ZipEntry zipentry = (ZipEntry) enumeration.nextElement();
            String s = zipentry.getName();

            if (s.startsWith(enumresourcepacktype.a() + "/")) {
                List<String> list = Lists.newArrayList(ResourcePackFile.b.split(s));

                if (list.size() > 1) {
                    String s1 = (String) list.get(1);

                    if (s1.equals(s1.toLowerCase(Locale.ROOT))) {
                        hashset.add(s1);
                    } else {
                        this.d(s1);
                    }
                }
            }
        }

        return hashset;
    }

    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    public void close() {
        if (this.c != null) {
            IOUtils.closeQuietly(this.c);
            this.c = null;
        }

    }

    @Override
    public Collection<MinecraftKey> a(EnumResourcePackType enumresourcepacktype, String s, int i, Predicate<String> predicate) {
        ZipFile zipfile;

        try {
            zipfile = this.b();
        } catch (IOException ioexception) {
            return Collections.emptySet();
        }

        Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
        List<MinecraftKey> list = Lists.newArrayList();
        String s1 = enumresourcepacktype.a() + "/";

        while (enumeration.hasMoreElements()) {
            ZipEntry zipentry = (ZipEntry) enumeration.nextElement();

            if (!zipentry.isDirectory() && zipentry.getName().startsWith(s1)) {
                String s2 = zipentry.getName().substring(s1.length());

                if (!s2.endsWith(".mcmeta")) {
                    int j = s2.indexOf(47);

                    if (j >= 0) {
                        String s3 = s2.substring(j + 1);

                        if (s3.startsWith(s + "/")) {
                            String[] astring = s3.substring(s.length() + 2).split("/");

                            if (astring.length >= i + 1 && predicate.test(s3)) {
                                String s4 = s2.substring(0, j);

                                list.add(new MinecraftKey(s4, s3));
                            }
                        }
                    }
                }
            }
        }

        return list;
    }
}
