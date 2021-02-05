package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringEscapeUtils;

public class CSVWriter {

    private final Writer a;
    private final int b;

    private CSVWriter(Writer writer, List<String> list) throws IOException {
        this.a = writer;
        this.b = list.size();
        this.a(list.stream());
    }

    public static CSVWriter.a a() {
        return new CSVWriter.a();
    }

    public void a(Object... aobject) throws IOException {
        if (aobject.length != this.b) {
            throw new IllegalArgumentException("Invalid number of columns, expected " + this.b + ", but got " + aobject.length);
        } else {
            this.a(Stream.of(aobject));
        }
    }

    private void a(Stream<?> stream) throws IOException {
        this.a.write((String) stream.map(CSVWriter::a).collect(Collectors.joining(",")) + "\r\n");
    }

    private static String a(@Nullable Object object) {
        return StringEscapeUtils.escapeCsv(object != null ? object.toString() : "[null]");
    }

    public static class a {

        private final List<String> a = Lists.newArrayList();

        public a() {}

        public CSVWriter.a a(String s) {
            this.a.add(s);
            return this;
        }

        public CSVWriter a(Writer writer) throws IOException {
            return new CSVWriter(writer, this.a);
        }
    }
}
