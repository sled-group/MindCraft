package net.minecraft.server;

public interface ILightEngine {

    default void a(BlockPosition blockposition, boolean flag) {
        this.a(SectionPosition.a(blockposition), flag);
    }

    void a(SectionPosition sectionposition, boolean flag);
}
