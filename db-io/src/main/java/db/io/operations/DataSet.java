package db.io.operations;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Ultimate result of DB queries?? This should probably be extracted
 * to an interface. Not sure if having this public is beneficial.
 */
class DataSet {

    public interface Action {
        void exec(Collection<Column> row);
    }

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

    public Set<String> columnNames() {
        return table.columnKeySet();
    }

    public void each(Action action) {
        for (Map<String, Column> rowMap : table.rowMap().values()) {
            action.exec(rowMap.values());
        }
    }
}
