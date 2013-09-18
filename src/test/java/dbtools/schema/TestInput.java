package dbtools.schema;

import dbtools.common.ResourceLoader;

public enum TestInput {
    EMPTY_TABLE("/input/empty-table.schema"),
    SINGLE_COL_TABLE("/input/table-with-one-column.schema"),
    MULTI_COL_TABLE("/input/table-with-multiple-columns.schema"),
    PK_COL_TABLE("/input/col-attribute.schema");

    private final String input;

    TestInput(String resName) {
      input = new ResourceLoader(getClass()).load(resName);
    }

    public String input() {
        return this.input;
    }
}
