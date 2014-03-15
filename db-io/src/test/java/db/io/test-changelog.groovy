package db.io

databaseChangeLog {

//
//  Liquibase column types. Oddly enough, these are not mentioned in the docs, these were obtained from the source.
//
//    "BIGINT"
//    "NUMBER || "NUMERIC"
//    "BLOB"
//    "BOOLEAN"
//    "CHAR"
//    "CLOB"
//    "CURRENCY"
//    "DATE"
//    "DATETIME"
//    "DOUBLE"
//    "FLOAT"
//    "INT"
//    "INTEGER"
//    "LONGBLOB"
//    "LONGVARBINARY"
//    "LONGVARCHAR"
//    "SMALLINT"
//    "TEXT"
//    "TIME"
//    "TIMESTAMP"
//    "TINYINT"
//    "UUID"
//    "VARCHAR"
//    "NVARCHAR"
//

    // Take a look at the sql version of this...I think I prefer it over this. The sql version
    // is much less verbose.
    //
    changeSet(id: 'create-schema', author: 'tstout') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "create schema if not exists db_io"
        }
    }

    changeSet(id: 'create-test-log-table', author: 'tstout') {
        createTable(tableName: 'logs', schemaName: 'db_io', remarks: '') {

            column(name: 'id', type: 'int', autoIncrement: 'true') {
                constraint(nullable: false, primarykey: true)
            }

            column(name: 'when', type: 'datetime')
            column(name: 'level', type: 'varchar(100)')
            column(name: 'msg', type: 'varchar(1024)')
            column(name: 'logger', type: 'varchar(100)')
            column(name: 'thread', type: 'varchar(100)')
        }
    }
}


