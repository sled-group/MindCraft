package net.minecraft.server;

public interface IVectorPosition {

    Vec3D a(CommandListenerWrapper commandlistenerwrapper);

    Vec2F b(CommandListenerWrapper commandlistenerwrapper);

    default BlockPosition c(CommandListenerWrapper commandlistenerwrapper) {
        return new BlockPosition(this.a(commandlistenerwrapper));
    }

    boolean a();

    boolean b();

    boolean c();
}
