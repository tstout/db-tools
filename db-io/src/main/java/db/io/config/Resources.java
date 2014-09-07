package db.io.config;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.io.Resources.getResource;

public final class Resources {
    private Resources() {}

    public static String load(String resName, Class clazz) {
        try {
            return com.google.common.io.Resources.toString(
                    getResource(clazz, resName),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw propagate(e);
        }
    }

    public static String load(String resName) {
        return load(resName, Resources.class);
    }
}
