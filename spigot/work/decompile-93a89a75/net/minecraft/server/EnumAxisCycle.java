package net.minecraft.server;

public enum EnumAxisCycle {

    NONE {
        @Override
        public int a(int i, int j, int k, EnumDirection.EnumAxis enumdirection_enumaxis) {
            return enumdirection_enumaxis.a(i, j, k);
        }

        @Override
        public EnumDirection.EnumAxis a(EnumDirection.EnumAxis enumdirection_enumaxis) {
            return enumdirection_enumaxis;
        }

        @Override
        public EnumAxisCycle a() {
            return this;
        }
    },
    FORWARD {
        @Override
        public int a(int i, int j, int k, EnumDirection.EnumAxis enumdirection_enumaxis) {
            return enumdirection_enumaxis.a(k, i, j);
        }

        @Override
        public EnumDirection.EnumAxis a(EnumDirection.EnumAxis enumdirection_enumaxis) {
            return null.d[Math.floorMod(enumdirection_enumaxis.ordinal() + 1, 3)];
        }

        @Override
        public EnumAxisCycle a() {
            return null.BACKWARD;
        }
    },
    BACKWARD {
        @Override
        public int a(int i, int j, int k, EnumDirection.EnumAxis enumdirection_enumaxis) {
            return enumdirection_enumaxis.a(j, k, i);
        }

        @Override
        public EnumDirection.EnumAxis a(EnumDirection.EnumAxis enumdirection_enumaxis) {
            return null.d[Math.floorMod(enumdirection_enumaxis.ordinal() - 1, 3)];
        }

        @Override
        public EnumAxisCycle a() {
            return null.FORWARD;
        }
    };

    public static final EnumDirection.EnumAxis[] d = EnumDirection.EnumAxis.values();
    public static final EnumAxisCycle[] e = values();

    private EnumAxisCycle() {}

    public abstract int a(int i, int j, int k, EnumDirection.EnumAxis enumdirection_enumaxis);

    public abstract EnumDirection.EnumAxis a(EnumDirection.EnumAxis enumdirection_enumaxis);

    public abstract EnumAxisCycle a();

    public static EnumAxisCycle a(EnumDirection.EnumAxis enumdirection_enumaxis, EnumDirection.EnumAxis enumdirection_enumaxis1) {
        return EnumAxisCycle.e[Math.floorMod(enumdirection_enumaxis1.ordinal() - enumdirection_enumaxis.ordinal(), 3)];
    }
}
