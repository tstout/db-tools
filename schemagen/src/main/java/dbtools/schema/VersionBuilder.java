package dbtools.schema;

class VersionBuilder {
    int minorVersion;
    int majorVersion;
    int pointVersion;
    String versionDescr;
    String versionName;

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
