package net.minecraft.server;

import java.io.Closeable;
import java.io.InputStream;

public interface IResource extends Closeable {

    InputStream b();

    String d();
}
