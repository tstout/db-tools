package db.io.migration;

import java.sql.Connection;

public final class Migrators {
    private Migrators() {
        throw new UnsupportedOperationException();
    }

    public static Migrator liquibaseMigrator() {
        return new Migrator() {

            @Override public void update(Connection dbConn) {
//                try {
//                    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c))
//                    liquibase = new Liquibase(YOUR_CHANGELOG, new FileSystemResourceAccessor(), database);
//                    liquibase.update();
//                } catch (SQLException e) {
//                    throw new DatabaseException(e);
//                } finally {
//                    if (c != null) {
//                        try {
//                            c.rollback();
//                            c.close();
//                        } catch (SQLException e) {
//                            //nothing to do
//                        }
//                    }
//                }
            }
        };
    }

}
