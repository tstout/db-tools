package db.io.operations;

import db.io.core.ConnFactory;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;

public class UpdateBuilder {
    private ConnFactory factory;
    private List<UpdateOp> ops = newArrayList();

    public UpdateBuilder addOp(String sql, Object... args) {
        ops.add(new UpdateOp(sql, args));
        return this;
    }

    public UpdateBuilder withConnFactory(ConnFactory factory) {
        this.factory = factory;
        return this;
    }

    public Update build() {
        Collection<UpdateOp> opsCopy = copyOf(ops);
        ops.clear();
        return new UpdateRunner(factory, opsCopy);
    }
}

