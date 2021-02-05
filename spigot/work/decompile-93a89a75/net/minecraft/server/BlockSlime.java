package net.minecraft.server;

public class BlockSlime extends BlockHalfTransparent {

    public BlockSlime(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public TextureType c() {
        return TextureType.TRANSLUCENT;
    }

    @Override
    public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
        if (entity.isSneaking()) {
            super.fallOn(world, blockposition, entity, f);
        } else {
            entity.b(f, 0.0F);
        }

    }

    @Override
    public void a(IBlockAccess iblockaccess, Entity entity) {
        if (entity.isSneaking()) {
            super.a(iblockaccess, entity);
        } else {
            Vec3D vec3d = entity.getMot();

            if (vec3d.y < 0.0D) {
                double d0 = entity instanceof EntityLiving ? 1.0D : 0.8D;

                entity.setMot(vec3d.x, -vec3d.y * d0, vec3d.z);
            }
        }

    }

    @Override
    public void stepOn(World world, BlockPosition blockposition, Entity entity) {
        double d0 = Math.abs(entity.getMot().y);

        if (d0 < 0.1D && !entity.isSneaking()) {
            double d1 = 0.4D + d0 * 0.2D;

            entity.setMot(entity.getMot().d(d1, 1.0D, d1));
        }

        super.stepOn(world, blockposition, entity);
    }
}
