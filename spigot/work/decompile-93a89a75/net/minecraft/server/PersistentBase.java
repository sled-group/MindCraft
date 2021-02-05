package net.minecraft.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PersistentBase {

    private static final Logger LOGGER = LogManager.getLogger();
    private final String id;
    private boolean c;

    public PersistentBase(String s) {
        this.id = s;
    }

    public abstract void a(NBTTagCompound nbttagcompound);

    public abstract NBTTagCompound b(NBTTagCompound nbttagcompound);

    public void b() {
        this.a(true);
    }

    public void a(boolean flag) {
        this.c = flag;
    }

    public boolean c() {
        return this.c;
    }

    public String getId() {
        return this.id;
    }

    public void a(File file) {
        if (this.c()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            nbttagcompound.set("data", this.b(new NBTTagCompound()));
            nbttagcompound.setInt("DataVersion", SharedConstants.a().getWorldVersion());

            try {
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                Throwable throwable = null;

                try {
                    NBTCompressedStreamTools.a(nbttagcompound, (OutputStream) fileoutputstream);
                } catch (Throwable throwable1) {
                    throwable = throwable1;
                    throw throwable1;
                } finally {
                    if (fileoutputstream != null) {
                        if (throwable != null) {
                            try {
                                fileoutputstream.close();
                            } catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        } else {
                            fileoutputstream.close();
                        }
                    }

                }
            } catch (IOException ioexception) {
                PersistentBase.LOGGER.error("Could not save data {}", this, ioexception);
            }

            this.a(false);
        }
    }
}
