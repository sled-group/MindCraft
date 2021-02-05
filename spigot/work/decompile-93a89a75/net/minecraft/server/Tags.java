package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tags<T> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson b = new Gson();
    private static final int c = ".json".length();
    private Map<MinecraftKey, Tag<T>> d = ImmutableMap.of();
    private final Function<MinecraftKey, Optional<T>> e;
    private final String f;
    private final boolean g;
    private final String h;

    public Tags(Function<MinecraftKey, Optional<T>> function, String s, boolean flag, String s1) {
        this.e = function;
        this.f = s;
        this.g = flag;
        this.h = s1;
    }

    @Nullable
    public Tag<T> a(MinecraftKey minecraftkey) {
        return (Tag) this.d.get(minecraftkey);
    }

    public Tag<T> b(MinecraftKey minecraftkey) {
        Tag<T> tag = (Tag) this.d.get(minecraftkey);

        return tag == null ? new Tag<>(minecraftkey) : tag;
    }

    public Collection<MinecraftKey> a() {
        return this.d.keySet();
    }

    public CompletableFuture<Map<MinecraftKey, Tag.a<T>>> a(IResourceManager iresourcemanager, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            Map<MinecraftKey, Tag.a<T>> map = Maps.newHashMap();
            Iterator iterator = iresourcemanager.a(this.f, (s) -> {
                return s.endsWith(".json");
            }).iterator();

            while (iterator.hasNext()) {
                MinecraftKey minecraftkey = (MinecraftKey) iterator.next();
                String s = minecraftkey.getKey();
                MinecraftKey minecraftkey1 = new MinecraftKey(minecraftkey.getNamespace(), s.substring(this.f.length() + 1, s.length() - Tags.c));

                try {
                    Iterator iterator1 = iresourcemanager.c(minecraftkey).iterator();

                    while (iterator1.hasNext()) {
                        IResource iresource = (IResource) iterator1.next();

                        try {
                            InputStream inputstream = iresource.b();
                            Throwable throwable = null;

                            try {
                                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
                                Throwable throwable1 = null;

                                try {
                                    JsonObject jsonobject = (JsonObject) ChatDeserializer.a(Tags.b, (Reader) bufferedreader, JsonObject.class);

                                    if (jsonobject == null) {
                                        Tags.LOGGER.error("Couldn't load {} tag list {} from {} in data pack {} as it's empty or null", this.h, minecraftkey1, minecraftkey, iresource.d());
                                    } else {
                                        ((Tag.a) map.computeIfAbsent(minecraftkey1, (minecraftkey2) -> {
                                            return (Tag.a) SystemUtils.a((Object) Tag.a.a(), (tag_a) -> {
                                                tag_a.a(this.g);
                                            });
                                        })).a(this.e, jsonobject);
                                    }
                                } catch (Throwable throwable2) {
                                    throwable1 = throwable2;
                                    throw throwable2;
                                } finally {
                                    if (bufferedreader != null) {
                                        if (throwable1 != null) {
                                            try {
                                                bufferedreader.close();
                                            } catch (Throwable throwable3) {
                                                throwable1.addSuppressed(throwable3);
                                            }
                                        } else {
                                            bufferedreader.close();
                                        }
                                    }

                                }
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
                        } catch (RuntimeException | IOException ioexception) {
                            Tags.LOGGER.error("Couldn't read {} tag list {} from {} in data pack {}", this.h, minecraftkey1, minecraftkey, iresource.d(), ioexception);
                        } finally {
                            IOUtils.closeQuietly(iresource);
                        }
                    }
                } catch (IOException ioexception1) {
                    Tags.LOGGER.error("Couldn't read {} tag list {} from {}", this.h, minecraftkey1, minecraftkey, ioexception1);
                }
            }

            return map;
        }, executor);
    }

    public void a(Map<MinecraftKey, Tag.a<T>> map) {
        HashMap hashmap = Maps.newHashMap();

        while (!map.isEmpty()) {
            boolean flag = false;
            Iterator iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<MinecraftKey, Tag.a<T>> entry = (Entry) iterator.next();
                Tag.a<T> tag_a = (Tag.a) entry.getValue();

                hashmap.getClass();
                if (tag_a.a(hashmap::get)) {
                    flag = true;
                    MinecraftKey minecraftkey = (MinecraftKey) entry.getKey();

                    hashmap.put(minecraftkey, tag_a.b(minecraftkey));
                    iterator.remove();
                }
            }

            if (!flag) {
                map.forEach((minecraftkey1, tag_a1) -> {
                    Tags.LOGGER.error("Couldn't load {} tag {} as it either references another tag that doesn't exist, or ultimately references itself", this.h, minecraftkey1);
                });
                break;
            }
        }

        map.forEach((minecraftkey1, tag_a1) -> {
            Tag tag = (Tag) hashmap.put(minecraftkey1, tag_a1.b(minecraftkey1));
        });
        this.b((Map) hashmap);
    }

    protected void b(Map<MinecraftKey, Tag<T>> map) {
        this.d = ImmutableMap.copyOf(map);
    }

    public Map<MinecraftKey, Tag<T>> b() {
        return this.d;
    }
}
