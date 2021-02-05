package net.minecraft.server;

public class BlockSand extends BlockFalling {

    private final int a;

    public BlockSand(int i, Block.Info block_info) {
        super(block_info);
        this.a = i;
    }
}
