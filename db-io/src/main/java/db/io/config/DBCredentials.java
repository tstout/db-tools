package db.io.config;

import java.util.Objects;

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
            return Objects.hash(url, pwd, user);
        }

        @Override public boolean equals(Object obj) {
            return Objects.equals(this, obj);
        }
    }
}
