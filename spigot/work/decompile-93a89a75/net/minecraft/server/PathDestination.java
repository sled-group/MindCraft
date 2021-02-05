package net.minecraft.server;

public class PathDestination extends PathPoint {

    private float m = Float.MAX_VALUE;
    private PathPoint n;
    private boolean o;

    public PathDestination(PathPoint pathpoint) {
        super(pathpoint.a, pathpoint.b, pathpoint.c);
    }

    public void a(float f, PathPoint pathpoint) {
        if (f < this.m) {
            this.m = f;
            this.n = pathpoint;
        }

    }

    public PathPoint d() {
        return this.n;
    }

    public void e() {
        this.o = true;
    }

    public boolean f() {
        return this.o;
    }
}
