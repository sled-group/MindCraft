package net.minecraft.server;

import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.function.Supplier;

public class ResourcePackSourceFolder implements ResourcePackSource {

    private static final FileFilter a = (file) -> {
        boolean flag = file.isFile() && file.getName().endsWith(".zip");
        boolean flag1 = file.isDirectory() && (new File(file, "pack.mcmeta")).isFile();

        return flag || flag1;
    };
    private final File file;

    public ResourcePackSourceFolder(File file) {
        this.file = file;
    }

    @Override
    public <T extends ResourcePackLoader> void a(Map<String, T> map, ResourcePackLoader.b<T> resourcepackloader_b) {
        if (!this.file.isDirectory()) {
            this.file.mkdirs();
        }

        File[] afile = this.file.listFiles(ResourcePackSourceFolder.a);

        if (afile != null) {
            File[] afile1 = afile;
            int i = afile.length;

            for (int j = 0; j < i; ++j) {
                File file = afile1[j];
                String s = "file/" + file.getName();
                T t0 = ResourcePackLoader.a(s, false, this.a(file), resourcepackloader_b, ResourcePackLoader.Position.TOP);

                if (t0 != null) {
                    map.put(s, t0);
                }
            }

        }
    }

    private Supplier<IResourcePack> a(File file) {
        return file.isDirectory() ? () -> {
            return new ResourcePackFolder(file);
        } : () -> {
            return new ResourcePackFile(file);
        };
    }
}
