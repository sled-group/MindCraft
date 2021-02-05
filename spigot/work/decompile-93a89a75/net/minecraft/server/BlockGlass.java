package net.minecraft.server;

public class BlockGlass extends BlockGlassAbstract {

    public BlockGlass(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }
}
