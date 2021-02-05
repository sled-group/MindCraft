package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class CrashReportSystemDetails {

    private final CrashReport a;
    private final String b;
    private final List<CrashReportSystemDetails.CrashReportDetail> c = Lists.newArrayList();
    private StackTraceElement[] d = new StackTraceElement[0];

    public CrashReportSystemDetails(CrashReport crashreport, String s) {
        this.a = crashreport;
        this.b = s;
    }

    public static String a(BlockPosition blockposition) {
        return a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
    }

    public static String a(int i, int j, int k) {
        StringBuilder stringbuilder = new StringBuilder();

        try {
            stringbuilder.append(String.format("World: (%d,%d,%d)", i, j, k));
        } catch (Throwable throwable) {
            stringbuilder.append("(Error finding world loc)");
        }

        stringbuilder.append(", ");

        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;

        try {
            l = i >> 4;
            i1 = k >> 4;
            j1 = i & 15;
            k1 = j >> 4;
            l1 = k & 15;
            i2 = l << 4;
            j2 = i1 << 4;
            k2 = (l + 1 << 4) - 1;
            l2 = (i1 + 1 << 4) - 1;
            stringbuilder.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", j1, k1, l1, l, i1, i2, j2, k2, l2));
        } catch (Throwable throwable1) {
            stringbuilder.append("(Error finding chunk loc)");
        }

        stringbuilder.append(", ");

        try {
            l = i >> 9;
            i1 = k >> 9;
            j1 = l << 5;
            k1 = i1 << 5;
            l1 = (l + 1 << 5) - 1;
            i2 = (i1 + 1 << 5) - 1;
            j2 = l << 9;
            k2 = i1 << 9;
            l2 = (l + 1 << 9) - 1;
            int i3 = (i1 + 1 << 9) - 1;

            stringbuilder.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", l, i1, j1, k1, l1, i2, j2, k2, l2, i3));
        } catch (Throwable throwable2) {
            stringbuilder.append("(Error finding world loc)");
        }

        return stringbuilder.toString();
    }

    public CrashReportSystemDetails a(String s, CrashReportCallable<String> crashreportcallable) {
        try {
            this.a(s, crashreportcallable.call());
        } catch (Throwable throwable) {
            this.a(s, throwable);
        }

        return this;
    }

    public CrashReportSystemDetails a(String s, Object object) {
        this.c.add(new CrashReportSystemDetails.CrashReportDetail(s, object));
        return this;
    }

    public void a(String s, Throwable throwable) {
        this.a(s, (Object) throwable);
    }

    public int a(int i) {
        StackTraceElement[] astacktraceelement = Thread.currentThread().getStackTrace();

        if (astacktraceelement.length <= 0) {
            return 0;
        } else {
            this.d = new StackTraceElement[astacktraceelement.length - 3 - i];
            System.arraycopy(astacktraceelement, 3 + i, this.d, 0, this.d.length);
            return this.d.length;
        }
    }

    public boolean a(StackTraceElement stacktraceelement, StackTraceElement stacktraceelement1) {
        if (this.d.length != 0 && stacktraceelement != null) {
            StackTraceElement stacktraceelement2 = this.d[0];

            if (stacktraceelement2.isNativeMethod() == stacktraceelement.isNativeMethod() && stacktraceelement2.getClassName().equals(stacktraceelement.getClassName()) && stacktraceelement2.getFileName().equals(stacktraceelement.getFileName()) && stacktraceelement2.getMethodName().equals(stacktraceelement.getMethodName())) {
                if (stacktraceelement1 != null != this.d.length > 1) {
                    return false;
                } else if (stacktraceelement1 != null && !this.d[1].equals(stacktraceelement1)) {
                    return false;
                } else {
                    this.d[0] = stacktraceelement;
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void b(int i) {
        StackTraceElement[] astacktraceelement = new StackTraceElement[this.d.length - i];

        System.arraycopy(this.d, 0, astacktraceelement, 0, astacktraceelement.length);
        this.d = astacktraceelement;
    }

    public void a(StringBuilder stringbuilder) {
        stringbuilder.append("-- ").append(this.b).append(" --\n");
        stringbuilder.append("Details:");
        Iterator iterator = this.c.iterator();

        while (iterator.hasNext()) {
            CrashReportSystemDetails.CrashReportDetail crashreportsystemdetails_crashreportdetail = (CrashReportSystemDetails.CrashReportDetail) iterator.next();

            stringbuilder.append("\n\t");
            stringbuilder.append(crashreportsystemdetails_crashreportdetail.a());
            stringbuilder.append(": ");
            stringbuilder.append(crashreportsystemdetails_crashreportdetail.b());
        }

        if (this.d != null && this.d.length > 0) {
            stringbuilder.append("\nStacktrace:");
            StackTraceElement[] astacktraceelement = this.d;
            int i = astacktraceelement.length;

            for (int j = 0; j < i; ++j) {
                StackTraceElement stacktraceelement = astacktraceelement[j];

                stringbuilder.append("\n\tat ");
                stringbuilder.append(stacktraceelement);
            }
        }

    }

    public StackTraceElement[] a() {
        return this.d;
    }

    public static void a(CrashReportSystemDetails crashreportsystemdetails, BlockPosition blockposition, @Nullable IBlockData iblockdata) {
        if (iblockdata != null) {
            crashreportsystemdetails.a("Block", iblockdata::toString);
        }

        crashreportsystemdetails.a("Block location", () -> {
            return a(blockposition);
        });
    }

    static class CrashReportDetail {

        private final String a;
        private final String b;

        public CrashReportDetail(String s, Object object) {
            this.a = s;
            if (object == null) {
                this.b = "~~NULL~~";
            } else if (object instanceof Throwable) {
                Throwable throwable = (Throwable) object;

                this.b = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                this.b = object.toString();
            }

        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }
}
