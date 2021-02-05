package net.minecraft.server;

public abstract class EntityFlying extends EntityInsentient {

    protected EntityFlying(EntityTypes<? extends EntityFlying> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    public void b(float f, float f1) {}

    @Override
    protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {}

    @Override
    public void e(Vec3D vec3d) {
        if (this.isInWater()) {
            this.a(0.02F, vec3d);
            this.move(EnumMoveType.SELF, this.getMot());
            this.setMot(this.getMot().a(0.800000011920929D));
        } else if (this.aD()) {
            this.a(0.02F, vec3d);
            this.move(EnumMoveType.SELF, this.getMot());
            this.setMot(this.getMot().a(0.5D));
        } else {
            float f = 0.91F;

            if (this.onGround) {
                f = this.world.getType(new BlockPosition(this.locX, this.getBoundingBox().minY - 1.0D, this.locZ)).getBlock().m() * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);

            f = 0.91F;
            if (this.onGround) {
                f = this.world.getType(new BlockPosition(this.locX, this.getBoundingBox().minY - 1.0D, this.locZ)).getBlock().m() * 0.91F;
            }

            this.a(this.onGround ? 0.1F * f1 : 0.02F, vec3d);
            this.move(EnumMoveType.SELF, this.getMot());
            this.setMot(this.getMot().a((double) f));
        }

        this.aE = this.aF;
        double d0 = this.locX - this.lastX;
        double d1 = this.locZ - this.lastZ;
        float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        this.aF += (f2 - this.aF) * 0.4F;
        this.aG += this.aF;
    }

    @Override
    public boolean isClimbing() {
        return false;
    }
}
