package net.minecraft.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.bridge.game.GameVersion;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinecraftVersion implements GameVersion {

    private static final Logger LOGGER = LogManager.getLogger();
    private final String b;
    private final String c;
    private final boolean d;
    private final int e;
    private final int f;
    private final int g;
    private final Date h;
    private final String i;

    public MinecraftVersion() {
        this.b = UUID.randomUUID().toString().replaceAll("-", "");
        this.c = "1.14.4";
        this.d = true;
        this.e = 1976;
        this.f = 498;
        this.g = 4;
        this.h = new Date();
        this.i = "1.14.4";
    }

    protected MinecraftVersion(JsonObject jsonobject) {
        this.b = ChatDeserializer.h(jsonobject, "id");
        this.c = ChatDeserializer.h(jsonobject, "name");
        this.i = ChatDeserializer.h(jsonobject, "release_target");
        this.d = ChatDeserializer.j(jsonobject, "stable");
        this.e = ChatDeserializer.n(jsonobject, "world_version");
        this.f = ChatDeserializer.n(jsonobject, "protocol_version");
        this.g = ChatDeserializer.n(jsonobject, "pack_version");
        this.h = Date.from(ZonedDateTime.parse(ChatDeserializer.h(jsonobject, "build_time")).toInstant());
    }

    public static GameVersion a() {
        try {
            InputStream inputstream = MinecraftVersion.class.getResourceAsStream("/version.json");
            Throwable throwable = null;

            MinecraftVersion minecraftversion;

            try {
                if (inputstream != null) {
                    InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                    Throwable throwable1 = null;

                    try {
                        Object object;

                        try {
                            object = new MinecraftVersion(ChatDeserializer.a((Reader) inputstreamreader));
                            return (GameVersion) object;
                        } catch (Throwable throwable2) {
                            object = throwable2;
                            throwable1 = throwable2;
                            throw throwable2;
                        }
                    } finally {
                        if (inputstreamreader != null) {
                            if (throwable1 != null) {
                                try {
                                    inputstreamreader.close();
                                } catch (Throwable throwable3) {
                                    throwable1.addSuppressed(throwable3);
                                }
                            } else {
                                inputstreamreader.close();
                            }
                        }

                    }
                }

                MinecraftVersion.LOGGER.warn("Missing version information!");
                minecraftversion = new MinecraftVersion();
            } catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            } finally {
                if (inputstream != null) {
                    if (throwable != null) {
                        try {
                            inputstream.close();
                        } catch (Throwable throwable5) {
                            throwable.addSuppressed(throwable5);
                        }
                    } else {
                        inputstream.close();
                    }
                }

            }

            return minecraftversion;
        } catch (JsonParseException | IOException ioexception) {
            throw new IllegalStateException("Game version information is corrupt", ioexception);
        }
    }

    public String getId() {
        return this.b;
    }

    public String getName() {
        return this.c;
    }

    public String getReleaseTarget() {
        return this.i;
    }

    public int getWorldVersion() {
        return this.e;
    }

    public int getProtocolVersion() {
        return this.f;
    }

    public int getPackVersion() {
        return this.g;
    }

    public Date getBuildTime() {
        return this.h;
    }

    public boolean isStable() {
        return this.d;
    }
}
