package dbtools.cmdopts;

import dbtools.common.ResourceLoader;

public enum Docs {

    HELP("/docs/help.txt");

    public final String text;

    Docs(String resName) {
        text = new ResourceLoader(this.getClass()).load(resName);
    }
}

