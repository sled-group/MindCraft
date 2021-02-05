package net.minecraft.server;

import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DragonControllerCharge extends AbstractDragonController {

    private static final Logger LOGGER = LogManager.getLogger();
    private Vec3D c;
    private int d;

    public DragonControllerCharge(EntityEnderDragon entityenderdragon) {
        super(entityenderdragon);
    }

    @Override
    public void c() {
        if (this.c == null) {
            DragonControllerCharge.LOGGER.warn("Aborting charge player as no target was set.");
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
        } else if (this.d > 0 && this.d++ >= 10) {
            this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
        } else {
            double d0 = this.c.c(this.a.locX, this.a.locY, this.a.locZ);

            if (d0 < 100.0D || d0 > 22500.0D || this.a.positionChanged || this.a.y) {
                ++this.d;
            }

        }
    }

    @Override
    public void d() {
        this.c = null;
        this.d = 0;
    }

    public void a(Vec3D vec3d) {
        this.c = vec3d;
    }

    @Override
    public float f() {
        return 3.0F;
    }

    @Nullable
    @Override
    public Vec3D g() {
        return this.c;
    }

    @Override
    public DragonControllerPhase<DragonControllerCharge> getControllerPhase() {
        return DragonControllerPhase.CHARGING_PLAYER;
    }
}
