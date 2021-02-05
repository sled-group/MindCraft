package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MethodProfilerResultsFilled implements MethodProfilerResults {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, Long> b;
    private final Map<String, Long> c;
    private final long d;
    private final int e;
    private final long f;
    private final int g;
    private final int h;

    public MethodProfilerResultsFilled(Map<String, Long> map, Map<String, Long> map1, long i, int j, long k, int l) {
        this.b = map;
        this.c = map1;
        this.d = i;
        this.e = j;
        this.f = k;
        this.g = l;
        this.h = l - j;
    }

    public List<MethodProfilerResultsField> a(String s) {
        long i = this.b.containsKey("root") ? (Long) this.b.get("root") : 0L;
        long j = (Long) this.b.getOrDefault(s, -1L);
        long k = (Long) this.c.getOrDefault(s, 0L);
        List<MethodProfilerResultsField> list = Lists.newArrayList();

        if (!s.isEmpty()) {
            s = s + '\u001e';
        }

        long l = 0L;
        Iterator iterator = this.b.keySet().iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (s1.length() > s.length() && s1.startsWith(s) && s1.indexOf(30, s.length() + 1) < 0) {
                l += (Long) this.b.get(s1);
            }
        }

        float f = (float) l;

        if (l < j) {
            l = j;
        }

        if (i < l) {
            i = l;
        }

        Set<String> set = Sets.newHashSet(this.b.keySet());

        set.addAll(this.c.keySet());
        Iterator iterator1 = set.iterator();

        String s2;

        while (iterator1.hasNext()) {
            s2 = (String) iterator1.next();
            if (s2.length() > s.length() && s2.startsWith(s) && s2.indexOf(30, s.length() + 1) < 0) {
                long i1 = (Long) this.b.getOrDefault(s2, 0L);
                double d0 = (double) i1 * 100.0D / (double) l;
                double d1 = (double) i1 * 100.0D / (double) i;
                String s3 = s2.substring(s.length());
                long j1 = (Long) this.c.getOrDefault(s2, 0L);

                list.add(new MethodProfilerResultsField(s3, d0, d1, j1));
            }
        }

        iterator1 = this.b.keySet().iterator();

        while (iterator1.hasNext()) {
            s2 = (String) iterator1.next();
            this.b.put(s2, (Long) this.b.get(s2) * 999L / 1000L);
        }

        if ((float) l > f) {
            list.add(new MethodProfilerResultsField("unspecified", (double) ((float) l - f) * 100.0D / (double) l, (double) ((float) l - f) * 100.0D / (double) i, k));
        }

        Collections.sort(list);
        list.add(0, new MethodProfilerResultsField(s, 100.0D, (double) l * 100.0D / (double) i, k));
        return list;
    }

    @Override
    public long a() {
        return this.d;
    }

    @Override
    public int b() {
        return this.e;
    }

    @Override
    public long c() {
        return this.f;
    }

    @Override
    public int d() {
        return this.g;
    }

    @Override
    public boolean a(File file) {
        file.getParentFile().mkdirs();
        OutputStreamWriter outputstreamwriter = null;

        boolean flag;

        try {
            outputstreamwriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            outputstreamwriter.write(this.a(this.g(), this.f()));
            boolean flag1 = true;

            return flag1;
        } catch (Throwable throwable) {
            MethodProfilerResultsFilled.LOGGER.error("Could not save profiler results to {}", file, throwable);
            flag = false;
        } finally {
            IOUtils.closeQuietly(outputstreamwriter);
        }

        return flag;
    }

    protected String a(long i, int j) {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append("---- Minecraft Profiler Results ----\n");
        stringbuilder.append("// ");
        stringbuilder.append(h());
        stringbuilder.append("\n\n");
        stringbuilder.append("Version: ").append(SharedConstants.a().getId()).append('\n');
        stringbuilder.append("Time span: ").append(i / 1000000L).append(" ms\n");
        stringbuilder.append("Tick span: ").append(j).append(" ticks\n");
        stringbuilder.append("// This is approximately ").append(String.format(Locale.ROOT, "%.2f", (float) j / ((float) i / 1.0E9F))).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
        stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        this.a(0, "root", stringbuilder);
        stringbuilder.append("--- END PROFILE DUMP ---\n\n");
        return stringbuilder.toString();
    }

    @Override
    public String e() {
        StringBuilder stringbuilder = new StringBuilder();

        this.a(0, "root", stringbuilder);
        return stringbuilder.toString();
    }

    private void a(int i, String s, StringBuilder stringbuilder) {
        List<MethodProfilerResultsField> list = this.a(s);

        if (list.size() >= 3) {
            for (int j = 1; j < list.size(); ++j) {
                MethodProfilerResultsField methodprofilerresultsfield = (MethodProfilerResultsField) list.get(j);

                stringbuilder.append(String.format("[%02d] ", i));

                for (int k = 0; k < i; ++k) {
                    stringbuilder.append("|   ");
                }

                stringbuilder.append(methodprofilerresultsfield.d).append('(').append(methodprofilerresultsfield.c).append('/').append(String.format(Locale.ROOT, "%.0f", (float) methodprofilerresultsfield.c / (float) this.h)).append(')').append(" - ").append(String.format(Locale.ROOT, "%.2f", methodprofilerresultsfield.a)).append("%/").append(String.format(Locale.ROOT, "%.2f", methodprofilerresultsfield.b)).append("%\n");
                if (!"unspecified".equals(methodprofilerresultsfield.d)) {
                    try {
                        this.a(i + 1, s + '\u001e' + methodprofilerresultsfield.d, stringbuilder);
                    } catch (Exception exception) {
                        stringbuilder.append("[[ EXCEPTION ").append(exception).append(" ]]");
                    }
                }
            }

        }
    }

    private static String h() {
        String[] astring = new String[]{"Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server."};

        try {
            return astring[(int) (SystemUtils.getMonotonicNanos() % (long) astring.length)];
        } catch (Throwable throwable) {
            return "Witty comment unavailable :(";
        }
    }

    @Override
    public int f() {
        return this.h;
    }
}
