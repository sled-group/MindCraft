package net.minecraft.server;

public abstract class EntityAmbient extends EntityInsentient {

    protected EntityAmbient(EntityTypes<? extends EntityAmbient> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    public boolean a(EntityHuman entityhuman) {
        return false;
    }
}
