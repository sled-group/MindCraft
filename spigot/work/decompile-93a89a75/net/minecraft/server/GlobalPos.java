package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Objects;

public final class GlobalPos implements MinecraftSerializable {

    private final DimensionManager dimensionManager;
    private final BlockPosition blockPosition;

    private GlobalPos(DimensionManager dimensionmanager, BlockPosition blockposition) {
        this.dimensionManager = dimensionmanager;
        this.blockPosition = blockposition;
    }

    public static GlobalPos create(DimensionManager dimensionmanager, BlockPosition blockposition) {
        return new GlobalPos(dimensionmanager, blockposition);
    }

    public static GlobalPos a(Dynamic<?> dynamic) {
        return (GlobalPos) dynamic.get("dimension").map(DimensionManager::a).flatMap((dimensionmanager) -> {
            return dynamic.get("pos").map(BlockPosition::a).map((blockposition) -> {
                return new GlobalPos(dimensionmanager, blockposition);
            });
        }).orElseThrow(() -> {
            return new IllegalArgumentException("Could not parse GlobalPos");
        });
    }

    public DimensionManager getDimensionManager() {
        return this.dimensionManager;
    }

    public BlockPosition getBlockPosition() {
        return this.blockPosition;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            GlobalPos globalpos = (GlobalPos) object;

            return Objects.equals(this.dimensionManager, globalpos.dimensionManager) && Objects.equals(this.blockPosition, globalpos.blockPosition);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.dimensionManager, this.blockPosition});
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("dimension"), this.dimensionManager.a(dynamicops), dynamicops.createString("pos"), this.blockPosition.a(dynamicops)));
    }

    public String toString() {
        return this.dimensionManager.toString() + " " + this.blockPosition;
    }
}
