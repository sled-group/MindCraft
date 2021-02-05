package net.minecraft.server;

public enum EnumBlockMirror {

    NONE, LEFT_RIGHT, FRONT_BACK;

    private EnumBlockMirror() {}

    public int a(int i, int j) {
        int k = j / 2;
        int l = i > k ? i - j : i;

        switch (this) {
            case FRONT_BACK:
                return (j - l) % j;
            case LEFT_RIGHT:
                return (k - l + j) % j;
            default:
                return i;
        }
    }

    public EnumBlockRotation a(EnumDirection enumdirection) {
        EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.k();

        return (this != EnumBlockMirror.LEFT_RIGHT || enumdirection_enumaxis != EnumDirection.EnumAxis.Z) && (this != EnumBlockMirror.FRONT_BACK || enumdirection_enumaxis != EnumDirection.EnumAxis.X) ? EnumBlockRotation.NONE : EnumBlockRotation.CLOCKWISE_180;
    }

    public EnumDirection b(EnumDirection enumdirection) {
        return this == EnumBlockMirror.FRONT_BACK && enumdirection.k() == EnumDirection.EnumAxis.X ? enumdirection.opposite() : (this == EnumBlockMirror.LEFT_RIGHT && enumdirection.k() == EnumDirection.EnumAxis.Z ? enumdirection.opposite() : enumdirection);
    }
}
