package net.minecraft.server;

import java.util.Objects;
import javax.annotation.Nullable;

public class MapIcon {

    private final MapIcon.Type type;
    private byte x;
    private byte y;
    private byte rotation;
    private final IChatBaseComponent name;

    public MapIcon(MapIcon.Type mapicon_type, byte b0, byte b1, byte b2, @Nullable IChatBaseComponent ichatbasecomponent) {
        this.type = mapicon_type;
        this.x = b0;
        this.y = b1;
        this.rotation = b2;
        this.name = ichatbasecomponent;
    }

    public MapIcon.Type getType() {
        return this.type;
    }

    public byte getX() {
        return this.x;
    }

    public byte getY() {
        return this.y;
    }

    public byte getRotation() {
        return this.rotation;
    }

    @Nullable
    public IChatBaseComponent getName() {
        return this.name;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof MapIcon)) {
            return false;
        } else {
            MapIcon mapicon = (MapIcon) object;

            return this.type != mapicon.type ? false : (this.rotation != mapicon.rotation ? false : (this.x != mapicon.x ? false : (this.y != mapicon.y ? false : Objects.equals(this.name, mapicon.name))));
        }
    }

    public int hashCode() {
        byte b0 = this.type.a();
        int i = 31 * b0 + this.x;

        i = 31 * i + this.y;
        i = 31 * i + this.rotation;
        i = 31 * i + Objects.hashCode(this.name);
        return i;
    }

    public static enum Type {

        PLAYER(false), FRAME(true), RED_MARKER(false), BLUE_MARKER(false), TARGET_X(true), TARGET_POINT(true), PLAYER_OFF_MAP(false), PLAYER_OFF_LIMITS(false), MANSION(true, 5393476), MONUMENT(true, 3830373), BANNER_WHITE(true), BANNER_ORANGE(true), BANNER_MAGENTA(true), BANNER_LIGHT_BLUE(true), BANNER_YELLOW(true), BANNER_LIME(true), BANNER_PINK(true), BANNER_GRAY(true), BANNER_LIGHT_GRAY(true), BANNER_CYAN(true), BANNER_PURPLE(true), BANNER_BLUE(true), BANNER_BROWN(true), BANNER_GREEN(true), BANNER_RED(true), BANNER_BLACK(true), RED_X(true);

        private final byte B;
        private final boolean C;
        private final int D;

        private Type(boolean flag) {
            this(flag, -1);
        }

        private Type(boolean flag, int i) {
            this.B = (byte) this.ordinal();
            this.C = flag;
            this.D = i;
        }

        public byte a() {
            return this.B;
        }

        public boolean c() {
            return this.D >= 0;
        }

        public int d() {
            return this.D;
        }

        public static MapIcon.Type a(byte b0) {
            return values()[MathHelper.clamp(b0, 0, values().length - 1)];
        }
    }
}
