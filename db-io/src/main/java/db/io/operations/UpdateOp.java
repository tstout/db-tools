package db.io.operations;

class UpdateOp {
    private final String sql;
    private final Object[] args;

    UpdateOp(String sql, Object[] args) {
        this.sql = sql;
        this.args = args;
    }

    String sql() {
        return sql;
    }

    Object[] args() {
        return args;
    }
}
