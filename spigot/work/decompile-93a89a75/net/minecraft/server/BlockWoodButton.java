package net.minecraft.server;

public class BlockWoodButton extends BlockButtonAbstract {

    protected BlockWoodButton(Block.Info block_info) {
        super(true, block_info);
    }

    @Override
    protected SoundEffect a(boolean flag) {
        return flag ? SoundEffects.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEffects.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}
