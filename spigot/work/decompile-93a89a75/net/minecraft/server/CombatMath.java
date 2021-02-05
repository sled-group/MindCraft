package net.minecraft.server;

public class CombatMath {

    public static float a(float f, float f1, float f2) {
        float f3 = 2.0F + f2 / 4.0F;
        float f4 = MathHelper.a(f1 - f / f3, f1 * 0.2F, 20.0F);

        return f * (1.0F - f4 / 25.0F);
    }

    public static float a(float f, float f1) {
        float f2 = MathHelper.a(f1, 0.0F, 20.0F);

        return f * (1.0F - f2 / 25.0F);
    }
}
