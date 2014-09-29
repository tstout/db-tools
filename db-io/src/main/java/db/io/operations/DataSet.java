package db.io.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Ultimate result of DB queries?? This should probably be extracted
 * to an interface. Not sure yet if having this public would be beneficial.
 */
class DataSet {

    interface Action {
        void exec(Collection<Column> row);
    }

    private Table<Integer, String, Column> table = HashBasedTable.create();

    Column get(int row, String colName) {
        return table.rowMap().get(row).get(colName);
    }

    DataSet put(int row, Column col) {
        table.put(row, col.name(), col);
        return this;
    }

    Set<String> columnNames() {
        return table.columnKeySet();
    }

    void each(Action action) {
        for (Map<String, Column> rowMap : table.rowMap().values()) {
            action.exec(rowMap.values());
        }
    }
}
