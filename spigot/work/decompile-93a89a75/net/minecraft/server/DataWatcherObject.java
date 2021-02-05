package net.minecraft.server;

public class DataWatcherObject<T> {

    private final int a;
    private final DataWatcherSerializer<T> b;

    public DataWatcherObject(int i, DataWatcherSerializer<T> datawatcherserializer) {
        this.a = i;
        this.b = datawatcherserializer;
    }

    public int a() {
        return this.a;
    }

    public DataWatcherSerializer<T> b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            DataWatcherObject<?> datawatcherobject = (DataWatcherObject) object;

            return this.a == datawatcherobject.a;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        return "<entity data: " + this.a + ">";
    }
}
