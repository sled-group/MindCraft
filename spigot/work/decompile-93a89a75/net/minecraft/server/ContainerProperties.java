package net.minecraft.server;

public class ContainerProperties implements IContainerProperties {

    private final int[] a;

    public ContainerProperties(int i) {
        this.a = new int[i];
    }

    @Override
    public int getProperty(int i) {
        return this.a[i];
    }

    @Override
    public void setProperty(int i, int j) {
        this.a[i] = j;
    }

    @Override
    public int a() {
        return this.a.length;
    }
}
