package db.io.common;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.google.common.base.Throwables.*;

public class ResourceLoader {
    private final Class<?> clazz;

    public ResourceLoader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String load(String resName) {
        try {
            return Resources.toString(Resources.getResource(clazz, resName), Charset.defaultCharset());
        } catch (IOException e) {
            throw propagate(e);
        }
    }
}

