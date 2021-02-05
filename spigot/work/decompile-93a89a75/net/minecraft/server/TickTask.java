package net.minecraft.server;

public class TickTask implements Runnable {

    private final int a;
    private final Runnable b;

    public TickTask(int i, Runnable runnable) {
        this.a = i;
        this.b = runnable;
    }

    public int a() {
        return this.a;
    }

    public void run() {
        this.b.run();
    }
}
