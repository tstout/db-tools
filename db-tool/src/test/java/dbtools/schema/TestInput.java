package dbtools.schema;

import dbtools.common.ResourceLoader;

public enum TestInput {
    EMPTY_TABLE("/input/empty-table.schema"),
    SINGLE_COL_TABLE("/input/table-with-one-column.schema"),
    MULTI_COL_TABLE("/input/table-with-multiple-columns.schema"),
    COL_ATTR_TABLE("/input/col-attribute.schema"),
    VERSION_INFO("/input/version-info.schema");

    private final String input;

    TestInput(String resName) {
      input = new ResourceLoader(getClass()).load(resName);
    }

    public String input() {
        return this.input;
    }
}
