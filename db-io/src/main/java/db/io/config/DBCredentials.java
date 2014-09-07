package db.io.config;

import com.google.common.base.Objects;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.*;

public interface DBCredentials {
    DBUrl url();

    DBPwd pwd();

    DBUser user();

    DBName dbName();

    DBDir dbDir();

    DBHost dbHost();

    public static class Builder {
        DBUrl url;
        DBPwd pwd;
        DBUser user;
        DBName dbName;
        DBDir dbDir;
        DBHost dbHost;

        public Builder withDBName(String dbName) {
            this.dbName = new DBName(checkNotNull(dbName));
            return this;
        }

        public Builder withDBName(DBName dbName) {
            this.dbName = checkNotNull(dbName);
            return this;
        }

        public Builder withDBDir(String dbDir) {
            this.dbDir = new DBDir(dbDir);
            return this;
        }

        public Builder withDBDir(DBDir dbDir) {
            this.dbDir = checkNotNull(dbDir);
            return this;
        }

        Builder withUrl(DBUrl url) {
            this.url = checkNotNull(url);
            return this;
        }

        public Builder withPwd(DBPwd pwd) {
            this.pwd = checkNotNull(pwd);
            return this;
        }

        public Builder withDbhost(DBHost dbHost) {
            this.dbHost = checkNotNull(dbHost);
            return this;
        }

        public Builder withUser(DBUser user) {
            this.user = checkNotNull(user);
            return this;
        }

        public DBCredentials build() {
            return new Default(url, pwd, user, dbName, dbDir, dbHost);
        }
    }

    static class Default implements DBCredentials {
        private final DBUrl url;
        private final DBPwd pwd;
        private final DBUser user;
        private final DBName dbName;
        private final DBDir dbDir;
        private final DBHost dbHost;

        Default(DBUrl url, DBPwd pwd, DBUser user, DBName dbName, DBDir dbDir, DBHost dbHost) {
            this.url = checkNotNull(url);
            this.pwd = fromNullable(pwd).or(new DBPwd());
            this.user = fromNullable(user).or(new DBUser());
            this.dbName = fromNullable(dbName).or(new DBName());
            this.dbDir = fromNullable(dbDir).or(new DBDir());
            this.dbHost = fromNullable(dbHost).or(new DBHost());
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

        @Override public DBName dbName() {
            return dbName;
        }

        @Override public DBDir dbDir() {
            return dbDir;
        }

        @Override public DBHost dbHost() {
            return dbHost;
        }

        @Override public int hashCode() {
            return Objects.hashCode(url, pwd, user, dbName);
        }

        @Override public boolean equals(Object obj) {
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            final DBCredentials other = (DBCredentials) obj;

            return Objects.equal(this.url, other.url()) &&
                    Objects.equal(this.pwd, other.pwd()) &&
                    Objects.equal(this.user, other.user()) &&
                    Objects.equal(this.dbName, other.dbName()) &&
                    Objects.equal(this.dbDir, other.dbDir()) &&
                    Objects.equal(this.dbHost, other.dbHost());
        }
    }
}
