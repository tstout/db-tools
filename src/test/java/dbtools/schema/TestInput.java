package dbtools.schema;

import dbtools.common.ResourceLoader;

public enum TestInput {
    EMPTY_TABLE("/input/empty-table.schema");

    private final String input;

    TestInput(String resName) {
      input = new ResourceLoader(getClass()).load(resName);
    }

    public String input() {
        return this.input;
    }
}
