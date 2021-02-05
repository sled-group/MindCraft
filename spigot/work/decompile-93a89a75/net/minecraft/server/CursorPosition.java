package net.minecraft.server;

public class CursorPosition {

    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private int g;
    private int h;
    private int i;
    private boolean j;

    public CursorPosition(int i, int j, int k, int l, int i1, int j1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = i1;
        this.f = j1;
    }

    public boolean a() {
        if (!this.j) {
            this.g = this.a;
            this.h = this.b;
            this.i = this.c;
            this.j = true;
            return true;
        } else if (this.g == this.d && this.h == this.e && this.i == this.f) {
            return false;
        } else {
            if (this.g < this.d) {
                ++this.g;
            } else if (this.h < this.e) {
                this.g = this.a;
                ++this.h;
            } else if (this.i < this.f) {
                this.g = this.a;
                this.h = this.b;
                ++this.i;
            }

            return true;
        }
    }

    public int b() {
        return this.g;
    }

    public int c() {
        return this.h;
    }

    public int d() {
        return this.i;
    }

    public int e() {
        int i = 0;

        if (this.g == this.a || this.g == this.d) {
            ++i;
        }

        if (this.h == this.b || this.h == this.e) {
            ++i;
        }

        if (this.i == this.c || this.i == this.f) {
            ++i;
        }

        return i;
    }
}
