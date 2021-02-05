package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;

public class DataConverterSkeleton extends DataConverterEntityNameAbstract {

    public DataConverterSkeleton(Schema schema, boolean flag) {
        super("EntitySkeletonSplitFix", schema, flag);
    }

    @Override
    protected Pair<String, Dynamic<?>> a(String s, Dynamic<?> dynamic) {
        if (Objects.equals(s, "Skeleton")) {
            int i = dynamic.get("SkeletonType").asInt(0);

            if (i == 1) {
                s = "WitherSkeleton";
            } else if (i == 2) {
                s = "Stray";
            }
        }

        return Pair.of(s, dynamic);
    }
}
