package net.minecraft.server;

public enum TickListPriority {

    EXTREMELY_HIGH(-3), VERY_HIGH(-2), HIGH(-1), NORMAL(0), LOW(1), VERY_LOW(2), EXTREMELY_LOW(3);

    private final int h;

    private TickListPriority(int i) {
        this.h = i;
    }

    public static TickListPriority a(int i) {
        TickListPriority[] aticklistpriority = values();
        int j = aticklistpriority.length;

        for (int k = 0; k < j; ++k) {
            TickListPriority ticklistpriority = aticklistpriority[k];

            if (ticklistpriority.h == i) {
                return ticklistpriority;
            }
        }

        if (i < TickListPriority.EXTREMELY_HIGH.h) {
            return TickListPriority.EXTREMELY_HIGH;
        } else {
            return TickListPriority.EXTREMELY_LOW;
        }
    }

    public int a() {
        return this.h;
    }
}
