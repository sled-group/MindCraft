package net.minecraft.server;

import java.util.function.Predicate;
import javax.annotation.Nullable;

public class MaterialPredicate implements Predicate<IBlockData> {

    private static final MaterialPredicate a = new MaterialPredicate(Material.AIR) {
        @Override
        public boolean test(@Nullable IBlockData iblockdata) {
            return iblockdata != null && iblockdata.isAir();
        }
    };
    private final Material b;

    private MaterialPredicate(Material material) {
        this.b = material;
    }

    public static MaterialPredicate a(Material material) {
        return material == Material.AIR ? MaterialPredicate.a : new MaterialPredicate(material);
    }

    public boolean test(@Nullable IBlockData iblockdata) {
        return iblockdata != null && iblockdata.getMaterial() == this.b;
    }
}
