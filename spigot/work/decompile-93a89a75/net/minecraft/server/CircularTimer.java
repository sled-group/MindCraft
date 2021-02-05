package net.minecraft.server;

public class CircularTimer {

    private final long[] a = new long[240];
    private int b;
    private int c;
    private int d;

    public CircularTimer() {}

    public void a(long i) {
        this.a[this.d] = i;
        ++this.d;
        if (this.d == 240) {
            this.d = 0;
        }

        if (this.c < 240) {
            this.b = 0;
            ++this.c;
        } else {
            this.b = this.b(this.d + 1);
        }

    }

    public int b(int i) {
        return i % 240;
    }
}
