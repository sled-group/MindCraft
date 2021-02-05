package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public enum EnumBannerPatternType {

    BASE("base", "b"), SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "), SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"), SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "), SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "), STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"), STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "), STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "), STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"), STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "), STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "), STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"), STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "), STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "), CROSS("cross", "cr", "# #", " # ", "# #"), STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "), TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"), TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "), TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "), TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "), DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "), DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"), DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "), DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "), CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "), RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "), HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "), HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "), HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"), HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"), BORDER("border", "bo", "###", "# #", "###"), CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.VINE)), GRADIENT("gradient", "gra", "# #", " # ", " # "), GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"), BRICKS("bricks", "bri", new ItemStack(Blocks.BRICKS)), GLOBE("globe", "glb"), CREEPER("creeper", "cre", new ItemStack(Items.CREEPER_HEAD)), SKULL("skull", "sku", new ItemStack(Items.WITHER_SKELETON_SKULL)), FLOWER("flower", "flo", new ItemStack(Blocks.OXEYE_DAISY)), MOJANG("mojang", "moj", new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));

    public static final int O = values().length;
    public static final int P = EnumBannerPatternType.O - 5 - 1;
    private final String Q;
    private final String R;
    private final String[] S;
    private ItemStack T;

    private EnumBannerPatternType(String s, String s1) {
        this.S = new String[3];
        this.T = ItemStack.a;
        this.Q = s;
        this.R = s1;
    }

    private EnumBannerPatternType(String s, String s1, ItemStack itemstack) {
        this(s, s1);
        this.T = itemstack;
    }

    private EnumBannerPatternType(String s, String s1, String s2, String s3, String s4) {
        this(s, s1);
        this.S[0] = s2;
        this.S[1] = s3;
        this.S[2] = s4;
    }

    public String b() {
        return this.R;
    }

    public static class a {

        private final List<Pair<EnumBannerPatternType, EnumColor>> a = Lists.newArrayList();

        public a() {}

        public EnumBannerPatternType.a a(EnumBannerPatternType enumbannerpatterntype, EnumColor enumcolor) {
            this.a.add(Pair.of(enumbannerpatterntype, enumcolor));
            return this;
        }

        public NBTTagList a() {
            NBTTagList nbttaglist = new NBTTagList();
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                Pair<EnumBannerPatternType, EnumColor> pair = (Pair) iterator.next();
                NBTTagCompound nbttagcompound = new NBTTagCompound();

                nbttagcompound.setString("Pattern", ((EnumBannerPatternType) pair.getLeft()).R);
                nbttagcompound.setInt("Color", ((EnumColor) pair.getRight()).getColorIndex());
                nbttaglist.add(nbttagcompound);
            }

            return nbttaglist;
        }
    }
}
