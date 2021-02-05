package net.minecraft.server;

import java.util.UUID;

public abstract class BossBattle {

    private final UUID h;
    public IChatBaseComponent title;
    protected float b;
    public BossBattle.BarColor color;
    public BossBattle.BarStyle style;
    protected boolean e;
    protected boolean f;
    protected boolean g;

    public BossBattle(UUID uuid, IChatBaseComponent ichatbasecomponent, BossBattle.BarColor bossbattle_barcolor, BossBattle.BarStyle bossbattle_barstyle) {
        this.h = uuid;
        this.title = ichatbasecomponent;
        this.color = bossbattle_barcolor;
        this.style = bossbattle_barstyle;
        this.b = 1.0F;
    }

    public UUID i() {
        return this.h;
    }

    public IChatBaseComponent j() {
        return this.title;
    }

    public void a(IChatBaseComponent ichatbasecomponent) {
        this.title = ichatbasecomponent;
    }

    public float getProgress() {
        return this.b;
    }

    public void a(float f) {
        this.b = f;
    }

    public BossBattle.BarColor l() {
        return this.color;
    }

    public void a(BossBattle.BarColor bossbattle_barcolor) {
        this.color = bossbattle_barcolor;
    }

    public BossBattle.BarStyle m() {
        return this.style;
    }

    public void a(BossBattle.BarStyle bossbattle_barstyle) {
        this.style = bossbattle_barstyle;
    }

    public boolean isDarkenSky() {
        return this.e;
    }

    public BossBattle a(boolean flag) {
        this.e = flag;
        return this;
    }

    public boolean isPlayMusic() {
        return this.f;
    }

    public BossBattle b(boolean flag) {
        this.f = flag;
        return this;
    }

    public BossBattle c(boolean flag) {
        this.g = flag;
        return this;
    }

    public boolean isCreateFog() {
        return this.g;
    }

    public static enum BarStyle {

        PROGRESS("progress"), NOTCHED_6("notched_6"), NOTCHED_10("notched_10"), NOTCHED_12("notched_12"), NOTCHED_20("notched_20");

        private final String f;

        private BarStyle(String s) {
            this.f = s;
        }

        public String a() {
            return this.f;
        }

        public static BossBattle.BarStyle a(String s) {
            BossBattle.BarStyle[] abossbattle_barstyle = values();
            int i = abossbattle_barstyle.length;

            for (int j = 0; j < i; ++j) {
                BossBattle.BarStyle bossbattle_barstyle = abossbattle_barstyle[j];

                if (bossbattle_barstyle.f.equals(s)) {
                    return bossbattle_barstyle;
                }
            }

            return BossBattle.BarStyle.PROGRESS;
        }
    }

    public static enum BarColor {

        PINK("pink", EnumChatFormat.RED), BLUE("blue", EnumChatFormat.BLUE), RED("red", EnumChatFormat.DARK_RED), GREEN("green", EnumChatFormat.GREEN), YELLOW("yellow", EnumChatFormat.YELLOW), PURPLE("purple", EnumChatFormat.DARK_BLUE), WHITE("white", EnumChatFormat.WHITE);

        private final String h;
        private final EnumChatFormat i;

        private BarColor(String s, EnumChatFormat enumchatformat) {
            this.h = s;
            this.i = enumchatformat;
        }

        public EnumChatFormat a() {
            return this.i;
        }

        public String b() {
            return this.h;
        }

        public static BossBattle.BarColor a(String s) {
            BossBattle.BarColor[] abossbattle_barcolor = values();
            int i = abossbattle_barcolor.length;

            for (int j = 0; j < i; ++j) {
                BossBattle.BarColor bossbattle_barcolor = abossbattle_barcolor[j];

                if (bossbattle_barcolor.h.equals(s)) {
                    return bossbattle_barcolor;
                }
            }

            return BossBattle.BarColor.WHITE;
        }
    }
}
