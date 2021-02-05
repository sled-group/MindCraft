package net.minecraft.server;

public enum EnumGamemode {

    NOT_SET(-1, ""), SURVIVAL(0, "survival"), CREATIVE(1, "creative"), ADVENTURE(2, "adventure"), SPECTATOR(3, "spectator");

    private final int f;
    private final String g;

    private EnumGamemode(int i, String s) {
        this.f = i;
        this.g = s;
    }

    public int getId() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public IChatBaseComponent c() {
        return new ChatMessage("gameMode." + this.g, new Object[0]);
    }

    public void a(PlayerAbilities playerabilities) {
        if (this == EnumGamemode.CREATIVE) {
            playerabilities.canFly = true;
            playerabilities.canInstantlyBuild = true;
            playerabilities.isInvulnerable = true;
        } else if (this == EnumGamemode.SPECTATOR) {
            playerabilities.canFly = true;
            playerabilities.canInstantlyBuild = false;
            playerabilities.isInvulnerable = true;
            playerabilities.isFlying = true;
        } else {
            playerabilities.canFly = false;
            playerabilities.canInstantlyBuild = false;
            playerabilities.isInvulnerable = false;
            playerabilities.isFlying = false;
        }

        playerabilities.mayBuild = !this.d();
    }

    public boolean d() {
        return this == EnumGamemode.ADVENTURE || this == EnumGamemode.SPECTATOR;
    }

    public boolean isCreative() {
        return this == EnumGamemode.CREATIVE;
    }

    public boolean f() {
        return this == EnumGamemode.SURVIVAL || this == EnumGamemode.ADVENTURE;
    }

    public static EnumGamemode getById(int i) {
        return a(i, EnumGamemode.SURVIVAL);
    }

    public static EnumGamemode a(int i, EnumGamemode enumgamemode) {
        EnumGamemode[] aenumgamemode = values();
        int j = aenumgamemode.length;

        for (int k = 0; k < j; ++k) {
            EnumGamemode enumgamemode1 = aenumgamemode[k];

            if (enumgamemode1.f == i) {
                return enumgamemode1;
            }
        }

        return enumgamemode;
    }

    public static EnumGamemode a(String s) {
        return a(s, EnumGamemode.SURVIVAL);
    }

    public static EnumGamemode a(String s, EnumGamemode enumgamemode) {
        EnumGamemode[] aenumgamemode = values();
        int i = aenumgamemode.length;

        for (int j = 0; j < i; ++j) {
            EnumGamemode enumgamemode1 = aenumgamemode[j];

            if (enumgamemode1.g.equals(s)) {
                return enumgamemode1;
            }
        }

        return enumgamemode;
    }
}
