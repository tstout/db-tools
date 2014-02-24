//databaseChangeLog {
//
//
//
//    changeSet(id: 'create-schema', author: 'tstout') {
//        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
//            "create schema if not exists db_io"
//        }
//    }
//
//    changeSet(id: 'create-test-table2', author: 'tstout') {
//        createTable(tableName: 'db_io_test', schemaName: 'db_io', remarks: '') {
//            column(name: 'id', type: 'int') {
//                constraint(nullable: false, primarykey: true)
//            }
//            column(name: 'name', type: 'varchar(255)')
//        }
//    }
//
//    changeSet(id: 'create-test-table3', author: 'tstout') {
//        createTable(schemaName: 'db_io', tableName: 'db_io_test3', remarks: '') {
//            column(name: 'id', type: 'int') {
//                constraint(nullable: false, primarykey: true)
//            }
//            column(name: 'name', type: 'varchar(255)')
//        }
//    }
//
//    changeSet(id: 'create-test-table4', author: 'tstout') {
//        createTable(schemaName: 'db_io', tableName: 'db_io_test4', remarks: '') {
//            column(name: 'id', type: 'int') {
//                constraint(nullable: false, primarykey: true)
//            }
//            column(name: 'name', type: 'varchar(255)')
//        }
//    }
//}
//    databaseChangeLog {
//
//
//}
//
