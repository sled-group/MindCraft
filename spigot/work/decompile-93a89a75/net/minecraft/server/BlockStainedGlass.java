package net.minecraft.server;

public class BlockStainedGlass extends BlockGlassAbstract implements IBeaconBeam {

    private final EnumColor color;

    public BlockStainedGlass(EnumColor enumcolor, Block.Info block_info) {
        super(block_info);
        this.color = enumcolor;
    }

    @Override
    public EnumColor a() {
        return this.color;
    }

    @Override
    public TextureType c() {
        return TextureType.TRANSLUCENT;
    }
}
