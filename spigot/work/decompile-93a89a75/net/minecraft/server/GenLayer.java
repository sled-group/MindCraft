package net.minecraft.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayer {

    private static final Logger LOGGER = LogManager.getLogger();
    private final AreaLazy b;

    public GenLayer(AreaFactory<AreaLazy> areafactory) {
        this.b = (AreaLazy) areafactory.make();
    }

    public BiomeBase[] a(int i, int j, int k, int l) {
        BiomeBase[] abiomebase = new BiomeBase[k * l];

        for (int i1 = 0; i1 < l; ++i1) {
            for (int j1 = 0; j1 < k; ++j1) {
                int k1 = this.b.a(i + j1, j + i1);
                BiomeBase biomebase = this.a(k1);

                abiomebase[j1 + i1 * k] = biomebase;
            }
        }

        return abiomebase;
    }

    private BiomeBase a(int i) {
        BiomeBase biomebase = (BiomeBase) IRegistry.BIOME.fromId(i);

        if (biomebase == null) {
            if (SharedConstants.b) {
                throw new IllegalStateException("Unknown biome id: " + i);
            } else {
                GenLayer.LOGGER.warn("Unknown biome id: ", i);
                return Biomes.b;
            }
        } else {
            return biomebase;
        }
    }

    public BiomeBase a(int i, int j) {
        return this.a(this.b.a(i, j));
    }
}
