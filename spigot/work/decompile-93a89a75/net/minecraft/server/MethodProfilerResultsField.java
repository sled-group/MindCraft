package net.minecraft.server;

public final class MethodProfilerResultsField implements Comparable<MethodProfilerResultsField> {

    public final double a;
    public final double b;
    public final long c;
    public final String d;

    public MethodProfilerResultsField(String s, double d0, double d1, long i) {
        this.d = s;
        this.a = d0;
        this.b = d1;
        this.c = i;
    }

    public int compareTo(MethodProfilerResultsField methodprofilerresultsfield) {
        return methodprofilerresultsfield.a < this.a ? -1 : (methodprofilerresultsfield.a > this.a ? 1 : methodprofilerresultsfield.d.compareTo(this.d));
    }
}
