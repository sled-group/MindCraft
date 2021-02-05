package net.minecraft.server;

import com.mojang.datafixers.DataFixer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Convertable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final DateTimeFormatter b = (new DateTimeFormatterBuilder()).appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral('_').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral('-').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral('-').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
    private final java.nio.file.Path c;
    private final java.nio.file.Path d;
    private final DataFixer e;

    public Convertable(java.nio.file.Path java_nio_file_path, java.nio.file.Path java_nio_file_path1, DataFixer datafixer) {
        this.e = datafixer;

        try {
            Files.createDirectories(Files.exists(java_nio_file_path, new LinkOption[0]) ? java_nio_file_path.toRealPath() : java_nio_file_path);
        } catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }

        this.c = java_nio_file_path;
        this.d = java_nio_file_path1;
    }

    private int e() {
        return 19133;
    }

    public WorldNBTStorage a(String s, @Nullable MinecraftServer minecraftserver) {
        return a(this.c, this.e, s, minecraftserver);
    }

    protected static WorldNBTStorage a(java.nio.file.Path java_nio_file_path, DataFixer datafixer, String s, @Nullable MinecraftServer minecraftserver) {
        return new WorldNBTStorage(java_nio_file_path.toFile(), s, minecraftserver, datafixer);
    }

    public boolean isConvertable(String s) {
        WorldData worlddata = this.b(s);

        return worlddata != null && worlddata.j() != this.e();
    }

    public boolean convert(String s, IProgressUpdate iprogressupdate) {
        return WorldUpgraderIterator.a(this.c, this.e, s, iprogressupdate);
    }

    @Nullable
    public WorldData b(String s) {
        return a(this.c, this.e, s);
    }

    @Nullable
    protected static WorldData a(java.nio.file.Path java_nio_file_path, DataFixer datafixer, String s) {
        File file = new File(java_nio_file_path.toFile(), s);

        if (!file.exists()) {
            return null;
        } else {
            File file1 = new File(file, "level.dat");

            if (file1.exists()) {
                WorldData worlddata = a(file1, datafixer);

                if (worlddata != null) {
                    return worlddata;
                }
            }

            file1 = new File(file, "level.dat_old");
            return file1.exists() ? a(file1, datafixer) : null;
        }
    }

    @Nullable
    public static WorldData a(File file, DataFixer datafixer) {
        try {
            NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a((InputStream) (new FileInputStream(file)));
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Data");
            NBTTagCompound nbttagcompound2 = nbttagcompound1.hasKeyOfType("Player", 10) ? nbttagcompound1.getCompound("Player") : null;

            nbttagcompound1.remove("Player");
            int i = nbttagcompound1.hasKeyOfType("DataVersion", 99) ? nbttagcompound1.getInt("DataVersion") : -1;

            return new WorldData(GameProfileSerializer.a(datafixer, DataFixTypes.LEVEL, nbttagcompound1, i), datafixer, i, nbttagcompound2);
        } catch (Exception exception) {
            Convertable.LOGGER.error("Exception reading {}", file, exception);
            return null;
        }
    }

    public File b(String s, String s1) {
        return this.c.resolve(s).resolve(s1).toFile();
    }
}
