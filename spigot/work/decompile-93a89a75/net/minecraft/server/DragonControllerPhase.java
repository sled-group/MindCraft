package net.minecraft.server;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class DragonControllerPhase<T extends IDragonController> {

    private static DragonControllerPhase<?>[] l = new DragonControllerPhase[0];
    public static final DragonControllerPhase<DragonControllerHold> HOLDING_PATTERN = a(DragonControllerHold.class, "HoldingPattern");
    public static final DragonControllerPhase<DragonControllerStrafe> STRAFE_PLAYER = a(DragonControllerStrafe.class, "StrafePlayer");
    public static final DragonControllerPhase<DragonControllerLandingFly> LANDING_APPROACH = a(DragonControllerLandingFly.class, "LandingApproach");
    public static final DragonControllerPhase<DragonControllerLanding> LANDING = a(DragonControllerLanding.class, "Landing");
    public static final DragonControllerPhase<DragonControllerFly> TAKEOFF = a(DragonControllerFly.class, "Takeoff");
    public static final DragonControllerPhase<DragonControllerLandedFlame> SITTING_FLAMING = a(DragonControllerLandedFlame.class, "SittingFlaming");
    public static final DragonControllerPhase<DragonControllerLandedSearch> SITTING_SCANNING = a(DragonControllerLandedSearch.class, "SittingScanning");
    public static final DragonControllerPhase<DragonControllerLandedAttack> SITTING_ATTACKING = a(DragonControllerLandedAttack.class, "SittingAttacking");
    public static final DragonControllerPhase<DragonControllerCharge> CHARGING_PLAYER = a(DragonControllerCharge.class, "ChargingPlayer");
    public static final DragonControllerPhase<DragonControllerDying> DYING = a(DragonControllerDying.class, "Dying");
    public static final DragonControllerPhase<DragonControllerHover> HOVER = a(DragonControllerHover.class, "Hover");
    private final Class<? extends IDragonController> m;
    private final int n;
    private final String o;

    private DragonControllerPhase(int i, Class<? extends IDragonController> oclass, String s) {
        this.n = i;
        this.m = oclass;
        this.o = s;
    }

    public IDragonController a(EntityEnderDragon entityenderdragon) {
        try {
            Constructor<? extends IDragonController> constructor = this.a();

            return (IDragonController) constructor.newInstance(entityenderdragon);
        } catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends IDragonController> a() throws NoSuchMethodException {
        return this.m.getConstructor(EntityEnderDragon.class);
    }

    public int b() {
        return this.n;
    }

    public String toString() {
        return this.o + " (#" + this.n + ")";
    }

    public static DragonControllerPhase<?> getById(int i) {
        return i >= 0 && i < DragonControllerPhase.l.length ? DragonControllerPhase.l[i] : DragonControllerPhase.HOLDING_PATTERN;
    }

    public static int c() {
        return DragonControllerPhase.l.length;
    }

    private static <T extends IDragonController> DragonControllerPhase<T> a(Class<T> oclass, String s) {
        DragonControllerPhase<T> dragoncontrollerphase = new DragonControllerPhase<>(DragonControllerPhase.l.length, oclass, s);

        DragonControllerPhase.l = (DragonControllerPhase[]) Arrays.copyOf(DragonControllerPhase.l, DragonControllerPhase.l.length + 1);
        DragonControllerPhase.l[dragoncontrollerphase.b()] = dragoncontrollerphase;
        return dragoncontrollerphase;
    }
}
