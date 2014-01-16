package db.io.config;

import com.google.common.collect.ImmutableClassToInstanceMap;

import static com.google.common.base.Preconditions.*;

public abstract class DbConfig {
    private ImmutableClassToInstanceMap<Property> config;
    private boolean configured = false;
    protected final DBContext context = new DBContext();

    public <T extends Property> T get(Class<T> type) {
        checkState(configured, "DbConfig not yet configured.");
        return config.getInstance(type);
    }

    protected void configured() {
        config = context.build();
        configured = true;
    }
}
