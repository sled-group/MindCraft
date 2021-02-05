package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnumBlockRotation {

    NONE, CLOCKWISE_90, CLOCKWISE_180, COUNTERCLOCKWISE_90;

    private EnumBlockRotation() {}

    public EnumBlockRotation a(EnumBlockRotation enumblockrotation) {
        switch (enumblockrotation) {
            case CLOCKWISE_180:
                switch (this) {
                    case NONE:
                        return EnumBlockRotation.CLOCKWISE_180;
                    case CLOCKWISE_90:
                        return EnumBlockRotation.COUNTERCLOCKWISE_90;
                    case CLOCKWISE_180:
                        return EnumBlockRotation.NONE;
                    case COUNTERCLOCKWISE_90:
                        return EnumBlockRotation.CLOCKWISE_90;
                }
            case COUNTERCLOCKWISE_90:
                switch (this) {
                    case NONE:
                        return EnumBlockRotation.COUNTERCLOCKWISE_90;
                    case CLOCKWISE_90:
                        return EnumBlockRotation.NONE;
                    case CLOCKWISE_180:
                        return EnumBlockRotation.CLOCKWISE_90;
                    case COUNTERCLOCKWISE_90:
                        return EnumBlockRotation.CLOCKWISE_180;
                }
            case CLOCKWISE_90:
                switch (this) {
                    case NONE:
                        return EnumBlockRotation.CLOCKWISE_90;
                    case CLOCKWISE_90:
                        return EnumBlockRotation.CLOCKWISE_180;
                    case CLOCKWISE_180:
                        return EnumBlockRotation.COUNTERCLOCKWISE_90;
                    case COUNTERCLOCKWISE_90:
                        return EnumBlockRotation.NONE;
                }
            default:
                return this;
        }
    }

    public EnumDirection a(EnumDirection enumdirection) {
        if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
            return enumdirection;
        } else {
            switch (this) {
                case CLOCKWISE_90:
                    return enumdirection.e();
                case CLOCKWISE_180:
                    return enumdirection.opposite();
                case COUNTERCLOCKWISE_90:
                    return enumdirection.f();
                default:
                    return enumdirection;
            }
        }
    }

    public int a(int i, int j) {
        switch (this) {
            case CLOCKWISE_90:
                return (i + j / 4) % j;
            case CLOCKWISE_180:
                return (i + j / 2) % j;
            case COUNTERCLOCKWISE_90:
                return (i + j * 3 / 4) % j;
            default:
                return i;
        }
    }

    public static EnumBlockRotation a(Random random) {
        EnumBlockRotation[] aenumblockrotation = values();

        return aenumblockrotation[random.nextInt(aenumblockrotation.length)];
    }

    public static List<EnumBlockRotation> b(Random random) {
        List<EnumBlockRotation> list = Lists.newArrayList(values());

        Collections.shuffle(list, random);
        return list;
    }
}
