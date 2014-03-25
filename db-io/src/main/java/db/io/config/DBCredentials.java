package db.io.config;

import com.google.common.base.Objects;

public interface DBCredentials {
    DBUrl url();

    DBPwd pwd();

    DBUser user();

    public static class Default implements DBCredentials {
        private final DBUrl url;
        private final DBPwd pwd;
        private final DBUser user;

        public Default(DBUrl url, DBPwd pwd, DBUser user) {
            this.url = url;
            this.pwd = pwd;
            this.user = user;
        }

        @Override
        public DBUrl url() {
            return url;
        }

        @Override
        public DBPwd pwd() {
            return pwd;
        }

        @Override
        public DBUser user() {
            return user;
        }

        @Override public int hashCode() {
            return Objects.hashCode(url, pwd, user);
        }

        @Override public boolean equals(Object obj) {
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            final DBCredentials other = (DBCredentials) obj;

            return Objects.equal(this.url, other.url()) &&
                    Objects.equal(this.pwd, other.pwd()) &&
                    Objects.equal(this.user, other.user());
        }
    }
}
