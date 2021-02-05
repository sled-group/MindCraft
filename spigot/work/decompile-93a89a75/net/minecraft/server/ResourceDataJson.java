package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ResourceDataJson extends ResourceDataAbstract<Map<MinecraftKey, JsonObject>> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int b = ".json".length();
    private final Gson c;
    private final String d;

    public ResourceDataJson(Gson gson, String s) {
        this.c = gson;
        this.d = s;
    }

    @Override
    protected Map<MinecraftKey, JsonObject> b(IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller) {
        Map<MinecraftKey, JsonObject> map = Maps.newHashMap();
        int i = this.d.length() + 1;
        Iterator iterator = iresourcemanager.a(this.d, (s) -> {
            return s.endsWith(".json");
        }).iterator();

        while (iterator.hasNext()) {
            MinecraftKey minecraftkey = (MinecraftKey) iterator.next();
            String s = minecraftkey.getKey();
            MinecraftKey minecraftkey1 = new MinecraftKey(minecraftkey.getNamespace(), s.substring(i, s.length() - ResourceDataJson.b));

            try {
                IResource iresource = iresourcemanager.a(minecraftkey);
                Throwable throwable = null;

                try {
                    InputStream inputstream = iresource.b();
                    Throwable throwable1 = null;

                    try {
                        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
                        Throwable throwable2 = null;

                        try {
                            JsonObject jsonobject = (JsonObject) ChatDeserializer.a(this.c, (Reader) bufferedreader, JsonObject.class);

                            if (jsonobject != null) {
                                JsonObject jsonobject1 = (JsonObject) map.put(minecraftkey1, jsonobject);

                                if (jsonobject1 != null) {
                                    throw new IllegalStateException("Duplicate data file ignored with ID " + minecraftkey1);
                                }
                            } else {
                                ResourceDataJson.LOGGER.error("Couldn't load data file {} from {} as it's null or empty", minecraftkey1, minecraftkey);
                            }
                        } catch (Throwable throwable3) {
                            throwable2 = throwable3;
                            throw throwable3;
                        } finally {
                            if (bufferedreader != null) {
                                if (throwable2 != null) {
                                    try {
                                        bufferedreader.close();
                                    } catch (Throwable throwable4) {
                                        throwable2.addSuppressed(throwable4);
                                    }
                                } else {
                                    bufferedreader.close();
                                }
                            }

                        }
                    } catch (Throwable throwable5) {
                        throwable1 = throwable5;
                        throw throwable5;
                    } finally {
                        if (inputstream != null) {
                            if (throwable1 != null) {
                                try {
                                    inputstream.close();
                                } catch (Throwable throwable6) {
                                    throwable1.addSuppressed(throwable6);
                                }
                            } else {
                                inputstream.close();
                            }
                        }

                    }
                } catch (Throwable throwable7) {
                    throwable = throwable7;
                    throw throwable7;
                } finally {
                    if (iresource != null) {
                        if (throwable != null) {
                            try {
                                iresource.close();
                            } catch (Throwable throwable8) {
                                throwable.addSuppressed(throwable8);
                            }
                        } else {
                            iresource.close();
                        }
                    }

                }
            } catch (IllegalArgumentException | IOException | JsonParseException jsonparseexception) {
                ResourceDataJson.LOGGER.error("Couldn't parse data file {} from {}", minecraftkey1, minecraftkey, jsonparseexception);
            }
        }

        return map;
    }
}
