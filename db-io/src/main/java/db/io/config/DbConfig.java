package db.io.config;

import com.google.common.collect.ImmutableClassToInstanceMap;

public abstract class DbConfig {
    private final ImmutableClassToInstanceMap<Property> config;

    public DbConfig() {
        DBContext props = new DBContext();
        config(props);
        config = props.build();
    }

    public <T extends Property> Property<T> get(Class<T> type) {
        return config.getInstance(type);
    }

    abstract protected void config(DBContext context);
}
