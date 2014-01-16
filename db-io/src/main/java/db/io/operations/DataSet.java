package db.io.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Ultimate result of DB queries??
 */
public class DataSet {
    private Table<Integer, String, Column> table = HashBasedTable.create();

    public Column get(int row, String colName) {
        return table.rowMap().get(row).get(colName);
    }

    public DataSet put(int row, Column col) {
        table.put(row, col.name(), col);
        return this;
    }

    public int numRows() {
        return table.rowMap().size();
    }

    // TODO - might be useful to expose some of Guava's table operations here...
}
