package db.io.config;

import com.google.common.collect.ImmutableClassToInstanceMap;

class DBContext {
    private ImmutableClassToInstanceMap.Builder<Property> builder =
            new ImmutableClassToInstanceMap.Builder<>();

    public DBContext add(Class clazz, Property property) {
        builder.put(clazz, property);
        return this;
    }

    ImmutableClassToInstanceMap<Property> build() {
        return builder.build();
    }
}
