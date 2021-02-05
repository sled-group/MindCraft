package net.minecraft.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ResourcePackAbstract implements IResourcePack {

    private static final Logger LOGGER = LogManager.getLogger();
    protected final File a;

    public ResourcePackAbstract(File file) {
        this.a = file;
    }

    private static String c(EnumResourcePackType enumresourcepacktype, MinecraftKey minecraftkey) {
        return String.format("%s/%s/%s", enumresourcepacktype.a(), minecraftkey.getNamespace(), minecraftkey.getKey());
    }

    protected static String a(File file, File file1) {
        return file.toURI().relativize(file1.toURI()).getPath();
    }

    @Override
    public InputStream a(EnumResourcePackType enumresourcepacktype, MinecraftKey minecraftkey) throws IOException {
        return this.a(c(enumresourcepacktype, minecraftkey));
    }

    @Override
    public boolean b(EnumResourcePackType enumresourcepacktype, MinecraftKey minecraftkey) {
        return this.c(c(enumresourcepacktype, minecraftkey));
    }

    protected abstract InputStream a(String s) throws IOException;

    protected abstract boolean c(String s);

    protected void d(String s) {
        ResourcePackAbstract.LOGGER.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", s, this.a);
    }

    @Nullable
    @Override
    public <T> T a(ResourcePackMetaParser<T> resourcepackmetaparser) throws IOException {
        InputStream inputstream = this.a("pack.mcmeta");
        Throwable throwable = null;

        Object object;

        try {
            object = a(resourcepackmetaparser, inputstream);
        } catch (Throwable throwable1) {
            throwable = throwable1;
            throw throwable1;
        } finally {
            if (inputstream != null) {
                if (throwable != null) {
                    try {
                        inputstream.close();
                    } catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                } else {
                    inputstream.close();
                }
            }

        }

        return object;
    }

    @Nullable
    public static <T> T a(ResourcePackMetaParser<T> resourcepackmetaparser, InputStream inputstream) {
        JsonObject jsonobject;

        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
            Throwable throwable = null;

            try {
                jsonobject = ChatDeserializer.a((Reader) bufferedreader);
            } catch (Throwable throwable1) {
                throwable = throwable1;
                throw throwable1;
            } finally {
                if (bufferedreader != null) {
                    if (throwable != null) {
                        try {
                            bufferedreader.close();
                        } catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    } else {
                        bufferedreader.close();
                    }
                }

            }
        } catch (JsonParseException | IOException ioexception) {
            ResourcePackAbstract.LOGGER.error("Couldn't load {} metadata", resourcepackmetaparser.a(), ioexception);
            return null;
        }

        if (!jsonobject.has(resourcepackmetaparser.a())) {
            return null;
        } else {
            try {
                return resourcepackmetaparser.a(ChatDeserializer.t(jsonobject, resourcepackmetaparser.a()));
            } catch (JsonParseException jsonparseexception) {
                ResourcePackAbstract.LOGGER.error("Couldn't load {} metadata", resourcepackmetaparser.a(), jsonparseexception);
                return null;
            }
        }
    }

    @Override
    public String a() {
        return this.a.getName();
    }
}
