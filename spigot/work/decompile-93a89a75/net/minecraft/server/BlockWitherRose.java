package net.minecraft.server;

public class BlockWitherRose extends BlockFlowers {

    public BlockWitherRose(MobEffectList mobeffectlist, Block.Info block_info) {
        super(mobeffectlist, 8, block_info);
    }

    @Override
    protected boolean a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        Block block = iblockdata.getBlock();

        return super.a_(iblockdata, iblockaccess, blockposition) || block == Blocks.NETHERRACK || block == Blocks.SOUL_SAND;
    }

    @Override
    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
        if (!world.isClientSide && world.getDifficulty() != EnumDifficulty.PEACEFUL) {
            if (entity instanceof EntityLiving) {
                EntityLiving entityliving = (EntityLiving) entity;

                if (!entityliving.isInvulnerable(DamageSource.WITHER)) {
                    entityliving.addEffect(new MobEffect(MobEffects.WITHER, 40));
                }
            }

        }
    }
}
