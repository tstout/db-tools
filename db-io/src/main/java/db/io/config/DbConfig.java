package db.io.config;

import com.google.common.collect.ImmutableClassToInstanceMap;

import static com.google.common.base.Preconditions.*;

/**
 * This is a little funky...not currently used...but I keep thinking it
 * will be useful soon.
 */
public abstract class DbConfig {
    private ImmutableClassToInstanceMap<Property> config;
    private boolean configured = false;
    private final DBContext context = new DBContext();

    public <T extends Property> T get(Class<T> type) {
        checkState(configured, "DbConfig not yet configured. Did you forget to call configure?");
        return config.getInstance(type);
    }

    protected void configured() {
        config = context.build();
        configured = true;
    }

    protected void add(Class clazz, Property property) {
        context.add(clazz, property);
    }
}
