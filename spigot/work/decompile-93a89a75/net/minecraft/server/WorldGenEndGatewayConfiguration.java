package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Optional;

public class WorldGenEndGatewayConfiguration implements WorldGenFeatureConfiguration {

    private final Optional<BlockPosition> a;
    private final boolean b;

    private WorldGenEndGatewayConfiguration(Optional<BlockPosition> optional, boolean flag) {
        this.a = optional;
        this.b = flag;
    }

    public static WorldGenEndGatewayConfiguration a(BlockPosition blockposition, boolean flag) {
        return new WorldGenEndGatewayConfiguration(Optional.of(blockposition), flag);
    }

    public static WorldGenEndGatewayConfiguration a() {
        return new WorldGenEndGatewayConfiguration(Optional.empty(), false);
    }

    public Optional<BlockPosition> b() {
        return this.a;
    }

    public boolean c() {
        return this.b;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, this.a.map((blockposition) -> {
            return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("exit_x"), dynamicops.createInt(blockposition.getX()), dynamicops.createString("exit_y"), dynamicops.createInt(blockposition.getY()), dynamicops.createString("exit_z"), dynamicops.createInt(blockposition.getZ()), dynamicops.createString("exact"), dynamicops.createBoolean(this.b)));
        }).orElse(dynamicops.emptyMap()));
    }

    public static <T> WorldGenEndGatewayConfiguration a(Dynamic<T> dynamic) {
        Optional<BlockPosition> optional = dynamic.get("exit_x").asNumber().flatMap((number) -> {
            return dynamic.get("exit_y").asNumber().flatMap((number1) -> {
                return dynamic.get("exit_z").asNumber().map((number2) -> {
                    return new BlockPosition(number.intValue(), number1.intValue(), number2.intValue());
                });
            });
        });
        boolean flag = dynamic.get("exact").asBoolean(false);

        return new WorldGenEndGatewayConfiguration(optional, flag);
    }
}
