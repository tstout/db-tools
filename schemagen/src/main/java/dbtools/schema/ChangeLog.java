package dbtools.schema;

import com.google.common.base.Objects;

public interface ChangeLog {
    int minorVersion();
    int majorVersion();
    int pointVersion();
    String descr();
    String name();

    public static final ChangeLog EMPTY = new DefaultChangeLog() {{
        major = 0;
        minor = 0;
        point = 0;
        name = "nil";
        descr = "nil";
    }};

    public static class DefaultChangeLog implements ChangeLog {
        protected int minor;
        protected int major;
        protected int point;
        protected String name;
        protected String descr;

        @Override public int minorVersion() {
            return minor;
        }

        @Override public int majorVersion() {
            return major;
        }

        @Override public int pointVersion() {
            return point;
        }

        @Override public String name() {
            return name;
        }

        public String descr() {
            return descr;
        }

        @Override public int hashCode() {
            return Objects.hashCode(minor, major, point, name, descr);
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof ChangeLog)) {
                return false;
            }

            ChangeLog other = (ChangeLog) o;
            return Objects.equal(major, other.majorVersion()) &&
                    Objects.equal(minor, other.minorVersion()) &&
                    Objects.equal(point, other.pointVersion()) &&
                    Objects.equal(name, other.name()) &&
                    Objects.equal(descr, other.descr());
        }
    }
}
