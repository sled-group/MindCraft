package net.minecraft.server;

public enum BlockPropertyInstrument implements INamable {

    HARP("harp", SoundEffects.BLOCK_NOTE_BLOCK_HARP), BASEDRUM("basedrum", SoundEffects.BLOCK_NOTE_BLOCK_BASEDRUM), SNARE("snare", SoundEffects.BLOCK_NOTE_BLOCK_SNARE), HAT("hat", SoundEffects.BLOCK_NOTE_BLOCK_HAT), BASS("bass", SoundEffects.BLOCK_NOTE_BLOCK_BASS), FLUTE("flute", SoundEffects.BLOCK_NOTE_BLOCK_FLUTE), BELL("bell", SoundEffects.BLOCK_NOTE_BLOCK_BELL), GUITAR("guitar", SoundEffects.BLOCK_NOTE_BLOCK_GUITAR), CHIME("chime", SoundEffects.BLOCK_NOTE_BLOCK_CHIME), XYLOPHONE("xylophone", SoundEffects.BLOCK_NOTE_BLOCK_XYLOPHONE), IRON_XYLOPHONE("iron_xylophone", SoundEffects.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE), COW_BELL("cow_bell", SoundEffects.BLOCK_NOTE_BLOCK_COW_BELL), DIDGERIDOO("didgeridoo", SoundEffects.BLOCK_NOTE_BLOCK_DIDGERIDOO), BIT("bit", SoundEffects.BLOCK_NOTE_BLOCK_BIT), BANJO("banjo", SoundEffects.BLOCK_NOTE_BLOCK_BANJO), PLING("pling", SoundEffects.BLOCK_NOTE_BLOCK_PLING);

    private final String q;
    private final SoundEffect r;

    private BlockPropertyInstrument(String s, SoundEffect soundeffect) {
        this.q = s;
        this.r = soundeffect;
    }

    @Override
    public String getName() {
        return this.q;
    }

    public SoundEffect a() {
        return this.r;
    }

    public static BlockPropertyInstrument a(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        if (block == Blocks.CLAY) {
            return BlockPropertyInstrument.FLUTE;
        } else if (block == Blocks.GOLD_BLOCK) {
            return BlockPropertyInstrument.BELL;
        } else if (block.a(TagsBlock.WOOL)) {
            return BlockPropertyInstrument.GUITAR;
        } else if (block == Blocks.PACKED_ICE) {
            return BlockPropertyInstrument.CHIME;
        } else if (block == Blocks.BONE_BLOCK) {
            return BlockPropertyInstrument.XYLOPHONE;
        } else if (block == Blocks.IRON_BLOCK) {
            return BlockPropertyInstrument.IRON_XYLOPHONE;
        } else if (block == Blocks.SOUL_SAND) {
            return BlockPropertyInstrument.COW_BELL;
        } else if (block == Blocks.PUMPKIN) {
            return BlockPropertyInstrument.DIDGERIDOO;
        } else if (block == Blocks.EMERALD_BLOCK) {
            return BlockPropertyInstrument.BIT;
        } else if (block == Blocks.HAY_BLOCK) {
            return BlockPropertyInstrument.BANJO;
        } else if (block == Blocks.GLOWSTONE) {
            return BlockPropertyInstrument.PLING;
        } else {
            Material material = iblockdata.getMaterial();

            return material == Material.STONE ? BlockPropertyInstrument.BASEDRUM : (material == Material.SAND ? BlockPropertyInstrument.SNARE : (material == Material.SHATTERABLE ? BlockPropertyInstrument.HAT : (material == Material.WOOD ? BlockPropertyInstrument.BASS : BlockPropertyInstrument.HARP)));
        }
    }
}
