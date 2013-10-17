package dbtools.schema;

class VersionBuilder {
    int minorVersion, majorVersion, pointVersion;
    String versionDescr, versionName;

    ChangeLog build() {
        return new ChangeLog.DefaultChangeLog() {{
            major = majorVersion;
            minor = minorVersion;
            point = pointVersion;
            name = versionName;
            descr = versionDescr;
        }};
    }
}
