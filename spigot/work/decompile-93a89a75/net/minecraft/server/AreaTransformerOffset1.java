package net.minecraft.server;

public interface AreaTransformerOffset1 extends AreaTransformer {

    @Override
    default int a(int i) {
        return i - 1;
    }

    @Override
    default int b(int i) {
        return i - 1;
    }
}
