package db.io.operations;

import db.io.core.ConnFactory;

public final class Queries {
    private Queries() {}

    public static Query newQuery(ConnFactory connFactory) {
        return new QueryRunner(connFactory);
    }
}
