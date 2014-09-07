package db.io.operations;

import db.io.core.ConnFactory;

public final class Updates {
    private Updates() {}

    public static Update newUpdate(ConnFactory connFactory, String sql, Object... args) {
        return new UpdateBuilder()
                .addOp(sql, args)
                .withConnFactory(connFactory)
                .build();
    }
}
