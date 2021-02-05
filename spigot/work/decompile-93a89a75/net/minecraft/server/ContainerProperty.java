package net.minecraft.server;

public abstract class ContainerProperty {

    private int a;

    public ContainerProperty() {}

    public static ContainerProperty a(final IContainerProperties icontainerproperties, final int i) {
        return new ContainerProperty() {
            @Override
            public int get() {
                return icontainerproperties.getProperty(i);
            }

            @Override
            public void set(int j) {
                icontainerproperties.setProperty(i, j);
            }
        };
    }

    public static ContainerProperty a(final int[] aint, final int i) {
        return new ContainerProperty() {
            @Override
            public int get() {
                return aint[i];
            }

            @Override
            public void set(int j) {
                aint[i] = j;
            }
        };
    }

    public static ContainerProperty a() {
        return new ContainerProperty() {
            private int a;

            @Override
            public int get() {
                return this.a;
            }

            @Override
            public void set(int i) {
                this.a = i;
            }
        };
    }

    public abstract int get();

    public abstract void set(int i);

    public boolean c() {
        int i = this.get();
        boolean flag = i != this.a;

        this.a = i;
        return flag;
    }
}
